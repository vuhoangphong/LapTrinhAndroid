package com.example.noteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.noteapplication.ui.priority.PriorityOJ;
import com.example.noteapplication.ui.priority.priority;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "notedb.sqlite";
    private  static final int SCHEMA_VERSION=1;
    public static final String NOTE_TABLE_NAME = "note";
    public static final String NOTE_COLUMN_ID  = "id";
    public static final String NOTE_COLUMN_PRIORITY = "priority";
    public static final String NOTE_COLUMN_CATEGORY = "category";
    public static final String NOTE_COLUMN_STATUS= "status";
    public static final String NOTE_COLUMN_CONTENT = "content";

    public static final String PRIORITY_COLUMN_ID = "Id";
    public static final String PRIORITY_COLUMN_NAME = "Name";
    public static final String PRIORITY_COLUMN_CREATEDATE = "CreateDate";
    public static final String PRIORITY = "Priority";

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

        db.execSQL(
                "create table " + PRIORITY +
                        "("+PRIORITY_COLUMN_ID+ "integer primary key autoincrement," +PRIORITY_COLUMN_NAME + "text," + PRIORITY_COLUMN_CREATEDATE+ " datetime )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS note");
        db.execSQL("DROP TABLE IF EXISTS Account");
        db.execSQL("DROP TABLE IF EXISTS " + PRIORITY);
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

    public boolean addPriority(PriorityOJ priorityOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRIORITY_COLUMN_NAME,priorityOJ.getName());
        contentValues.put(PRIORITY_COLUMN_CREATEDATE,priorityOJ.getCreateDate());
        long inserted = db.insert(PRIORITY,null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public List<PriorityOJ> priorityOJList (){
        List<PriorityOJ> listPri = new ArrayList<PriorityOJ>();
        String queryString = "SELECT * FROM " + PRIORITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do {
                int priorityId = cursor.getInt(0);
                String priorityName = cursor.getString(cursor.getColumnIndex("Name"));
                String priorityDatetime = cursor.getString(2);
                PriorityOJ newPriority = new PriorityOJ(priorityId,priorityName,priorityDatetime);
                listPri.add(newPriority);
            }while (cursor.moveToNext());
        }else{}
        cursor.close();
        db.close();
        return listPri;
    }
}
