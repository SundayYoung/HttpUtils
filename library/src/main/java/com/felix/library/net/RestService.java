package com.felix.library.net;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

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
