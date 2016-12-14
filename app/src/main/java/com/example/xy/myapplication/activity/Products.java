package com.example.xy.myapplication.activity;

/**
 * Created by xy on 2016/11/8.
 */
public class Products {
    private int img;
    private String title;

    public Products(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
