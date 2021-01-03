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

import static com.example.noteapplication.Login.AccInfo;

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
        contentValues.put("IDAcc",AccInfo.getId());
        long inserted = db.insert(TABLE_NOTE,null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public List<noteOJ> getNote (){
        List<noteOJ> list = new ArrayList<noteOJ>();
        String queryString = "SELECT * FROM  Note Where IDAcc = "+ AccInfo.getId()+ " ORDER BY id DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do {
                noteOJ  noteOJ = new noteOJ(cursor.getString(4),cursor.getString(2),cursor.getString(1),cursor.getString(3),cursor.getString(6),cursor.getString(5));
                list.add(noteOJ);
            }while (cursor.moveToNext());
        }else{}
        cursor.close();
        db.close();
        return list;
    }


    public List<CategoryOJ> getSpinnerCategory (){
        List<CategoryOJ> list = new ArrayList<CategoryOJ>();
        String queryString = "SELECT * FROM  Category Where IDAcc = "+ AccInfo.getId()+" ORDER BY id DESC";
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


    public List<StatusViewModel> getSpinnerStatus (){
        List<StatusViewModel> lstStatus = new ArrayList<StatusViewModel>();
        String queryString = "SELECT * FROM " + TABLE_STATUS +" Where IDAcc = "+ AccInfo.getId();
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
