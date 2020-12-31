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

public class Priority_Activity extends AppCompatActivity implements Dialog_Add_Priority.dialog_Add_Priority_Listener {

    private List<Priority> listPriority = new ArrayList<>();
    private PriorityAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority_);

        FloatingActionButton faBtnAddPriority = (FloatingActionButton) findViewById(R.id.faBtnAddPriority);
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
        Priority p = new Priority();
        p.setName(priority);
        p.setCreatedate(date);
        listPriority.add(p);
        ListView listView = findViewById(R.id.lvPriority);
        adapter = new Priority_Activity.PriorityAdapter();
        listView.setAdapter(adapter);
    }

    public class PriorityAdapter extends ArrayAdapter<Priority> {
        public PriorityAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public PriorityAdapter() {
            super(Priority_Activity.this, android.R.layout.simple_list_item_1, listPriority);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_priority, null);
            }
            Priority p = listPriority.get(position);
            ((TextView) row.findViewById(R.id.name)).setText(p.getName());
            ((TextView) row.findViewById(R.id.date)).setText("Create Date: " + "" + p.getCreatedate());
            return row;
        }
    }
}