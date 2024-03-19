package org.bot.botsettings.functions;

import org.bot.botsettings.BotSettings;

public class BotMessage extends BotOptions {

    private static final BotSettings botSettings = new BotSettings();

    public static void sendIntroductionMessage(String chatId) {
        String introMessage = "👋 Hi there! I'm Bozon, your CryptoBot assistant. " +
                "I'm here to help you stay updated with the latest cryptocurrency prices and trends. " +
                "Feel free to ask me for the price of any cryptocurrency, and I'll fetch it for you. " +
                "Let's explore the world of crypto together! 💰📈n\nFor more Information type: /info";
        botSettings.sendMessage(chatId, introMessage);
    }
    public static void sendHelpMessage(String chatId) {
        String helpMessage = """
                ❓ Help:
                1. FAQ - /faq
                2. Contact Support - /contact
                """;

        botSettings.sendMessage(chatId, helpMessage);
    }

    public static void sendFaq(String chatId)  {
        String faqMessage = "\uD83D\uDC4B Hi there! I'm Bozon, your CryptoBot assistant. I'm here to help you stay updated with the latest cryptocurrency prices and trends. Feel free to ask me for the price of any cryptocurrency, and I'll fetch it for you. Let's explore the world of crypto together! \uD83D\uDCB0\uD83D\uDCC8\n" +
                "\n" +
                "For more information, type: /info\n";
        botSettings.sendMessage(chatId, faqMessage);
    }

    public static void sendContact(String chatId) {
        String contactInformation = "For contact please use this email-address: gnatykwork@gmail.com";
        botSettings.sendMessage(chatId, contactInformation);
    }

    public static void sendInformation(String chatId)  {
        String information = "ℹ️ Information:\nHere you can find the currency: /currency\nSign-in: /sign\nHere you can find help: /Help";
        botSettings.sendMessage(chatId, information);
    }

    public static void sendThank(String chatId)  {
        String thankMessage = "\uD83C\uDF89 Thank you for joining us! We hope you will be satisfied with our services and find them valuable. " +
                "If you have any questions or need assistance, feel free to reach out to us anytime. " +
                "We're here to help and support you on your journey. Welcome aboard! \uD83D\uDE80";
        botSettings.sendMessage(chatId, thankMessage);
    }

    public static void exMessage(String chatId) {
        String ex = "Invalid operation ❌ Please try again with a valid operation.";
        botSettings.sendMessage(chatId, ex);
    }

    public static void sendCurrencyInfo(String chatId) {
        String currInf = "Currency information:";
        String currMessage = "Type the name of currency: \nExample: bitcoin, ethereum, etc.";
        botSettings.sendMessage(chatId, currInf);
        botSettings.sendMessage(chatId, currMessage);
    }

    public static void sendCurrencyNotFound(String chatId) {
        String currencyNotFound = "❌ Sorry, the currency you requested was not found. Please try again.";
        botSettings.sendMessage(chatId, currencyNotFound);
    }

    public static void sendGenericErrorMessage(String chatId) {
        String genericErrorMessage = "❌ Oops! Something went wrong. Please try again later.";
        botSettings.sendMessage(chatId, genericErrorMessage);
    }
}
