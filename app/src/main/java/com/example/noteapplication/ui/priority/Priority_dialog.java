package com.example.noteapplication.ui.priority;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.noteapplication.DBHelper;
import com.example.noteapplication.Login;
import com.example.noteapplication.R;
import com.example.noteapplication.ui.category.CategoryOJ;
import com.example.noteapplication.ui.category.Category_dialog;
import com.example.noteapplication.ui.category.category_DB;

import java.util.Date;

public class Priority_dialog extends DialogFragment {
    public dialog_Add_Priority_Listener dialogAddPriorityListener ;
    String name  = "-1";
    EditText txt;
    int keyId  ;
    public Priority_dialog(String name,int keyId) {
        this.name= name;
        this.keyId = keyId;
    }
    public Priority_dialog() {
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.priority_form_fragment,null);
        txt = (EditText)view.findViewById(R.id.inputPriority);
        if(name.equals("-1")){  // when click add
            builder.setView(view)
                    .setTitle("Priority From").setNegativeButton("close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(txt.getText().toString().equals(""))
                    {
                        Toast.makeText(Priority_dialog.this.getContext(),"error name priority null",Toast.LENGTH_SHORT).show();
                    }else {
                        try {
                            String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
                            PriorityOJ priorityOJ = new PriorityOJ(-1,txt.getText().toString(),date);
                            priority_DB priority_db = new priority_DB(Priority_dialog.this.getContext());
                            priority_db.addPriority(priorityOJ);
                            Toast.makeText(Priority_dialog.this.getContext(),"insert success",Toast.LENGTH_SHORT).show();
                        }catch (ClassCastException e){
                            Toast.makeText(Priority_dialog.this.getContext(),"error insert",Toast.LENGTH_SHORT).show();
                        }
                    }

                    dialogAddPriorityListener.getData();
                }
            });
        }else { //when click edit
            EditText txt =  (EditText)view.findViewById(R.id.inputPriority);
            txt.setText(name);
            builder.setView(view)
                    .setTitle("Priority Edit").setNegativeButton("close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PriorityOJ priorityOJ = new PriorityOJ();
                    priorityOJ.setName(txt.getText().toString());
                    priorityOJ.setCreateDate(java.text.DateFormat.getDateTimeInstance().format(new Date()));
                    priorityOJ.setId(keyId);
                    priority_DB priority_db = new priority_DB(Priority_dialog.this.getContext());
                    priority_db.updatePriority(priorityOJ);
                    dialogAddPriorityListener.getData();
                }
            });

        }
        return builder.create();
    }
    @Override
    public void onAttach( Context context) {
        super.onAttach(context);
        dialogAddPriorityListener = (dialog_Add_Priority_Listener) getParentFragment();
    }

    public interface dialog_Add_Priority_Listener {
        //void applyAdd(String priority, String date);
        void getData();
    }
}
