package com.kailin.architecture_model.util;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.concurrent.atomic.AtomicReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class LoggerUtil {

    private static final AtomicReference<LoggerUtil> reference = new AtomicReference<>();

    public static LoggerUtil getInstance() {
        while (true) {
            LoggerUtil instance = reference.get();
            if (instance != null)
                return instance;

            instance = new LoggerUtil();
            if (reference.compareAndSet(null, instance))
                return instance;
        }
    }

    private LoggerUtil(){
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable throwable) {
        Logger.log(priority, tag, message, throwable);
    }

    public void d(@NonNull String message, @Nullable Object... args) {
        Logger.d(message, args);
    }

    public void d(@Nullable Object object) {
        Logger.d(object);
    }

    public void e(@NonNull String message, @Nullable Object... args) {
        Logger.e(null, message, args);
    }

    public void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        Logger.e(throwable, message, args);
    }

    public void i(@NonNull String message, @Nullable Object... args) {
        Logger.i(message, args);
    }

    public void v(@NonNull String message, @Nullable Object... args) {
        Logger.v(message, args);
    }

    public void w(@NonNull String message, @Nullable Object... args) {
        Logger.w(message, args);
    }

    public void wtf(@NonNull String message, @Nullable Object... args) {
        Logger.wtf(message, args);
    }

    public void json(@Nullable String json) {
        Logger.json(json);
    }

    public void xml(@Nullable String xml) {
        Logger.xml(xml);
    }
}
