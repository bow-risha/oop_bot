package bot;

import aplication.*;
import aplication.responces.Response;
import aplication.utils.Actions;
import aplication.utils.States;
import domain.UserRepository;
import domain.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.UUID;

import static java.lang.Math.toIntExact;

public class WishBot extends TelegramLongPollingBot {
    private final UserRepository userRepository;
    private final CommandHandler commandHandler;
    private final KeyboardMapper keyboardMapper;
    private final QueryHandler queryHandler;

    private final String BotName;

    public WishBot(BotCredentials credentials,
                   QueryHandler queryHandler,
                   CommandHandler commandHandler,
                   UserRepository userRepository, KeyboardMapper keyboardMapper) {
        super(credentials.getBotKey());
        BotName = credentials.getBotName();
        this.userRepository = userRepository;
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
        this.keyboardMapper = keyboardMapper;
    }

    @Override
    public void onUpdateReceived(Update update) {


        if (update.hasCallbackQuery()) {
            User user = getOrAddUser(update.getCallbackQuery().getMessage().getChatId());
            onCallbackReceived(update,user);
            return;
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            User user = getOrAddUser(update.getMessage().getChatId());
            String text = update.getMessage().getText();
            if (user.hasState()) {
                onStatefulUpdateReceived(update,user);
                return;
            }
            if (text.startsWith(Actions.AddWIshText)) {
                SetStateCommand command = new SetStateCommand(user.getChatID(), States.AddWish);
                commandHandler.handle(command);
                SendMessage(update, user, new Response("Введите ваше желание"));
            }
            if (text.startsWith(Actions.GetWIshText)) {
                GetWishesQuery query = new GetWishesQuery(user);
                ArrayList<Response> result = queryHandler.handle(query);
                for (int i = 0; i < result.size(); i++) {
                    SendMessage(update, user, result.get(i));
                }
            }
        }
    }

    public void onStatefulUpdateReceived(Update update, User user) {
        if (user.getUserState().startsWith(States.AddWish)) {
            String wishName = update.getMessage().getText();
            CreateWishCommand command = new CreateWishCommand(wishName, user);
            commandHandler.handle(command);
            SetStateCommand cleanState = new SetStateCommand(user.getChatID(), States.Empty);
            commandHandler.handle(cleanState);
            SendMessage(update, user, new Response("желание добавлено!"));
        }
        if (user.getUserState().startsWith(States.AddWishDescription)) {
            String wishDescription = update.getMessage().getText();
            String wishId=user.getUserState().substring(States.AddWishDescription.length());
            AddDescriptionCommand command = new AddDescriptionCommand(wishDescription, UUID.fromString(wishId));
            commandHandler.handle(command);
            SetStateCommand cleanState = new SetStateCommand(user.getChatID(), States.Empty);
            commandHandler.handle(cleanState);
            SendMessage(update, user, new Response("Описание добавлено!"));
        }
    }

    public void onCallbackReceived(Update update, User user) {
        String callbackData=update.getCallbackQuery().getData();
        if (callbackData.startsWith(States.Setstate)) {
            SetStateCommand setStateCommand = new SetStateCommand(user.getChatID(), callbackData.substring(States.Setstate.length()));
            commandHandler.handle(setStateCommand);
            EditMessageText editMessage = new EditMessageText();
            editMessage.setChatId(user.getChatID());
            editMessage.setMessageId(toIntExact(update.getCallbackQuery().getMessage().getMessageId()));
            editMessage.setText("Добавьте описание");
            try {
                execute(editMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BotName;
    }

    private void SendMessage(Update update, User user, Response response) {
        SendMessage message = new SendMessage();
        message.setText(response.getText());
        message.setChatId(user.getChatID());
        if (response.getKeys().size() > 0) {
            InlineKeyboardMarkup keyboard = keyboardMapper.getInlineKeyboardMarkup(response.getKeys());
            message.setReplyMarkup(keyboard);
        } else {
            ReplyKeyboardMarkup downKeyboard = keyboardMapper.getKeyboardByState(user.getUserState());
            message.setReplyMarkup(downKeyboard);
        }

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    private User getOrAddUser(Long chatId) {
        User user = null;
            user = userRepository.getUser(chatId);
            if (user == null) {
                user = new User(chatId);
                userRepository.addUser(user);
            }
        return user;
    }
}