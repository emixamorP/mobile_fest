package com.example.festival.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.festival.MyApplication;
import com.example.festival.entity.Inscription;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InscriptionDao {

    // Create
    @SuppressLint("NewApi")
    public static Long saveInscription(Inscription inscription) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_user", inscription.getId_user().getId());

        values.put("id_groupe", inscription.getId_groupe().getId());
        values.put("date", String.valueOf(LocalDate.now()));
        values.put("statut", "VALIDER");

        return  db.insert("Inscription", null, values);
       // db.close();
    }

    // Read (Single Inscription)
    @SuppressLint("Range")
    public static Inscription findInscriptionById(int inscriptionId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.query("Inscription", null, "id_inscription=?", new String[]{String.valueOf(inscriptionId)}, null, null, null);

        Inscription inscription = null;
        if (cursor.moveToFirst()) {
            inscription = new Inscription();
            inscription.setId(cursor.getInt(cursor.getColumnIndex("id_inscription")));
            inscription.setId_user(cursor.getInt(cursor.getColumnIndex("user")));

            inscription.setId_groupe(cursor.getInt(cursor.getColumnIndex("id_groupe")));
            inscription.setStatut(cursor.getString(cursor.getColumnIndex("statut")));
        }

        cursor.close();
      //  db.close();
        return inscription;
    }

    // Read (All Inscriptions)
    @SuppressLint("Range")
    public static List<Inscription> findAllInscriptions() {
        List<Inscription> inscriptions = new ArrayList<>();
        SQLiteDatabase db = MyApplication.getDbHelper().getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Inscription", null);

        if (cursor.moveToFirst()) {
            do {
                Inscription inscription = new Inscription();
                inscription.setId(cursor.getInt(cursor.getColumnIndex("id_inscription")));
                inscription.setId_user(cursor.getInt(cursor.getColumnIndex("id_user")));
                inscription.setId_groupe(cursor.getInt(cursor.getColumnIndex("id_groupe")));
                inscription.setStatut(cursor.getString(cursor.getColumnIndex("statut")));

                inscriptions.add(inscription);
            } while (cursor.moveToNext());
        }

        cursor.close();
      //  db.close();

        return inscriptions;
    }

    // Update
    public int updateInscription(Inscription inscription) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("statut", inscription.getStatut());

        int rowsAffected = db.update("Inscription", values, "id_inscription=?", new String[]{String.valueOf(inscription.getId())});
        //   db.close();
        return rowsAffected;
    }

    // Delete
    public void deleteInscription(int inscriptionId) {
        SQLiteDatabase db = MyApplication.getDbHelper().getWritableDatabase();
        db.delete("Inscription", "id_inscription=?", new String[]{String.valueOf(inscriptionId)});
       // db.close();
    }

}