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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.uploadpicture.view.CircleImageView;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.entity.GetVediosListEntity;
import com.hxe.hxeplatform.entity.HotVideoEntity;
import com.hxe.hxeplatform.rxretrofit.common.Api;
import com.hxe.hxeplatform.rxretrofit.http.RetrofitManager;
import com.hxe.hxeplatform.utils.PhoneWindowSizeUtils;
import com.hxe.hxeplatform.utils.RandomUtil;
import com.hxe.hxeplatform.utils.SharedPreferencesUtils;
import com.hxe.hxeplatform.utils.ToastShow;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jzvd.JZVideoPlayerStandard;
import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/26.
 * Description:
 */

public class MyHotVideoAdapter extends RecyclerView.Adapter<MyHotVideoAdapter.MyViewHolder> {
    boolean isOpen = false;
    boolean isRightShow=true;
    private List<HotVideoEntity.DataBean> data = null;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    private OnItemlongClickListener mOnItemlongClickListener;
    private List<Integer> heights;

    public MyHotVideoAdapter(Context context, List<HotVideoEntity.DataBean> data) {
        this.data = data;
        this.context = context;
        getRandomHeight(this.data);


    }
    private void getRandomHeight(List<HotVideoEntity.DataBean> lists) {//得到随机item的高度
        heights = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            heights.add((int) (300 + Math.random() * 400));
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // LayoutInflater.from(context).inflate()
        View view = View.inflate(context, R.layout.item_hot_video_layout, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ViewGroup.LayoutParams params =  holder.hotVideo.getLayoutParams();//得到item的LayoutParams布局参数
        int[] in = { 4, 3,5, 2, };
        int notSimple = RandomUtil.getNotSimple(in, 3);

        int phoneWindowWidth = PhoneWindowSizeUtils.getPhoneWindowWidth(context);

        params.height = 400+notSimple;
        params.width = phoneWindowWidth/2;
       // params.height = 200;//把随机的高度赋予itemView布局
        holder.itemView.setLayoutParams(params);//把params设置给itemView布局

        Glide.with(context).load(data.get(position).cover).into(holder.hotVideo);



//判断是否设置了监听器
        //点击事件

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(mOnItemClickListener!=null){
                        int position = holder.getLayoutPosition(); // 1
                        mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                    }

                }
            });


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

        ImageView hotVideo;



        public MyViewHolder(View itemView) {
            super(itemView);
            hotVideo = itemView.findViewById(R.id.iv_video_img);

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
