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

public class FollowModel {

    public void setFollow(String uid,String followId){

        
    }
    
    private MFollow mFollow;
    
    public void setmFollow( MFollow mFollow){
        this.mFollow=mFollow;
                
    }
    
    interface MFollow{
        void onSuccess(ResponseBody value);
        void onError(Throwable e);
    }
}
