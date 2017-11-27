package com.hxe.hxeplatform.rxretrofit.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Author:wangcaiwen
 * Time:2017/11/17.
 * Description:
 */

public interface BaseApiService {

    /**
     * 以String为参数
     * @param url
     * @param maps
     * @return
     */
   /* @GET("{url}")
    Observable<BaseResponse<Object>> executeGet(
            @Path("url") String url,
            @QueryMap Map<String, String> maps);*/

    /**
     * 以json为参数上传
     * @param url
     * @param json
     * @return
     */
    @POST("{url}")
   Observable<ResponseBody> jsonData(
            @Path("url") String url,
            @Body RequestBody json);


    /**
     * 文件上传
     * @param url
     * @param body
     * @return
     */
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> uploadFile(
            @Path("url") String url,
            @Part("image\"; filename=\"image.jpg") RequestBody body);

    /**
     *
     */

}
