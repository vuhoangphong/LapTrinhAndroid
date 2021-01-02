package com.example.noteapplication.ui.note;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.noteapplication.R;
import com.example.noteapplication.ui.category.Category_dialog;
import com.example.noteapplication.ui.status.status_dialog;

import java.util.Date;

public class note_dialog extends DialogFragment {
    public  interface  dialog_Add_Note_Listener{
        void addNote(String note, String date);

    }
    public note_dialog.dialog_Add_Note_Listener dialogAddNoteListener ;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog_note,null);


        builder.setView(view)
                .setTitle("Add Note").setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText txt =  (EditText)view.findViewById(R.id.etNoteDialog);
                String note= txt.getText().toString() ;
                String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
                Spinner spinner = (Spinner)view.findViewById(R.id.spinner);

                dialogAddNoteListener.addNote(note,date);
            }
        });




        return builder.create();
    }
    @Override
    public void onAttach( Context context) {
        super.onAttach(context);
        dialogAddNoteListener = (dialog_Add_Note_Listener) getParentFragment();
    }
}


