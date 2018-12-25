package com.felix.httputils.utils;

import android.content.Context;
import okhttp3.CertificatePinner;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

public class SSLUtils {

    /**
     * SSL Pinning 获取证书
     *
     * @return certificata
     */
    public static CertificatePinner getCertificata(Context context) {
        Certificate ca = null;

        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            InputStream caInput = context.getResources().openRawResource(0);
            try {
                ca = cf.generateCertificate(caInput);
            } finally {
                caInput.close();
            }
        } catch (CertificateException | IOException e) {
        }

        String certPin = "";
        if (ca != null) {
            certPin = CertificatePinner.pin(ca);
        }
        CertificatePinner certificatePinner = new CertificatePinner.Builder()
                .add("url", certPin)
                .build();

        return certificatePinner;
    }
}
