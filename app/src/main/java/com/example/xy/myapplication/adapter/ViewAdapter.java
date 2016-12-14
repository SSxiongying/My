package com.example.xy.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.xy.myapplication.R;
import java.util.List;

/**
 * Created by xy on 2016/11/8.
 */
public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder>{
    LayoutInflater inflater;
    private List<Integer> productses;
    private List<String> mTxt;
    private MyOnClick mMyOnClick;
    Context context;

    public ViewAdapter(Context context,List<Integer> list,List<String> text) {
        inflater=LayoutInflater.from(context);
        productses=list;
        mTxt=text;
    }
    //定义一个监听接口，
    public interface MyOnClick{
        void OnClick(int position);//短按
        void OnLongClick(int position);//长按
    }
    //调用方法
    public void setMyOnClick(MyOnClick mMyOnClick)
    {
        this.mMyOnClick=mMyOnClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imags;
        TextView text;
        public ViewHolder(View itemView)
        {
            super(itemView);
        }
    }

    //重写getItemCount()方法,返回的Item数量在数据的基础上面+1，增加一项FootView布局项
    @Override
    public int getItemCount() {
        return productses.size();
    }

    @Override
    public ViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_gird_item,null);
        ViewHolder holder=new ViewHolder(view);
        holder.imags= (ImageView) view.findViewById(R.id.image_lh);
        holder.text= (TextView) view.findViewById(R.id.text_pro);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imags.setImageResource(productses.get(position));
        holder.text.setText(mTxt.get(position));
        if (mMyOnClick!=null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mMyOnClick.OnClick(position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mMyOnClick.OnLongClick(position);
                    return true;
                }
            });
        }
    }

    //添加数据
    public void addData(int posision)
    {
        //在item中加入数据，并通知条目加入一条
       if (productses.size() ==0)
       {
           productses.add(0,R.mipmap.e);
       }else
       {
           productses.add(posision,R.mipmap.e);
           notifyItemInserted(posision);
           notifyItemRangeChanged(posision,productses.size());
       }
    }
    public void removeData(int position) {
        //保证列表有数据，并且最少有一条
        if(productses.size()<2&&productses.size()!=0){
            productses.remove(0);
            notifyDataSetChanged();
        }else if(productses.size()==0){//当列表没有数据提示用户，免得造成系统崩溃
            Toast.makeText(context,"搞毛啊，没数据了",Toast.LENGTH_SHORT).show();
        }else {//更新列表
            productses.remove(position);
            notifyDataSetChanged();
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, productses.size());
        }
    }
}
