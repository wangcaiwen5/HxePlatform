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

    public void uploadFile(String uid ,String content, List<File> files ){
        System.out.println("添加文件流");
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("uid",uid)
                .addFormDataPart("content",content);
        System.out.println("uid==="+uid);
        if(files !=null && files.size()>0){
            for (File file : files) {
                System.out.println("文件=="+file.getPath());
                RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
                builder.addFormDataPart("jokeFiles",file.getName(),requestBody);
            }
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        RetrofitManager.getInstance(BaseApplication.getContext()).createBaseApi().moreUploadFile(Api.MORE_UPLOAD_File,
                parts,
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
