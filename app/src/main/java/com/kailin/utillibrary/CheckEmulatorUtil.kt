package com.kailin.utillibrary

import android.os.Build

import java.util.concurrent.atomic.AtomicReference

class CheckEmulatorUtil private constructor() {

    val isEmulator: Boolean
        get() = (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                || Build.PRODUCT == "google_sdk")

    companion object {

        private val reference = AtomicReference<CheckEmulatorUtil>()

        fun instance(): CheckEmulatorUtil {
            while (true) {
                var instance: CheckEmulatorUtil? = reference.get()
                if (instance != null)
                    return instance

                instance = CheckEmulatorUtil()
                if (reference.compareAndSet(null, instance))
                    return instance
            }
        }
    }
}
