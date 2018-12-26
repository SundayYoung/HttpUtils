package com.felix.library.utils;

import com.felix.library.net.BaseServerResponse;
import com.felix.library.net.RestApiProvider;
import com.felix.library.net.RestBaseCallBack;
import com.felix.library.net.RestService;
import okhttp3.RequestBody;
import retrofit2.Call;

import java.util.Map;

/**
 * @author liuhaiyang
 */
public class HttpUtils {

    public static void getData(String url, Object request, RestBaseCallBack callBack) {
        Map<String, Object> queryMap = JsonUtils.javaBeanToMap(request);
        RestService mRestService = getService();
        Call<BaseServerResponse> mCall;
        mCall = mRestService.loadData(url, queryMap);
        mCall.enqueue(callBack);
    }

    public static void getData(String url, RestBaseCallBack callBack) {
        RestService mRestService = getService();
        Call<BaseServerResponse> mCall;
        mCall = mRestService.loadData(url);
        mCall.enqueue(callBack);
    }

    public static void postData(String url, Object request, RestBaseCallBack callBack) {
        RestService mRestService = getService();
        Call<BaseServerResponse> mCall;
        mCall = mRestService.postData(url, request);
        mCall.enqueue(callBack);
    }

    public static void postData(String url, RestBaseCallBack callBack) {
        postData(url, new Object(), callBack);
    }


    public static void upload(String url, Map<String, RequestBody> partMap, RestBaseCallBack callBack) {
        RestService mRestService = getService();
        Call<BaseServerResponse> mCall;
        mCall = mRestService.uploadFile(url, partMap);
        mCall.enqueue(callBack);
    }

    private static RestService getService() {
        return RestApiProvider.getInstance().builder().getApiService(RestService.class);
    }
}
