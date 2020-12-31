package com.example.noteapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Category_Activity extends AppCompatActivity implements Dialog_Add_Category.dialog_Add_Category_Listener {
    private List<Category> listcategory = new ArrayList<Category>();
    private CategoryAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_);
        FloatingActionButton  btnAddCategory  = findViewById(R.id.addCategory);
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

    @Override
    public void applyAdd(String category, String date) {
        Category c = new Category();
        c.setName(category);
        c.setCreatedate(date);
        listcategory.add(c);
        ListView listView = findViewById(R.id.list_item);
        adapter = new CategoryAdapter() ;
       listView.setAdapter(adapter);
    }

    public class CategoryAdapter extends ArrayAdapter<Category>{
        public CategoryAdapter(Context context ,int textViewResourceId){
            super(context,textViewResourceId);
        }
        public CategoryAdapter(){
            super(Category_Activity.this, android.R.layout.simple_list_item_1,listcategory);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            if(row == null){
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_category,null);
            }
            Category c = listcategory.get(position);
            ((TextView)row.findViewById(R.id.name)).setText(c.getName());
            ((TextView)row.findViewById(R.id.date)).setText(c.getCreatedate());
            return row;
        }
    }



}