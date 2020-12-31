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

public class Dialog_Add_Status extends AppCompatDialogFragment {
    private Dialog_Add_Status.dialog_Add_Status_Listener dialogAddStatusListener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog_add_status,null);
        builder.setView(view)
                .setTitle("Status From").setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText txt =  (EditText)view.findViewById(R.id.inputStatus);
                String status= txt.getText().toString() ;
                String date = Calendar.getInstance().getTime().toString();
                dialogAddStatusListener.applyAdd(status,date);
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            dialogAddStatusListener = (Dialog_Add_Status.dialog_Add_Status_Listener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()+"error");
        }
    }

    public  interface  dialog_Add_Status_Listener{
        void applyAdd(String status, String date);
    }
}
