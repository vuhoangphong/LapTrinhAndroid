package com.example.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn_Activity extends AppCompatActivity {
    EditText edtUserName, edtPassWord;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_);
        edtUserName = (EditText)findViewById(R.id.editTextUserName);
        edtPassWord = (EditText)findViewById(R.id.editTextPassWord);
        btnLogin = (Button)findViewById(R.id.buttonLogin);
        Button btnExit = (Button) findViewById(R.id.btnExit);

        btnLogin.setOnClickListener (new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String username = "admin";
                String password = "admin";
                if (edtUserName.getText().toString().equals(username) && edtPassWord.getText().toString().equals(password))
                {
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Tài khoản hoặc mật khẩu sai",Toast.LENGTH_LONG).show();
                }
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}