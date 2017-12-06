package com.hxe.hxeplatform.rxretrofit.http;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
import retrofit2.http.Url;

/**
 * Author:wangcaiwen
 * Time:2017/11/17.
 * Description:
 */

public interface BaseApiService {

    /**public ---- 数据内容皆被储存起来，就连有密码保护的网页也储存，安全性很低
     private ---- 数据内容只能被储存到私有的cache，仅对某个用户有效，不能共享
     no-cache ---- 可以缓存，但是只有在跟WEB服务器验证了其有效后，才能返回给客户端
     no-store ---- 请求和响应都禁止被缓存
     max-age： ----- 本响应包含的对象的过期时间
     max-stale ---- 允许读取过期时间必须小于max-stale 值的缓存对象。
     no-transform ---- 告知代理,不要更改媒体类型,比如jpg,被你改成png.
     * 以String为参数
     * @param url
     * @param maps
     * @return
     */
    @GET()
    //@FormUrlEncoded
  // @Headers("Cache-Control: public, max-age=3600")
    Observable<ResponseBody> executePost(
            @Url String url,
            @QueryMap Map<String, String> maps);

    @POST()
    @FormUrlEncoded
        // @Headers("Cache-Control: public, max-age=3600")
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
