package com.example.project;

public class Register_db {
    String username;
    String password;
    String email;

    public Register_db() {

    }

    public Register_db(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}