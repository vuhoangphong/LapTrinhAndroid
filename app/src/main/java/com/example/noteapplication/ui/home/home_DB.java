package com.example.noteapplication.ui.home;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.noteapplication.DBHelper;
import com.example.noteapplication.ui.status.StatusViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.noteapplication.Login.AccInfo;

public class home_DB extends DBHelper {
    public home_DB(Context context) {
        super(context);
    }

    public int  countSatatus (String nameStatus){
        int count = 0;
      try {
          String queryString = "SELECT COUNT(*) as count FROM " + TABLE_NOTE + " Where IDAcc = " + AccInfo.getId()+" AND Status = '"+nameStatus+"'" ;
          SQLiteDatabase db = this.getReadableDatabase();
          Cursor cursor = db.rawQuery(queryString,null);
          cursor.moveToFirst();
          count =cursor.getInt(0);
          cursor.close();
          db.close();
      }catch (Exception e){
          return count;
      }
        return count;
    }

    public int  sumSatatus (){
        int count = 0;
        try {
            String queryString = "SELECT COUNT(*) as sum FROM " + TABLE_STATUS + " Where IDAcc = " + AccInfo.getId();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryString,null);
            cursor.moveToFirst();
            count =cursor.getInt(0);
            cursor.close();
            db.close();
        }catch (Exception e){
            return count;
        }
        return count;
    }

    public List<StatusViewModel> GetListStatus (){
        List<StatusViewModel> lstStatus = new ArrayList<StatusViewModel>();
        String queryString = "SELECT * FROM " + TABLE_STATUS + " Where IDAcc = " + AccInfo.getId() ;
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
