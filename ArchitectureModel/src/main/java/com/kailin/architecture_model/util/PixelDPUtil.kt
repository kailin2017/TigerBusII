package com.kailin.architecture_model.util

import android.content.Context
import android.util.DisplayMetrics

import java.util.concurrent.atomic.AtomicReference

class PixelDPUtil private constructor() {

    /**
     * Covert dp to px
     *
     * @param dp
     * @param context
     * @return pixel
     */
    fun convertDpToPixel(dp: Float, context: Context): Int {
        val px = dp * getDensity(context)
        return px.toInt()
    }

    /**
     * Covert px to dp
     *
     * @param px
     * @param context
     * @return dp
     */
    fun convertPixelToDp(px: Float, context: Context): Int {
        val dp = px / getDensity(context)
        return dp.toInt()
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
    private fun getDensity(context: Context): Float {
        val metrics = context.resources.displayMetrics
        return metrics.density
    }

    companion object {

        private val reference = AtomicReference<PixelDPUtil>()

        val instance: PixelDPUtil
            get() {
                while (true) {
                    var instance: PixelDPUtil? = reference.get()
                    if (instance != null)
                        return instance

                    instance = PixelDPUtil()
                    if (reference.compareAndSet(null, instance))
                        return instance
                }
            }
    }
}
