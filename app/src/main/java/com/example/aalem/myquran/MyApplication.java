package com.example.aalem.myquran;

import android.app.Application;
import android.util.Log;

import com.example.aalem.myquran.helpers.DBHelper;

import java.io.IOException;

public class MyApplication extends Application {
    public static DBHelper dbHelper;
    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DBHelper(this);
        try {
            dbHelper.updateDataBase();
            Log.e("db", "creating database");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
