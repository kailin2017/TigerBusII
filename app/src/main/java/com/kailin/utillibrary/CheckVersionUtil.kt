package com.kailin.utillibrary

import android.os.Build

import java.util.concurrent.atomic.AtomicReference

class CheckVersionUtil private constructor() {

    val isAboveM: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

    val isBelowM: Boolean
        get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.M

    val isAboveN: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

    val isBelowN: Boolean
        get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.N

    val isAboveO: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    val isBelowO: Boolean
        get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.O

    val isAboveP: Boolean
        get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

    val isBelowP: Boolean
        get() = Build.VERSION.SDK_INT < Build.VERSION_CODES.P

    fun isNewVersion(version: String): Boolean {
        val appVersion = BuildConfig.VERSION_NAME.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val checkVersion = version.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val minLength = Math.min(appVersion.size, checkVersion.size)
        var t = 0
        while (t < minLength) {
            val compareResult = appVersion[t].compareTo(checkVersion[t])
            if (compareResult > 0) {
                return false
            } else if (compareResult < 0) {
                return true
            }
            t++
        }
        return appVersion.size < checkVersion.size
    }

    companion object {

        private val reference = AtomicReference<CheckVersionUtil>()

        fun instance(): CheckVersionUtil {
            while (true) {
                var instance: CheckVersionUtil? = reference.get()
                if (instance != null)
                    return instance

                instance = CheckVersionUtil()
                if (reference.compareAndSet(null, instance))
                    return instance
            }
        }
    }
}
