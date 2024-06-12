package com.example.festival.entity;

import java.io.Serializable;

public class Groupe implements Serializable {

    private int id ;
    private String nom ;
    private String description;
    private Number nombredutilisateur ;

    public int getId() {
        return id;
    }

    public void setId(int id_competition) {
        this.id = id_competition;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getNombredutilisateur() {
        return nombredutilisateur.toString();
    }

    public void setNombredutilisateur(Number nombredutilisateur) {
        this.nombredutilisateur = nombredutilisateur;
    }


}

