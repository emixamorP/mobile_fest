package com.example.festival.entity;

import java.io.Serializable;

public class Role implements Serializable {
    public int getId() {
        return id_role;
    }

    public void setId(int id_role) {
        this.id_role = id_role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    private int id_role;
    private String roleName;

// ajoutez les getters et les setters

}
