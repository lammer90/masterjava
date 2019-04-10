package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableSet;
import com.sun.xml.ws.developer.JAXWSProperties;
import ru.javaops.web.WebStateException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.soap.MTOMFeature;
import javax.xml.ws.soap.SOAPBinding;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;

public class MailServiceClient {

    public static void main(String[] args) throws MalformedURLException, WebStateException {
        Service service = Service.create(
                new URL("http://localhost:8080/mail/mailService?wsdl"),
                new QName("http://mail.javaops.ru/", "MailServiceImplService"));

        MTOMFeature feature = new MTOMFeature();

        MailService mailService = service.getPort(MailService.class, feature);

        Map<String, Object> ctxt=((BindingProvider)mailService).getRequestContext();
        ctxt.put(JAXWSProperties.HTTP_CLIENT_STREAMING_CHUNK_SIZE, 8192);

        /*BindingProvider bp = (BindingProvider) mailService;
        SOAPBinding binding = (SOAPBinding) bp.getBinding();
        binding.setMTOMEnabled(true);
*/
        DataHandler dh = new DataHandler(new
                FileDataSource(Paths.get("C:/1/payload.xml").toFile()));

        String state = mailService.sendWithAtachment(ImmutableSet.of(new Addressee("lammer.90@yandex.ru", null)), null,
                "Group mail subject", "Group mail body", null);
        System.out.println("Group mail state: " + state);

        /*GroupResult groupResult = mailService.sendBulk(ImmutableSet.of(
                new Addressee("Мастер Java <masterjava@javaops.ru>"),
                new Addressee("Bad Email <bad_email.ru>")), "Bulk mail subject", "Bulk mail body");
        System.out.println("\nBulk mail groupResult:\n" + groupResult);*/
    }
}
