package org.bot.settings.functions;

import org.bot.HibernateRunner;
import org.bot.data.UserContext;
import org.bot.settings.BotSettings;

import org.hibernate.cfg.Configuration;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotOptions extends BotSettings {

    private static boolean emailRequested = false;
    private static final BotSettings botSettings = new BotSettings();

    public static void SignIn(Update update) throws TelegramApiException {
        Configuration configuration = new Configuration().configure();
        String chatId = update.getMessage().getChatId().toString();

        Long userId = update.getMessage().getFrom().getId();
        String userName = update.getMessage().getFrom().getUserName();

        if (!emailRequested) {
            botSettings.sendMessage(chatId, "Please type your email: ");
            emailRequested = true;
        } else {
            String getText = update.getMessage().getText();

            UserContext userContext = new UserContext();
            HibernateRunner.setConfiguration(configuration);

            userContext.setUserName(userName);
            userContext.setUserEmail(getText);
            userContext.setUserId(userId);
            userContext.setUserNum(1);

            HibernateRunner.dbAdd(userContext);
            emailRequested = false;
        }
    }
}