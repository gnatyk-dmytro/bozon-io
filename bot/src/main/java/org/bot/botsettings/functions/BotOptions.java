package org.bot.botsettings.functions;

import org.bot.HibernateRunner;
import org.bot.data.UserContext;
import org.bot.botsettings.BotSettings;
import org.hibernate.cfg.Configuration;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.Optional;

public class BotOptions extends BotSettings {

    private static final int EMAIL_REQUEST_DELAY = 1500;
    private static boolean emailRequested = false;
    private static final BotSettings botSettings = new BotSettings();

    public static void SignIn(Update update) throws TelegramApiException {
        Configuration configuration = configureHibernate();
        String chatId = getChatId(update);
        Long userId = getUserId(update);
        Optional<String> userName = getUserName(update);

        if (!emailRequested) {
            requestEmail(chatId);
        } else {
            processEmail(update, userId, userName.orElse(""));
        }
    }

    private static Configuration configureHibernate() {
        return new Configuration().configure();
    }

    private static String getChatId(Update update) {
        return update.getMessage().getChatId().toString();
    }

    private static Long getUserId(Update update) {
        return update.getMessage().getFrom().getId();
    }

    private static Optional<String> getUserName(Update update) {
        return Optional.ofNullable(update.getMessage().getFrom().getUserName());
    }

    private static void requestEmail(String chatId) throws TelegramApiException {
        botSettings.sendMessage(chatId, "Please type your email: ");
        try {
            Thread.sleep(EMAIL_REQUEST_DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        emailRequested = true;
    }

    private static void processEmail(Update update,Long userId, String userName) {
        String getText = update.getMessage().getText();
        UserContext userContext = createUserContext(userId, userName, getText);
        HibernateRunner.dbAdd(userContext);
        emailRequested = false;
    }

    private static UserContext createUserContext(Long userId, String userName, String userEmail) {
        UserContext userContext = new UserContext();
        userContext.setUserId(userId);
        userContext.setUserName(userName);
        userContext.setUserEmail(userEmail);
        userContext.setUserNum(0);
        return userContext;
    }
}