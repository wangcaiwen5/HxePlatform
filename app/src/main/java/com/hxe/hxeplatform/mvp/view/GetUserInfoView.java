package com.hxe.hxeplatform.mvp.view;

import com.hxe.hxeplatform.base.BaseView;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/1.
 * Description:
 */

public interface GetUserInfoView extends BaseView {


    @Override
    void onSuccess(ResponseBody body);



    @Override
    void onError(Throwable throwable);
}
