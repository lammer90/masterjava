package ru.javaops.masterjava.service.mail;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

import javax.mail.Address;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MailSender {
    static void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) throws Exception {
        log.info("Send mail to \'" + to + "\' cc \'" + cc + "\' subject \'" + subject + (log.isDebugEnabled() ? "\nbody=" + body : ""));

        StringBuilder rString = new StringBuilder();
        to.forEach(s -> rString.append(s.getEmail()).append(","));
        rString.deleteCharAt(rString.lastIndexOf(","));

        try {
            PostConstruction.TransportSender tr = PostConstruction.getNewEmail();
            tr.send(body, subject, rString.toString());
        } catch (Exception e) {
            log.info("Error");
        }
    }
}
