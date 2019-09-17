package com.example.sqliternek;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, "sozluk", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
                sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS kelimeler(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,turkce TEXT,ingilizce TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS kelimeler");
            this.onCreate(sqLiteDatabase);
    }
}
