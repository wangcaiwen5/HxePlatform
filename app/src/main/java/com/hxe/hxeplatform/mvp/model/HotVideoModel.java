package com.hxe.hxeplatform.mvp.model;

import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.rxretrofit.common.Api;
import com.hxe.hxeplatform.rxretrofit.http.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/7.
 * Description:
 */

public class HotVideoModel {

    public void getHotVideo(String page){
        Map<String, String> map = new HashMap<>();
        map.put("page",page);
        RetrofitManager.getInstance(BaseApplication.getContext()).requestData(Api.GET_HOT_VIDEO, map, new RetrofitManager.MyShowCallBack() {
            @Override
            public void onError(Throwable e) {
                mHotVideo.MHotVideoListError(e);
            }

            @Override
            public void onSuccess(ResponseBody value) {
                mHotVideo.MHotVideoListSuccess(value);
            }
        });
    }


    private MHotVideo mHotVideo;
    public void setmHotVideo(MHotVideo mHotVideo){
        this.mHotVideo = mHotVideo;
    }

    public interface MHotVideo{
        void MHotVideoListSuccess(ResponseBody json);
        void MHotVideoListError(Throwable e);
    }



}
