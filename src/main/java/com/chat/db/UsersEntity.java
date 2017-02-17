package com.chat.db;

/**
 * Created by takunaka on 17.02.17.
 */
public class UsersEntity {
    private String username;
    private Integer id;
    private String password;

    public UsersEntity() {
    }

    public UsersEntity(String username, Integer id, String password) {
        this.username = username;
        this.id = id;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
