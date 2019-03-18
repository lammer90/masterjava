package ru.javaops.masterjava;

import com.google.common.io.Resources;
import ru.javaops.masterjava.xml.schema.Group;
import ru.javaops.masterjava.xml.schema.ObjectFactory;
import ru.javaops.masterjava.xml.schema.Payload;
import ru.javaops.masterjava.xml.schema.User;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;
import ru.javaops.masterjava.xml.util.XsltProcessor;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

public class MainXml {

    private static final JaxbParser JAXB_PARSER = new JaxbParser(ObjectFactory.class);

    static {
        JAXB_PARSER.setSchema(Schemas.ofClasspath("payload.xsd"));
    }

    public static void main(String[] args) throws JAXBException, IOException, TransformerException{
        parseHTML();
        /*System.out.println(findUsersByProject("MasterJava", "payload.xml"));*/
    }

    public static List<User> findUsersByProject(String project, String file) throws JAXBException, IOException{
        Payload payload = JAXB_PARSER.unmarshal(
                Resources.getResource(file).openStream());
        /*return payload.getUsers().getUser().stream()
                .*/
        return null;
    }

    public static void parseHTML() throws TransformerException, IOException{
        /*try (InputStream xslInputStream = Resources.getResource("usersHTML.xsl").openStream();
             InputStream xmlInputStream = Resources.getResource("payload.xml").openStream()) {*/

            TransformerFactory tFactory=TransformerFactory.newInstance();

            Source xslDoc=new StreamSource("C:/masterjava/src/main/resources/usersHTML.xsl");
            Source xmlDoc=new StreamSource("C:/masterjava/src/main/resources/payload.xml");

            String outputFileName="CompanyInfo.html";

            OutputStream htmlFile=new FileOutputStream(outputFileName);
            Transformer trasform=tFactory.newTransformer(xslDoc);
            trasform.setParameter("project_name", "MasterJava");
            trasform.transform(xmlDoc, new StreamResult(htmlFile));
    }
}
