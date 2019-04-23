package com.kailin.architecture_model.util;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public final class UUIDUtil {

    private static final String PERFERENCES_KEY_UUID = "UUID";

    private static final AtomicReference<UUIDUtil> reference = new AtomicReference<>();

    public static UUIDUtil getInstance(Context context) {
        while (true) {
            UUIDUtil instance = reference.get();
            if (instance != null)
                return instance;

            instance = new UUIDUtil(context);
            if (reference.compareAndSet(null, instance))
                return instance;
        }
    }

    private final Context context;
    private final SharedPreferencesUtil sharedPreferencesUtil;

    private UUIDUtil(Context context) {
        this.context = context.getApplicationContext();
        this.sharedPreferencesUtil = SharedPreferencesUtil.getInstance(context);
    }

    public String getUUID() {
        String uuid = sharedPreferencesUtil.getString(PERFERENCES_KEY_UUID);
        if (TextUtils.isEmpty(uuid)) {
            String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            uuid = UUID.nameUUIDFromBytes(androidId.getBytes()).toString();
            if (TextUtils.isEmpty(uuid)) {
                uuid = UUID.randomUUID().toString();
            }
            sharedPreferencesUtil.putString(PERFERENCES_KEY_UUID, uuid);
        }
        return uuid;
    }
}
