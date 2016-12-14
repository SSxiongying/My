package com.example.xy.myapplication.bean;

/**
 * Created by xy on 2016/11/16.
 */
public class ContentData {
    private String data;
    private String title;
    private String channelName;
    private String desc;

    public ContentData(String data, String title, String channelName, String desc) {
        this.data = data;
        this.title = title;
        this.channelName = channelName;
        this.desc = desc;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
