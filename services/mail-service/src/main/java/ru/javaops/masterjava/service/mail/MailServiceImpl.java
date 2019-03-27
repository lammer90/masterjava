package ru.javaops.masterjava.service.mail;

import lombok.extern.slf4j.Slf4j;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "ru.javaops.masterjava.service.mail.MailService")
@Slf4j
public class MailServiceImpl implements MailService{
    public void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body){
        try {
            MailSender.sendMail(to, cc, subject, body);
        } catch (Exception e) {
            log.info("ERROR");
        }
    }
}