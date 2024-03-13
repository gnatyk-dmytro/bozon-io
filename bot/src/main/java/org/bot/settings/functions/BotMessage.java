package org.bot.settings.functions;

import org.bot.settings.BotSettings;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotMessage extends BotOptions {

    private static final BotSettings botSettings = new BotSettings();

    public BotMessage() {}
    public static void sendIntroductionMessage(String chatId) throws TelegramApiException {
        String introMessage = "\uD83E\uDD16 Hi there! I'm Bozon, your CryptoBot assistant. " +
                "I'm here to help you stay updated with the latest cryptocurrency prices and trends. " +
                "Feel free to ask me for the price of any cryptocurrency, and I'll fetch it for you. " +
                "Let's explore the world of crypto together! \uD83D\uDCB0\uD83D\uDCC8";

        botSettings.sendMessage(chatId, introMessage);
    }
    public static void sendInformation(String chatId) throws TelegramApiException {
        String information = "";

        botSettings.sendMessage(chatId, information);
    }

    public static void sendInfo(String chatId) throws TelegramApiException {
        String infoMessage = "";

        botSettings.sendMessage(chatId, infoMessage);
    }

    public static void cryptoInfo(String chatId) throws TelegramApiException {
        String cryptoInf = "";

        botSettings.sendMessage(chatId, cryptoInf);
    }
}
