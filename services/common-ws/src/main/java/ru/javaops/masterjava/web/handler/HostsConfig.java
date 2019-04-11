package ru.javaops.masterjava.web.handler;

import com.typesafe.config.Config;
import ru.javaops.masterjava.config.Configs;

import javax.xml.ws.Service;

public class HostsConfig {
    private static Config HOSTS;

    public static String USER;
    public static String PASS;
    public static String DEBUG_CLIENT;
    public static String DEBUG_SERVER;

    static {
        HOSTS = Configs.getConfig("hosts.conf", "hosts.mail");

        USER = HOSTS.getString("user");
        PASS = HOSTS.getString("password");
        DEBUG_CLIENT = HOSTS.getString("debug.client");
        DEBUG_SERVER = HOSTS.getString("debug.server");
    }
}
