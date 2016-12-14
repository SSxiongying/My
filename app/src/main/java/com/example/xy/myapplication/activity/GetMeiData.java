package com.example.xy.myapplication.activity;

import android.util.Log;

import com.example.xy.myapplication.bean.Contance;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by xy on 2016/11/29.
 */
public class GetMeiData {
    StringBuffer stringBuffer=new StringBuffer();
    public String getMeiData(String name)
    {
        Log.e("sss1",name);
        Log.e("sss10",name);
        try {
            name= URLEncoder.encode(name,"UTF-8");
            Log.e("sss11",name);
            URL uri=new URL("http://apis.baidu.com/tngou/cook/name?name="+name);
            Log.e("GetMeiData", uri.toString());
            HttpURLConnection httpURLConnection= (HttpURLConnection) uri.openConnection();
            httpURLConnection.setRequestMethod("GET");//设置请求方式
            Log.e("sss15",name);
            httpURLConnection.setRequestProperty("apikey", Contance.BaiduApi);//设置参数头
            Log.e("sss13",name);
            InputStream inputStream=httpURLConnection.getInputStream();
            Log.e("sss14",name);
            Log.e("sss16",name);
            //乱码的原因是我们使用的字节流获取数据
            // 所以转换成字符流  但最好是缓冲流
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            int temp=0;
            while ((temp=bufferedReader.read())!=-1)
            {
                stringBuffer.append((char)temp);
            }
            Log.e("sss17",name);
//            return new String(stringBuffer.toString().getBytes(),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("GetHttpData", "e:" + e);
        }
        Log.e("GetMeiData", stringBuffer.toString());
        return stringBuffer.toString();
    }
}
