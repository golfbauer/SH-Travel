package de.hhn.se.labswp.wstgsh.security.email;

import javax.mail.Message;
import javax.mail.Session;

public interface EmailSender {

  void sendMailViaGmail(String recipient, String content);

  Message prepareMessage(Session session, String accountEmail, String recipient,
                             String content);
}
