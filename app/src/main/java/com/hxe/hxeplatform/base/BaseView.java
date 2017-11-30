package com.hxe.hxeplatform.base;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/14.
 * Description:
 */

public interface BaseView {
    void onSuccess(ResponseBody msg);
    void onFail(String msg);
    void ShowProgressBar();
    void hideProgressBar();
    void onError(Throwable throwable);
}
