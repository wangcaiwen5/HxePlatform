package com.hxe.hxeplatform.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bwie.uploadpicture.view.CircleImageView;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.entity.JokesEntity;
import com.hxe.hxeplatform.myview.MyGridView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Author:wangcaiwen
 * Time:2017/11/26.
 * Description:
 */

public class MyJokesAdapter extends RecyclerView.Adapter<MyJokesAdapter.MyViewHolder> {
    boolean isOpen = false;
    private List<JokesEntity.DataBean> list;
    private Context context;
    private OnItemClickListener mOnItemClickListener;
    private OnItemlongClickListener mOnItemlongClickListener;



    public MyJokesAdapter(Context context,List<JokesEntity.DataBean> list) {
        this.list = list;
        this.context = context;
    }

    public void addAll(List<JokesEntity.DataBean> lists){
        if(list!=null && list.size()>0){
            System.out.println("添加了一个lists集合");

            list.addAll(lists);
        }
        notifyDataSetChanged();
    }


   /* @Override
    public int getItemViewType(int position) {
        String imgUrls = (String) list.get(position).imgUrls;
        String[] split = imgUrls.split("\\|");
        if(split.length==0){
            return FlagUtils.ZERO_TYPE;
        }else if(split.length==1){
            return FlagUtils.ONE_TYPE;
        }else if(split.length==2){
            return FlagUtils.TWO_TYPE;
        }else if(split.length==3){
            return FlagUtils.THREE_TYPE;
        }else if(split.length==4){
            return FlagUtils.FOUR_TYPE;
        }else if(split.length==5){
            return FlagUtils.FIVE_TYPE;
        }else if(split.length==6){
            return FlagUtils.SIX_TYPE;
        }else if(split.length==7){
            return FlagUtils.SEVEN_TYPE;
        }else if(split.length==8){
            return FlagUtils.EIGHT_TYPE;
        }else if(split.length==9){
            return FlagUtils.NINE_TYPE;
        }

        return 0;

    }*/

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

      /*  if(viewType==0){
            view = View.inflate(context, R.layout.item_layout_jokes, null);
        }else if(viewType==1){
            view = View.inflate(context, R.layout.item_layout_jokes_image1, null);
        }else if(viewType==2){
            view = View.inflate(context, R.layout.item_layout_jokes_image2, null);
        }else if(viewType==3){
            view = View.inflate(context, R.layout.item_layout_jokes_image3, null);
        }else if(viewType==4){
            view = View.inflate(context, R.layout.item_layout_jokes_image4, null);
        }else if(viewType==5){
            view = View.inflate(context, R.layout.item_layout_jokes_image5, null);
        }else if(viewType==6){
            view = View.inflate(context, R.layout.item_layout_jokes_image6, null);
        }else if(viewType==7){
            view = View.inflate(context, R.layout.item_layout_jokes_image7, null);
        }else if(viewType==8){
            view = View.inflate(context, R.layout.item_layout_jokes_image8, null);
        }else if(viewType==9){
            view = View.inflate(context, R.layout.item_layout_jokes_image9, null);
        }*/
        View view; view = View.inflate(context, R.layout.item_layout_jokes, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        RequestOptions option = new RequestOptions().placeholder(R.drawable.loading_02);
        Glide.with(context).load(list.get(position).user.icon).apply(option).into(holder.ivHeadImg);
            holder.tvNickname.setText(list.get(position).user.nickname);
            holder.tvDate.setText(list.get(position).createTime);
            holder.tvMessage.setText(list.get(position).content);
            String imgUrls = list.get(position).imgUrls;
        /*WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = 0;*/
        System.out.println("图片集合===="+imgUrls);
        GridLayoutManager manager=null;
        if(!TextUtils.isEmpty(imgUrls)){
            String[] split = imgUrls.split("\\|");
            if(split.length==1){
                manager = new GridLayoutManager(context,1);
            }else if (split.length==2){
                manager = new GridLayoutManager(context,2);
            }else if (split.length == 3){
                manager = new GridLayoutManager(context,2);
            }else if(split.length==4){
                manager = new GridLayoutManager(context,2);
            }else if(split.length == 5){
                manager = new GridLayoutManager(context,2);
            }else if(split.length == 6){
                manager = new GridLayoutManager(context,3);
            }else if(split.length == 7){
                manager = new GridLayoutManager(context,3);
            }else if(split.length == 8){
                manager = new GridLayoutManager(context,3);
            }else if(split.length == 9){
                manager = new GridLayoutManager(context,3);
            }

            holder.mGridView.setLayoutManager(manager);
            holder.mGridView.setAdapter(new GridImageListAdapter(split,context));
        }






        holder.menu_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final float menu_addx = holder.menu_add.getX();
                final float llreportx = holder.llreport.getX();
                final float llcopyurlx = holder.llcopyurl.getX();
                final float llshieldx = holder.llshield.getX();
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
                    animatorSet.playTogether(alphaConfirm, alphatorEdit, alphatorSend);
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
                    animatorSet.setDuration(400);
                    animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
                    animatorSet.playTogether(animatorConfirm, animatorEdit, animatorSend);
                    animatorSet.playTogether(alphaConfirm, alphatorEdit, alphatorSend);
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
        return list.size();
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
        RecyclerView mGridView;
        TextView tvPl;
        TextView tvFx;
        TextView tvGz;

        public MyViewHolder(View itemView) {
            super(itemView);
            menu_add = itemView.findViewById(R.id.iv_isopenMenu);
            tvNickname = itemView.findViewById(R.id.tv_jokes_nickname);
            ivHeadImg = itemView.findViewById(R.id.iv_head_img);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvMessage  = itemView.findViewById(R.id.tv_message);

            llcopyurl=itemView.findViewById(R.id.ll_ly);
            llreport = itemView.findViewById(R.id.ll_fx);
            llshield = itemView.findViewById(R.id.ll_sc);
            mGridView = itemView.findViewById(R.id.mGridview);
            tvFx = itemView.findViewById(R.id.tv_fx);
            tvPl = itemView.findViewById(R.id.tv_pl);
            tvGz = itemView.findViewById(R.id.tv_gz);

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
