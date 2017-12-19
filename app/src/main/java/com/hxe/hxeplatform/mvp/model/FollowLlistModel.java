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

public class FollowLlistModel {

    public void getFollowList(String uid){
        Map<String, String> map = new HashMap<>();
        map.put("uid",uid);
        RetrofitManager.getInstance(BaseApplication.getContext()).createBaseApi().login(Api.GET_FOLLOW_LIST, map, new RetrofitManager.MyShowCallBack() {
            @Override
            public void onError(Throwable e) {
                mFollow.onError(e);
            }

            @Override
            public void onSuccess(ResponseBody value) {
                mFollow.onSuccess(value);
            }
        });
        
    }
    
    private MFollow mFollow;
    
    public void setmFollow( MFollow mFollow){
        this.mFollow=mFollow;
                
    }
    
   public interface MFollow{
        void onSuccess(ResponseBody value);
        void onError(Throwable e);
    }
}
