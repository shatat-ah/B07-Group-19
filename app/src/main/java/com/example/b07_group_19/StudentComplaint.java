package com.example.b07_group_19;

public class StudentComplaint {
    private String title;
    private String description;

    public StudentComplaint(String t, String d){
        title = t;
        description = d;
    }
    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setDescription(String newDescription){
        this.description = newDescription;
    }
}
