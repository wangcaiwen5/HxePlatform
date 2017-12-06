package com.hxe.hxeplatform.mvp.model;

import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.rxretrofit.common.Api;
import com.hxe.hxeplatform.rxretrofit.http.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/28.
 * Description:登录,获取登录状态
 */

public class LoginModel{


    //登录逻辑
    public void login(String username,String password){

        Map<String, String> map = new HashMap<>();
        map.put("mobile",username);
        map.put("password",password);
        RetrofitManager.getInstance(BaseApplication.getContext()).createBaseApi().login(Api.LOGIN_API, map, new RetrofitManager.MyShowCallBack() {
            @Override
            public void onError(Throwable e) {
                mLogin.mLoginError(e);
                System.out.println("loginError"+e);
            }
            @Override
            public void onSuccess(ResponseBody value) {
                mLogin.mLoginSuccess(value);
            }
        });


    }



    private MLogin mLogin;

    public void setmLogin(MLogin mLogin) {
        this.mLogin = mLogin;
    }

    public interface MLogin{
        void mLoginSuccess(ResponseBody json);
        void mLoginError(Throwable e);

    }
}
