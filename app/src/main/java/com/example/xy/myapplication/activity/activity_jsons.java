package com.example.xy.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.bean.ContentData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xy on 2016/11/16.
 */
public class activity_jsons extends AppCompatActivity {
    Context context;
    RecyclerView recyclerView;
    List<ContentData> arrayList=new ArrayList<>();
    String content;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsons);
        Log.e("op","ssss");
        //找到控件
        recyclerView= (RecyclerView) findViewById(R.id.myRecycler);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStreamReader inputStreamReader=new InputStreamReader(getResources()
                            .getAssets().open("json.txt"),"gb2312");
                    BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                    String line="";
                    String result="";
                    while ((line=bufferedReader.readLine())!=null)
                    {
                        result+=line;
                    }
                    content=result;

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //通过IO流读取文件

                //InputStream is=getResources().getAssets().open("json.txt");
                //解决乱码，转换为字节流
                //int size=is.available();
                //byte[] buff=new byte[size];
                //is.read();
//                    int temp=0;
//                    byte[] ui=new byte[is.available()];
//                    is.read(ui);
                //content=new String(ui,"UTF-8");
                //is.close();
                //content=new String(buff,"UTF-8");
                try {
                    //第一层：对象
                    JSONObject jsonobject=new JSONObject(content);
                    Log.e("onCreateio",jsonobject.toString());
                    //第二层：对象
                    if (content!=null&&content.startsWith("\ufeff"))
                    {
                        content=content.substring(1);
                    }
                    JSONObject jsonobjectone=new JSONObject("showapi_res_body");
                    Log.e("onCreateOne: ",jsonobjectone.toString());
                    //第三层：对象
                    JSONObject jsonobjectSecond=new JSONObject("showapi_res_body");
                    Log.e("onCreateSecond: ",jsonobjectSecond.toString());
                    //第四层：数组
                    JSONArray jsonarray=new JSONArray("contentlist");
                    Log.e("onCreateThere: ",jsonarray.toString());
                    //这里拿到的是20个内容的列表
                    for(int i=0;i<jsonarray.length();i++)
                    {
                        jsonobject=new JSONObject(jsonarray.get(i).toString());
                        Log.e("activity_jsons", "jsonobject.get(title):" + jsonobject.get("title") );
                        String data=jsonobject.getString("pubDate").toString();
                        String title=jsonobject.getString("title").toString();
                        String channelName=jsonobject.getString("channelName").toString();
                        String desc=jsonobject.getString("desc").toString();
                        //创建一个对象
                        ContentData contentData=new ContentData(data,title,channelName,desc);
                        //将数据加入集合
                        arrayList.add(contentData);
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            LinearLayoutManager manager=new LinearLayoutManager(context);
                            recyclerView.setLayoutManager(manager);
                            activity_content_adapter adapter=new activity_content_adapter(context,arrayList);
                            recyclerView.setAdapter(adapter);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("activity_weather", "e:" + e);
                }
            }
        }).start();
    }
}
