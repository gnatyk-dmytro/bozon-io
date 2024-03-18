package org.bot.botsettings.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import java.util.Random;

public abstract class EmailSender {
    private static final String EMAIL = "example@example.com"; // EMAIL --> HERE
    private static final String PASSWORD = "password"; // PASSWORD --> HERE
    public static void emailSend(String userEmail) {
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

        String emailBody = "Please type this code to bot: " + codeGenerator();
        EmailUtil.sendEmail(session, userEmail, "Welcome to bozon:io", emailBody);
    }

    public static boolean checkAuth(int code) {
        return code == codeGenerator();
    }

    private static int codeGenerator() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }
}