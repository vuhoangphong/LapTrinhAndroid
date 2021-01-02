package com.example.noteapplication.ui.category;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.noteapplication.R;
import com.example.noteapplication.ui.priority.Priority_dialog;
import com.example.noteapplication.ui.priority.priority_DB;

import java.util.Date;

public class Category_dialog extends DialogFragment {
    public  interface  dialog_Add_Category_Listener{
        void getData();
    }
    public   dialog_Add_Category_Listener dialogAddCategoryListener ;
    String name  = "-1";
    public Category_dialog(String name) {
        this.name= name;
    }
    public Category_dialog() {
    }

    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_category,null);

        if(name.equals("-1")){  // when click add
            builder.setView(view)
                    .setTitle("Category From").setNegativeButton("close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText txt =  (EditText)view.findViewById(R.id.input);

                    try {
                        String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
                        CategoryOJ categoryOJ = new CategoryOJ(-1,txt.getText().toString(),date);
                        category_DB category_db = new category_DB(Category_dialog.this.getContext());
                        category_db.insetCategory(categoryOJ);
                    }catch (ClassCastException e){
                        Toast.makeText(Category_dialog.this.getContext(),"error insert",Toast.LENGTH_SHORT).show();
                    }
                    dialogAddCategoryListener.getData();
                }
            });
        }else { //when click edit
//            EditText txt =  (EditText)view.findViewById(R.id.input);
//            txt.setText(name);
//            builder.setView(view)
//                    .setTitle("Category Edit").setNegativeButton("close", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//
//                }
//            }).setPositiveButton("save", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    String category= txt.getText().toString() ;
//                    String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
//                    dialogAddCategoryListener.applyEdit(category,date);
//                }
//            });

        }

        return builder.create();
    }

    // send data to fragment
    @Override
    public void onAttach( Context context) {
        super.onAttach(context);
        dialogAddCategoryListener = (dialog_Add_Category_Listener) getParentFragment();
    }



}
