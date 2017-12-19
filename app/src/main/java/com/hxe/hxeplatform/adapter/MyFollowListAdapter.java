package com.hxe.hxeplatform.adapter;


import android.content.Context;

import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.uploadpicture.view.CircleImageView;
import com.onetime.platform.R;
import com.hxe.hxeplatform.entity.FollowListEntity;


import java.util.List;

/**
 * Author:wangcaiwen
 * Time:2017/11/26.
 * Description:
 */

public class MyFollowListAdapter extends RecyclerView.Adapter<MyFollowListAdapter.MyViewHolder> {
    boolean isOpen = false;
    private  List<FollowListEntity.DataBean> data;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    private OnItemlongClickListener mOnItemlongClickListener;



    public MyFollowListAdapter(Context context,  List<FollowListEntity.DataBean> data) {
        this.data = data;
        this.context = context;
    }





    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view; view = View.inflate(context, R.layout.item_follow_list_layout, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {




        RequestOptions option = new RequestOptions().placeholder(R.drawable.loading_02);
       Glide.with(context).load(data.get(position).icon).apply(option).into(holder.ivHeadImg);
       holder.tvDate.setText(data.get(position).createtime);
       holder.tvNickname.setText(data.get(position).nickname);


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
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView ivHeadImg;
        TextView tvNickname;
        TextView  tvDate;
        TextView tvMessage;


        public MyViewHolder(View itemView) {
            super(itemView);

            tvNickname = itemView.findViewById(R.id.tv_follow_nikeName);
            ivHeadImg = itemView.findViewById(R.id.iv_follow_headImg);
            tvDate = itemView.findViewById(R.id.tv_follow_time);
            tvMessage  = itemView.findViewById(R.id.tv_follow_message);



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
