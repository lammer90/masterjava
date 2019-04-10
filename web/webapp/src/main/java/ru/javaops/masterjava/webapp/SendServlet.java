package ru.javaops.masterjava.webapp;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import lombok.extern.slf4j.Slf4j;
import ru.javaops.masterjava.service.mail.Addressee;
import ru.javaops.masterjava.service.mail.GroupResult;
import ru.javaops.masterjava.service.mail.MailWSClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@WebServlet("/send")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10) //10 MB in memory limit
@Slf4j
public class SendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String result;
        try {
            log.info("Start sending");
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            String users = req.getParameter("email");
            String subject = req.getParameter("subject");
            String body = req.getParameter("body");
            //int attach = Integer.parseInt(req.getParameter("att"));
         /*   if (attach == 0) {
                GroupResult groupResult = MailWSClient.sendBulk(MailWSClient.split(users), subject, body);
                result = groupResult.toString();
            } else {*/
                Part filePart = req.getPart("fileToUpload");
                try (InputStream is = filePart.getInputStream()) {
                    Path tempFile = Files.createTempFile("file", ".tmp");
                    Files.copy(is, tempFile, StandardCopyOption.REPLACE_EXISTING);
                    result = MailWSClient.sendToGroupWithAttach(ImmutableSet.of(new Addressee(users)), ImmutableSet.of(), subject, body, tempFile);
                }
        //    }

            log.info("Processing finished with result: {}", result);
        } catch (Exception e) {
            log.error("Processing failed", e);
            result = e.toString();
        }
        resp.getWriter().write(result);
    }
}
