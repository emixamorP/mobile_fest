package com.example.festival.entity;

import java.io.Serializable;

public class Inscription implements Serializable {

    private int id_inscription;
    private User id_user ;
    private Groupe id_groupe;
    private String statut ;


    public int getId() {
        return id_inscription;
    }

    public void setId(int id) {
        this.id_inscription = id;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }



    public Groupe getId_groupe() {
        return id_groupe;
    }

    public void setId_groupe(Groupe id_groupe) {
        this.id_groupe = id_groupe;
    }
    public void setId_user(int user) {
    }

    public void setId_groupe(int idGroupe) {
    }

    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }

}
