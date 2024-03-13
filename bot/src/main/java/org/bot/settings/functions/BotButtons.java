package org.bot.settings.functions;

import org.bot.settings.BotSettings;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

public class BotButtons extends BotSettings {

    private static final BotSettings botSettings = new BotSettings();

    public static void sendButtons(String chatId) throws TelegramApiException {
        if (chatId == null || chatId.isEmpty()) {
            System.out.println("Invalid chatId");
            return;
        }

        SendMessage send = new SendMessage();
        send.setChatId(chatId);
        send.setText("Choose an option");

        setCustomButtons(send);
        botSettings.execute(send);
    }

    public static void setCustomButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow firstKeyboardRow = new KeyboardRow();
        firstKeyboardRow.add("Sign-in");
        firstKeyboardRow.add("Help");

        KeyboardRow secondKeyboardRow = new KeyboardRow();
        secondKeyboardRow.add("Info");
        secondKeyboardRow.add("Settings");

        keyboard.add(firstKeyboardRow);
        keyboard.add(secondKeyboardRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }
}
