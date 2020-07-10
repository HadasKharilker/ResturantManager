package model;

import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    //private static String to = "hadas.khri@gmail.com";
    private static String from = "resturantmanagerhit@gmail.com";
    private static String host = "smtp.gmail.com";

    /*
    public static void sendMail(String messageToSend, Set<String> mailsTO) throws Exception {
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("resturantmanagerhit@gmail.com", "HIT123456");
            }

        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            for (String mailTo : mailsTO) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            }

            message.setSubject("resturant hit!");

            message.setText(messageToSend);

            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            throw new Exception("fail to send mails");
        }
    }
*/
    public static void sendMail(String messageToSend, String mailTo)throws Exception {
        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("resturantmanagerhit@gmail.com", "HIT123456");
            }

        });

        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));


            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));


            message.setSubject("resturant hit!");

            message.setText(messageToSend);

            System.out.println("sending...");

            Transport.send(message);
            System.out.println("Sent message successfully....");



        } catch (MessagingException mex) {
            throw new Exception("fail to send mails");
        }
    }

}