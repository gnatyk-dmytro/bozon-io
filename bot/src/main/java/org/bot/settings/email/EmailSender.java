package org.bot.settings.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class EmailSender {

    private static final String EMAIL = "example@example.com"; // EMAIL --> HERE
    private static final String PASSWORD = "password"; // PASSWORD --> HERE
    public static void emailSend() {
        Properties smtpProperties = new Properties();
        smtpProperties.put("mail.smtp.auth", "true");
        smtpProperties.put("mail.smtp.starttls.enable", "true");
        smtpProperties.put("mail.smtp.host", "smtp-mail.outlook.com");
        smtpProperties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(smtpProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        EmailUtil.sendEmail(session, EMAIL, "SimpleEmail Testing Subject", "SimpleEmail Testing Body");
    }
}