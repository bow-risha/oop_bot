package bot;

import domain.Wish;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class WishBot extends TelegramLongPollingBot {

    private String BotName;
    public WishBot(BotCredentials credentials){
        super(credentials.BotKey);
        BotName= credentials.BotName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            SendMessage message = new SendMessage();
            message.setText(update.getMessage().getText());
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