package com.example.xy.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xy.myapplication.R;
import com.example.xy.myapplication.bean.NesData;

import java.util.List;

/**
 * Created by xy on 2016/11/28.
 */
public class activity_nesAdapter extends RecyclerView.Adapter<activity_nesAdapter.ViewHolder> {

    private LayoutInflater inflater;
    List<NesData> arrayList;
    Context context;

    public activity_nesAdapter(Context context, List<NesData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        inflater=LayoutInflater.from(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titles;
        TextView content;
        TextView data;
        ImageView imags;
        public ViewHolder(View itemView) {
            super(itemView);
            titles= (TextView) itemView.findViewById(R.id.titless_test);
            content= (TextView) itemView.findViewById(R.id.content_test);
            data= (TextView) itemView.findViewById(R.id.timess_test);
            imags= (ImageView) itemView.findViewById(R.id.imagess_test);
        }
    }
    @Override
    public activity_nesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_nes_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(activity_nesAdapter.ViewHolder holder, int position) {

        holder.titles.setText(arrayList.get(position).getTitle());
        holder.content.setText(arrayList.get(position).getContent());
        holder.data.setText(arrayList.get(position).getData());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
