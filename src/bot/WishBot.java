package bot;

import aplication.CommandHandler;
import aplication.CreateWishCommand;
import aplication.GetWishesQuery;
import aplication.QueryHandler;
import aplication.responces.Key;
import aplication.responces.Response;
import domain.UserRepository;
import domain.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class WishBot extends TelegramLongPollingBot {
    private UserRepository userRepository;
    private CommandHandler commandHandler;
    public QueryHandler queryHandler;

    private String BotName;

    public WishBot(BotCredentials credentials,
                   QueryHandler queryHandler,
                   CommandHandler commandHandler,
                   UserRepository userRepository) {
        super(credentials.getBotKey());
        BotName = credentials.getBotName();
        this.userRepository = userRepository;
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        User user = getOrAddUser(update);

        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            if (text.startsWith("/add")) {
                String wishName = text.substring("/add ".length());
                CreateWishCommand command = new CreateWishCommand(wishName, user);
                commandHandler.handle(command);
                SendMessage(update, "желание добавлено!", new ArrayList<>());
            }
            if (text.startsWith("/get")) {
                GetWishesQuery query = new GetWishesQuery(user);
                ArrayList<Response> result = queryHandler.handle(query);
                for (int i = 0; i < result.size(); i++) {
                    SendMessage(update, i + ")" + result.get(i).getText(), result.get(i).getKeys());
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BotName;
    }

    private void SendMessage(Update update, String msg, ArrayList<Key> keys) {
        SendMessage message = new SendMessage();
        message.setText(msg);
        message.setChatId(update.getMessage().getChatId());
        InlineKeyboardMarkup keyboard = getInlineKeyboardMarkup(keys);
        message.setReplyMarkup(keyboard);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private InlineKeyboardMarkup getInlineKeyboardMarkup(ArrayList<Key> keys) {
        InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttonsMarkup = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        for (Key key : keys) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(key.getName());
            button.setCallbackData(key.getLink());
            buttons.add(button);
        }
        buttonsMarkup.add(buttons);
        keyboard.setKeyboard(buttonsMarkup);
        return keyboard;
    }

    private User getOrAddUser(Update update) {
        User user = null;
        if (update.hasMessage()) {
            user = userRepository.getUser(update.getMessage().getChatId());
            if (user == null) {
                user = new User(update.getMessage().getChatId());
                userRepository.addUser(user);
            }
        }
        return user;
    }
}