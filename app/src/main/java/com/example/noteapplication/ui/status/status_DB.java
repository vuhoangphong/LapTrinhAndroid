package com.example.noteapplication.ui.status;

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

public class status_DB extends DBHelper {

    public status_DB(Context context) {
        super(context);
    }

    public boolean addStatus(StatusViewModel status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NameStatus",status.getName());
        contentValues.put("DateSta",status.getDatetime());
        contentValues.put("IDAcc", AccInfo.getId());
        long inserted = db.insert(TABLE_STATUS,null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public boolean  deleteStatus(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long inserted = db.delete("Status","id = "+id,null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public boolean  updateStatus(StatusViewModel status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NameStatus",status.getName());
        contentValues.put("DateSta",status.getDatetime());
        contentValues.put("ID",status.getID());
        contentValues.put("IDAcc",AccInfo.getId());
        long inserted = db.update("Status",contentValues,"ID = "+ status.getID(),null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }



    public List<StatusViewModel> GetListStatus (){
        List<StatusViewModel> lstStatus = new ArrayList<StatusViewModel>();
        String queryString = "SELECT * FROM " + TABLE_STATUS + " WHERE IDAcc = " + AccInfo.getId() ;
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
