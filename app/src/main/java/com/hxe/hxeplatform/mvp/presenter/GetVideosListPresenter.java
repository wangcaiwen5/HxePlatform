package com.hxe.hxeplatform.mvp.presenter;

import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.mvp.model.GetVideosListModel;
import com.hxe.hxeplatform.mvp.view.GetVideosListView;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/1.
 * Description:
 */

public class GetVideosListPresenter extends BasePresenter<GetVideosListView> implements GetVideosListModel.MGetVideosList{


    private GetVideosListModel getVideosListModel;
    private GetVideosListView getVideosListView;

    public GetVideosListPresenter(GetVideosListView getVideosListView) {
        this.getVideosListView = getVideosListView;
        getVideosListModel = new GetVideosListModel();
        getVideosListModel.setmGetVideosList(this);
    }

    public void getVideosList(String type,String uid,String page){
        getVideosListView.ShowProgressBar();
        getVideosListModel.getVideosList(type,uid,page);

    }

    @Override
    public void onError(Throwable e) {
        getVideosListView.onError(e);
        getVideosListView.hideProgressBar();
    }

    @Override
    public void onSuccess(ResponseBody value) {
        getVideosListView.onSuccess(value);
        getVideosListView.hideProgressBar();
    }
}
