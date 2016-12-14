package com.example.xy.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xy.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by xy on 2016/11/13.
 */
public class ListviewAdapter extends BaseAdapter {
    //创建集合
    ArrayList<HashMap<String,Object>> item=new ArrayList<>();
    LayoutInflater inflater;
    Context context;

    public ListviewAdapter(Context context,ArrayList<HashMap<String, Object>> item) {
        this.item = item;
        this.inflater=LayoutInflater.from(context);
    }

    //获取集合的长度
    @Override
    public int getCount() {
        return item.size();
    }


    @Override
    public Object getItem(int i) {
        return item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        ImageView imageView;
        TextView txtTitle;
        TextView txts;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null) {
            view = inflater.inflate(R.layout.activity_adapter_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.ItemImage);
            holder.txtTitle = (TextView) view.findViewById(R.id.ItemTitle);
            holder.txts = (TextView) view.findViewById(R.id.ItemText);
            view.setTag(holder);
        }else
        {
            holder= (ViewHolder) view.getTag();
        }
        holder.imageView.setImageResource(R.drawable.bilibili);
        holder.txtTitle.setText(item.get(i).get("ItemTitle").toString());
        holder.txts.setText(item.get(i).get("ImageText").toString());
        return view;
    }
}
