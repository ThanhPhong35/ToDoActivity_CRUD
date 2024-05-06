package com.example.model;

import java.io.Serializable;

public class ThongTin implements Serializable {

    private String title;
    private String date;
    private String desc;

    public ThongTin() {
    }

    public ThongTin(String title, String date, String desc) {
        this.title = title;
        this.date = date;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
