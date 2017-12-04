package com.hxe.hxeplatform.mvp.presenter;

import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.mvp.model.UpLoadVideoModel;
import com.hxe.hxeplatform.mvp.view.UpLoadVideoView;

import java.io.File;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/1.
 * Description:
 */

public class UpLoadVideoPresenter extends BasePresenter<UpLoadVideoView> implements UpLoadVideoModel.MUploadVideoFile {

    private UpLoadVideoView upLoadVideoView;
    private UpLoadVideoModel upLoadVideoModel;


    public UpLoadVideoPresenter(UpLoadVideoView upLoadVideoView) {
        this.upLoadVideoView = upLoadVideoView;
        upLoadVideoModel = new UpLoadVideoModel();
        upLoadVideoModel.setmUploadFile(this);
    }

    public void UpLoadVideoData(String uid, File videoFile, File coverFile, String videoDesc, String latitude, String longitude){
       upLoadVideoView.ShowProgressBar();
        upLoadVideoModel.UpLoadVideo(uid,videoFile,coverFile,videoDesc,latitude,longitude);
    }

    @Override
    public void mUploadVideoSuccess(ResponseBody json) {
            upLoadVideoView.onSuccess(json);
            upLoadVideoView.hideProgressBar();
    }

    @Override
    public void mUploadVideoError(Throwable e) {
        upLoadVideoView.onError(e);
        upLoadVideoView.hideProgressBar();
    }
}
