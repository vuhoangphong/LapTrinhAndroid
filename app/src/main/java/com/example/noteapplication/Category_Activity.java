package com.example.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Category_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_);
        Button  btnAddCategory  = findViewById(R.id.addCategory);
        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    public  void openDialog(){
        Dialog_Add_Category dialogAddCategory = new Dialog_Add_Category();
        dialogAddCategory.show(getSupportFragmentManager(),"example dialog");
    }
}