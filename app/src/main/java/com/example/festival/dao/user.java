package com.example.festival.dao;

import java.util.Date;

public class user {

    private int id;
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String role;

    public class Role {
        private int id;
        private String roleName;
    public int getId() {
        return id;
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

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getRole() {
        return role;
    }
}
}
