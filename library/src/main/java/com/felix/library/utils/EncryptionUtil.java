package com.felix.library.utils;

import android.content.Context;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 入参加密工具类
 *
 * @author liuhaiyang
 */
public class EncryptionUtil {

    private static String publicKey;

    public static JSONObject getEncryptionContent(Context context, JSONObject busJson) {
        String keyOriginal = AlgorithmUtil.getRandomString(16);
        if (publicKey == null) {
            publicKey = getPublicKeySit(context);
        }
        String keyBase64 = AlgorithmUtil.encryptDataByPublicKey(keyOriginal.getBytes(), AlgorithmUtil.keyStrToPublicKey(publicKey));

        String ivOriginal = AlgorithmUtil.getRandomString(16);
        while (ivOriginal.equals(keyOriginal)) {
            ivOriginal = AlgorithmUtil.getRandomString(16);
        }
        String ivBase64 = Base64.encodeToString(ivOriginal.getBytes(), Base64.NO_WRAP);

        JSONObject valueObj = new JSONObject();

        try {
            valueObj.put("timestamp", System.currentTimeMillis());
            valueObj.put("OS", "ANDROID");
            valueObj.put("appVersion", Build.VERSION.RELEASE);
            valueObj.put("data", busJson);

        } catch (JSONException e) {
            Log.e("JSON-Value", e.getMessage());
        }

        byte[] valueAes = AlgorithmUtil.encryptsss(keyOriginal, ivOriginal, valueObj.toString());
        String valueBase64 = Base64.encodeToString(valueAes, Base64.NO_WRAP);

        StringBuilder sb = new StringBuilder();
        sb.append(ivBase64);
        sb.append(valueBase64);
        String mac = AlgorithmUtil.sha256_HMAC(sb.toString(), keyOriginal);

        JSONObject dataObj = new JSONObject();

        try {
            dataObj.put("iv", ivBase64);
            dataObj.put("value", valueBase64);
            dataObj.put("mac", mac);
        } catch (JSONException e) {
            Log.e("JSON-Data", e.getMessage());
        }

        String dataBase64 = Base64.encodeToString(dataObj.toString().getBytes(), Base64.NO_WRAP);

        JSONObject postjson = new JSONObject();
        try {
            postjson.put("key", keyBase64);
            postjson.put("data", dataBase64);
        } catch (JSONException e) {
            Log.e("JSON-Post", e.getMessage());
        }
        return postjson;
    }

    /**
     * 获取公钥文件内容
     *
     * @return
     */
    private static String getPublicKeySit(Context context) {
        try {
            //0 = R.raw.id_rsa
            InputStream is = context.getResources().openRawResource(0);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String str;
            StringBuilder sb = new StringBuilder();
            while ((str = br.readLine()) != null) {
                if (!str.contains("----")) {
                    sb.append(str);
                    sb.append("\n");
                }
            }
            br.close();
            isr.close();
            is.close();
            return sb.toString();
        } catch (IOException e) {
            Log.e("GetPublicKey", e.getMessage());
        }
        return null;
    }
}
