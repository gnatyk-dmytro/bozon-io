package org.bot;

import org.bot.botsettings.BotSettings;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

    /* @author Dmytro Gnatyk */
    /* @version 1.0 */

public class Main {
    public static void main(String[] args) throws Exception{
        registerTelegramBot();
        System.out.println("**Bot start Work:");
    }

    private static void registerTelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        BotSettings botSettings = new BotSettings();
        botsApi.registerBot(botSettings);
    }
}
