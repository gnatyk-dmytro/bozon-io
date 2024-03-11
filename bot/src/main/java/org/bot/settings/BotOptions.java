package org.bot.settings;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;

import jakarta.mail.internet.MimeMultipart;
import lombok.Getter;
import org.bot.HibernateRunner;
import org.bot.data.UserContext;

import org.hibernate.cfg.Configuration;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.Properties;

abstract class BotOptions extends BotSettings {

    @Getter
    private String userEmail;

    @Getter
    private String userPassword;

    public void getUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void getUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
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

            hibernateRunner.dbAdd(userContext);
            emailRequested = false;
        }
    }

    private void sendEmail(String userEmail) throws MessagingException {
        Properties prop = new Properties();
        setProperty(prop);

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userEmail, userPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(userEmail));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(userEmail));
        message.setSubject("Mail Subject");

        String msg = "This is my first email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    private void setProperty(Properties prop) {
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.mailtrap.io");
        prop.put("mail.smtp.port", "25");
        prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
    }
}