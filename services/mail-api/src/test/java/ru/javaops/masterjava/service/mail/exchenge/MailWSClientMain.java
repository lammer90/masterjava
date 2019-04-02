package ru.javaops.masterjava.service.mail.exchenge;

import com.google.common.collect.ImmutableList;
import ru.javaops.masterjava.service.mail.auto.Addressee;

public class MailWSClientMain {
    public static void main(String[] args) {
        Addressee add = new Addressee();
        add.setEmail("To <lammer.90@yandex.ru>");
        MailWSClientTest.sendToGroup(
                ImmutableList.of(add),
                ImmutableList.of(add), "Subject", "Body");
    }
}