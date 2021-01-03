package com.example.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteapplication.ui.Account_Model;
import com.example.noteapplication.ui.status.StatusViewModel;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity{
    public static Account_Model AccInfo = null;
    CheckBox remember;
    EditText etUserName,etPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.editTextUserName);
        etPass = (EditText) findViewById(R.id.editTextPassWord);
        remember = findViewById(R.id.remember_me);

        Button button = (Button) findViewById(R.id.buttonLogin);
        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
        String checkbox = preferences.getString("remember","");
        if(checkbox.equals("true")){
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(Login.this,"Please Sign In",Toast.LENGTH_LONG).show();
        }
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Login_DB acc= new Login_DB(Login.this);
                Boolean c= acc.getData(etUserName.getText().toString(),etPass.getText().toString());


                if(etUserName.getText().toString().length()>0&&etPass.getText().toString().length()>0){

                    if(c == true){
                        AccInfo = acc.getAccInfo(etUserName.getText().toString(),etPass.getText().toString());

                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Login.this,R.string.incorrectUserPass,Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Login.this,R.string.inputUserNamePass,Toast.LENGTH_LONG).show();
            }
        }});
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.apply();
                    Toast.makeText(Login.this,"Remember",Toast.LENGTH_LONG).show();
                }else {
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();

                }
            }
        });
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

        public Account_Model getAccInfo (String userName,String passWord){
            Account_Model acc= new Account_Model();
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor c=db.rawQuery("Select * from Account where Email = ? and Password = ?", new String[]{userName,passWord});
            int Id = c.getColumnIndex("ID");
            int fName = c.getColumnIndex("FirstName");
            int lName = c.getColumnIndex("LastName");
            int email = c.getColumnIndex("Email");
            int pass = c.getColumnIndex("Password");
            if(c.moveToFirst()){
                    acc.setId(c.getInt(Id));
                    acc.setFirstName(c.getString(fName));
                    acc.setLastName(c.getString(lName));
                    acc.setEmail(c.getString(email));
                    acc.setPassWord(c.getString(pass));

            }
            db.close();
            return acc;
        }


        public Boolean getData(String userName,String passWord){
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor res=db.rawQuery("Select * from Account where Email = ? and Password = ?", new String[]{userName,passWord});
           if (res.getCount()>0)return true;
           else return false;
        }
    }
}

