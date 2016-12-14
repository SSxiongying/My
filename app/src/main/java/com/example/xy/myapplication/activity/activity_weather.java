package com.example.xy.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.bean.GetHttpData;
import com.example.xy.myapplication.bean.WeatherData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xy on 2016/11/14.
 */
public class activity_weather extends AppCompatActivity {
    Context context;
    RecyclerView recyclerView;
    EditText editWeather;
    Button btnQurry;

    List<WeatherData> arrayList=new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        if(context==null){
            context=this;
        }
        Log.e("sss","sss");
        recyclerView= (RecyclerView) findViewById(R.id.Review_weather);
        editWeather= (EditText) findViewById(R.id.edit_weather);
        btnQurry= (Button) findViewById(R.id.btn_query);
        btnQurry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("YU","UI");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("ssss1","op");
                        String weatherData=new GetHttpData().getHttpData(editWeather.getText().toString());
                        Log.e("ssss2",weatherData);
                        try {
                            JSONObject jsonObject=new JSONObject(weatherData);
                            Log.e("sss3",jsonObject.toString());
                            JSONArray jsonArray=new JSONArray(jsonObject.getString("results"));
                            Log.e("array4",jsonArray.toString());
                            jsonObject=new JSONObject(jsonArray.get(0).toString());
                            Log.e("run: ",jsonArray.get(0).toString());
                            String city=jsonObject.get("currentCity").toString();
                            String pm25=jsonObject.get("pm25").toString();
                            Log.e("array5",jsonObject.toString());
                            jsonArray=new JSONArray(jsonObject.getString("weather_data"));
                            Log.e("array6",jsonArray.toString());
                            Log.e("activity_weather7", "jsonArray.length():" + jsonArray.length());
                            //这里拿到是个四天天气的数组 我们需要由东西装它
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                jsonObject=new JSONObject(jsonArray.get(i).toString());
                                Log.e("activity_weather", "jsonObject.get(weather):" + jsonObject.get("weather"));
                                 String date=jsonObject.get("date").toString();
                                 String weather=jsonObject.get("weather").toString();
                                 String wind=jsonObject.get("wind").toString();
                                 String temperature=jsonObject.get("temperature").toString();
                                WeatherData weatherDa=new WeatherData(city,pm25,date,weather,wind,temperature);
                                arrayList.add(weatherDa);
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
                                    recyclerView.setLayoutManager(linearLayoutManager);
                                    activity_jsonadapter activity_jsonadapter=new activity_jsonadapter(context,arrayList);
                                    recyclerView.setAdapter(activity_jsonadapter);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("activity_weather", "e:" + e);
                        }
                    }
                }).start();
            }
        });
    }
}
