package com.hxe.hxeplatform.mvp.presenter;

import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.mvp.model.HotVideoModel;
import com.hxe.hxeplatform.mvp.view.HotVideoView;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/7.
 * Description:
 */

public class HotVideoPresenter extends BasePresenter<HotVideoView> implements HotVideoModel.MHotVideo {

    private HotVideoView hotVideoView;
    private HotVideoModel hotVideoModel;

    public HotVideoPresenter(HotVideoView hotVideoView) {
        this.hotVideoView = hotVideoView;
        hotVideoModel = new HotVideoModel();
        hotVideoModel.setmHotVideo(this);
    }

    public void getVideoHotList(String page){
        hotVideoView.ShowProgressBar();
        hotVideoModel.getHotVideo(page);
    }

    @Override
    public void MHotVideoListSuccess(ResponseBody json) {
        hotVideoView.onSuccess(json);
        hotVideoView.hideProgressBar();
    }

    @Override
    public void MHotVideoListError(Throwable e) {
        hotVideoView.onError(e);
        hotVideoView.hideProgressBar();
    }
}
