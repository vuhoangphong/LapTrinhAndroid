package com.example.noteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;

public class Priority_Activity extends AppCompatActivity implements Dialog_Add_Priority.dialog_Add_Priority_Listener {

    FloatingActionButton faBtnAddPriority;
    ListView lvPriority;
    ArrayList<String> arrayListPriority;
    ArrayAdapter arrayAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority_);

        lvPriority = (ListView)findViewById(R.id.lvPriority);
        arrayListPriority = new ArrayList();

        String date = Calendar.getInstance().getTime().toString();
        arrayListPriority.add("abc" + date);
        arrayListPriority.add("xyz" + date);

        arrayAdapter = new ArrayAdapter(Priority_Activity.this, android.R.layout.simple_list_item_1, arrayListPriority);
        lvPriority.setAdapter(arrayAdapter);

        faBtnAddPriority = (FloatingActionButton) findViewById(R.id.faBtnAddPriority);
        faBtnAddPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog();
            }
        });
    }

    public void OpenDialog()
    {
        Dialog_Add_Priority dialog_add_priority = new Dialog_Add_Priority();
        dialog_add_priority.show(getSupportFragmentManager(),"priority");
    }

    @Override
    public void applyAdd(String priority, String date) {
        Toast.makeText( getApplicationContext(),priority+" "+date,Toast.LENGTH_SHORT);
        arrayListPriority.add(priority);
        //TextView textView = findViewById(R.id.textView);
        //textView.setText(priority);
    }
}