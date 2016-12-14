package com.example.xy.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.adapter.activity_nesAdapter;
import com.example.xy.myapplication.bean.NesData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xy on 2016/11/28.
 */
public class acticity_search extends AppCompatActivity {

    Context context;
    EditText editText;
    RecyclerView recyclerView;
    String url;
    Button button;
    List<NesData> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aseach_view);
        if (context == null) {
            context = this;
        }
        Log.e("ssa", "ss");
        //查找控件
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler);
        editText = (EditText) findViewById(R.id.txt_seach);
        button = (Button) findViewById(R.id.btn_seach);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("YU","UI");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("ssss1","op");
                        String NewsData=new GetSearchData().getSeachData(editText.getText().toString());
                        Log.e("ssss2",NewsData);
                        try {
                            JSONObject jsonObject=new JSONObject(NewsData);
                            //第二层：对象
                            JSONObject jsonObjectOne=new JSONObject(jsonObject.getString("showapi_res_body"));
                            Log.e("HttpRequestOne",jsonObjectOne.toString());
                            //第三层:对象
                            JSONObject jsonObjectSecond=new JSONObject(jsonObjectOne.getString("pagebean"));
                            Log.e("HttpRequestSecond",jsonObjectSecond.toString());
                            String allPages=jsonObjectSecond.get("allPages").toString();
                            Log.e("allPages: ",jsonObjectSecond.get("allPages").toString());
                            //第四层:数组
                            JSONArray jsonArray=new JSONArray(jsonObjectSecond.getString("contentlist"));
                            Log.e("jsonAplysss: ",jsonArray.toString());
                            Log.e("HttpRequestArray",jsonArray.length()+"");
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                jsonObject=new JSONObject(jsonArray.get(i).toString());
                                String title=jsonObject.getString("title");
                                Log.e("HttpRequest: ", jsonObject.getString("title"));
                                String content = jsonObject.getString("desc");
                                Log.e("HttpRequestOne", jsonObject.getString("desc"));
                                String data = jsonObject.getString("pubDate");
                                Log.e("HttpRequestSecond", jsonObject.getString("pubDate"));
                                String link = jsonObject.getString("link");
                                Log.e("jsonAply: ", jsonObject.getString("link"));
                                JSONArray jsonArrays=jsonObject.getJSONArray("imageurls");
                                for (int x=0;x<jsonArrays.length();x++)
                                {
                                    jsonObject = new JSONObject(jsonArrays.get(x).toString());
                                    url = jsonObject.get("url").toString();
                                    Log.e("jsonAply: ", jsonObject.get("url").toString());
                                }
                                NesData nesdata=new NesData(title,content,data,link,allPages,url);
                                list.add(nesdata);
                                Log.e("pages: ", list.get(i).getAllPages());
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    LinearLayoutManager manager=new LinearLayoutManager(context);
                                    recyclerView.setLayoutManager(manager);
                                    activity_nesAdapter adapter=new activity_nesAdapter(context,list);
                                    recyclerView.setAdapter(adapter);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}