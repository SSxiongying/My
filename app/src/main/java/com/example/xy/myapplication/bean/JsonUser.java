package com.example.xy.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by xy on 2016/11/14.
 */
public class JsonUser {
    private int resultCode;
    ArrayList<User> userList;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }
}
