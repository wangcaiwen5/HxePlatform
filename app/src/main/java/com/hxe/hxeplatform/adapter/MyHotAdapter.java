package com.hxe.hxeplatform.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class MyHotAdapter extends RecyclerView.Adapter<MyHotAdapter.MyViewHolder> {
    boolean isOpen = false;
    private List<Item> list = null;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    private OnItemlongClickListener mOnItemlongClickListener;

    public MyHotAdapter(Context context) {
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

        holder.menu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                float menu_addx = holder.menu_add.getX();
                float llreportx = holder.llreport.getX();
                float llcopyurlx = holder.llcopyurl.getX();
                float llshieldx = holder.llshield.getX();
                holder.llshield.setX(llshieldx);
                holder.llcopyurl.setX(llcopyurlx);
                holder.llreport.setX(llreportx);

                if (!isOpen) {

                    /*confirm.setVisibility(VISIBLE);
                    edit.setVisibility(VISIBLE);
                    send.setVisibility(VISIBLE);*/
                    holder.llreport.setVisibility(View.VISIBLE);
                    holder.llcopyurl.setVisibility(View.VISIBLE);
                    holder.llshield.setVisibility(View.VISIBLE);
                    holder.menu_add.animate().rotation(360).setDuration(800).start();
                    holder.menu_add.setImageResource(R.mipmap.subtract);
                    Log.e("qqq",  holder.llreport.getTranslationX() + "");
//                    注意这里的translationX要回到原始位置
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat(holder.llreport, "translationX", 0);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat( holder.llcopyurl, "translationX", 0);
                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat( holder.llshield, "translationX", 0);

                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(400);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.start();
                    isOpen = true;
                } else {
                    holder.menu_add.animate().rotation(0).setDuration(800).start();
                    holder.menu_add.setImageResource(R.mipmap.add);
//                    注意这里的translationX要运行到plus后面
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat(holder.llreport, "translationX", menu_addx - llreportx);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(holder.llcopyurl, "translationX", menu_addx - llcopyurlx);
                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat(holder.llshield, "translationX", menu_addx - llshieldx);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(400);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.start();
                    animatorSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            holder.llreport.setVisibility(View.GONE);
                            holder.llcopyurl.setVisibility(View.GONE);
                            holder.llshield.setVisibility(View.GONE);
                        }
                    });
                    isOpen = false;
                }
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
        ImageView menu_add;
        CircleImageView ivHeadImg;
        TextView tvNickname;
        TextView  tvDate;
        TextView tvMessage;
        ImageView ivFengmian;
        ImageView ivGz;
        ImageView ivSc;
        ImageView ivFx;
        ImageView ivPl;
        LinearLayout llcopyurl;
        LinearLayout llreport ;
        LinearLayout llshield;



        public MyViewHolder(View itemView) {
            super(itemView);
            menu_add = itemView.findViewById(R.id.iv_isopenMenu);
            tvNickname = itemView.findViewById(R.id.iv_item_title);
            ivHeadImg = itemView.findViewById(R.id.iv_head_img);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvMessage  = itemView.findViewById(R.id.tv_message);
            ivFengmian = itemView.findViewById(R.id.iv_fengmian);
            ivGz = itemView.findViewById(R.id.iv_gz);
            ivSc = itemView.findViewById(R.id.iv_sc);
            ivPl = itemView.findViewById(R.id.iv_pl);
            ivFx = itemView.findViewById(R.id.iv_fx);
            llcopyurl=itemView.findViewById(R.id.ll_copy);
            llreport = itemView.findViewById(R.id.ll_jb);
            llshield = itemView.findViewById(R.id.ll_pb);
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