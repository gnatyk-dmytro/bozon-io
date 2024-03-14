package org.bot.settings;

import lombok.SneakyThrows;
import org.bot.settings.functions.BotButtons;
import org.bot.settings.functions.BotMessage;
import org.bot.settings.functions.BotOptions;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotSettings extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() { return "BOT_NAME"; } // BOT NAME --> Here
    @Override
    public String getBotToken() { return "BOT_TOKEN"; } // BOT TOKEN --> Here

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();

            logMessage(update);
            switch (messageText) {
                case "/start" -> {
                    BotMessage.sendIntroductionMessage(chatId);
                    BotButtons.sendButtons(chatId);
                }
                case "Sign-in" -> {
                    BotOptions.SignIn(update);
                    BotMessage.sendThank(chatId);
                }
                case "Help" -> BotMessage.sendHelpMessage(chatId);
                case "Settings" -> BotMessage.sendSettingsMessage(chatId);
                default -> BotMessage.exMessage(chatId);
            }
        }
    }

    public void sendMessage(String chatId, String message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        sendMessage.setText(message);
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
