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

    companion object {

        private val reference = AtomicReference<CheckVersionUtil>()

        val instance: CheckVersionUtil
            get() {
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
