package com.example.noteapplication.ui.note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.noteapplication.DBHelper;
import com.example.noteapplication.ui.category.CategoryOJ;
import com.example.noteapplication.ui.priority.PriorityOJ;
import com.example.noteapplication.ui.status.StatusViewModel;

import java.util.ArrayList;
import java.util.List;

public class note_DB extends DBHelper {
    public note_DB(Context context) {
        super(context);
    }

    public boolean  insetCategory(noteOJ noteOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Content",noteOJ.getName());
        contentValues.put("Category",noteOJ.getCategory());
        contentValues.put("Priority",noteOJ.getPriority());
        contentValues.put("Status",noteOJ.getStatus());
        contentValues.put("PlanDate",noteOJ.getPlanDate());
        contentValues.put("DateCreate",noteOJ.getCreateDate());
        long inserted = db.insert(TABLE_NOTE,null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public List<CategoryOJ> getSpinnerCategory (){
        List<CategoryOJ> list = new ArrayList<CategoryOJ>();
        String queryString = "SELECT * FROM  Category ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String Datetime = cursor.getString(2);
                CategoryOJ  categoryOJ = new CategoryOJ(id,name,Datetime);
                list.add(categoryOJ);
            }while (cursor.moveToNext());
        }else{}
        cursor.close();
        db.close();
        return list;
    }


    public List<PriorityOJ> getSpinnerPriority (){
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


    public List<StatusViewModel> getSpinnerStatus (){
        List<StatusViewModel> lstStatus = new ArrayList<StatusViewModel>();
        String queryString = "SELECT * FROM " + TABLE_STATUS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do {
                int Id = cursor.getInt(0);
                String StatusName = cursor.getString(1);
                String DateSta = cursor.getString(2);
                StatusViewModel n = new StatusViewModel(Id,StatusName,DateSta);
                lstStatus.add(n);
            }while (cursor.moveToNext());
        }else{}
        cursor.close();
        db.close();
        return lstStatus;
    }


}
