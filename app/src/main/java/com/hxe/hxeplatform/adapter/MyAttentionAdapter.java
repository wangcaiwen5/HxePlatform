package com.hxe.hxeplatform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.uploadpicture.view.CircleImageView;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.entity.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wangcaiwen
 * Time:2017/11/26.
 * Description:
 */

public class MyAttentionAdapter extends RecyclerView.Adapter<MyAttentionAdapter.MyViewHolder> {

    private List<Item> list = null;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    private OnItemlongClickListener mOnItemlongClickListener;

    public MyAttentionAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.item_hot_layout, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });

        //判断是否设置了监听器
        //点击事件
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }

        //长按监听事件
        if(mOnItemlongClickListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int layoutPosition = holder.getLayoutPosition();
                    mOnItemlongClickListener.onitemLongClick(view,layoutPosition);
                    return false;

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAdd;
        CircleImageView ivHeadImg;
        TextView tvNickname;
        TextView  tvDate;
        TextView tvMessage;
        ImageView ivFengmian;
        ImageView ivGz;
        ImageView ivSc;
        ImageView ivFx;
        ImageView ivPl;


        public MyViewHolder(View itemView) {
            super(itemView);
            ivAdd = itemView.findViewById(R.id.iv_item_img);
            tvNickname = itemView.findViewById(R.id.iv_item_title);
            ivHeadImg = itemView.findViewById(R.id.iv_head_img);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvMessage  = itemView.findViewById(R.id.tv_message);
        }
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemlongClickListener(OnItemlongClickListener mOnItemlongClickListener){
        this.mOnItemlongClickListener = mOnItemlongClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface  OnItemlongClickListener{
        void onitemLongClick(View view, int position);
    }
}
