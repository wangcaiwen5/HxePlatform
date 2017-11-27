package com.hxe.hxeplatform.rxretrofit.http;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/17.
 * Description:
 */

public abstract class MyCallBack {
   // public void onStart(){}

    //public void onCompleted(){}

    abstract public void onError(Throwable e);



    abstract public void onSuccess(ResponseBody responseBody);
}
