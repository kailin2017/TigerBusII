package com.kailin.architecture_model.util;

import android.os.Build;

import java.util.concurrent.atomic.AtomicReference;

public final class CheckEmulatorUtil {

    private static final AtomicReference<CheckEmulatorUtil> reference = new AtomicReference<>();

    public static CheckEmulatorUtil getInstance() {
        while (true) {
            CheckEmulatorUtil instance = reference.get();
            if (instance != null)
                return instance;

            instance = new CheckEmulatorUtil();
            if (reference.compareAndSet(null, instance))
                return instance;
        }
    }

    private CheckEmulatorUtil() {
    }

    public boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"))
                || "google_sdk".equals(Build.PRODUCT);
    }
}
