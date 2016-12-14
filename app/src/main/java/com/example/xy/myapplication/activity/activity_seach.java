package com.example.xy.myapplication.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.adapter.Adapter_seach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by xy on 2016/11/23.
 */
public class activity_seach extends AppCompatActivity implements SearchView.OnQueryTextListener{

    SearchView searchView;
    ListView listView;
    List<HashMap<String ,String>> list;
    //private final String[] mStrings = { "aaaaa", "bbbbbb", "cccccc", "ddddddd" };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seachview);
        initView();
    }

    private void initView() {
        listView= (ListView) findViewById(R.id.myListViewsss);
        list=new ArrayList<>();
        HashMap<String,String> map=new HashMap<>();
        map.put("shui","苹果");
        map.put("shui","哈密瓜");
        map.put("shui","香蕉");
        map.put("shui","橘子");
        map.put("shui","梨子");
        map.put("shui","柿子");
        map.put("shui","西瓜");
        list.add(map);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(activity_seach.this,android.R.layout.simple_list_item_1,list);
        Adapter_seach adapter_seach=new Adapter_seach(activity_seach.this,list);
        listView.setAdapter(adapter_seach);
        //设置listView可以被过虑
        listView.setTextFilterEnabled(true);
        //找到控件
        searchView= (SearchView) findViewById(R.id.seachview);
        // 设置该SearchView默认是否自动缩小为图标
        searchView.setIconifiedByDefault(false);
        // 为该SearchView组件设置事件监听器
        searchView.setOnQueryTextListener(this);
        // 设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        // 设置该SearchView内默认显示的提示文本
        searchView.setQueryHint("查找");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(this, "textChange---", Toast.LENGTH_SHORT).show();
        if (TextUtils.isEmpty(query))
        {
            listView.clearTextFilter();
        }else {
            listView.setFilterText(query);
        }

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        // 实际应用中应该在该方法内执行实际查询
        // 此处仅使用Toast显示用户输入的查询内容
       Toast.makeText(this, "您的选择是:" + newText, Toast.LENGTH_SHORT).show();
        return false;
    }
}
