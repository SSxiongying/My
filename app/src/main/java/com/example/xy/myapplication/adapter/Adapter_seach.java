package com.example.xy.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xy.myapplication.R;

import java.util.HashMap;
import java.util.List;

import static com.example.xy.myapplication.R.layout.activity_adapter_item;
import static com.example.xy.myapplication.R.layout.activity_seach_item;

/**
 * Created by xy on 2016/11/26.
 */
public class Adapter_seach extends BaseAdapter {

    Context context;
    List<HashMap<String,String>> list;
    LayoutInflater inflater;

    public Adapter_seach(Context context, List<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    public class ViewHolder{
        TextView textView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null)
        {
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.activity_seach_item,null);
            holder.textView= (TextView) view.findViewById(R.id.text_apple);
            view.setTag(holder);
        }else
        {
            holder= (ViewHolder) view.getTag();
        }
        return view;
    }
}
