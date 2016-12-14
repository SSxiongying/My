package com.example.xy.myapplication.bean;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by xy on 2016/11/14.
 */
public class GetHttpData {

    public String getHttpData(String cityname)
    {
        Log.e("sss1",cityname);
        Log.e("sss10",cityname);
        try {
            cityname=URLEncoder.encode(cityname,"UTF-8");
            URL uri=new URL("http://api.map.baidu.com/telematics/v3/weather?location="+cityname+"&output=json&ak=FiiilmB9ouyERsfzrNVKKx58612ikwW4");
            HttpURLConnection httpURLConnection= (HttpURLConnection) uri.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            StringBuffer stringBuffer=new StringBuffer();
            //乱码的原因是我们使用的字节流获取数据
            // 所以转换成字符流  但最好是缓冲流
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            int temp=0;
            while ((temp=bufferedReader.read())!=-1)
            {
                stringBuffer.append((char)temp);
            }
            return new String(stringBuffer.toString().getBytes(),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("GetHttpData", "e:" + e);
        }
        return cityname;
    }
}
