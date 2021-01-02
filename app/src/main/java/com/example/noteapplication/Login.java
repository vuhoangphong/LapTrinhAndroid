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

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUserName = (EditText) findViewById(R.id.editTextUserName);
        EditText etPass = (EditText) findViewById(R.id.editTextPassWord);

        Button button = (Button) findViewById(R.id.buttonLogin);
        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Login_DB acc= new Login_DB(Login.this);
                Cursor c= acc.getData(etUserName.getText().toString(),etPass.getText().toString());
                String userName_Return =  c.getString(c.getColumnIndex("Email"));
                String userPass_Return =  c.getString(c.getColumnIndex("Pass"));
                if (!c.isClosed()) {
                    c.close();
                }

                // Perform action on click
                if(etUserName.getText().toString().length()>0&&etPass.getText().toString().length()>0){

                    if(userName_Return.equals(etUserName.getText().toString())&&userPass_Return.equals(etPass.getText().toString())){
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Login.this,R.string.incorrectUserPass,Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Login.this,R.string.inputUserNamePass,Toast.LENGTH_LONG).show();
            }
        }});
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }});

    }
    class Login_DB extends DBHelper{
        public Login_DB(Context context) {
            super(context);
        }



        public Cursor getData(String userName,String passWord){
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor res=db.rawQuery("Select * from Account where Email = " + userName + " and Password = "+ passWord +"", null);
            return res;
        }
    }
}

