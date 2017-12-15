package com.hxe.hxeplatform.mvp.model;

import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.rxretrofit.common.Api;
import com.hxe.hxeplatform.rxretrofit.http.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/11.
 * Description:
 */

public class UpdateModel {

    public void UpdateVersion(){
        Map<String, String> map = new HashMap<>();
        RetrofitManager.getInstance(BaseApplication.getContext()).createBaseApi().requestData(Api.UPDATE_VERSION_UPDATE, map, new RetrofitManager.MyShowCallBack() {
            @Override
            public void onError(Throwable e) {
                mUpdateVersion.mUpdateVersionError(e);
            }

            @Override
            public void onSuccess(ResponseBody value) {
                mUpdateVersion.mUpdateVersionSuccess(value);
            }
        });
    }

    private updateVersion mUpdateVersion;

    public void setUpdateVersion(updateVersion mUpdateVersion){
        this.mUpdateVersion=mUpdateVersion;
    }

    public interface updateVersion{
        void mUpdateVersionSuccess(ResponseBody json);
        void mUpdateVersionError(Throwable e);
    }
}
