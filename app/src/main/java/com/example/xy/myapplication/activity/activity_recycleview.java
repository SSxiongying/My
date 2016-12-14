package com.example.xy.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.xy.myapplication.R;

/**
 * Created by xy on 2016/11/10.
 */
public class activity_recycleview extends AppCompatActivity {

    private Toolbar toolbarss;
    DrawerLayout drawerLayoutss;
    ActionBarDrawerToggle toggless;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbarsecond);
        drawerLayoutss= (DrawerLayout) findViewById(R.id.myDrawerLayout);
        toolbarss= (Toolbar) findViewById(R.id.toobar_second);
        toolbarss.setTitle("新闻管理系统");
        toolbarss.setTitleTextColor(Color.YELLOW);
        toolbarss.setSubtitle("一级页面");
        toolbarss.setSubtitleTextColor(Color.BLUE);
        toolbarss.setNavigationIcon(R.mipmap.mt);
        setSupportActionBar(toolbarss);
        toggless=new ActionBarDrawerToggle(this,drawerLayoutss,toolbarss,R.string.open,R.string.close);
        drawerLayoutss.setDrawerListener(toggless);
        //设置菜单按钮的点击事件
        toolbarss.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menu_one:
                        Toast.makeText(activity_recycleview.this,"图标排序",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_second:
                        Toast.makeText(activity_recycleview.this,"隐藏程序",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_there:
                        Toast.makeText(activity_recycleview.this,"新建文件夹",Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_menu_there,menu);
        return true;
    }
}
