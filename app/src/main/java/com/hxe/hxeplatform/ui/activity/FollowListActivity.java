package com.hxe.hxeplatform.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.onetime.platform.R;
import com.hxe.hxeplatform.adapter.MyFollowListAdapter;
import com.hxe.hxeplatform.base.BaseActivity;
import com.hxe.hxeplatform.entity.FollowListEntity;
import com.hxe.hxeplatform.entity.UserInfoEntity;
import com.hxe.hxeplatform.mvp.presenter.GetFollowListPresenter;
import com.hxe.hxeplatform.mvp.view.FollowListView;
import com.hxe.hxeplatform.myview.MyToolBar;
import com.hxe.hxeplatform.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/15.
 * Description:
 */

public class FollowListActivity extends BaseActivity<GetFollowListPresenter> implements FollowListView {
    @BindView(R.id.mToolBarFollow)
    MyToolBar mToolBarFollow;
    @BindView(R.id.rv_follow_list)
    RecyclerView rvFollowList;
    private MyFollowListAdapter adapter;

    @Override
    protected GetFollowListPresenter getPresenter() {
        return new GetFollowListPresenter(this);
    }

    @Override
    protected boolean getIsWindow() {
        return false;
    }

    @Override
    protected void init() {

        initView();
        initUser();
    }

    private void initView() {
        rvFollowList.setLayoutManager(new LinearLayoutManager(this));
        mToolBarFollow.setLeftTitleText("返回");
        mToolBarFollow.setMainTitle("我的关注");
        mToolBarFollow.setRightTitleText("热门关注");
        mToolBarFollow.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initUser() {
        String uid = SharedPreferencesUtils.getInstance(this).getString("uid");
        System.out.println("uid==========="+uid);
        mPresenter.getFollowList(uid);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_follow;
    }

    @Override
    public void onSuccess(ResponseBody msg) {
        try {
            String string = msg.string();
            System.out.println("用户关注列表==="+string);
            Gson gson = new Gson();
            FollowListEntity followListEntity = gson.fromJson(string, FollowListEntity.class);
            List<FollowListEntity.DataBean> data = followListEntity.data;
            if(data.size()>0){
                if(adapter==null){
                    adapter = new MyFollowListAdapter(this,data);
                    rvFollowList.setAdapter(adapter);
                }else{
                    adapter.notifyDataSetChanged();
                }

            }
            adapter.setOnItemClickListener(new MyFollowListAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    gotoActivity(UserCenterActivity.class);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFail(String msg) {

    }

    @Override
    public void ShowProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("请求失败"+throwable);
    }


}
