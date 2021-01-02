package com.example.noteapplication.ui.note;

import androidx.lifecycle.ViewModel;

public class NoteViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private String name="";
    private String category="";
    private String Priority="";
    private String Status="";
    private String Plandate="";
    private String CreatedDate="";
    public String getName(){
        return this.name;
    }
    public String setName(String name){
        return this.name = name;
    }
    public String getCategory(){
        return this.category;
    }
    public String setPriority(String priority){
        return this.Priority = priority;
    }
    public String getPriority(){
        return this.category;
    }
    public String setCategory(String category){ return this.category = category; }
    public String getCreatedate(){
        return this.CreatedDate;
    }
    public String setCreatedate(String datetime){
        return this.CreatedDate = datetime;
    }
}