package com.example.xy.myapplication.adapter;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xy.myapplication.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by xy on 2016/11/10.
 */
public class MyReclerAdapter extends RecyclerView.Adapter<MyReclerAdapter.ViewHolder>{

    List<Integer> mData;
    List<String> mText;
    LayoutInflater inflater;
    private MyOnClick myOnClicks;

    public MyReclerAdapter(Context context,List<Integer> mData, List<String> mText) {
        this.mData = mData;
        this.mText=mText;
        inflater=LayoutInflater.from(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imags_second;
        TextView text_second;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    //创建回调
    public interface MyOnClick {
        void onClick(int position);
    }

    public void  setMyOnClicks(MyOnClick myOnClicks)
    {
        this.myOnClicks=myOnClicks;
    }

    //查找控件
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_reycleview_item,null);
        ViewHolder holder=new ViewHolder(view);
        holder.imags_second= (ImageView) view.findViewById(R.id.image_second);
        holder.text_second= (TextView) view.findViewById(R.id.mTxts_second);
        return holder;
    }

    //设置内容
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imags_second.setImageResource(mData.get(position));
        holder.text_second.setText(mText.get(position));
        if (myOnClicks!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myOnClicks.onClick(position);//点击事件
                }
            });
        }
    }

    //获取数据源的长度
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
