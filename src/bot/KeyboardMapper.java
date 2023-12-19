package bot;

import aplication.responces.Key;
import aplication.utils.Actions;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardMapper {
    public InlineKeyboardMarkup getInlineKeyboardMarkup(List<Key> keys) {
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

    public ReplyKeyboardMarkup getKeyboardByState(String state){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton getWIshButton=new KeyboardButton(Actions.GetWIshText);
        KeyboardButton addWIshButton=new KeyboardButton(Actions.AddWIshText);
        row.add(getWIshButton);
        row.add(addWIshButton);
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}
