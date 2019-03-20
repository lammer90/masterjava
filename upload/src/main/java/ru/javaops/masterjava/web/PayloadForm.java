package ru.javaops.masterjava.web;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
import ru.javaops.masterjava.xml.schema.User;

import java.util.ArrayList;
import java.util.List;

public class PayloadForm extends ActionForm {
    public static List<User> users;
    public FormFile upfile;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public FormFile getUpfile() {
        return upfile;
    }

    public void setUpfile(FormFile upfile) {
        this.upfile = upfile;
    }
}
