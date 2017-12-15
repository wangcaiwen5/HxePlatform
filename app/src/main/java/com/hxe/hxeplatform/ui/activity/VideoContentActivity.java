package com.hxe.hxeplatform.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hxe.hxeplatform.R;
import com.hxe.hxeplatform.adapter.VideosContentAdapter;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.entity.HotVideoEntity;
import com.hxe.hxeplatform.entity.VideoContentEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.JZVideoPlayerStandard;

import static com.igexin.sdk.GTServiceManager.context;

public class VideoContentActivity extends BaseActivity {


    @BindView(R.id.jz_video)
    JZVideoPlayerStandard jzVideo;
    @BindView(R.id.rv_content_comments)
    RecyclerView rvContentComments;
    List<HotVideoEntity.DataBean.CommentsBean> comments = new ArrayList<>();
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    protected boolean getIsWindow() {
        return true;
    }

    @Override
    protected void init() {
        initView();
        initData();
    }

    private void initView() {
    rvContentComments.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String videos = extras.getString("videos");
        int position = extras.getInt("position");
        System.out.println("索引"+position);
        Gson gson = new Gson();
        HotVideoEntity hotVideoEntity = gson.fromJson(videos, HotVideoEntity.class);
        List<HotVideoEntity.DataBean> data = hotVideoEntity.data;
        comments = data.get(position).comments;
        String videoUrl = data.get(position).videoUrl;
        jzVideo.setUp(videoUrl
                , JZVideoPlayerStandard.SCREEN_WINDOW_LIST,
                "视频"+data.get(position).workDesc);
        Glide.with(this).load(data.get(position).cover).into(jzVideo.thumbImageView);
        if(this.comments.size()>0){
           VideosContentAdapter adapter = new VideosContentAdapter(this, this.comments,data);
           rvContentComments.setAdapter(adapter);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_content;
    }


}
