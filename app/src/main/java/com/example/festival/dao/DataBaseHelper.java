package com.example.festival.dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DataBaseHelper {
    // Nom de la base de données
    private static final String DATABASE_NAME = "user_roles.db";
    // Version de la base de données
    private static final int DATABASE_VERSION = 1;
    // Commandes SQL pour créer les tables User et Role
    private static final String CREATE_TABLE_USER =
            "CREATE TABLE User (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nom TEXT NOT NULL," +
            "prenom TEXT NOT NULL," +
            "email TEXT UNIQUE NOT NULL," +
            "password TEXT NOT NULL," +
            "roleId INTEGER," +
            "FOREIGN KEY(roleId) REFERENCES Role(id));
    private static final String CREATE_TABLE_ROLE =
            "CREATE TABLE Role (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "roleName TEXT NOT NULL)";
    // Constructeur
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// Création des tables User et Role
        db.execSQL(CREATE_TABLE_ROLE);
        db.execSQL(CREATE_TABLE_USER);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
// Ici vous pouvez gérer les mises à jour de votre schéma de base
        de données pour les nouvelles versions
    }
}
