package com.example.noteapplication.ui.status;

import android.widget.ArrayAdapter;

import androidx.lifecycle.ViewModel;

public class StatusViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private String name="";
    private String datetime="";
    public String getName(){
        return this.name;
    }
    public String setName(String name){
        return this.name = name;
    }
    public String getDatetime(){
        return this.datetime;
    }
    public String setDatetime(String datetime){
        return this.datetime = datetime;
    }

}
