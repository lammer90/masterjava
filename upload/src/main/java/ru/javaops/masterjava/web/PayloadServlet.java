package ru.javaops.masterjava.web;

import ru.javaops.masterjava.xml.schema.User;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@MultipartConfig
public class PayloadServlet extends HttpServlet {

    private static final Comparator<User> USER_COMPARATOR = Comparator.comparing(User::getValue).thenComparing(User::getEmail);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Part filePart = req.getPart("upfile");
        Set<User> users = new TreeSet<>(USER_COMPARATOR);

        try (BufferedInputStream is = new BufferedInputStream(filePart.getInputStream())) {
            StaxStreamProcessor processor = new StaxStreamProcessor(is);

            JaxbParser parser = new JaxbParser(User.class);
            while (processor.doUntil(XMLEvent.START_ELEMENT, "User")) {
                User user = parser.unmarshal(processor.getReader(), User.class);
                users.add(user);
            }
        } catch (XMLStreamException | JAXBException e) {
        }

        req.setAttribute("users", users);
        req.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(req, resp);
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");

        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
