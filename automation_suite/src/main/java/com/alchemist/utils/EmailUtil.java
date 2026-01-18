package com.alchemist.utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(
            String subject,
            String htmlBody,
            File[] attachments) {

        final String from = ConfigReader.get("EMAIL_FROM");
        final String password = ConfigReader.get("EMAIL_PASSWORD");
        String to = ConfigReader.get("EMAIL_TO");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", ConfigReader.get("SMTP_HOST"));
        props.put("mail.smtp.port", ConfigReader.get("SMTP_PORT"));

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);

            Multipart multipart = new MimeMultipart();

            // HTML Body
            MimeBodyPart bodyPart = new MimeBodyPart();
            bodyPart.setContent(htmlBody, "text/html");
            multipart.addBodyPart(bodyPart);

            // Attachments
            for (File file : attachments) {
                if (file != null && file.exists()) {
                    MimeBodyPart attachPart = new MimeBodyPart();
                    attachPart.attachFile(file);
                    multipart.addBodyPart(attachPart);
                }
            }

            message.setContent(multipart);
            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
