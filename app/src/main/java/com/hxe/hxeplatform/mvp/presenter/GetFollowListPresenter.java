package com.hxe.hxeplatform.mvp.presenter;

import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.mvp.model.FollowLlistModel;
import com.hxe.hxeplatform.mvp.view.FollowListView;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/16.
 * Description:
 */

public class GetFollowListPresenter extends BasePresenter<FollowListView> implements FollowLlistModel.MFollow {

    private FollowLlistModel followLlistModel;
    private FollowListView followListView;

    public GetFollowListPresenter(FollowListView followListView) {
        this.followListView = followListView;
        followLlistModel = new FollowLlistModel();
        followLlistModel.setmFollow(this);
    }

    public void getFollowList(String uid){
        followListView.ShowProgressBar();
        followLlistModel.getFollowList(uid);
    }

    @Override
    public void onSuccess(ResponseBody value) {
        followListView.onSuccess(value);
        followListView.hideProgressBar();
    }

    @Override
    public void onError(Throwable e) {
        followListView.onError(e);
        followListView.hideProgressBar();
    }
}
