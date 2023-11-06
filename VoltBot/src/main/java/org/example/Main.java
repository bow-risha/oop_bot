package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BotCredentials credentials = objectMapper
                    .readValue(getResourceFile("settings.json"), BotCredentials.class);
        try {
            TelegramBotsApi api =new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(new EchoBot(credentials));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static File getResourceFile(final String fileName)
    {
        URL url = Main.class
                .getClassLoader()
                .getResource(fileName);

        if(url == null) {
            throw new IllegalArgumentException(fileName + " is not found 1");
        }

        File file = new File(url.getFile());

        return file;
    }
}

