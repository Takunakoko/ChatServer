package com.chat;

/**
 * Created by takunaka on 17.02.17.
 */
public class Configurator {
    private static String host = "localhost";
    private static String port = "3306";
    private static String username = "root";
    private static String password = "root";

    private Configurator() {
    }

    public static String getHost() {
        return host;
    }

    public static String getPort() {
        return port;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
