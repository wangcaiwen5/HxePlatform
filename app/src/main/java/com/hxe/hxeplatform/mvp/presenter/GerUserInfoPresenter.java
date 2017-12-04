package com.hxe.hxeplatform.mvp.presenter;

import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.mvp.model.GetUserInfoModel;
import com.hxe.hxeplatform.mvp.view.GetUserInfoView;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/1.
 * Description:
 */

public class GerUserInfoPresenter extends BasePresenter implements GetUserInfoModel.MGetInfo{

    private GetUserInfoView getUserInfoView;
    private GetUserInfoModel getUserInfoModel;

    public GerUserInfoPresenter(GetUserInfoView getUserInfoView) {
        this.getUserInfoView = getUserInfoView;
        getUserInfoModel = new GetUserInfoModel();
        getUserInfoModel.setGetInfo(this);
    }


    public void getUserInfo(String uid){
        getUserInfoModel.getUserInfo(uid);
    }

    @Override
    public void onSuccess(ResponseBody value) {
        getUserInfoView.onSuccess(value);
    }

    @Override
    public void onError(Throwable e) {
        getUserInfoView.onError(e);


    }
}
