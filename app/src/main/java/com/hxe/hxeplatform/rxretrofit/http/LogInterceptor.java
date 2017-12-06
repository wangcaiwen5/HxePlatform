package com.hxe.hxeplatform.rxretrofit.http;

import android.util.Log;

import com.hxe.hxeplatform.base.BaseApplication;
import com.hxe.hxeplatform.utils.AppUtils;
import com.hxe.hxeplatform.utils.NetUtils;
import com.hxe.hxeplatform.utils.SharedPreferencesUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:kson
 * E-mail:19655910@qq.com
 * Time:2017/10/18
 * Description:
 */
public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        boolean internetConnection = NetUtils.isInternetConnection(BaseApplication.getContext());//网络连接状态
        String token = SharedPreferencesUtils.getInstance(BaseApplication.getContext()).getString("token");
        String appVersionCode = AppUtils.getAppVersionCode(BaseApplication.getContext());
        System.out.println("appVersionCode==="+appVersionCode);
        System.out.println("Token==="+token);
//https://www.zhaoapi.cn/quarter/getJokes?source=android&appVersion=100
        //获取到request
        Request request = chain.request();
        //获取方法
        String method = request.method();
        //公共参数
        Map<String,Object> hashmap = new HashMap<>();
        hashmap.put("source","android");
        hashmap.put("appVersion",appVersionCode);
        hashmap.put("token",token+"" );


        //get请求的封装
        if(method.equals("GET")){



            //获取到请求地址api
            HttpUrl httpUrlurl = request.url();
            String newUrl = httpUrlurl.toString();
            /*System.out.println("拦截器修改前url=="+url);
            String newUrl=url;
            for (Map.Entry<String, Object> entry : hashmap.entrySet()) {
                newUrl+= entry.getKey()+"="+entry.getValue()+"&&";
            }*/

            if(!internetConnection){
                //如果没有网络,从缓存获取数据
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                System.out.println("网络关闭,读取缓存");
                System.out.println("网络关闭==="+newUrl);
            }

            if(internetConnection){
                //有网络,缓存时间段,缓存60s
                System.out.println("有网络,设置缓存90s");
                String cacheControl = request.cacheControl().toString();
                request = request.newBuilder().header("Cache-Control","public, max-age=10000")
                        .url(newUrl)
                        .build();  //重新构建请求

            }else{
                System.out.println("网络关闭,缓存cache");
                int cacheTime=60*60*60*60;
                request = request.newBuilder().header("Cache-Control","public, max-age="+cacheTime)
                        .url(newUrl)
                        .build();
            }


        }

        return  chain.proceed(request);
    }
}
