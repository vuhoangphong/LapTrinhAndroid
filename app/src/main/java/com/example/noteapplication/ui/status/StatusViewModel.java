package com.example.noteapplication.ui.status;

import android.widget.ArrayAdapter;

import androidx.lifecycle.ViewModel;

public class StatusViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private  int id;
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
    public int getID(){
        return this.id;
    }
    public int setID(int Id){
        return this.id = Id;
    }

    public StatusViewModel() {
        
    }

    public StatusViewModel(int id, String name, String dateTime) {
        this.id = id;
        this.name = name;
        this.datetime = dateTime;
    }
}
