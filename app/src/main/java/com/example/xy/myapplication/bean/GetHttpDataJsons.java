package com.example.xy.myapplication.bean;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by xy on 2016/11/16.
 */
public class GetHttpDataJsons {

    public String getHttpDataJsons(String pubData)
    {
        try {
            pubData= URLEncoder.encode(pubData,"UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return pubData;
    }
}
