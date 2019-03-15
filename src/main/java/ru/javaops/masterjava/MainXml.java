package ru.javaops.masterjava;

import com.google.common.io.Resources;
import ru.javaops.masterjava.xml.schema.Group;
import ru.javaops.masterjava.xml.schema.ObjectFactory;
import ru.javaops.masterjava.xml.schema.Payload;
import ru.javaops.masterjava.xml.schema.User;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MainXml {

    private static final JaxbParser JAXB_PARSER = new JaxbParser(ObjectFactory.class);

    static {
        JAXB_PARSER.setSchema(Schemas.ofClasspath("payload.xsd"));
    }

    public static void main(String[] args) throws JAXBException, IOException{
        System.out.println(findUsersByProject("MasterJava", "payload.xml"));
    }

    public static List<User> findUsersByProject(String project, String file) throws JAXBException, IOException{
        Payload payload = JAXB_PARSER.unmarshal(
                Resources.getResource(file).openStream());
        return payload.getProjects().getProject().stream()
                .filter(p -> p.getName().equals(project))
                .flatMap(p -> p.getGroups().getGroup().stream())
                .flatMap(g -> g.getUsers().getUser().stream())
                .map(u -> (User)u.getValue())
                .distinct()
                .collect(Collectors.toList());
    }
}
