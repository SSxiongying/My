package com.example.xy.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.adapter.ListviewAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by xy on 2016/11/13.
 */
public class activity_arrayadapter_list extends AppCompatActivity {

    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrayadapter);
        listView= (ListView) findViewById(R.id.listview_array);
        //创建集合
        ArrayList<HashMap<String,Object>> listItem=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            HashMap<String,Object> map=new HashMap<>();
            map.put("ItemImage",R.drawable.bilibili);
            map.put("ItemTitle","第" + i +"行");
            map.put("ImageText","这是第" + i + "行");
            listItem.add(map);
        }
        ListviewAdapter adapter=new ListviewAdapter(this,listItem);
        listView.setAdapter(adapter);
    }
}
