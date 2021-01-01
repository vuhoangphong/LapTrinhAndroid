package com.example.noteapplication.ui.priority;

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

public class Priority_dialog extends DialogFragment {
    public dialog_Add_Priority_Listener dialogAddPriorityListener ;
    String name  = "-1";
    public Priority_dialog(String name) {
        this.name= name;
    }
    public Priority_dialog() {
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.priority_form_fragment,null);

        if(name.equals("-1")){  // when click add
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
                    String date = java.text.DateFormat.getDateTimeInstance().format(new Date());
                    dialogAddPriorityListener.applyAdd(priority,date);
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
        void applyAdd(String priority, String date);
    }
}
