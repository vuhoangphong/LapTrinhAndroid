package com.example.noteapplication.ui.changepass;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.noteapplication.MainActivity;
import com.example.noteapplication.R;
import com.example.noteapplication.ui.profile.profile;

public class changepass extends Fragment {

    private ChangepassViewModel mViewModel;
    Button btnChange, btnReturn;

    public static changepass newInstance() {
        return new changepass();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.changepass_fragment, container, false);
        btnChange = (Button)view.findViewById(R.id.btnChangePass);
        btnReturn = (Button)view.findViewById(R.id.btnChangePass_Return);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(changepass.this.getContext(),"changed",Toast.LENGTH_SHORT).show();
            }
        });
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(changepass.this.getContext(),"return",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(changepass.this.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChangepassViewModel.class);
        // TODO: Use the ViewModel
    }

}