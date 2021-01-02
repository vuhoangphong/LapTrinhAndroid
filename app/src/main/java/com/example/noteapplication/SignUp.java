package com.example.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteapplication.ui.status.StatusViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText fName= (EditText)findViewById(R.id.etUserFirstName);
        EditText lName= (EditText)findViewById(R.id.etUserLastName);
        EditText email= (EditText)findViewById(R.id.etEmail);
        EditText pass= (EditText)findViewById(R.id.etPassword);
        FloatingActionButton btnAddAcc = (FloatingActionButton)findViewById(R.id.btnAddAcc);
        btnAddAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new SignUp_DB(getApplicationContext()).addAcc(fName.getText().toString(),lName.getText().toString(),email.getText().toString(),pass.getText().toString());
                    Toast.makeText(getApplicationContext(),"Create Success",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUp.this,MainActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"UnSuccess",Toast.LENGTH_LONG).show();
                }

            }
        });
    }



}
class SignUp_DB extends DBHelper{
    public SignUp_DB(Context context) {
        super(context);
    }



    public boolean addAcc(String fName,String lName,String email,String passWord){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("FirstName",fName);
        contentValues.put("LastName",lName);
        contentValues.put("Email",email);
        contentValues.put("Password",passWord);
        long inserted = db.insert(TABLE_ACCOUNT,null,contentValues);
        if(inserted == -1 )
            return false;
        else
            return true;
    }
}