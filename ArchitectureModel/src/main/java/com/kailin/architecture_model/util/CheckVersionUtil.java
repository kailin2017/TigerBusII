package com.kailin.architecture_model.util;

import android.os.Build;

import java.util.concurrent.atomic.AtomicReference;

public final class CheckVersionUtil {

    private static final AtomicReference<CheckVersionUtil> reference = new AtomicReference<>();

    public static CheckVersionUtil getInstance() {
        while (true) {
            CheckVersionUtil instance = reference.get();
            if (instance != null)
                return instance;

            instance = new CheckVersionUtil();
            if (reference.compareAndSet(null, instance))
                return instance;
        }
    }

    private CheckVersionUtil(){}

    public boolean isAboveM(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public boolean isAboveN(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public boolean isAboveO(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    public boolean isAboveP(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }

    public boolean isBelowM(){
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M;
    }

    public boolean isBelowN(){
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.N;
    }

    public boolean isBelowO(){
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.O;
    }

    public boolean isBelowP(){
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.P;
    }

    public boolean isBleowVersion(String localVersion,String onlineVersion) {
        String[] localVersions = localVersion.split("\\.");
        String[] onlineVersions = onlineVersion.split("\\.");
        int minLength = Math.min(localVersions.length, onlineVersions.length);
        int t = 0;
        while (t < minLength) {
            int compareResult = localVersions[t].compareTo(onlineVersions[t]);
            if (compareResult > 0) {
                return false;
            } else if (compareResult < 0) {
                return true;
            }
            t++;
        }
        return localVersions.length < onlineVersions.length;
    }
}
