package com.hxe.hxeplatform.mvp.presenter;

import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.mvp.model.UpdateModel;
import com.hxe.hxeplatform.mvp.view.UpdateView;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/11.
 * Description:
 */

public class UpdatePresenter extends BasePresenter<UpdateView> implements UpdateModel.updateVersion{

    private UpdateModel updateModel;
    private UpdateView updateView;

    public UpdatePresenter(UpdateView updateView) {
        this.updateView = updateView;
        updateModel = new UpdateModel();
        updateModel.setUpdateVersion(this);
    }

    public void updateVersion(){
        updateView.ShowProgressBar();
        updateModel.UpdateVersion();
    }

    @Override
    public void mUpdateVersionSuccess(ResponseBody json) {
        updateView.onSuccess(json);
        updateView.hideProgressBar();
    }

    @Override
    public void mUpdateVersionError(Throwable e) {
        updateView.onError(e);
        updateView.hideProgressBar();
    }
}
