package com.example.festival.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Nom de la base de données
    private static final String DATABASE_NAME = "user_roles.db";
    // Version de la base de données
    private static final int DATABASE_VERSION = 2;
    // Commandes SQL pour créer les tables User et Role
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE User (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT NOT NULL," +
                    "prenom TEXT NOT NULL," +
                    "email TEXT UNIQUE NOT NULL," +
                    "password TEXT NOT NULL," +
                    "roleId INTEGER," +
                    "FOREIGN KEY(roleId) REFERENCES Role(id),"+
                    "FOREIGN KEY(id_equipe) REFERENCES Equipe(id))";

    private static final String CREATE_TABLE_ROLE =
            "CREATE TABLE Role (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "roleName TEXT NOT NULL)";

    private static final String CREATE_TABLE_INSCRIPTION =
            "CREATE TABLE Inscription (" +
                    "id_inscription INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "id_user INTEGER NOT NULL," +
                    "id_groupe INTEGER NOT NULL," +
                    "statut TEXT NOT NULL," +
                    "FOREIGN KEY(id_user) REFERENCES User(id)," +
                    "FOREIGN KEY(id_groupe) REFERENCES Groupe(id))";

    private static final String CREATE_TABLE_GROUPE =
            "CREATE TABLE Groupe (" +
                    "id_groupe INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nom TEXT NOT NULL," +
                    "description TEXT NOT NULL," +
                    "nombredutilisateur INTEGER NOT NULL)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création des tables User et Role
        db.execSQL(CREATE_TABLE_ROLE);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_GROUPE);
        db.execSQL(CREATE_TABLE_INSCRIPTION);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    if(oldVersion <2){
        // Insertion des données initiales dans les tables Club et Groupe
        insertGroupe(db);

      }
    }

    private void insertGroupe(SQLiteDatabase db) {
        db.execSQL("INSERT INTO Groupe (nom, description, nombredutilisateur) VALUES ('Groupe de football', 'Description de la groupe', '2024-05-01', '2024-05-15', 100)");
    }

}
