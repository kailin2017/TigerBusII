package com.kailin.architecture_model.util;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public final class CheckRootUtil {

    private static final AtomicReference<CheckRootUtil> reference = new AtomicReference<>();

    public static CheckRootUtil getInstance() {
        while (true) {
            CheckRootUtil instance = reference.get();
            if (instance != null)
                return instance;

            instance = new CheckRootUtil();
            if (reference.compareAndSet(null, instance))
                return instance;
        }
    }

    private final String[] rootPaths = new String[]{
            "/sbin/",
            "/system/bin/",
            "/system/xbin/",
            "/data/local/xbin/",
            "/data/local/bin/",
            "/system/sd/xbin/",
            "/system/bin/failsafe/",
            "/data/local/"
    };

    public boolean isRooted() {
        return doesFileExists();
    }

    private boolean doesFileExists() {
        for (String path : rootPaths) {
            File file = new File(String.format("%s/su", path));
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }
}
