package com.example.xy.myapplication.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.adapter.MyReclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xy on 2016/11/10.
 */
public class activity_myRecy extends AppCompatActivity{
    TextView tv;
    SwipeRefreshLayout swipeRefreshlayout;
    Toolbar toolbars;
    DrawerLayout drawerLayouts;
    ActionBarDrawerToggle toggles;
    RecyclerView recyclerView;
    MyReclerAdapter adapter;
    List<Integer> mData;
    List<String> mTxts;
    WebView webView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        webView= (WebView) findViewById(R.id.web);
        webView.loadUrl("http://www.baidu.com");
        tv= (TextView) findViewById(R.id.textViews);
        swipeRefreshlayout= (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        //设置刷新时动画的颜色，可以设置四个
        swipeRefreshlayout.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color.holo_red_light,
                android.R.color.holo_orange_light,android.R.color.holo_green_light);
        //刷新
        swipeRefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                tv.setText("正在刷新");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText("刷新完成");
                        swipeRefreshlayout.setRefreshing(false);
                    }
                },6000);   //设置延迟
            }
        });
        initData();
        initView();
    }

    private void initData() {
         mData=new ArrayList<Integer>(Arrays.asList(R.mipmap.t,R.mipmap.ui,R.mipmap.q,R.mipmap.vb
                                        ,R.mipmap.kl,R.mipmap.nm,R.mipmap.cv,R.mipmap.afe));
        mTxts=new ArrayList<String>(Arrays.asList("李易峰","汪峰","鹿晗","胡歌"
                ,"王伟","王五","何炅","主页"));
    }
    private void initView() {

        final LinearLayout linearLayout;
        linearLayout= (LinearLayout) findViewById(R.id.main_drawer);
        //找到控件
        drawerLayouts= (DrawerLayout) findViewById(R.id.my_drawerss);
        toolbars= (Toolbar) findViewById(R.id.mToolbars);
        //设置以及标题
        toolbars.setTitle("新闻管理系统");
        toolbars.setTitleTextColor(Color.YELLOW);
        //设置二级标题
        toolbars.setSubtitle("一级页面");
        //设置图标
        toolbars.setNavigationIcon(R.mipmap.mt);
        setSupportActionBar(toolbars);
        toolbars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        toggles=new ActionBarDrawerToggle(this,drawerLayouts,toolbars,R.string.open,R.string.close);
        drawerLayouts.setDrawerListener(toggles);

        //toolBar的点击事件
        toolbars.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.one_menus:
                        Toast.makeText(activity_myRecy.this, "图标排序", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.second_menus:
                        Toast.makeText(activity_myRecy.this,"智能分类",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.there_menus:
                        Toast.makeText(activity_myRecy.this,"隐藏图标",Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });
        //查找控件
        recyclerView= (RecyclerView) findViewById(R.id.recycler);
        //创建网格布局
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        //添加分隔线
        recyclerView.addItemDecoration(new RecycleViewGridDivier(activity_myRecy.this,GridLayoutManager.VERTICAL));
        //添加动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //适配器
        adapter=new MyReclerAdapter(this,mData,mTxts);
        //点击事件
        adapter.setMyOnClicks(new MyReclerAdapter.MyOnClick() {
            @Override
            public void onClick(int position) {
                linearLayout.setBackgroundResource(mData.get(position));
            }
        });

        //设置适配器
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_menu_second,menu);
        return true;
    }
}
