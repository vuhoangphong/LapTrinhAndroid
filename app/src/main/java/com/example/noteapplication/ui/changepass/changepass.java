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
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteapplication.MainActivity;
import com.example.noteapplication.R;
import com.example.noteapplication.ui.profile.ProfileOJ;
import com.example.noteapplication.ui.profile.profile;

import static com.example.noteapplication.Login.AccInfo;

public class changepass extends Fragment {

    private ChangepassViewModel mViewModel;
    Button btnChange, btnReturn;
    EditText edOld_Pass, edNew_Pass, edAgain_Pass;

    public static changepass newInstance() {
        return new changepass();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.changepass_fragment, container, false);
        btnChange = (Button)view.findViewById(R.id.btnChangePass);
        btnReturn = (Button)view.findViewById(R.id.btnChangePass_Return);
        edOld_Pass = (EditText)view.findViewById(R.id.etChangePass_Current);
        edNew_Pass = (EditText)view.findViewById(R.id.etChangePass_New);
        edAgain_Pass = (EditText)view.findViewById(R.id.etChangePass_Confirm);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePass_DB changePass_db = new ChangePass_DB(changepass.this.getContext());
                String pass_old = edOld_Pass.getText().toString();
                String pass_new = edNew_Pass.getText().toString();
                String pass_new_agian = edAgain_Pass.getText().toString();
                if(pass_new.equals("") && pass_new_agian.equals("") && pass_old.equals("")) {
                    Toast.makeText(changepass.this.getContext()," Error, please enter value ",Toast.LENGTH_SHORT).show();
                }else if(pass_new_agian.equals("") && pass_new.equals("")){
                    Toast.makeText(changepass.this.getContext()," Error, please enter new password and confirm password ",Toast.LENGTH_SHORT).show();
                }else if(pass_old.equals("") && pass_new.equals("")){
                    Toast.makeText(changepass.this.getContext()," Error, please enter old password and new password ",Toast.LENGTH_SHORT).show();
                }else if(pass_old.equals("") && pass_new_agian.equals("")){
                    Toast.makeText(changepass.this.getContext()," Error, please enter old password and confirm password ",Toast.LENGTH_SHORT).show();
                }else if(pass_new.equals("")) {
                    Toast.makeText(changepass.this.getContext(), " Error, please enter new password ", Toast.LENGTH_SHORT).show();
                }else if(pass_new_agian.equals("")) {
                    Toast.makeText(changepass.this.getContext(), " Error, please enter new confirm password ", Toast.LENGTH_SHORT).show();
                }else if(pass_old.equals("")) {
                    Toast.makeText(changepass.this.getContext(), " Error, please enter old password ", Toast.LENGTH_SHORT).show();
                }else {
                    if(pass_new.equals(pass_new_agian)) {
                        ChangePassOJ changePassOJ = new ChangePassOJ(AccInfo.getId(), AccInfo.getFirstName(), AccInfo.getLastName(), AccInfo.getEmail(), pass_new);
                        boolean update = changePass_db.updatePass(changePassOJ);
                        if (update == true) {
                            Intent intent = new Intent(changepass.this.getContext(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(changepass.this.getContext(), " update password ", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(changepass.this.getContext(), " update error password ", Toast.LENGTH_SHORT).show();
                    }
                }
                /*if(pass_new.equals(pass_new_agian))
                {
                    ChangePassOJ changePassOJ = new ChangePassOJ(AccInfo.getId(),AccInfo.getFirstName(),AccInfo.getLastName(),AccInfo.getEmail(),pass_new);
                    boolean update =changePass_db.updatePass(changePassOJ);
                    if(update == true) {
                        Intent intent = new Intent(changepass.this.getContext(), MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(changepass.this.getContext(), " update password ", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(changepass.this.getContext()," update error password ",Toast.LENGTH_SHORT).show();
                }else if(pass_new.equals("") && pass_new_agian.equals("") && pass_old.equals("")){
                    Toast.makeText(changepass.this.getContext()," Error, please enter value ",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(changepass.this.getContext(),"password & confirm password are not match",Toast.LENGTH_SHORT).show();*/


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