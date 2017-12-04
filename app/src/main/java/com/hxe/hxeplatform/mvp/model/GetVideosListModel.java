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

public class GetVideosListModel {

    public void getVideosList(String type,String uid,String page){

        Map<String, String> map = new HashMap<>();
        map.put("tyep",type);
        map.put("uid",uid);
        map.put("page",page);
        RetrofitManager.getInstance(BaseApplication.getContext()).requestData(Api.GET_VIDEOS, map, new RetrofitManager.MyShowCallBack() {
            @Override
            public void onError(Throwable e) {
                mGetVideosList.onError(e);
            }

            @Override
            public void onSuccess(ResponseBody value) {
                mGetVideosList.onSuccess(value);
            }
        });

    }

    private MGetVideosList mGetVideosList;
    public void setmGetVideosList(MGetVideosList mGetVideosList){
        this.mGetVideosList = mGetVideosList;
    }

    public interface MGetVideosList{
        void onError(Throwable e);
        void  onSuccess(ResponseBody value);
    }

}
