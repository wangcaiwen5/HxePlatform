package com.hxe.hxeplatform.mvp.presenter;

import android.text.TextUtils;

import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.mvp.model.LoginModel;
import com.hxe.hxeplatform.mvp.view.LoginView;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/28.
 * Description:
 */

public class LoginPresenter extends BasePresenter<LoginView> implements LoginModel.MLogin {

    private LoginModel loginModel;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModel();
        loginModel.setmLogin(this);
    }

    public void login(String mobile,String password){
        loginView.ShowProgressBar();
        if(TextUtils.isEmpty(mobile) || TextUtils.isEmpty(password)){
            loginView.onFail("账号密码不能为空");
            return;
        }

        loginModel.login(mobile,password);

    }



    @Override
    public void mLoginSuccess(ResponseBody json) {
        loginView.onSuccess(json);
        loginView.hideProgressBar();
    }

    @Override
    public void mLoginError(Throwable e) {
        loginView.onError(e);//登录异常
        loginView.hideProgressBar();
    }


}
