package com.example.noteapplication.ui.changepass;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.noteapplication.DBHelper;

public class ChangePass_DB extends DBHelper {
    public ChangePass_DB(Context context) {
        super(context);
    }

    public boolean  updatePass(ChangePassOJ changePassOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Password",changePassOJ.getPassword());
        long update = db.update(TABLE_ACCOUNT, contentValues, " ID = " + changePassOJ.getId(), null);
        if(update == -1 )
            return false;
        else
            return true;
    }
}
