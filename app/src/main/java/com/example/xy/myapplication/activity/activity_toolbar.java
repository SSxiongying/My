package com.example.xy.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.example.xy.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xy on 2016/11/9.
 */
public class activity_toolbar extends AppCompatActivity {
    //声明侧滑菜单
    DrawerLayout drawerLayout;
    ListView myListView;
    ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    List<String> Datas;//声明集合

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        drawerLayout= (DrawerLayout) findViewById(R.id.my_drawer);
        initData();
        initView();
        //找到控件ListView
        myListView= (ListView) findViewById(R.id.myListView);
        //设置适配器
        myListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,Datas));
    }

    private void initData() {
        Datas=new ArrayList<String>(Arrays.asList("搜狐","网易","百度","掌阅"));
    }

    private void initView() {
        toolbar= (Toolbar) findViewById(R.id.myToolbar);

        //一级标题
        toolbar.setTitle("新闻管理系统");
        //一级标题的颜色
        toolbar.setTitleTextColor(Color.YELLOW);
        //二级标题
        toolbar.setSubtitle("一级界面");
        toolbar.setSubtitleTextColor(Color.GREEN); //二级标题的颜色
        toolbar.setNavigationIcon(R.mipmap.mt);
        setSupportActionBar(toolbar);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.one_menu:
                        Toast.makeText(activity_toolbar.this,"图标排序",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.two_menu:
                        Toast.makeText(activity_toolbar.this,"隐藏程序",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.three_menu:
                        Toast.makeText(activity_toolbar.this,"智能分类",Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }
}
