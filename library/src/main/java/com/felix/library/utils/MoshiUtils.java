package com.felix.library.utils;

import android.util.Log;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuhaiyang 2018.12.26
 */
public class MoshiUtils {

    private static final Moshi MOSHI = new Moshi.Builder().build();

    /**
     * 将已经从json转换后的对象（通常是一个Map）转换成对应Bean的对象实例
     * @param obj java bean
     * @param typeOfT bean type
     * @param <D> 要转换的对象类型
     * @return 转换后的对象
     */
    public static <D> D toBean(Object obj, Type typeOfT) {
        JsonAdapter<Object> jsonAdapter = MOSHI.adapter(typeOfT);
        return (D) jsonAdapter.fromJsonValue(obj);
    }

    /**
     * json to java bean
     * @param obj java bean
     * @return 转换后的对象
     */
    public static <T> T toBean(String obj, Class<T> taClass) {
        JsonAdapter jsonAdapter = MOSHI.adapter(taClass);
        try {
            return (T) jsonAdapter.fromJson(obj);
        } catch (IOException e) {
            Log.e("MoshiUtils toBean", e.toString());
        }
        return null;
    }

    /**
     * java bean to json
     * @param obj java bean
     * @return 转换后的json
     */
    public static String toJson(Object obj) {
        JsonAdapter<Object> jsonAdapter = MOSHI.adapter(Object.class);
        return jsonAdapter.toJson(obj);
    }



    /**
     * java bean to map
     * @param obj java bean
     * @return 转换后的map
     */
    public static Map<String, Object> toMap(Object obj) {
        ParameterizedType newMapType = Types.newParameterizedType(Map.class, String.class, Object.class);
        JsonAdapter<Map<String, Object>> jsonAdapter = MOSHI.adapter(newMapType);
        try {
            return jsonAdapter.fromJson(toJson(obj));
        } catch (IOException e) {
            Log.e("MoshiUtils ToMap", e.toString());
        }
        return new HashMap<>(2);
    }
}
