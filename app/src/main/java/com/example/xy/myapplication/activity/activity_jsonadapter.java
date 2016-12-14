package com.example.xy.myapplication.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.bean.WeatherData;

import java.util.List;

/**
 * Created by xy on 2016/11/14.
 */
public class activity_jsonadapter extends RecyclerView.Adapter<activity_jsonadapter.Viewholder> {

    private LayoutInflater inflater;
    List<WeatherData> arrayList;
    Context context;

    public activity_jsonadapter(Context context,List<WeatherData> arrayList) {
        this.context=context;
        this.arrayList = arrayList;
        inflater=LayoutInflater.from(context);
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        //加载控件
        TextView textcity;
        TextView pm;
        TextView data;
        TextView weather;
        TextView wind;
        TextView temperature;

        public Viewholder(View itemView) {
            super(itemView);
            textcity= (TextView) itemView.findViewById(R.id.txt_city);
            pm= (TextView) itemView.findViewById(R.id.txt_pm);
            //去布局文件 添加控件 再这里绑定
            data= (TextView) itemView.findViewById(R.id.data);
            weather= (TextView) itemView.findViewById(R.id.weather);
            wind= (TextView) itemView.findViewById(R.id.wind);
            temperature= (TextView) itemView.findViewById(R.id.temperature);
        }
    }
    @Override
    public activity_jsonadapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=inflater.inflate(R.layout.activity_weather_item,parent,false);
        //创建ViewHolder
        Viewholder viewholder=new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        Log.e("activity_jsonadapter", arrayList.get(position).getCurrentCity());
        Log.e("activity_jsonadapter", arrayList.get(position).getPm25());
        holder.textcity.setText(arrayList.get(position).getCurrentCity());
        holder.pm.setText(arrayList.get(position).getPm25());
        //再这里设置值
        holder.data.setText(arrayList.get(position).getDate());
        holder.weather.setText(arrayList.get(position).getWeather());
        holder.wind.setText(arrayList.get(position).getWind());
        holder.temperature.setText(arrayList.get(position).getTemperature());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
