package com.felix.httputils.utils

import com.felix.httputils.net.BaseServerResponse
import com.felix.httputils.net.RestApiProvider
import com.felix.httputils.net.RestBaseCallBack
import com.felix.httputils.net.RestService
import okhttp3.RequestBody
import retrofit2.Call

/**
 * Created by liuhaiyang on 2018/12/25.
 */
class HttpUtils {

    companion object {

        fun getData(url: String, request: Any, callBack: RestBaseCallBack<*>) {
            val queryMap = JsonUtils.javaBeanToMap(request)
            val service = RestApiProvider.getInstance().builder().getApiService(RestService::class.java)
            val mCall: Call<BaseServerResponse> = service.loadData(url, queryMap)
            mCall.enqueue(callBack)
        }

        fun getData(url: String, callBack: RestBaseCallBack<*>) {
            val service = RestApiProvider.getInstance().builder().getApiService(RestService::class.java)
            val mCall: Call<BaseServerResponse> = service.loadData(url)
            mCall.enqueue(callBack)
        }

        fun postData(url: String, request: Any, callBack: RestBaseCallBack<*>) {
            val service = RestApiProvider.getInstance().builder().getApiService(RestService::class.java)
            val mCall: Call<BaseServerResponse> = service.postData(url, request)
            mCall.enqueue(callBack)
        }

        fun postData(url: String, callBack: RestBaseCallBack<*>) {
            postData(url, Any(), callBack)
        }

        fun upload(url: String, partMap: Map<String, RequestBody>, callBack: RestBaseCallBack<*>) {
            val mRestService = RestApiProvider.getInstance().builder().getApiService(RestService::class.java)
            val mCall: Call<BaseServerResponse> = mRestService.uploadFile(url, partMap)
            mCall.enqueue(callBack)
        }
    }
}