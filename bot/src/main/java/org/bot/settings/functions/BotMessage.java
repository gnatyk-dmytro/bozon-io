package org.bot.settings.functions;

import org.bot.settings.BotSettings;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotMessage extends BotOptions {

    private static final BotSettings botSettings = new BotSettings();

    public static void sendIntroductionMessage(String chatId) throws TelegramApiException {
        String introMessage = "👋 Hi there! I'm Bozon, your CryptoBot assistant. " +
                "I'm here to help you stay updated with the latest cryptocurrency prices and trends. " +
                "Feel free to ask me for the price of any cryptocurrency, and I'll fetch it for you. " +
                "Let's explore the world of crypto together! 💰📈n\nFor more Information type: /info";
        botSettings.sendMessage(chatId, introMessage);
    }
    public static void sendHelpMessage(String chatId) throws TelegramApiException {
        String helpMessage = """
                ❓ Help:
                1. How to Use the Bot
                2. FAQ
                3. Contact Support
                """;

        botSettings.sendMessage(chatId, helpMessage);
    }

    public static void sendInformation(String chatId) throws TelegramApiException {
        String information = "ℹ️ Information:\nHere you can find the currency: /currency\nSign-in: /sign\nHere you can find help: /Help";
        botSettings.sendMessage(chatId, information);
    }

    public static void sendThank(String chatId) throws TelegramApiException {
        String thankMessage = "\uD83C\uDF89 Thank you for joining us! We hope you will be satisfied with our services and find them valuable. " +
                "If you have any questions or need assistance, feel free to reach out to us anytime. " +
                "We're here to help and support you on your journey. Welcome aboard! \uD83D\uDE80";
        botSettings.sendMessage(chatId, thankMessage);
    }

    public static void exMessage(String chatId) throws TelegramApiException {
        String ex = "Invalid operation ❌ Please try again with a valid operation.";
        botSettings.sendMessage(chatId, ex);
    }

    public static void sendCurrencyInfo(String chatId) throws TelegramApiException{
        String currMessage = "Type the name of crypto-currency:";
        botSettings.sendMessage(chatId, currMessage);
    }

    public static void sendCurrencyNotFound(String chatId) throws TelegramApiException {
        String currencyNotFound = "❌ Sorry, the currency you requested was not found. Please try again.";
        botSettings.sendMessage(chatId, currencyNotFound);
    }

    public static void sendPriceNotFound(String chatId) throws TelegramApiException {
        String priceNotFound = "❌ Sorry, the price for the requested cryptocurrency could not be found. Please try again later.";
        botSettings.sendMessage(chatId, priceNotFound);
    }

    public static void sendGenericErrorMessage(String chatId) throws TelegramApiException {
        String genericErrorMessage = "❌ Oops! Something went wrong. Please try again later.";
        botSettings.sendMessage(chatId, genericErrorMessage);
    }
}
