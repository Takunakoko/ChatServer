package com.chat.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by takunaka on 17.02.17.
 */
public class UsersDAO {
    private static Connection conn;

    static {
        try {
            conn = Connector.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UsersEntity getUser(String login) throws SQLException {
        Statement st = conn.createStatement();
        UsersEntity ue = new UsersEntity();

        ResultSet rs = st.executeQuery("SELECT * FROM ChatDatabase.USERS WHERE username = " + login);
        while (rs.next()){
            ue.setId(rs.getInt("id"));
            ue.setPassword(rs.getString("password"));
            ue.setUsername(rs.getString("username"));
        }
        rs.close();
        st.close();
        return ue;
    }

    public static void addUser(UsersEntity entity) throws SQLException {
        Statement st = conn.createStatement();
        st.execute("INSERT INTO ChatDatabase.USERS (username, password) " +
                "VALUES ("+ entity.getUsername() + "," + entity.getPassword() + ")");
        st.close();
    }



}
