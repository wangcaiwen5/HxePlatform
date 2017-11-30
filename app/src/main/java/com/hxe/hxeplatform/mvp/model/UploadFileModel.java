package com.hxe.hxeplatform.mvp.model;

import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.rxretrofit.common.Api;
import com.hxe.hxeplatform.rxretrofit.common.BaseApi;
import com.hxe.hxeplatform.rxretrofit.http.RetrofitManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/11/29.
 * Description:
 */

public class UploadFileModel {

    public void uploadFile(String uid , List<File> files){


        List<MultipartBody.Part> parts = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {
            System.out.println("========上传"+files.size());
            File singleFile = files.get(i);
            // 创建 RequestBody，用于封装 请求RequestBody
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), files.get(i));
            //File字段
            MultipartBody.Part body = MultipartBody.Part.createFormData("file",singleFile.getName(),requestFile);
            parts.add(body);
        }
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), "我的头像");
        RetrofitManager.getInstance(BaseApplication.getContext()).createBaseApi().moreUploadFile(Api.UPLOAD_FILE,
                parts,
                uid,
                description,
                new RetrofitManager.MyShowCallBack() {
            @Override
            public void onError(Throwable e) {
                mUploadFile.mUploadFileError(e);
            }

            @Override
            public void onSuccess(ResponseBody value) {
                mUploadFile.mUploadFileSuccess(value);
            }
        });
    }
    private MUploadFile mUploadFile;

    public void setmUploadFile(MUploadFile mUploadFile) {
        this.mUploadFile = mUploadFile;
    }

    public interface MUploadFile{
        void mUploadFileSuccess(ResponseBody json);
        void mUploadFileError(Throwable e);

    }
}
