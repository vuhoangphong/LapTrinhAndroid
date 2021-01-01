package com.example.noteapplication.ui.category;

import androidx.annotation.NonNull;

public class CategoryOJ {
    String name;
    String Createdate;

    public String getName() {
        return name;
    }

    public String getCreatedate() {
        return Createdate;
    }

    public void setCreatedate(String createdate) {
        Createdate = createdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return (getName());
    }
}
