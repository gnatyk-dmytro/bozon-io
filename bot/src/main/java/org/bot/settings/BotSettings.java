package org.bot.settings;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.List;

public class BotSettings extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() { return "BOT_NAME"; } // BOT NAME --> Here
    @Override
    public String getBotToken() { return "BOT_TOKEN"; } // BOT TOKEN --> Here

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            BotOptions botOptions = new BotOptions();

            logMessage(update);
            if (messageText.equals("/start")) {
                sendIntroductionMessage(chatId);
                sendButtons(chatId);
            } else if (messageText.equals("Sign-in")) {
                botOptions.SignIn(update);
            } else {
                sendMessage(chatId, "Invalid operation");
            }
        }
    }

    private void sendButtons(String chatId) throws TelegramApiException {
        if (chatId == null || chatId.isEmpty()) {
            System.out.println("Invalid chatId");
            return;
        }

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Please select an option:");

        setCustomButtons(sendMessage);
        executeSafe(sendMessage);
    }

    private void sendIntroductionMessage(String chatId) throws TelegramApiException {
        String introMessage = "\uD83E\uDD16 Hi there! I'm Bozon, your CryptoBot assistant. " +
                "I'm here to help you stay updated with the latest cryptocurrency prices and trends. " +
                "Feel free to ask me for the price of any cryptocurrency, and I'll fetch it for you. " +
                "Let's explore the world of crypto together! \uD83D\uDCB0\uD83D\uDCC8";

        sendMessage(chatId, introMessage);
    }

    private void setCustomButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Crypto"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardSecondRow.add(new KeyboardButton("Sign-in"));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    protected void sendMessage(String chatId, String message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        sendMessage.setText(message);
        executeSafe(sendMessage);
    }

    private void executeSafe(SendMessage sendMessage) throws TelegramApiException{
        execute(sendMessage);
    }

    private void logMessage(Update update) {
        if (update.hasMessage()) {
            String from = update.getMessage().getFrom().getFirstName();
            String messageText = update.getMessage().getText();
            System.out.printf("\t#%s - send text message: %s\n", from, messageText);
        }
    }
}
