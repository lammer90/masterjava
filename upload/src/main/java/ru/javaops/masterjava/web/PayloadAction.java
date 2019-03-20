package ru.javaops.masterjava.web;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ru.javaops.masterjava.xml.schema.User;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.servlet.annotation.MultipartConfig;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PayloadAction extends Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws Exception {
        String target = "failure";
        PayloadForm payloadForm = (PayloadForm) form;

        try (InputStream is = Files.newInputStream(Paths.get("C:/masterjava/upload/src/test/resources/payload.xml"))) {
            StaxStreamProcessor processor = new StaxStreamProcessor(is);

            List<User> users = new ArrayList<>();

            JaxbParser parser = new JaxbParser(User.class);
            while (processor.doUntil(XMLEvent.START_ELEMENT, "User")) {
                User user = parser.unmarshal(processor.getReader(), User.class);
                users.add(user);
            }
            target = "success";

            payloadForm.setUsers(users);
        }

        return mapping.findForward(target);
    }
}
