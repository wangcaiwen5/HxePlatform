package com.hxe.hxeplatform.rxretrofit.http;

import android.content.Context;
import android.util.Log;


import com.hxe.hxeplatform.rxretrofit.common.BaseApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;


/**
 * Author:wangcaiwen
 * Time:2017/11/17.
 * Description:单例静态内部类模式
 */

public class RetrofitManager {

    private BaseApiService baseApiService;
    private static OkHttpClient okHttpClient;
    public static String baseUrl= BaseApi.BASE_URL;
    private static Context mContext;
    //private static RetrofitManager retrofitInstance;;
    private static Retrofit retrofit;




    private static class  SingletonHolder{
        private static final RetrofitManager INSTANCE = new RetrofitManager(mContext);

    }

    public static RetrofitManager getInstance(Context context){
        if(context !=null){
            mContext=context;
        }
        return SingletonHolder.INSTANCE;
    }

    public static RetrofitManager getInstance(Context context,String url){
        if(context !=null){
            mContext=context.getApplicationContext();
        }
        return new RetrofitManager(context,url);
    }

    public static RetrofitManager getInstance(Context context, String url, Map<String,String> params){
        if(context !=null){
            mContext=context.getApplicationContext();
        }
        return new RetrofitManager(context,url);
    }


    private RetrofitManager(Context context, String url) {
        this(context,url,null);
    }

    private RetrofitManager(Context context) {
        this(context,baseUrl,null);
    }

    private RetrofitManager(Context context, String url, Map<String, String> headers) {


         okHttpClient = new OkHttpClient.Builder()
                //.addInterceptor();// TODO: 2017/11/17 添加拦截器
                .connectTimeout(30, TimeUnit.SECONDS)//链接超时
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
            throw new RuntimeException("service为空呀!!!");
        }
        return retrofit.create(service1);

    }


    public void json(String url, RequestBody body){

        io.reactivex.Observable<ResponseBody> responseBodyObservable = baseApiService.jsonData(url, body);
        responseBodyObservable.subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {
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



    private MyShowCallBack myShowCallBack;

    public void  setMyShowCallBack(MyShowCallBack myShowCallBack){
        this.myShowCallBack = myShowCallBack;
    }

   public interface  MyShowCallBack{
       void onError(Throwable e);
       void onSuccess(ResponseBody responseBody);
   }



}
