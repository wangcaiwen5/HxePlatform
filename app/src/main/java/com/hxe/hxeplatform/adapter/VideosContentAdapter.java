package com.hxe.hxeplatform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.entity.GetVediosListEntity;
import com.hxe.hxeplatform.entity.HotVideoEntity;

import java.util.List;

/**
 * Author:wangcaiwen
 * Time:2017/12/14.
 * Description:
 */

public class VideosContentAdapter extends RecyclerView.Adapter<VideosContentAdapter.MyViewHolder> {
    private Context context;
    private  List<HotVideoEntity.DataBean.CommentsBean> comments ;
    private List<HotVideoEntity.DataBean> data;

    public VideosContentAdapter(Context context,  List<HotVideoEntity.DataBean.CommentsBean> comments, List<HotVideoEntity.DataBean> data) {
        this.context = context;
        this.comments = comments;
        this.data = data;
    }

    @Override
    public VideosContentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_comments_layout, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String nickname = data.get(position).user.nickname;
        holder.tvName.setText(nickname);
        holder.tvComments.setText(":"+comments.get(position).content);
    }



    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvComments;
        TextView tvName;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvComments = itemView.findViewById(R.id.tv_comments);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
