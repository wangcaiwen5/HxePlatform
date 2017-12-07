package com.hxe.hxeplatform.rxretrofit.http;

import android.content.Context;
import android.os.Environment;
import android.util.Log;


import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.rxretrofit.common.BaseApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Author:wangcaiwen
 * Time:2017/11/17.
 * Description:单例静态内部类模式
 */

public class RetrofitManager {

    private BaseApiService baseApiService;
    private static OkHttpClient okHttpClient;
    private static final long cacheSize = 1024 * 1024 * 20;// 缓存文件最大限制大小20
    private static String cacheDirectory = Environment.getExternalStorageDirectory() + "/okttpcaches"; // 设置缓存文件路径
    private static Cache cache = new Cache(new File(BaseApplication.getContext().getCacheDir()+"/okttpcaches"), cacheSize);  //

    public static String baseUrl= BaseApi.BASE_URL;
    private static Context mContext;
    //private static RetrofitManager retrofitInstance;;
    private static Retrofit retrofit;

    private static class  SingletonHolder{
        private static final RetrofitManager INSTANCE = new RetrofitManager(mContext);

    }

    public static RetrofitManager getInstance(Context context){
        if(context !=null){
            mContext=context.getApplicationContext();
        }
        return SingletonHolder.INSTANCE;
    }




    private RetrofitManager(Context context, String url) {
        this(context,url,null);
    }

    private RetrofitManager(Context context) {
        this(context,baseUrl,null);
    }

    private RetrofitManager(Context context, String url, Map<String, String> headers) {


         okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpInterceptor())
                // .addNetworkInterceptor(new LogInterceptor())
                .connectTimeout(60*5, TimeUnit.SECONDS)//链接超时
                .writeTimeout(30, TimeUnit.SECONDS)// 设置写入超时时间
                .readTimeout(30, TimeUnit.SECONDS)// 设置读取数据超时时间
                 .cache(cache)
                .build();

        retrofit=new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }

    public RetrofitManager createBaseApi(){
        baseApiService = create(BaseApiService.class);
        return this;
    }

    public <T> T create(final Class<T> service1){
        if(service1==null){
            throw new RuntimeException("service为空!!!");
        }
        return retrofit.create(service1);

    }

    public void login(String url,  Map<String,String> body,final MyShowCallBack myShowCallBack){

        Observable<ResponseBody> responseBodyObservable = baseApiService.loginPost(url,body);

        responseBodyObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody s) {
                myShowCallBack.onSuccess(s);

            }

            @Override
            public void onError(Throwable e) {
                myShowCallBack.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });



    }


    public void moreUploadFile(String url, List<MultipartBody.Part> parts,final MyShowCallBack myShowCallBack){

        Observable<ResponseBody> responseBodyObservable = baseApiService.moreFileUpload(url,parts);

        responseBodyObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody s) {
                myShowCallBack.onSuccess(s);

            }

            @Override
            public void onError(Throwable e) {
                myShowCallBack.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });



    }
    public void requestData(String url, Map<String,String> body, final MyShowCallBack myShowCallBack){

        Observable<ResponseBody> baseResponseObservable = baseApiService.executePost(url, body);

        baseResponseObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody value) {
                myShowCallBack.onSuccess(value);

            }

            @Override
            public void onError(Throwable e) {
                Log.e("错误:", e + "");
                myShowCallBack.onError(e);
            }

            @Override
            public void onComplete() {

            }
        });



    }




   public interface  MyShowCallBack{
       void onError(Throwable e);
       void onSuccess(ResponseBody value);
      // void onComplete();
   }



}
