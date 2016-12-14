package com.example.xy.myapplication.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.activity.MainActivity;

import java.util.List;

/**
 * Created by xy on 2016/11/8.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private LayoutInflater inflater;
    private List<Integer> mDatas;
    private List<String> mTexts;
    private MyOnClick mMyOnClickss;

    public RecyclerViewAdapter(Context context, List<Integer> datas,List<String> texts) {
        mDatas = datas;
        mTexts=texts;
        inflater=LayoutInflater.from(context);
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView mImage;
        TextView mTxt;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    //回调
    public interface MyOnClick{
        void OnClick(int position);
    }

    public void setMyOnClick(MyOnClick mMyOnClickss)
    {
        this.mMyOnClickss=mMyOnClickss;
    }
    //获取总的条目数
    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    //创建ViewHolder
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局
        View view=inflater.inflate(R.layout.activity_main_item,null);
        //创建ViewHolder
        ViewHolder viewHolder=new ViewHolder(view);
        //找到ID
        viewHolder.mImage= (ImageView) view.findViewById(R.id.mImage);
        viewHolder.mTxt= (TextView) view.findViewById(R.id.mTxt);
        return viewHolder;
    }

    /**
     * 设置值
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mImage.setImageResource(mDatas.get(position));
        holder.mTxt.setText(mTexts.get(position));
        if (mMyOnClickss!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMyOnClickss.OnClick(position);
                }
            });
        }
    }


}
