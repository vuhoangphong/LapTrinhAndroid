package com.example.noteapplication.ui.status;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.noteapplication.R;
import com.example.noteapplication.ui.category.Category_dialog;

import java.util.Date;

public class status_dialog extends DialogFragment {
    public  interface  dialog_Add_Status_Listener{
        void addStatus(String status, String date);

    }
    public status_dialog.dialog_Add_Status_Listener dialogAddStatusListener ;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog_status,null);


            builder.setView(view)
                    .setTitle("Add Status").setNegativeButton("close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setPositiveButton("add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText txt =  (EditText)view.findViewById(R.id.etStatusDialog);
                    String status="Name: "+txt.getText().toString() ;
                    String date = "Created Day: "+java.text.DateFormat.getDateTimeInstance().format(new Date());
                    dialogAddStatusListener.addStatus(status,date);
                }
            });




        return builder.create();
    }
    @Override
    public void onAttach( Context context) {
        super.onAttach(context);
        dialogAddStatusListener = (dialog_Add_Status_Listener) getParentFragment();
    }
}
