package bot;

import aplication.CommandHandler;
import aplication.CreateWishCommand;
import aplication.GetWishesQuery;
import aplication.QueryHandler;
import domain.UserRepository;
import domain.User;
import domain.Wish;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class WishBot extends TelegramLongPollingBot {
    private UserRepository userRepository;
    private CommandHandler commandHandler;
    public QueryHandler queryHandler;

    private String BotName;
    public WishBot(BotCredentials credentials,
                   QueryHandler queryHandler,
                   CommandHandler commandHandler,
                   UserRepository userRepository){
        super(credentials.BotKey);
        BotName= credentials.BotName;
        this.userRepository=userRepository;
        this.commandHandler=commandHandler;
        this.queryHandler=queryHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        User user = getOrAddUser(update);


        if (update.hasMessage() && update.getMessage().hasText()){
            String text = update.getMessage().getText();
            if (text.startsWith("/add")){
                String wishName = text.substring("/add ".length());
                CreateWishCommand command = new CreateWishCommand(wishName, user);
                commandHandler.handle(command);
                SendMessage(update, "желание добавлено!");
            }

            if (text.startsWith("/get")){
                GetWishesQuery query = new GetWishesQuery(user);
                ArrayList<Wish> result = queryHandler.handle(query);
                for (int i = 0; i < result.size() ; i++){
                    SendMessage(update, i + ")" + result.get(i).Name);
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BotName;
    }


    private void SendMessage(Update update, String msg){
        SendMessage message = new SendMessage();
        message.setText(msg);
        message.setChatId(update.getMessage().getChatId());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    private User getOrAddUser(Update update){
        User user = null;
        if (update.hasMessage()) {
            user = userRepository.GetUser(update.getMessage().getChatId());
            if (user == null) {
                user = new User(update.getMessage().getChatId());
                userRepository.AddUser(user);
            }
        }
        return user;
    }
}