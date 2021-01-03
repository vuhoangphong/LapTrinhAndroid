package com.example.noteapplication.ui.profile;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteapplication.Login;
import com.example.noteapplication.MainActivity;
import com.example.noteapplication.R;
import com.example.noteapplication.SignUp;
import com.example.noteapplication.ui.category.CategoryOJ;
import com.example.noteapplication.ui.category.Category_dialog;
import com.example.noteapplication.ui.category.category_DB;
import com.example.noteapplication.ui.priority.priority;
import com.example.noteapplication.ui.priority.priority_DB;

import java.util.Date;

public class profile extends Fragment {

    private ProfileViewModel mViewModel;
    Button btnHome, btnConfirm;
    EditText etFirstName, etLastName, etEmail;

    public static profile newInstance() {
        return new profile();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        btnHome = (Button)view.findViewById(R.id.btnReturnHome);
        btnConfirm = (Button)view.findViewById(R.id.btnConfirmEditProfile);
        etFirstName = (EditText)view.findViewById(R.id.etEditFirstName);
        etLastName = (EditText)view.findViewById(R.id.etEditLastName);
        etEmail = (EditText)view.findViewById(R.id.etEditEmail);


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profile.this.getContext(),"Home",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(profile.this.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(profile.this.getContext(),"update profile",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // TODO: Use the ViewModel
    }

}