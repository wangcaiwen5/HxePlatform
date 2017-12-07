package com.hxe.hxeplatform.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.uploadpicture.view.CircleImageView;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.entity.GetVediosListEntity;
import com.hxe.hxeplatform.entity.Item;
import com.hxe.hxeplatform.rxretrofit.common.Api;
import com.hxe.hxeplatform.rxretrofit.http.RetrofitManager;
import com.hxe.hxeplatform.utils.RandomUtil;
import com.hxe.hxeplatform.utils.SharedPreferencesUtils;
import com.hxe.hxeplatform.utils.ToastShow;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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

public class MyHotAdapter extends RecyclerView.Adapter<MyHotAdapter.MyViewHolder> {
    boolean isOpen = false;
    boolean isRightShow=true;
    private List<GetVediosListEntity.DataBean> data = null;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    private OnItemlongClickListener mOnItemlongClickListener;

    public MyHotAdapter(Context context,List<GetVediosListEntity.DataBean> data) {
        this.data = data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       // LayoutInflater.from(context).inflate()
        View view = View.inflate(context, R.layout.item_hot_layout, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        GetVediosListEntity.DataBean.UserBean user = data.get(position).user;
        holder.tvNickname.setText(user.nickname);
        holder.tvDate.setText(data.get(position).createTime);
        holder.tvMessage.setText(data.get(position).workDesc);
        RequestOptions option = new RequestOptions().placeholder(R.drawable.loading_02).diskCacheStrategy(DiskCacheStrategy.NONE);
        Glide.with(BaseApplication.getContext()).load(user.icon).apply(option).into(holder.ivHeadImg);
        holder.tvcomment.setText(data.get(position).commentNum+"");//评论
        int[] in = { 1, 2, 3, 4, 5, 6, 7 };
        int notSimple = RandomUtil.getNotSimple(in, 3);
        holder.tvshare.setText(notSimple+"");//分享
        int notSimple1 = RandomUtil.getNotSimple(in, 2);
        holder.tvcollect.setText(notSimple1+"");
        int notSimple2 = RandomUtil.getNotSimple(in, 4);
        holder.tvattention.setText(notSimple2+"");
        String videoUrl = data.get(position).videoUrl;
        String substring = videoUrl.substring(22);
        System.out.println("切割=="+substring);
        System.out.println("=======视频地址"+data.get(position).videoUrl);
        if(holder.app_video_box!=null){
            holder.app_video_box.setUp("http://120.27.23.105"+substring
                    , JZVideoPlayerStandard.SCREEN_WINDOW_LIST,
                    "视频"+data.get(position).workDesc);
                Glide.with(context).load(data.get(position).cover).into(holder.app_video_box.thumbImageView);
        }






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
                    ObjectAnimator alphaConfirm = ObjectAnimator.ofFloat(holder.llreport, "alpha", 0.1f,1f);
                    ObjectAnimator alphatorEdit = ObjectAnimator.ofFloat( holder.llcopyurl, "alpha", 0.1f,1f);
                    ObjectAnimator alphatorSend = ObjectAnimator.ofFloat( holder.llshield, "alpha", 0.1f,1f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(500);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.playTogether(alphaConfirm,alphatorEdit,alphatorSend);
                    animatorSet.start();
                    isOpen = true;
                } else {
                    holder.menu_add.animate().rotation(0).setDuration(800).start();
                    holder.menu_add.setImageResource(R.mipmap.add);
//                    注意这里的translationX要运行到plus后面
                    ObjectAnimator animatorConfirm = ObjectAnimator.ofFloat(holder.llreport, "translationX", menu_addx - llreportx);
                    ObjectAnimator animatorEdit = ObjectAnimator.ofFloat(holder.llcopyurl, "translationX", menu_addx - llcopyurlx);
                    ObjectAnimator animatorSend = ObjectAnimator.ofFloat(holder.llshield, "translationX", menu_addx - llshieldx);


                    ObjectAnimator alphaConfirm = ObjectAnimator.ofFloat(holder.llreport, "alpha", 1f,0.1f);
                    ObjectAnimator alphatorEdit = ObjectAnimator.ofFloat( holder.llcopyurl, "alpha", 1f,0.1f);
                    ObjectAnimator alphatorSend = ObjectAnimator.ofFloat( holder.llshield, "alpha", 1f,0.1f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.setDuration(500);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.playTogether(alphaConfirm,alphatorEdit,alphatorSend);
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


        holder.ivattention.setOnClickListener(new View.OnClickListener() {
            boolean isSelect=true;
            @Override
            public void onClick(final View v) {
                if(isSelect){
                    Map<String, String> map = new HashMap<>();
                    map.put("uid", SharedPreferencesUtils.getInstance(context).getString("uid"));
                    map.put("followId",data.get(position).uid+"");
                    int uid = data.get(position).uid;
                    System.out.println("uid====="+uid);
                    RetrofitManager.getInstance(context).login(Api.FOLLW, map, new RetrofitManager.MyShowCallBack() {
                        @Override
                        public void onError(Throwable e) {
                            System.out.println("关注==="+e);
                        }

                        @Override
                        public void onSuccess(ResponseBody value) {
                            try {
                                JSONObject object = new JSONObject(value.string());
                                String code = object.getString("code");
                                String msg = object.getString("msg");
                                if(code.equals("0")){
                                    holder.ivattention.setImageResource(R.mipmap.red_gz);
                                    ToastShow.getSingleton(context).showToast(msg);
                                }else{
                                    ToastShow.getSingleton(context).showToast(msg);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });


                    isSelect=false;
                }else{
                    ToastShow.getSingleton(context).showToast("取消关注");
                    holder.ivattention.setImageResource(R.mipmap.sc);
                    isSelect=true;
                }

            }
        });

        holder.ivcollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastShow.getSingleton(context).showToast("已收藏");
            }
        });

        holder.ivshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastShow.getSingleton(context).showToast("已分享");
            }
        });

        holder.ivcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastShow.getSingleton(context).showToast("留言");
            }
        });


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
        ImageView menu_add;
        CircleImageView ivHeadImg;
        TextView tvNickname;
        TextView  tvDate;
        TextView tvMessage;
        LinearLayout llcopyurl;
        LinearLayout llreport ;
        LinearLayout llshield;
        LinearLayout llItemSum;
        TextView tvattention;
        ImageView ivattention;
        TextView tvcollect;
        ImageView ivcollect;
        TextView tvshare;
        ImageView ivshare;
        TextView tvcomment;
        ImageView ivcomment;
        RecyclerView rvComment;
        JZVideoPlayerStandard app_video_box;




        public MyViewHolder(View itemView) {
            super(itemView);
            llItemSum = itemView.findViewById(R.id.ll_item_sum);

            tvattention = itemView.findViewById(R.id.tv_attention);
            ivattention = itemView.findViewById(R.id.iv_attention);

            tvcollect = itemView.findViewById(R.id.tv_collect);
            ivcollect = itemView.findViewById(R.id.iv_collect);

            ivshare = itemView.findViewById(R.id.iv_share);
            tvshare = itemView.findViewById(R.id.tv_share);

            ivcomment = itemView.findViewById(R.id.iv_comment);
            tvcomment = itemView.findViewById(R.id.tv_comment);

            rvComment = itemView.findViewById(R.id.rv_comments);


            menu_add = itemView.findViewById(R.id.iv_isopenMenu);
            tvNickname = itemView.findViewById(R.id.tv_nickname);
            ivHeadImg = itemView.findViewById(R.id.iv_head_img);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvMessage  = itemView.findViewById(R.id.tv_message);

            llcopyurl=itemView.findViewById(R.id.ll_copy);
            llreport = itemView.findViewById(R.id.ll_jb);
            llshield = itemView.findViewById(R.id.ll_pb);
            app_video_box=   itemView.findViewById(R.id.app_video_box);
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
