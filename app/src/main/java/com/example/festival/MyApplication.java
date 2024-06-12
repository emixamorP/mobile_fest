package com.example.festival;

import android.app.Application;

import com.example.festival.dao.GroupeDao;
import com.example.festival.dao.DatabaseHelper;
import com.example.festival.dao.RoleDao;
import com.example.festival.entity.Groupe;
import com.example.festival.entity.Role;

import java.util.Arrays;

public class MyApplication extends Application {
    private static DatabaseHelper dbHelper ;
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialiser votre base de données ici
        dbHelper = new DatabaseHelper(this);

        dbHelper.getWritableDatabase();
    }
    public static DatabaseHelper getDbHelper() {
        return dbHelper;
    }
    public static void setDbHelper(DatabaseHelper dbHelper) {
        MyApplication.dbHelper = dbHelper;
    }


}