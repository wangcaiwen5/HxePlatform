package com.hxe.hxeplatform.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.entity.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wangcaiwen
 * Time:2017/11/26.
 * Description:
 */

public class MyLeftAdapter extends RecyclerView.Adapter<MyLeftAdapter.MyViewHolder> {

    private List<Item> list = null;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    private OnItemlongClickListener mOnItemlongClickListener;

    public MyLeftAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
        list.add(new Item("我的关注",R.mipmap.my_gz));
        list.add(new Item("我的收藏",R.mipmap.my_sc));
        list.add(new Item("搜索好友",R.mipmap.my_ss));
        list.add(new Item("消息通知",R.mipmap.my_tz));
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.item_left_layout, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.itemImg.setImageResource(list.get(position).getImage());
        holder.itemtitle.setText(list.get(position).getTitle());
        holder.itemtitle .setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
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
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImg;
        TextView itemtitle;
        public MyViewHolder(View itemView) {
            super(itemView);
             itemImg = itemView.findViewById(R.id.iv_item_img);
             itemtitle = itemView.findViewById(R.id.iv_item_title);
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
        void onitemLongClick(View view,int position);
    }
}
