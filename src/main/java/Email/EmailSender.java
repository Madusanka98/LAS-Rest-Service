package resources.Email;
import jakarta.mail.Authenticator;
import java.util.Properties;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
/**
 *
 * @author Madusanka(MadusankaB
 */
public class EmailSender {
    
    
    public static void sendEmail(String emailAddressTo, String msgSubject, String msgText) {
      //provide recipient's email ID
//      String to = emailAddressTo;
//      //provide sender's email ID
//      String from = "donotreply525@gmail.com";
//      //provide Mailtrap's username
//      final String username = "Do-not-reply";
//      //provide Mailtrap's password
//      final String password = "ncjp kemu tlwq ugpy";
//      //final String password = "efgi pwxn qdct lbvl";
//      //provide Mailtrap's host address
//      String host = "smtp.gmail.com";
//      //configure Mailtrap's SMTP server details
//      Properties props = new Properties();
////      props.put("mail.smtp.auth", "true");
////      props.put("mail.smtp.starttls.enable", "true");
////      props.put("mail.smtp.host", host);
////      props.put("mail.smtp.port", "587");
////      props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//
//        props.put("mail.smtp.auth", true);
//        props.put("mail.smtp.starttls.enable", true);
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//      //create the Session object
//      Session session = Session.getInstance(props,
//         new jakarta.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//               return new PasswordAuthentication(username, password);
//    }
//         });
//      try {
//    //create a MimeMessage object
//    Message message = new MimeMessage(session);
//    //set From email field
//    message.setFrom(new InternetAddress(from));
//    //set To email field
//    message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
//    //set email subject field
//    message.setSubject(msgSubject);
//    //set the content of the email message
//    message.setText(msgText);
//    //send the email message
//    Transport.send(message);
//    System.out.println("Email Message Sent Successfully");
//      } catch (MessagingException e) {
//         throw new RuntimeException(e);
//      }
      
      
      
      
      Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable TLS encryption
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        // Set your email credentials
        String username = "donotreply525@gmail.com";
        String password = "ncjp kemu tlwq ugpy";

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set From: header field
            message.setFrom(new InternetAddress("donotreply525@gmail.com"));

            // Set To: header field
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddressTo));

            // Set Subject: header field
            message.setSubject(msgSubject);

            // Set the actual message
            message.setText(msgText);

            // Send the message
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
   }

}
