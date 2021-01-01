package com.example.noteapplication.ui.status;

import androidx.core.app.ComponentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.noteapplication.MainActivity;
import com.example.noteapplication.R;
import com.example.noteapplication.StatusActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class status extends Fragment {

    private StatusViewModel mViewModel;

    public static status newInstance() {
        return new status();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.status_fragment,container,false);
        FloatingActionButton myFab = view.findViewById(R.id.btnAddStatus);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Dialog digStatus = new Dialog(getActivity());

                digStatus.setContentView(R.layout.custom_dialog_status);
                digStatus.show();
            }
        });

    return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(StatusViewModel.class);
        // TODO: Use the ViewModel
    }


}