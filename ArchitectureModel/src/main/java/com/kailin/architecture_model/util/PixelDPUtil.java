package com.kailin.architecture_model.util;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.concurrent.atomic.AtomicReference;

public final class PixelDPUtil {

    private static final AtomicReference<PixelDPUtil> reference = new AtomicReference<>();

    public static PixelDPUtil getInstance() {
        while (true) {
            PixelDPUtil instance = reference.get();
            if (instance != null)
                return instance;

            instance = new PixelDPUtil();
            if (reference.compareAndSet(null, instance))
                return instance;
        }
    }

    private PixelDPUtil(){}

    /**
     * Covert dp to px
     *
     * @param dp
     * @param context
     * @return pixel
     */
    public int convertDpToPixel(float dp, Context context) {
        float px = dp * getDensity(context);
        return (int) px;
    }

    /**
     * Covert px to dp
     *
     * @param px
     * @param context
     * @return dp
     */
    public int convertPixelToDp(float px, Context context) {
        float dp = px / getDensity(context);
        return (int) dp;
    }

    /**
     * 取得螢幕密度
     * 120dpi = 0.75
     * 160dpi = 1 (default)
     * 240dpi = 1.5
     *
     * @param context
     * @return
     */
    private float getDensity(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }
}
