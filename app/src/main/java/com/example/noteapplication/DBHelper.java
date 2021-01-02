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
    private static final String TABLE_ACCOUNT = "Account";
    private static final String TABLE_CATEGORY = "Category";
    private static final String TABLE_NOTE = "Note";
    private static final String TABLE_PRIORITY = "Priority";
    private static final String TABLE_STATUS = "Status";
    private static final String COLUMN_PRIORITY_NAME_PRIORITY = "NamePriority";
    private static final String COLUMN_PRIORITY_DATE_PRI = "DatePri";


    public  DBHelper(Context context){
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_ACCOUNT + " ( ID    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, FirstName    TEXT, LastName    TEXT, Email    TEXT, Password    TEXT )"
        );
        db.execSQL(
                "CREATE TABLE " + TABLE_CATEGORY + " ( ID    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NameCategory    TEXT, DateCate    TEXT)"
        );
        db.execSQL(
                "CREATE TABLE " + TABLE_NOTE + " ( ID    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Priority    INTEGER, Category    INTEGER, Status    INTEGER, Content    TEXT, DateCreate    TEXT, FOREIGN KEY(Priority) REFERENCES Priority (ID), FOREIGN KEY(Status) REFERENCES Status(ID), FOREIGN KEY(Category) REFERENCES Category(ID) )"
        );
        db.execSQL(
                "CREATE TABLE " + TABLE_PRIORITY + " ( ID    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRIORITY_NAME_PRIORITY + "    TEXT, " + COLUMN_PRIORITY_DATE_PRI + "    TEXT)"
        );
        db.execSQL(
                "CREATE TABLE " + TABLE_STATUS + " ( ID    INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, NameStatus    TEXT, DateSta    TEXT)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS note");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRIORITY);
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

        db.insert(TABLE_ACCOUNT, null, accountValue);
        db.close();
        return true;
    }

    public boolean addPriority(PriorityOJ priorityOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRIORITY_NAME_PRIORITY,priorityOJ.getName());
        contentValues.put(COLUMN_PRIORITY_DATE_PRI,priorityOJ.getCreateDate());
        long inserted = db.insert(TABLE_PRIORITY,null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public List<PriorityOJ> priorityOJList (){
        List<PriorityOJ> listPri = new ArrayList<PriorityOJ>();
        String queryString = "SELECT * FROM " + TABLE_PRIORITY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do {
                int priorityId = cursor.getInt(0);
                String priorityName = cursor.getString(1);
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
