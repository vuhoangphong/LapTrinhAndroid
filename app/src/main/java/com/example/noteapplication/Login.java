package com.example.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity{
    public static Account_Model AccInfo = null;
    int RC_SIGN_IN=0;
    SignInButton btnSignIn_GG;
    GoogleSignInClient mGoogleSignInClient;
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
        String remember_user = preferences.getString("UserName","");
        String remember_pass = preferences.getString("Pass","");
        if(checkbox.equals("true")){
            AccInfo = new Login_DB(Login.this).getAccInfo(remember_user,remember_pass);
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
                    editor.putString("UserName",etUserName.getText().toString());
                    editor.putString("Pass",etPass.getText().toString());
                    editor.apply();
                    Toast.makeText(Login.this,"Remember",Toast.LENGTH_LONG).show();
                }else {
                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.putString("UserName","");
                    editor.putString("Pass","");
                    editor.apply();

                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }});
        btnSignIn_GG = findViewById(R.id.sign_in_button);
        btnSignIn_GG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;

                }
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            if (account != null) {
                Boolean acc_App=new Login_DB(Login.this).getData(account.getEmail(),account.getEmail());
                if(acc_App == true){
                    AccInfo = new Login_DB(Login.this).getAccInfo(account.getEmail(),account.getEmail());
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);

                    startActivity(intent);
                }else {
                    boolean signUp =new SignUp_DB(Login.this).addAcc(account.getGivenName(),account.getFamilyName(),account.getEmail(),account.getEmail());
                    if(signUp== true){
                        AccInfo = new Login_DB(Login.this).getAccInfo(account.getEmail(),account.getEmail());
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(Login.this,R.string.Create_Success,Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(Login.this,R.string.Create_Un_success,Toast.LENGTH_LONG).show();
                    }
                }


            }

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ERROR", "signInResult:failed code=" + e.getStatusCode());

        }
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

