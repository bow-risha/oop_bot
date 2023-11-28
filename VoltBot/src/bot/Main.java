package bot;

import aplication.CommandHandler;
import aplication.QueryHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.UserRepository;
import domain.Wish;
import domain.WishRepository;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {
        UserRepository userRepository= new UserRepository();
        WishRepository wishRepository = new WishRepository();
        CommandHandler commandHandler= new CommandHandler(wishRepository);
        QueryHandler queryHandler = new QueryHandler(wishRepository);

        ObjectMapper objectMapper = new ObjectMapper();
        BotCredentials credentials = objectMapper.readValue(getResourceFile("settings.json"), BotCredentials.class);
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);

        WishBot wishBot=new WishBot(credentials, queryHandler, commandHandler,userRepository);
        api.registerBot(wishBot);
    }

    private static File getResourceFile(final String fileName) {
        URL url = Main.class
                .getClassLoader()
                .getResource(fileName);

        if (url == null) {
            throw new IllegalArgumentException(fileName + " is not found 1");
        }
        File file = new File(url.getFile());
        return file;
    }
}

