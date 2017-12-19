package com.hxe.hxeplatform.mvp.view;

import com.hxe.hxeplatform.base.BaseView;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/16.
 * Description:
 */

public interface FollowListView extends BaseView {
    @Override
    void onFail(String msg);

    @Override
    void onSuccess(ResponseBody json);

    @Override
    void hideProgressBar();

    @Override
    void ShowProgressBar();

    @Override
    void onError(Throwable throwable);
}
