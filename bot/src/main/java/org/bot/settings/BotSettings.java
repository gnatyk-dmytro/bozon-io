package org.bot.settings;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotSettings extends TelegramLongPollingBot {
    private final String botToken = "BOT_TOKEN"; // Bot Token --> Here
    private final String botName = "BOT_NAME"; // Bot Name --> Here

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();

            String from = update.getMessage().getFrom().getFirstName();
            System.out.printf("\t#%s - send text message: %s\n", from, messageText);

            if (messageText.equals("/start")) {
                String introMessage = "\uD83E\uDD16 Hi there! I'm Bozon, your CryptoBot assistant. " +
                        "I'm here to help you stay updated with the latest cryptocurrency prices and trends. " +
                        "Feel free to ask me for the price of any cryptocurrency, and I'll fetch it for you. " +
                        "Let's explore the world of crypto together! \uD83D\uDCB0\uD83D\uDCC8";

                Message(chatId, introMessage);
            }
        }
    }

    private void Message(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(message);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
