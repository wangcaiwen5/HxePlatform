package com.hxe.hxeplatform.mvp.model;

import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.rxretrofit.common.Api;
import com.hxe.hxeplatform.rxretrofit.http.RetrofitManager;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Author:wangcaiwen
 * Time:2017/12/1.
 * Description:
 */

public class UpLoadVideoModel {

    /**
     *
     * @param uid
     * @param videoFile
     * @param coverFile
     * @param videoDesc
     * @param latitude  经度
     * @param longitude  纬度
     */
   public void UpLoadVideo(String uid, File videoFile, File coverFile, String videoDesc,String latitude,String longitude){

       MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM)
               .addFormDataPart("uid",uid)
               .addFormDataPart("latitude",latitude)
               .addFormDataPart("longitude",longitude)
               .addFormDataPart("videoDesc",videoDesc);

       RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),videoFile);
       RequestBody requestBody1=RequestBody.create(MediaType.parse("multipart/form-data"),coverFile);
       builder.addFormDataPart("coverFile",videoFile.getName(),requestBody1);
       builder.addFormDataPart("videoFile",videoFile.getName(),requestBody);
       List<MultipartBody.Part> parts = builder.build().parts();

       RetrofitManager.getInstance(BaseApplication.getContext()).createBaseApi().moreUploadFile(Api.PUBLISTSH_VIDEO,
               parts,
               new RetrofitManager.MyShowCallBack() {
                   @Override
                   public void onError(Throwable e) {
                        mUploadFile.mUploadVideoError(e);
                   }

                   @Override
                   public void onSuccess(ResponseBody value) {
                        mUploadFile.mUploadVideoSuccess(value);
                   }
               }
       );

   };


    private MUploadVideoFile mUploadFile;

    public void setmUploadFile( MUploadVideoFile mUploadFile) {
        this.mUploadFile = mUploadFile;
    }

    public interface MUploadVideoFile{
        void mUploadVideoSuccess(ResponseBody json);
        void mUploadVideoError(Throwable e);

    }

}
