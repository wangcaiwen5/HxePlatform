package com.hxe.hxeplatform.mvp.presenter;

import android.text.TextUtils;

import com.hxe.hxeplatform.base.BasePresenter;
import com.hxe.hxeplatform.mvp.model.UploadFileModel;
import com.hxe.hxeplatform.mvp.view.UploadFileView;

import java.io.File;
import java.util.List;

import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/29.
 * Description:
 */

public class UploadPresenter extends BasePresenter<UploadFileView> implements UploadFileModel.MUploadFile {

    private UploadFileView fileView;
    private UploadFileModel fileModel;

    public UploadPresenter(UploadFileView fileView) {
        this.fileView = fileView;
        fileModel  = new UploadFileModel();
        fileModel.setmUploadFile(this);
    }

    public void uploadFile(String uid , List<File> files){
        fileView.ShowProgressBar();
        if(TextUtils.isEmpty(uid)){
            throw new RuntimeException("uid不能为空!!!");
        }
        if(files.size()==0){
            throw new RuntimeException("files集合不能为空!!!");
        }
        fileModel.uploadFile(uid,files);
    }

    @Override
    public void mUploadFileSuccess(ResponseBody json) {
        fileView.onSuccess(json);
        fileView.hideProgressBar();
    }

    @Override
    public void mUploadFileError(Throwable e) {
        fileView.onError(e);
        fileView.hideProgressBar();
    }
}
