package com.hxe.hxeplatform.mvp.model;

import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.rxretrofit.common.Api;
import com.hxe.hxeplatform.rxretrofit.http.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/1.
 * Description:
 */

public class GetUserInfoModel {

    public void getUserInfo(String uid){
        Map<String, String> map = new HashMap<>();
        map.put("uid",uid);
        RetrofitManager.getInstance(BaseApplication.getContext()).createBaseApi().requestData(Api.GET_USER_INFO, map, new RetrofitManager.MyShowCallBack() {
            @Override
            public void onError(Throwable e) {
                mGetInfo.onError(e);
            }

            @Override
            public void onSuccess(ResponseBody value) {
                mGetInfo.onSuccess(value);
            }
        });
    }

    private MGetInfo mGetInfo;

    public void setGetInfo(MGetInfo mGetInfo){
        this.mGetInfo=mGetInfo;
    }


    public interface MGetInfo{
        void onSuccess(ResponseBody value);
        void onError(Throwable e);
    }
}
