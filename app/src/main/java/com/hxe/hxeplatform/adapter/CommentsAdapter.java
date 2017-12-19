package com.hxe.hxeplatform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onetime.platform.R;
import com.hxe.hxeplatform.entity.GetVediosListEntity;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Author:wangcaiwen
 * Time:2017/12/14.
 * Description:
 * git branch查看分支
 * git branch 分支名
 * git init  初始化
 * git status 查看状态
 * git add . 提交到暂存区
 * git commit -m "描述"
 * git pull 拉,将其他合作者push的代码下载到本地
 * git push 上传,提交
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {

    private Context context;
     private List<GetVediosListEntity.DataBean.CommentsBean> comments;
    private List<GetVediosListEntity.DataBean> data;

    public CommentsAdapter(Context context, List<GetVediosListEntity.DataBean.CommentsBean> comments, List<GetVediosListEntity.DataBean> data) {
        this.context = context;
        this.comments = comments;
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.item_comments_layout, null);
        MyViewHolder myViewHolder =new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        System.out.println(data.size()+"评论列表");
        System.out.println("列表"+position);
       // System.out.println("==="+data.get(position).user.nickname);
       // holder.tvName.setText(data.get(position).user.nickname+" ");
        holder.tvComments.setText(":"+comments.get(position).content);
        if(position==3){
            holder.tvName.setText("点击查看更多");
            return;
        }
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
