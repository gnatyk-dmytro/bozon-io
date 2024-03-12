package org.bot.settings.email;

import lombok.Data;

import javax.mail.MessagingException;

@Data
public class EmailSender {
    private String smtpHostServer = "";
    private String emailId = "";

    public void SendEmail() {
        try {
            EmailUtil.sendEmail(smtpHostServer, emailId, "recipient@example.com", "Subject", "Body");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
