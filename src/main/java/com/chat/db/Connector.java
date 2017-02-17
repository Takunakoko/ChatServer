package com.chat.db;

import com.chat.Configurator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by takunaka on 17.02.17.
 */
public class Connector {
    private static Connection conn;

    private Connector() {
    }

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection("jdbc:mysql://" + Configurator.getHost() + ":" + Configurator.getPort() +
                    "/ChatDatabase?" +
                    "user=" + Configurator.getUsername() +
                    "&password=" + Configurator.getPassword());
        }
        return conn;
    }

}
