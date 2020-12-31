package com.example.noteapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Calendar;

public class Dialog_Add_Priority extends AppCompatDialogFragment
{
    private dialog_Add_Priority_Listener dialogAddPriorityListener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_add_priority,null);
        builder.setView(view)
                .setTitle("Priority From").setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText txt =  (EditText)view.findViewById(R.id.inputPriority);
                String priority= txt.getText().toString() ;
                String date = Calendar.getInstance().getTime().toString();
                dialogAddPriorityListener.applyAdd(priority,date);
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            dialogAddPriorityListener = (Dialog_Add_Priority.dialog_Add_Priority_Listener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"error");
        }
    }

    public  interface  dialog_Add_Priority_Listener{
        void applyAdd(String priority, String date);
    }
}
