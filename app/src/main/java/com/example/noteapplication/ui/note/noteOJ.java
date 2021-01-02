package com.example.noteapplication.ui.note;

public class noteOJ {
    String name;
    String category;
    String priority;
    String status;
    String planDate;
    String createDate;

    public noteOJ(String name,String category,String priority,String status,String planDate,String createDate) {
        this.name = name;
        this.category = category;
        this.priority = priority;
        this.status = status;
        this.planDate = planDate;
        this.createDate = createDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getPlanDate() {
        return planDate;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setPlanDate(String planDate) {
        this.planDate = planDate;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
