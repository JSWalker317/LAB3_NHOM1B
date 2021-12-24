package com.example.Lab3_Nhom1B.model;

import java.io.Serializable;

public class Note implements Serializable {
    private int id;
    private String title;
    private String description;
    private String createDate;

    public Note(String title, String description, String createDate, int id) {
        this.title = title;
        this.description = description;
        this.createDate = createDate;
        this.id = id;
    }
    public Note(String title, String description, String createDate) {
        this.title = title;
        this.description = description;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
