package com.example.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUserName = (EditText) findViewById(R.id.editTextUserName);
        EditText etPass = (EditText) findViewById(R.id.editTextPassWord);

        Button button = (Button) findViewById(R.id.buttonLogin);
        Button btnRegister = (Button) findViewById(R.id.btnSignUp);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                if(etUserName.getText().toString().length()>0&&etPass.getText().toString().length()>0){

                    if(etUserName.getText().toString().equals("admin")&&etPass.getText().toString().equals("admin")){
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(Login.this,R.string.incorrectUserPass,Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(Login.this,R.string.inputUserNamePass,Toast.LENGTH_LONG).show();
            }
        }});

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                        Intent intent = new Intent(Login.this,SignUp.class);
                        startActivity(intent);

            }});

        }
    }

