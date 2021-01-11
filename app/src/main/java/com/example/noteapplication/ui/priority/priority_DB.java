package com.example.noteapplication.ui.priority;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.noteapplication.DBHelper;
import com.example.noteapplication.Login;
import com.example.noteapplication.ui.category.CategoryOJ;

import java.util.ArrayList;
import java.util.List;

import static com.example.noteapplication.Login.AccInfo;

public class priority_DB extends DBHelper {

    public priority_DB(Context context) {
        super(context);
    }

    public boolean addPriority(PriorityOJ priorityOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRIORITY_NAME_PRIORITY,priorityOJ.getName());
        contentValues.put(COLUMN_PRIORITY_DATE_PRI,priorityOJ.getCreateDate());
        contentValues.put("IDAcc", AccInfo.getId());
        long inserted = db.insert(TABLE_PRIORITY,null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public boolean  deletePriority(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long inserted = db.delete("Priority","id = "+id,null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public boolean  updatePriority(PriorityOJ priorityOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_PRIORITY_NAME_PRIORITY,priorityOJ.getName());
        contentValues.put(COLUMN_PRIORITY_DATE_PRI,priorityOJ.getCreateDate());
        contentValues.put("ID",priorityOJ.getId());
        contentValues.put("IDAcc",AccInfo.getId());
        long inserted = db.update("Priority",contentValues,"ID = "+ priorityOJ.getId(),null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public List<PriorityOJ> priorityOJList (){
        List<PriorityOJ> listPri = new ArrayList<PriorityOJ>();
        String queryString = "SELECT * FROM " + TABLE_PRIORITY + " WHERE IDAcc = " + AccInfo.getId();
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
