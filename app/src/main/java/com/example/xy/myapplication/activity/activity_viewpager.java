package com.example.xy.myapplication.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.adapter.ViewAdapter;

import java.util.ArrayList;

/**
 * Created by xy on 2016/11/12.
 */
public class activity_viewpager extends AppCompatActivity {

    ViewPager mytab_pager;//导航页
    PagerTabStrip mytab;//声明标签页
    //创建视图集合
    ArrayList<View> viewContaener=new ArrayList<>();
    //创建标题集合
    ArrayList<String> titleContanet=new ArrayList<>();

    public String TAG = "tag";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        //找到控件
        mytab_pager= (ViewPager) findViewById(R.id.my_sexcond_viewpager);
        mytab= (PagerTabStrip) findViewById(R.id.pager_tab);
        //取消tab下面的长横线
        mytab.setDrawFullUnderline(false);
        //设置tab的背景色
        mytab.setBackgroundColor(this.getResources().getColor(R.color.lightpink));
        //设置当前tab页签的下划线颜色
        mytab.setTabIndicatorColor(this.getResources().getColor(R.color.red));
        mytab.setTextSpacing(200);
        //加载布局
        View view_one= LayoutInflater.from(this).inflate(R.layout.activity_view_tabone,null);
        View view_secone=LayoutInflater.from(this).inflate(R.layout.activity_view_tabsecond,null);
        View view_there=LayoutInflater.from(this).inflate(R.layout.activity_view_tabthere,null);
        View view_four=LayoutInflater.from(this).inflate(R.layout.activity_view_tabfour,null);
        //开始添加view
        viewContaener.add(view_one);
        viewContaener.add(view_secone);
        viewContaener.add(view_there);
        viewContaener.add(view_four);
        //添加标签内容
        titleContanet.add("网易新闻");
        titleContanet.add("网易体育");
        titleContanet.add("网易财经");
        titleContanet.add("网易女人");
        //设置适配器
        mytab_pager.setAdapter(new PagerAdapter() {
            //删除页卡
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                ((ViewPager)container).removeView(viewContaener.get(position));
            }
            //返回页卡的数量
            @Override
            public int getCount() {
                return viewContaener.size();
            }
            //每次滑动的时候生成的组件
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                //这个方法用来实例化页卡
                ((ViewPager) container).addView(viewContaener.get(position));//添加页卡
                return viewContaener.get(position);
            }
            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }
            //获取每页的标题
            @Override
            public CharSequence getPageTitle(int position) {
                return titleContanet.get(position);
            }
        });
        mytab_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
                Log.d(TAG, "--------changed:" + arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.d(TAG, "-------scrolled arg0:" + arg0);
                Log.d(TAG, "-------scrolled arg1:" + arg1);
                Log.d(TAG, "-------scrolled arg2:" + arg2);
            }

            @Override
            public void onPageSelected(int arg0) {
                Log.d(TAG, "------selected:" + arg0);
            }
        });
    }
}
