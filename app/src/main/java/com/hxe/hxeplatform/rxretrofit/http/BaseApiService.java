package com.hxe.hxeplatform.rxretrofit.http;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Author:wangcaiwen
 * Time:2017/11/17.
 * Description:
 */

public interface BaseApiService {
    @Streaming //大文件时要加不然会OOM
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
    /*
     * 以String为参数
     * @param url
     * @param maps
     * @return
     */
    @GET()
    Observable<ResponseBody> executePost(
            @Url String url,
            @QueryMap Map<String, String> maps);

    @POST()
    @FormUrlEncoded
    Observable<ResponseBody> loginPost(
            @Url String url,
            @FieldMap Map<String, String> maps);

    /**
     * 以json为参数上传
     * @param url
     * @param json
     * @return
     */
    @POST()
    @Multipart
   Observable<ResponseBody> jsonData(
            @Url String url,
            @Body RequestBody json);



    /**
     *多文件上传
     */
    @Multipart
    @POST()
    Observable<ResponseBody> moreFileUpload(@Url String url,@Part() List<MultipartBody.Part> parts);

}
