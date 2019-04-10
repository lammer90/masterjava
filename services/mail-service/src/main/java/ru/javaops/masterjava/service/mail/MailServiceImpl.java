package ru.javaops.masterjava.service.mail;

import com.sun.xml.ws.developer.StreamingDataHandler;
import ru.javaops.masterjava.ExceptionType;
import ru.javaops.web.WebStateException;
import sun.misc.IOUtils;

import javax.activation.DataHandler;
import javax.jws.WebService;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

@WebService(endpointInterface = "ru.javaops.masterjava.service.mail.MailService", targetNamespace = "http://mail.javaops.ru/"
//          , wsdlLocation = "WEB-INF/wsdl/mailService.wsdl"
)
public class MailServiceImpl implements MailService {
    public String sendToGroup(Set<Addressee> to, Set<Addressee> cc, String subject, String body) throws WebStateException {
        return MailSender.sendToGroup(to, cc, subject, body, null);
    }

    @Override
    public GroupResult sendBulk(Set<Addressee> to, String subject, String body) throws WebStateException {
        return MailServiceExecutor.sendBulk(to, subject, body);
    }

    @Override
    public String sendWithAtachment(Set<Addressee> to, Set<Addressee> cc, String subject, String body, DataHandler data) throws WebStateException {
        try {
            StreamingDataHandler dh = (StreamingDataHandler) data;
            InputStream inputStream = new BufferedInputStream(dh.getInputStream());

            Path tempFile = Files.createTempFile("stream2file", ".tmp");
            Files.copy(inputStream, tempFile);
            return MailSender.sendToGroup(to, cc, subject, body, tempFile);
        } catch (Exception e) {
            throw new WebStateException(e, ExceptionType.ATTACH);
        }
    }
}