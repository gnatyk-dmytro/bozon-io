package org.bot.settings;


import org.bot.HibernateRunner;
import org.bot.data.UserContext;

import org.hibernate.cfg.Configuration;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

class BotOptions extends BotSettings {

    private boolean emailRequested = false;

    public void SignIn(Update update) throws TelegramApiException {
        Configuration configuration = new Configuration().configure();
        String chatId = update.getMessage().getChatId().toString();

        Long userId = update.getMessage().getFrom().getId();
        String userName = update.getMessage().getFrom().getUserName();

        if (!emailRequested) {
            sendMessage(chatId, "Please type your email: ");
            emailRequested = true;
        } else {
            String getText = update.getMessage().getText();

            HibernateRunner hibernateRunner = new HibernateRunner(configuration);
            UserContext userContext = new UserContext();

            userContext.setUserId(userId);
            userContext.setUserName(userName);
            userContext.setUserEmail(getText);
            userContext.setUserNum(1);

            hibernateRunner.dbAdd(userContext);
            emailRequested = false;
        }
    }
}