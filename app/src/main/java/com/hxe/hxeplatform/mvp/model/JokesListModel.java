package com.hxe.hxeplatform.mvp.model;

import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.rxretrofit.common.Api;
import com.hxe.hxeplatform.rxretrofit.http.RetrofitManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/28.
 * Description:
 */

public class JokesListModel {
//https://www.zhaoapi.cn/quarter/getJokes?page=1;
    //获取段子list
    public void getJokesList(String page){
        final Map<String, String> map = new HashMap<>();
        map.put("page",page);
        RetrofitManager.getInstance(BaseApplication.getContext()).createBaseApi().requestData(Api.GET_JOKES, map, new RetrofitManager.MyShowCallBack() {
            @Override
            public void onError(Throwable e) {
               mjokesList.mjokesListError(e);
            }

            @Override
            public void onSuccess(ResponseBody value) {
                mjokesList.mjokesListSuccess(value);




            }
        });
    }

    private MjokesList mjokesList;

    public void setmLogin(MjokesList mjokesList) {
        this.mjokesList = mjokesList;
    }

    public interface MjokesList{
        void mjokesListSuccess(ResponseBody json);
        void mjokesListError(Throwable e);
    }
}
