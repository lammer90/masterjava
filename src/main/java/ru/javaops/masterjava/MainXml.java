package ru.javaops.masterjava;

import com.google.common.io.Resources;
import ru.javaops.masterjava.xml.schema.*;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainXml {

    private static final JaxbParser JAXB_PARSER = new JaxbParser(ObjectFactory.class);

    static {
        JAXB_PARSER.setSchema(Schemas.ofClasspath("payload.xsd"));
    }

    public static void main(String[] args) throws JAXBException, IOException, TransformerException {
        parseHTML();
        //System.out.println(findUsersByProject("topjava", "payload.xml"));
    }

    public static List<User> findUsersByProject(String projectName, String file) throws JAXBException, IOException {
        Payload payload = JAXB_PARSER.unmarshal(
                Resources.getResource(file).openStream());
        Project project = payload.getProjects().getProject().stream()
                .filter(p -> p.getName().equals(projectName))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Wrong argument"));

        return payload.getUsers().getUser().stream()
                .filter(u -> !Collections.disjoint(project.getGroup(), u.getGroupRefs()))
                .sorted(Comparator.comparing(User::getEmail))
                .collect(Collectors.toList());
    }

    public static void parseHTML() throws TransformerException, IOException {
        String outputFileName = "CompanyInfo.html";
        try (InputStream xslInputStream = Resources.getResource("usersHTML.xsl").openStream();
             InputStream xmlInputStream = Resources.getResource("payload.xml").openStream();
             OutputStream htmlFile = new FileOutputStream(outputFileName)) {

            XsltProcessor processor = new XsltProcessor(xslInputStream);
            processor.setParam("project_name", "topjava");
            processor.transformToFile(xmlInputStream, htmlFile);
        }
    }
}
