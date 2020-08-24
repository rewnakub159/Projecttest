package com.example.project;

public class Register_DB {
    String username;
    String password;
    String email;

    public Register_DB(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Register_DB() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}