package com.felix.httputils.net;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by liuhaiyang on 2016/9/6.
 */
public interface RestService {

    @GET
    Call<BaseServerResponse> loadData(@Url String url, @QueryMap Map<String, Object> map);

    @GET
    Call<BaseServerResponse> loadData(@Url String url);

    @POST
    Call<BaseServerResponse> postData(@Url String url, @Body Object body);

    @POST
    @Multipart
    Call<BaseServerResponse> uploadFile(@Url String url, @PartMap Map<String, RequestBody> params);

    @POST
    @Multipart
    Call<BaseServerResponse> uploadFile(@Url String url, @PartMap Map<String, RequestBody> params, @Header("token") String token);

    @POST
    @Multipart
    Call<BaseServerResponse> uploadFile(@Url String url, @Part() List<MultipartBody.Part> parts);

}
