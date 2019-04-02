package ru.javaops.masterjava.service.mail;

import com.google.common.collect.ImmutableSet;

public class MailWSClientMain {
    public static void main(String[] args) {
        MailWSClient.sendToGroup(
                ImmutableSet.of(new Addressee("To <lammer.90@yandex.ru>")),
                ImmutableSet.of(new Addressee("Copy <lammer.90@yandex.ru>")), "Subject", "Body");
    }
}