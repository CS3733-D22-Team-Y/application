package edu.wpi.cs3733.d22.teamY;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

  private MailService() {}

  /**
   * Google will deprecate this by May 30, 2022.
   *
   * @param to The email address to send the email to.
   * @param subject The subject of the email.
   * @param body The body of the email.
   * @return True if the email was sent successfully.
   */
  public static boolean sendMessage(String to, String subject, String body) {

    // Recipient's email ID needs to be mentioned.

    // Sender's email ID needs to be mentioned
    String from = "yowlingyodelingyoshis@gmail.com";

    // Assuming you are sending email from through gmails smtp
    String host = "smtp.gmail.com";

    // Get system properties
    Properties properties = System.getProperties();

    // Setup mail server
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "465");
    properties.put("mail.smtp.ssl.enable", "true");
    properties.put("mail.smtp.auth", "true");

    // Get the Session object.// and pass username and password
    Session session =
        Session.getInstance(
            properties,
            new javax.mail.Authenticator() {

              protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, "ihatemario");
              }
            });

    // Used to debug SMTP issues
    //    session.setDebug(true);

    try {
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      // Set Subject: header field
      message.setSubject(subject);

      // Now set the actual message
      message.setText(body);

      // Send message
      Transport.send(message);
      return true;
    } catch (MessagingException mex) {
      mex.printStackTrace();
    }
    return false;
  }
}
