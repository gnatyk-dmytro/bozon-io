package org.bot;

import org.bot.settings.BotSettings;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            registerTelegramBot();

            System.out.println("**Bot Start Work:");
        } catch (Exception e) {
            System.err.println("Error occurred while registering Telegram bot:");
            e.printStackTrace();
        }
    }

    private static void registerTelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        BotSettings botSettings = new BotSettings();
        botsApi.registerBot(botSettings);
    }
}
