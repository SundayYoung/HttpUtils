package com.felix.httputils.net;

/**
 * Created by liuhaiyang on 2016/9/6.
 */
public interface RestApiProviderBase {
    <T> T getApiService(Class<T> var1);
}
