package com.example.noteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "notedb.sqlite";
    private  static final int SCHEMA_VERSION=1;
    public static final String NOTE_TABLE_NAME = "note";
    public static final String NOTE_COLUMN_ID  = "id";
    public static final String NOTE_COLUMN_PRIORITY = "priority";
    public static final String NOTE_COLUMN_CATEGORY = "category";
    public static final String NOTE_COLUMN_STATUS= "status";
    public static final String NOTE_COLUMN_CONTENT = "content";


    public  DBHelper(Context context){
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table note " +
                        "(id integer primary key autoincrement, name text,priority text,category text, status text,content text)"
        );
        db.execSQL(
                "create table Account " +
                        "(id integer primary key autoincrement, username text,password text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS note");
        db.execSQL("DROP TABLE IF EXISTS Account");
        onCreate(db);
    }

    public boolean addNote(String name,String priority,String category,String status, String content){
        /*,*/
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues noteValues = new ContentValues();
        noteValues.put("name",name);
        noteValues.put("priority", priority);
        noteValues.put("category",category);
        noteValues.put("status",status);
        noteValues.put("content",content);
        db.insert("note", null, noteValues);
        db.close();
        return true;
    }

    public boolean addAccount(String userName,String passWord){
        /*,*/
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues accountValue = new ContentValues();
        accountValue.put("name",userName);
        accountValue.put("priority", passWord);

        db.insert("Account", null, accountValue);
        db.close();
        return true;
    }
}
