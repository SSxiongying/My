package com.example.xy.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.adapter.ViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xy on 2016/11/8.
 */
public class activity_main extends AppCompatActivity implements View.OnClickListener {
    //声明控件
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    //声明集合
    GridLayoutManager gridLayoutManager;
    private List<Integer> mDatas;
    private List<String> mTxt;
    ViewAdapter adapter;
    private Button addbtn, delbtn;
    int lastPosition=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gird);
        //找到控件，设置监听
        addbtn = (Button) findViewById(R.id.addData);
        addbtn.setOnClickListener(this);
        delbtn = (Button) findViewById(R.id.delData);
        delbtn.setOnClickListener(this);
        final Handler handler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //0表示下拉刷新
                if (msg.what==0)
                {
                    //添加数据
                    mDatas.add(0,R.mipmap.cx);
                    mTxt.add(0,"张哲瀚");
                    adapter.addData(mDatas.size());
                    adapter.addData(mTxt.size());
                    //刷新adapter
                    adapter.notifyItemRangeChanged(0,mDatas.size());
                    //设置progressbar为不显示
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        };
        //加载数据
        initData();
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip);
        recyclerView = (RecyclerView) findViewById(R.id.mRecycler);
        //设置刷新颜色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color.holo_red_light,
                android.R.color.holo_orange_light,android.R.color.holo_green_light);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try {
                                handler.sendEmptyMessage(0);
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * 正在滑动
             * @param recyclerView
             * @param newState
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE &&lastPosition+1==adapter.getItemCount())
                {
                    swipeRefreshLayout.setRefreshing(true);
                    handler.sendEmptyMessageDelayed(0,3000);
                }
            }

            /**
             * 滑动结束
             * 我们去获取最后item的位置
             * @param recyclerView
             * @param dx
             * @param dy
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                /*通过recycler的manager来获取*/
                 lastPosition=gridLayoutManager.findLastVisibleItemPosition();
            }
        });
        recyclerView.setHasFixedSize(true);
        //创建网格布局：参数一：本类对象，参数二：列数
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        //声明适配器
        adapter = new ViewAdapter(activity_main.this, mDatas, mTxt);
        adapter.setMyOnClick(new ViewAdapter.MyOnClick() {
            @Override
            public void OnClick(int position) {
                Toast.makeText(activity_main.this, "您点击了:" + position + "Item", Toast.LENGTH_LONG).show();
            }

            @Override
            public void OnLongClick(int position) {
                Toast.makeText(activity_main.this, "您点击了:" + position + "Item", Toast.LENGTH_LONG).show();
            }
        });
        //创建适配器
        recyclerView.setAdapter(adapter);

        //设置item之间的间隔
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        recyclerView.addItemDecoration(new RecycleViewGridDivier(activity_main.this, GridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void initData() {
        mDatas = new ArrayList<Integer>(Arrays.asList(R.mipmap.o, R.mipmap.q, R.mipmap.r, R.mipmap.t,
                R.mipmap.t, R.mipmap.w, R.mipmap.y, R.mipmap.op, R.mipmap.cv, R.mipmap.cx, R.mipmap.gh
                , R.mipmap.ii, R.mipmap.jk, R.mipmap.ki, R.mipmap.ki, R.mipmap.kl, R.mipmap.lyf, R.mipmap.nm,
                R.mipmap.ui, R.mipmap.vb, R.mipmap.ww, R.mipmap.yy, R.mipmap.zx));
        mTxt = new ArrayList<String>(Arrays.asList("李易峰", "洋洋", "陈晓", "杨洋", "李易峰", "洋洋", "陈晓", "杨洋",
                "洋洋", "陈晓", "杨洋", "洋洋", "陈晓", "杨洋", "洋洋", "陈晓", "杨洋",
                "洋洋", "陈晓", "杨洋", "洋洋", "陈晓", "胡歌"));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.addData:
                adapter.addData(1);
                break;

            case R.id.delData:
                adapter.removeData(1);
                break;
        }
    }
}
