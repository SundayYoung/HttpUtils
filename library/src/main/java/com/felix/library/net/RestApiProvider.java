package com.felix.library.net;


import okhttp3.CertificatePinner;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by liuhaiyang on 2016/9/6.
 * Singleton don't new construction method
 */
public final class RestApiProvider implements RestApiProviderBase {

    private final Map<String, Object> mServiceInstances = new HashMap<>();
    private Retrofit mRetrofit;
    private OkHttpClient mOkHttpClient;
    private static volatile RestApiProvider sInstance;


    private RestApiProvider() {
    }

    public RestApiProvider withInterceptor(Interceptor intertor) {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(intertor)
                    .build();
        }
        return sInstance;
    }

    public RestApiProvider withInterceptor(Interceptor intertor, CertificatePinner pinner) {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(intertor)
                    .certificatePinner(pinner)
                    .build();
        }
        return sInstance;
    }

    public RestApiProvider builder() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl("http://hiyoung.top")
                    .addConverterFactory(GsonConverterFactory.create())
                    //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return sInstance;
    }

    public static RestApiProvider getInstance() {
        if (sInstance == null) {
            synchronized (RestApiProvider.class) {
                if (sInstance == null) {
                    sInstance = new RestApiProvider();
                }
            }
        }
        return sInstance;
    }

    @Override
    public <T> T getApiService(Class<T> clazz) {
        T service;

        if ((service = (T) mServiceInstances.get(clazz.getCanonicalName())) != null) {
            return service;
        }

        service = mRetrofit.create(clazz);
        mServiceInstances.put(Objects.requireNonNull(clazz.getCanonicalName()), service);
        return service;
    }

}
