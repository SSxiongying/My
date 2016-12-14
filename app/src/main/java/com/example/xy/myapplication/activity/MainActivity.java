package com.example.xy.myapplication.activity;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.adapter.RecyclerViewAdapter;
import com.example.xy.myapplication.fragment.Fragment_camero;
import com.example.xy.myapplication.fragment.Fragment_fashion;
import com.example.xy.myapplication.fragment.Fragment_file;
import com.example.xy.myapplication.fragment.Fragment_huiyuan;
import com.example.xy.myapplication.fragment.Fragment_manager;
import com.example.xy.myapplication.fragment.Fragment_money;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    SwipeRefreshLayout swipeRefreshlayout;
    Toolbar toolbarsss;
    DrawerLayout myDrawerlayout;
    ActionBarDrawerToggle togglesss;
    private RecyclerView myrecyclerView;
    private RecyclerViewAdapter adapter;//声明适配器
    private List<Integer> mDatas;//声明集合
    private List<String> mTexts;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //0表示下拉刷新
            if (msg.what==0)
            {

            }
        }
    };

    //创建Fragment对象
    Fragment_huiyuan fragment_huiyuan=new Fragment_huiyuan();
    Fragment_money fragment_money=new Fragment_money();
    Fragment_fashion fragment_fashion=new Fragment_fashion();
    Fragment_manager fragment_manager=new Fragment_manager();
    Fragment_camero fragment_camero=new Fragment_camero();
    Fragment_file fragment_file=new Fragment_file();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();
        transaction.add(R.id.frame,fragment_huiyuan);

        transaction.commit();
        //下拉刷新
        tv= (TextView) findViewById(R.id.textViews);
        swipeRefreshlayout= (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        //设置刷新时动画的颜色，可以设置四个
        swipeRefreshlayout.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color.holo_red_light,
                android.R.color.holo_orange_light,android.R.color.holo_green_light);

        //刷新
        // 这句话是为了，第一次进入页面的时候显示加载进度条
//        swipeRefreshlayout.setProgressViewOffset(false,0, (int) TypedValue.
//                applyDimension(TypedValue.COMPLEX_UNIT_DIP,24,getResources().getDisplayMetrics()));
//        swipeRefreshlayout.setOnRefreshListener(new MyfreshListener());
//        myrecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
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

        //加载数据
        initData();
        initView();
    }

    private void initView() {
        LinearLayout linearLayout;
        linearLayout= (LinearLayout) findViewById(R.id.main_drawers);
        myDrawerlayout= (DrawerLayout) findViewById(R.id.my_drawer_seconds);
        toolbarsss= (Toolbar) findViewById(R.id.mtoolbars_second);
        toolbarsss.setTitle("新闻管理系统");
        toolbarsss.setTitleTextColor(Color.YELLOW);
        toolbarsss.setSubtitle("一级页面");//设置二级标题
        toolbarsss.setNavigationIcon(R.mipmap.mt);//设置图标
        setSupportActionBar(toolbarsss);
        togglesss=new ActionBarDrawerToggle(this,myDrawerlayout,toolbarsss,R.string.open,R.string.close);
        myDrawerlayout.setDrawerListener(togglesss);
        //toolBar的点击事件
        toolbarsss.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menu_use_one:
                        Toast.makeText(MainActivity.this, "图标排序", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_use_second:
                        Toast.makeText(MainActivity.this, "智能分类", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_use_there:
                        Toast.makeText(MainActivity.this, "隐藏图标", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        //找到控件
        myrecyclerView= (RecyclerView) findViewById(R.id.recyclerss);
        myrecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        //设置布局管理器
        LinearLayoutManager manager=new LinearLayoutManager(this);
        myrecyclerView.setLayoutManager(manager);
        //创建适配器
        adapter=new RecyclerViewAdapter(this,mDatas,mTexts);
        //Fragment动态使用
        adapter.setMyOnClick(new RecyclerViewAdapter.MyOnClick() {
            @Override
            public void OnClick(int position) {
                //linearLayout.setBackgroundResource(mDatas.get(position));
                FragmentManager fm=getSupportFragmentManager();
                FragmentTransaction transaction=fm.beginTransaction();
                switch (mTexts.get(position))
                {
                    case "了解会员特权":
                        switch_content(fragment_huiyuan,fragment_huiyuan);
                        transaction.hide(fragment_money);
                        transaction.hide(fragment_manager);
                        transaction.hide(fragment_file);
                        transaction.hide(fragment_camero);
                        transaction.hide(fragment_fashion);
                        break;
                    case "QQ钱包":
                        switch_content(fragment_huiyuan,fragment_money);
                        transaction.hide(fragment_huiyuan);
                        transaction.hide(fragment_manager);
                        transaction.hide(fragment_file);
                        transaction.hide(fragment_camero);
                        transaction.hide(fragment_fashion);
                        break;
                    case "个性装扮":
                        switch_content(fragment_huiyuan,fragment_fashion);
                        transaction.hide(fragment_huiyuan);
                        transaction.hide(fragment_manager);
                        transaction.hide(fragment_file);
                        transaction.hide(fragment_camero);
                        transaction.hide(fragment_money);
                        break;
                    case "我的收藏":
                        switch_content(fragment_huiyuan,fragment_manager);
                        transaction.hide(fragment_money);
                        transaction.hide(fragment_huiyuan);
                        transaction.hide(fragment_file);
                        transaction.hide(fragment_camero);
                        transaction.hide(fragment_fashion);
                        break;
                    case "我的相册":
                        switch_content(fragment_huiyuan,fragment_camero);
                        transaction.hide(fragment_manager);
                        transaction.hide(fragment_money);
                        transaction.hide(fragment_huiyuan);
                        transaction.hide(fragment_file);
                        transaction.hide(fragment_fashion);
                        break;
                    case "我的文件":
                        switch_content(fragment_huiyuan,fragment_file);
                        transaction.hide(fragment_camero);
                        transaction.hide(fragment_manager);
                        transaction.hide(fragment_money);
                        transaction.hide(fragment_huiyuan);
                        transaction.hide(fragment_fashion);
                        break;
                }
                transaction.commit();
                myDrawerlayout.closeDrawers();//关闭DrawerLayout

            }
        });

        //设置适配器
        myrecyclerView.setAdapter(adapter);
        //添加分隔线
        myrecyclerView.addItemDecoration(new RecycleViewDivider(MainActivity.this,LinearLayoutManager.VERTICAL));
    }

    public void switch_content(android.support.v4.app.Fragment from, android.support.v4.app.Fragment to)
    {
        if (fragment_huiyuan!=null)
        {
            FragmentManager fm=getSupportFragmentManager();
            FragmentTransaction transaction=fm.beginTransaction();
            if (!to.isAdded())
            {
                transaction.hide(from).add(R.id.frame,to).commit();
            }else
            {
                transaction.hide(from).show(to).commit();
            }
        }
    }
    //加载数据
    private void initData()
    {
        mDatas=new ArrayList<Integer>(Arrays.asList(R.drawable.douban_daily,R.drawable.douban_fm,R.drawable.doubanbook,R.drawable.duitang
                ,R.drawable.duomi,R.drawable.draw2));
        mTexts=new ArrayList<String>(Arrays.asList("了解会员特权","QQ钱包","个性装扮","我的收藏","我的相册","我的文件"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_menu_two,menu);
        return true;
    }
 public class MyfreshListener implements SwipeRefreshLayout.OnRefreshListener{

     @Override
     public void onRefresh() {

     }
 }
}
