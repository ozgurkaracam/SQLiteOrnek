package com.example.sqliternek;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class KeliemelerDao {
    private Database db;

    public KeliemelerDao(Database db) {
        this.db = db;
    }

    public void kelimeEkle(String turkce, String ingilizce){
        SQLiteDatabase database=db.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("ingilizce",ingilizce);
        contentValues.put("turkce",turkce);

        database.insertOrThrow("kelimeler",null,contentValues);
        database.close();

    }

    public ArrayList<Kelimeler> TumKelimeler(){
        ArrayList<Kelimeler> kelimelers=new ArrayList<>();
        SQLiteDatabase database=db.getWritableDatabase();
        Cursor c=database.rawQuery("SELECT * FROM kelimeler",null);
        while (c.moveToNext()){
            kelimelers.add(new Kelimeler(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("turkce")),c.getString(c.getColumnIndex("ingilizce"))));
        }
        database.close();
        return kelimelers;


    }

    public void kelimeDuzenle(int id,String turkce,String ingilizce){
        SQLiteDatabase database=db.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("turkce",turkce);
        contentValues.put("ingilizce",ingilizce);
        database.update("kelimeler",contentValues,"id=?",new String[]{String.valueOf(id)});
        database.close();
    }
    public void kelimeSil(int id){
        SQLiteDatabase database=db.getWritableDatabase();
        database.delete("kelimeler","id=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void tumKelimeleriSil(){
        SQLiteDatabase database=db.getWritableDatabase();
        database.execSQL("DELETE from kelimeler");
        db.close();
    }
}
