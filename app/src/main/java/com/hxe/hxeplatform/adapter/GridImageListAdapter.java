package com.hxe.hxeplatform.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import com.onetime.platform.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;


/**
 * Author:wangcaiwen
 * Time:2017/11/30.
 * Description:
 */

public class GridImageListAdapter extends RecyclerView.Adapter<GridImageListAdapter.MyViewHolder> {

    private String[] imgs;

    private Context context;

    public GridImageListAdapter(String[] imgs, Context context) {
        this.imgs = imgs;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_more_img_layout, null);
        MyViewHolder holder = new MyViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

       // RequestBuilder<Drawable> load = Glide.with(context).load(imgs[position]);

        RequestOptions option = new RequestOptions().override(200,200).error(R.mipmap.img_loading_fail).placeholder(R.mipmap.img_loading_fail);
        RequestBuilder<Drawable> apply = Glide.with(context).load(imgs[position]).apply(option);

           apply.into(holder.imageView);



        // Glide.with(context).load(imgs[position]).apply(option).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imgs.length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_img);

        }
    }
}
