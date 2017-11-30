package com.hxe.hxeplatform.mvp.view;

import com.hxe.hxeplatform.base.BaseView;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/29.
 * Description:
 */

public interface UploadFileView extends BaseView{
    @Override
    void onFail(String msg);

    @Override
    void onSuccess(ResponseBody body);

    @Override
    void hideProgressBar();

    @Override
    void ShowProgressBar();

    @Override
    void onError(Throwable throwable);
}
