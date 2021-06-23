package de.hhn.se.labswp.wstgsh.webapi.models.email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailSender {

  @Override
  public void sendMailViaGmail(String recipient, String content) {
    System.out.println("Prepare Message to send");
    Properties properties = new Properties();

    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", "smtp.gmail.com");
    properties.put("mail.smtp.port", "587");

    String accountEmail = "schleswigholsteinapp@gmail.com";
    String password = "einfach2#";

    Session session = Session.getInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(accountEmail, password);
      }
    });


    Message message = prepareMessage(session, accountEmail, recipient, content);

    try {
      Transport.send(message);
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    System.out.println("Message send");
  }

  @Override
  public Message prepareMessage(Session session, String accountEmail, String recipient,
                                String content) {
    try {
      Multipart multipart = new MimeMultipart("alternative");

      MimeBodyPart htmlPart = new MimeBodyPart();
      htmlPart.setContent(content, "text/html; charset=utf-8");

      Message message = new MimeMessage(session);
      message.setSubject("SH-Travel Email Confirmation");

      multipart.addBodyPart(htmlPart);
      message.setContent(multipart);

      message.setFrom(new InternetAddress(accountEmail));
      message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
      message.setContent(multipart);
      message.saveChanges();

      return message;
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
