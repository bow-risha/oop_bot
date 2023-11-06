package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class ChallengeBot extends TelegramLongPollingBot {

    private final String BotName;

    private final DailyChallengeProvider Challenges;

    public ChallengeBot(BotCredentials credentials, DailyChallengeProvider challenges) {
        super(credentials.BotKey);
        BotName = credentials.BotName;
        Challenges = challenges;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            SendMessage message = new SendMessage();
            message.setText(Challenges.getChallenge());
            message.setChatId(update.getMessage().getChatId());

            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BotName;
    }

}
