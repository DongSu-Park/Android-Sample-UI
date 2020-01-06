package com.floreerin.doit_android_sample_ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class ch11_DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "movie";
    public static final String MOVIE_ID = "movie_id";
    public static final String MOVIE_TITLE = "movie_title";
    public static final String MOVIE_DATE = "movie_date";
    public static final String MOVIE_TIME = "movie_time";
    public static final String MOVIE_RATE = "movie_rate";

    public ch11_DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        println("onCreate 호출됨");

        String sql = "create table if not exists " + TABLE_NAME + "(" +
                MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MOVIE_TITLE + " TEXT, " +
                MOVIE_DATE + " TEXT, " +
                MOVIE_TIME + " INTEGER, " +
                MOVIE_RATE + " NUMERIC " +
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("onUpgrade 호출됨 : " + oldVersion + " -> " + newVersion);

        if (newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }
    }

    private void println(String data) {
        Log.d("DatabaseHelper", data);
    }

}
