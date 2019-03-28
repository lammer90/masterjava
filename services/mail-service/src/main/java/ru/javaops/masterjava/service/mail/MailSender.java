package ru.javaops.masterjava.service.mail;

import dao.PostResultDAO;
import lombok.extern.slf4j.Slf4j;
import model.PostResult;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.DBITestProvider;

import javax.mail.Address;
import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class MailSender {
    static void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) {
        log.info("Send mail to \'" + to + "\' cc \'" + cc + "\' subject \'" + subject + (log.isDebugEnabled() ? "\nbody=" + body : ""));

        String addrTo = convert(to);
        String addrCc = convert(cc);
        boolean success = false;

        try {
            PostConstruction.TransportSender tr = PostConstruction.getNewEmail();
            tr.send(body, subject, addrTo, addrCc);
            success = true;
        } catch (MessagingException e) {
            log.info("Post Error >>>");

        }

        DBITestProvider.initDBI();
        PostResultDAO dao = DBIProvider.getDao(PostResultDAO.class);
        dao.insert(new PostResult(LocalDateTime.now(), addrTo, addrCc, subject, body, success));
    }

    private static String convert(List<Addressee> list){
        StringBuilder rString = new StringBuilder();
        list.forEach(s -> rString.append(s.getEmail()).append(","));
        return rString.deleteCharAt(rString.lastIndexOf(",")).toString();
    }
}
