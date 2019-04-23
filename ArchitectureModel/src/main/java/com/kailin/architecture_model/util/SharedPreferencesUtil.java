package com.kailin.architecture_model.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.kailin.architecture_model.encrypt.AESUtil;

import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

public final class SharedPreferencesUtil {

    private static final AtomicReference<SharedPreferencesUtil> reference = new AtomicReference<>();

    public static SharedPreferencesUtil getInstance(Context context) {
        while (true) {
            SharedPreferencesUtil instance = reference.get();
            if (instance != null)
                return instance;

            instance = new SharedPreferencesUtil(context);
            if (reference.compareAndSet(null, instance))
                return instance;
        }
    }

    private final SharedPreferences sharedPreferences;
    private final AESUtil aesDefaultUtil = AESUtil.getInstance();

    private SharedPreferencesUtil(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences("ArchitectureModel", Context.MODE_PRIVATE);
    }

    private SharedPreferencesUtil(Context context,String name) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(encrypt(key), value).apply();
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, true);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(encrypt(key), defaultValue);
    }

    public void putInt(String key, int value) {
        sharedPreferences.edit().putInt(encrypt(key), value).commit();
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(encrypt(key), defaultValue);
    }

    public void putString(String key, String value) {
        sharedPreferences.edit().putString(encrypt(key), encrypt(value)).commit();
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return decrypt(sharedPreferences.getString(encrypt(key), defaultValue));
    }

    public void putStringSet(String key, Set<String> value) {
        Set<String> temp = new TreeSet<>();
        for (String s : value) {
            temp.add(encrypt(s));
        }
        sharedPreferences.edit().putStringSet(encrypt(key), temp).commit();
    }

    public Set<String> getStringSet(String key) {
        Set<String> temp = sharedPreferences.getStringSet(encrypt(key), new TreeSet<>());
        Set<String> result = new TreeSet<>();
        for (String s : temp) {
            result.add(aesDefaultUtil.decrypt(s));
        }
        return result;
    }

    private String encrypt(String string) {
        // Android 7.0 以下手機不可含有 \n
        return aesDefaultUtil.encrypt(string).replace("\n", "");
    }

    private String decrypt(String string) {
        return aesDefaultUtil.decrypt(string);
    }
}
