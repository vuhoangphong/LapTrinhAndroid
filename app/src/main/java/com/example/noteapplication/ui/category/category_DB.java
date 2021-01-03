package com.example.noteapplication.ui.category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.noteapplication.DBHelper;
import com.example.noteapplication.ui.priority.PriorityOJ;

import java.util.ArrayList;
import java.util.List;

import static com.example.noteapplication.Login.AccInfo;

public class category_DB extends DBHelper {
    public category_DB(Context context) {
        super(context);
    }

    public boolean  insetCategory(CategoryOJ categoryOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NameCategory",categoryOJ.getName());
        contentValues.put("DateCate",categoryOJ.getCreatedate());
        contentValues.put("IDAcc",AccInfo.getId());
        long inserted = db.insert("Category",null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public boolean  deleteCategory(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long inserted = db.delete("Category","id = "+id,null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public boolean  updateCategory(CategoryOJ categoryOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NameCategory",categoryOJ.getName());
        contentValues.put("DateCate",categoryOJ.getCreatedate());
        contentValues.put("ID",categoryOJ.getId());
        contentValues.put("IDAcc",AccInfo.getId());
        long inserted = db.update("Category",contentValues,"ID = "+ categoryOJ.getId(),null);
        if(inserted == -1 )
            return false;
        else
            return true;
    }

    public List<CategoryOJ> getListCategory (){
        List<CategoryOJ> list = new ArrayList<CategoryOJ>();
        String queryString = "SELECT * FROM  Category Where IDAcc = " + AccInfo.getId()+" ORDER BY id DESC";
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
}
