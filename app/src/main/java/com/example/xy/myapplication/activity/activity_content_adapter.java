package com.example.xy.myapplication.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.bean.ContentData;

import java.util.List;

/**
 * Created by xy on 2016/11/16.
 */
public class activity_content_adapter extends RecyclerView.Adapter<activity_content_adapter.ViewHolder> {
    private LayoutInflater inflater;
    List<ContentData> list;//声明集合
    Context context;

    public activity_content_adapter(Context context, List<ContentData> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textdata;
        TextView title;
        TextView channelName;
        TextView desc;

        public ViewHolder(View itemView) {
            super(itemView);
            //加载控件
            textdata= (TextView) itemView.findViewById(R.id.dada_content);
            title= (TextView) itemView.findViewById(R.id.titlesss);
            channelName= (TextView) itemView.findViewById(R.id.channelName);
            desc= (TextView) itemView.findViewById(R.id.desc);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载布局
        View view=inflater.inflate(R.layout.activity_content_jsonitem,parent,false);
        //创建ViewHolder
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //设置控件的值
        holder.textdata.setText(list.get(position).getData());
        holder.title.setText(list.get(position).getTitle());
        holder.channelName.setText(list.get(position).getChannelName());
        holder.desc.setText(list.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
