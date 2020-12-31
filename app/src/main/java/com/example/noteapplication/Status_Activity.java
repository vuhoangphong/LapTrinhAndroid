package com.example.noteapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Status_Activity extends AppCompatActivity implements Dialog_Add_Status.dialog_Add_Status_Listener{

    private List<Status> listStatus = new ArrayList<>();
    private Status_Activity.StatusAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_);

        FloatingActionButton faBtnAddStatus = (FloatingActionButton) findViewById(R.id.faBtnAddStatus);
        faBtnAddStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenDialog();
            }
        });
    }

    public void OpenDialog()
    {
        Dialog_Add_Status dialog_add_status = new Dialog_Add_Status();
        dialog_add_status.show(getSupportFragmentManager(),"status");
    }

    @Override
    public void applyAdd(String status, String date) {
        Toast.makeText( getApplicationContext(),status+" "+date,Toast.LENGTH_SHORT);
        Status p = new Status();
        p.setName(status);
        p.setCreatedate(date);
        listStatus.add(p);
        ListView listView = findViewById(R.id.lvStatus);
        adapter = new Status_Activity.StatusAdapter();
        listView.setAdapter(adapter);
    }

    public class StatusAdapter extends ArrayAdapter<Status> {
        public StatusAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public StatusAdapter() {
            super(Status_Activity.this, android.R.layout.simple_list_item_1, listStatus);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_status, null);
            }
            Status p = listStatus.get(position);
            ((TextView) row.findViewById(R.id.name)).setText(p.getName());
            ((TextView) row.findViewById(R.id.date)).setText("Create Date: " + "" + p.getCreatedate());
            return row;
        }
    }
}