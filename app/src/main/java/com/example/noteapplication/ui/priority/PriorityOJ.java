package com.example.noteapplication.ui.priority;

import androidx.annotation.NonNull;

public class PriorityOJ {
    String name;
    String createDate;
    public String getName() {
        return name;
    }

    public String getCreatedate() {
        return createDate;
    }

    public void setCreatedate(String createdate) {
        createDate = createdate;
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
