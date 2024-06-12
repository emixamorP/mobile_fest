package com.example.festival.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.festival.MyApplication;
import com.example.festival.entity.Groupe;

import java.util.ArrayList;
import java.util.List;

public class GroupeDao {
    // Create
    public static void saveGroupe(Groupe groupe) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", groupe.getNom());
        values.put("description", groupe.getDescription());
        values.put("nombredutilisateur", groupe.getNombredutilisateur());
        db.insert("Groupe", null, values);
        // db.close();
    }

    // Read (Single Groupe)
    @SuppressLint("Range")
    public static Groupe findGroupeById(int groupeId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.query("Groupe", null, "id_groupe=?", new String[]{String.valueOf(groupeId)}, null, null, null);

        Groupe groupe = null;
        if (cursor.moveToFirst()) {
            groupe = new Groupe();
            groupe.setId(cursor.getInt(cursor.getColumnIndex("id_groupe")));
            groupe.setNom(cursor.getString(cursor.getColumnIndex("nom")));
            groupe.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            groupe.setNombredutilisateur(cursor.getInt(cursor.getColumnIndex("nombredutilisateur")));
        }

        cursor.close();
        //  db.close();
        return groupe;
    }

    // Read (All Groupes)
    @SuppressLint("Range")
    public static List<Groupe> findAllGroupes() {
        List<Groupe> groupes = new ArrayList<>();
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Groupe", null);

        if (cursor.moveToFirst()) {
            do {
                Groupe groupe = new Groupe();
                groupe.setId(cursor.getInt(cursor.getColumnIndex("id_groupe")));
                groupe.setNom(cursor.getString(cursor.getColumnIndex("nom")));
                groupe.setDescription(cursor.getString(cursor.getColumnIndex("description")));
                groupe.setNombredutilisateur(cursor.getInt(cursor.getColumnIndex("nombredutilisateur")));

                groupes.add(groupe);
            } while (cursor.moveToNext());
        }

        cursor.close();
        // db.close();

        return groupes;
    }

    // Update
    public static int updateGroupe(Groupe groupe) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nom", groupe.getNom());
        values.put("description", groupe.getDescription());
        values.put("nombredutilisateur", groupe.getNombredutilisateur());

        int rowsAffected = db.update("Groupe", values, "id_groupe=?", new String[]{String.valueOf(groupe.getId())});
        //db.close();
        return rowsAffected;
    }

    // Delete
    public static void deleteGroupe(int groupeId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        db.delete("Groupe", "id_groupe=?", new String[]{String.valueOf(groupeId)});
        //db.close();
    }
}
