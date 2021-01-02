package com.example.noteapplication.ui.category;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noteapplication.R;
import com.example.noteapplication.ui.priority.priority;
import com.example.noteapplication.ui.priority.priority_DB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class category extends Fragment  implements Category_dialog.dialog_Add_Category_Listener,PopupMenu.OnMenuItemClickListener{
    private  static  String TAG="category";
    private CategoryViewModel mViewModel;

    public static category newInstance() {
        return new category();
    }


    private List<CategoryOJ> listcategory = new ArrayList<CategoryOJ>();
    private int index = 0;
    private ListView listView;
    ArrayList<CategoryOJ> arrayCategoryOJS = new ArrayList<CategoryOJ>();
    private CategoryAdapter adapter = null;
    Button cancel,add;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.category_fragment, container, false);
        FloatingActionButton b = v.findViewById(R.id.addCategory);
        listView = v.findViewById(R.id.listView);
        b.setOnClickListener(new View.OnClickListener() {  // button click
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        listView = v.findViewById(R.id.listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {  // press and hold
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                showPopup(view);
                return false;
            }
        });
        refreshData();
      return v;
    }


    // open dialog add category
    public  void openDialog(){
        Category_dialog dialogAddCategory = new Category_dialog();
        dialogAddCategory.show(getChildFragmentManager(),"example dialog");
    }

    //open dialog edit when press and hold
    public  void openDialogEdit(String name){
        Category_dialog dialogAddCategory = new Category_dialog(name);
        dialogAddCategory.show(getChildFragmentManager(),"example dialog");
    }

    @Override
    public void getData() {
        refreshData();
    }

    public void refreshData(){
        category_DB categoryDb = new category_DB(category.this.getContext());
        listcategory = categoryDb.getListCategory();
        CategoryAdapter categoryAdapter = new CategoryAdapter();
        listView.setAdapter(categoryAdapter);
    }


    // adapter
    public class CategoryAdapter extends ArrayAdapter<CategoryOJ>{
        public CategoryAdapter(Context context ,int textViewResourceId){
            super(context,textViewResourceId);
        }
        public CategoryAdapter(){
            super(category.this.getContext(), android.R.layout.simple_list_item_1,listcategory);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            if(row == null){
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row_category,null);
            }
            CategoryOJ c = listcategory.get(position);
            ((TextView)row.findViewById(R.id.name)).setText(c.getName());
            ((TextView)row.findViewById(R.id.date)).setText(c.getCreatedate());
            return row;
        }
    }


    // shows a pop-up when pressed and held
    public  void  showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(getContext(),v);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.popup_menu_category);
        popupMenu.show();
    }

    // switch case chose
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.edit:
                openDialogEdit(listcategory.get(index).getName());
                adapter.notifyDataSetChanged();
                return  true;
            case R.id.delete:
                listcategory.remove(index);
                adapter.notifyDataSetChanged();
                return  true;
            default:
                return false;
        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        // TODO: Use the ViewModel
    }


}