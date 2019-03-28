package ru.javaops.masterjava.service.mail;

import com.sun.mail.smtp.SMTPMessage;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;

import javax.mail.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PostConstruction {

    private final static Properties prop = new Properties();
    private final static Properties props = new Properties();
    private static String NAME;
    private static String HOST;
    private static Integer PORT;
    private static String EMAIL;
    private static String PASSWORD;

    private static String SSL;
    private static boolean TLS;
    private static boolean DEBUG;

    static {
        try {
            InputStream in = new FileInputStream("C:/masterjava/services/mail-service/src/main/resources/mail.conf");
            prop.load(in);
            HOST = prop.getProperty("mail.host");
            PORT = Integer.parseInt(prop.getProperty("mail.port"));
            EMAIL = prop.getProperty("mail.username");
            PASSWORD = prop.getProperty("mail.password");

            SSL = prop.getProperty("mail.useSSL");
            TLS = Boolean.getBoolean(prop.getProperty("mail.useTLS"));
            DEBUG = Boolean.getBoolean(prop.getProperty("mail.debug"));
            NAME = prop.getProperty("mail.name");

            props.put("mail.smtp.host", HOST);
            props.put("mail.smtp.port", PORT);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.socketFactory.port", PORT);
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.ssl", SSL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TransportSender getNewEmail() throws MessagingException {
        Authenticator authenticator = new DefaultAuthenticator(EMAIL, PASSWORD);
        Session session = Session.getDefaultInstance(props, authenticator);
        session.setDebug(true);

        SMTPMessage message = new SMTPMessage(session);
        message.setEnvelopeFrom(EMAIL);

        Transport transport = session.getTransport("smtp");
        transport.connect(HOST, PORT, EMAIL, PASSWORD);

        return (TransportSender) (text, subject, adr, cc) -> {
            message.setSubject(subject);
            message.setText(text);
            message.setRecipients(Message.RecipientType.TO, adr);
            message.setRecipients(Message.RecipientType.CC, cc);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        };
    }

    public interface TransportSender {
        void send(String text, String subject, String adr, String cc) throws MessagingException;
    }
}
