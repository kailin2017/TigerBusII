package com.kailin.architecture_model.util;

import android.text.TextUtils;

import java.util.concurrent.atomic.AtomicReference;

public final class CheckProxyUtil {

    private static final AtomicReference<CheckProxyUtil> reference = new AtomicReference<>();

    public static CheckProxyUtil getInstance() {
        while (true) {
            CheckProxyUtil instance = reference.get();
            if (instance != null)
                return instance;

            instance = new CheckProxyUtil();
            if (reference.compareAndSet(null, instance))
                return instance;
        }
    }

    private CheckProxyUtil() {
    }

    public boolean isProxy() {
        int proxyPort;
        try {
            proxyPort = Integer.valueOf(System.getProperty("http.proxyHost"));
        } catch (Exception e) {
            proxyPort = -1;
        }
        return !TextUtils.isEmpty(System.getProperty("http.proxyPort")) && proxyPort > -1;
    }
}
