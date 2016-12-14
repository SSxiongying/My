package com.example.xy.myapplication.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.fragment.Fragment_four;
import com.example.xy.myapplication.fragment.Fragment_one;
import com.example.xy.myapplication.fragment.Fragment_second;
import com.example.xy.myapplication.fragment.Fragment_there;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xy on 2016/11/12.
 */
public class activity_viewpager_fragment extends AppCompatActivity implements View.OnClickListener{
    //声明四个导航按钮
    Button btn_one;
    Button btn_second;
    Button btn_there;
    Button btn_four;
    //引导页
    ViewPager viewPager;
    //页面（碎片）集合
    List<Fragment> fragments;

    Fragment_one fragment_one;
    Fragment_second fragment_second;
    Fragment_there fragment_there;
    Fragment_four fragment_four;
    ImageView intabs;//覆盖层
    //屏幕宽度
    int screenWidth;
    //当前选中的项
    int currenttab = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_fragment);
        btn_one= (Button) findViewById(R.id.btn_one);
        btn_second= (Button) findViewById(R.id.btn_second);
        btn_there= (Button) findViewById(R.id.btn_there);
        btn_four= (Button) findViewById(R.id.btn_four);

        //设置监听
        btn_one.setOnClickListener(this);
        btn_second.setOnClickListener(this);
        btn_there.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        //引导页
        viewPager= (ViewPager) findViewById(R.id.myViewpager);
        fragments=new ArrayList<Fragment>();
        fragment_one=new Fragment_one();
        fragment_second=new Fragment_second();
        fragment_there=new Fragment_there();
        fragment_four=new Fragment_four();

        //将Fragment添加到集合
        fragments.add(fragment_one);
        fragments.add(fragment_second);
        fragments.add(fragment_there);
        fragments.add(fragment_four);

        //屏幕宽度
        screenWidth=getResources().getDisplayMetrics().widthPixels;

        btn_second.measure(0,0);
        intabs= (ImageView) findViewById(R.id.img_overtab);//覆盖层

        RelativeLayout.LayoutParams imageParams= new RelativeLayout.
                LayoutParams(screenWidth/4, btn_second.getMeasuredHeight());
        imageParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        intabs.setLayoutParams(imageParams);
        //设置适配器
        viewPager.setAdapter(new myFrageStatePagerAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_one:
                changeView(0);
                break;
            case R.id.btn_second:
                changeView(1);
                break;
            case R.id.btn_there:
                changeView(2);
                break;
            case R.id.btn_four:
                changeView(3);
                break;
        }
    }
    //手动设置ViewPager要显示的试图
    public void changeView(int desTab)
    {
        viewPager.setCurrentItem(desTab,true);
    }

    public class myFrageStatePagerAdapter extends FragmentStatePagerAdapter {

        public myFrageStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        /**
         * 每次更新完ViewPager的内容后，调用该接口，
         * 此处复写，主要是为了能让导航栏上的覆盖层能够动态的移动。
         * @param container
         */
                public void finishUpdate(ViewGroup container)
        {
            super.finishUpdate(container);
            //获取当前的视图是位于ViewGroup的第几个位置，用来更新对应覆盖层的位置。
            int currentItem=viewPager.getCurrentItem();
            if (currentItem==currenttab)
            {
                return;
            }
            imageMove(viewPager.getCurrentItem());
            currenttab=viewPager.getCurrentItem();
        }
    }

    /**
     * 移动覆盖层，目标tab，也就是要移动到的导航按钮的位置
     * 第一个导航按钮与i应0，第二个对应1，以此类推
     * @param moveToTab
     */
    public void imageMove(int moveToTab)
    {
        int startPosition=0;
        int movePosition=0;
        startPosition=currenttab*(screenWidth/4);
        movePosition=moveToTab*(screenWidth/4);
        //平移动画
        TranslateAnimation translateAnimation=new TranslateAnimation(startPosition,movePosition,0,0);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200);//设置动画的持续时间
        intabs.startAnimation(translateAnimation);
    }
}
