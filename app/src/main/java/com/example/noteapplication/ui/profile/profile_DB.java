package com.example.noteapplication.ui.profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.noteapplication.DBHelper;
import com.example.noteapplication.ui.category.CategoryOJ;

public class profile_DB extends DBHelper {
    public profile_DB(Context context) {
        super(context);
    }

    public boolean  updateProfile(ProfileOJ profileOJ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstName",profileOJ.getFirstName());
        contentValues.put("LastName",profileOJ.getLastName());
        contentValues.put("Email",profileOJ.getEmail());
        long update = db.update(TABLE_ACCOUNT, contentValues, " ID = " + profileOJ.getId(), null);
        if(update == -1 )
            return false;
        else
            return true;
    }


}
