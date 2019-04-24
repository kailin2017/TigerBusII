package com.kailin.utillibrary

import android.content.Context
import android.provider.Settings
import com.kailin.utillibrary.security.SecurityPreferencesUtil
import java.util.concurrent.atomic.AtomicReference

class UUIDUtil private constructor(private val context: Context) {

    val UUID: String
        get() {
            val spUtil = SecurityPreferencesUtil.getInstance(context)
            var uuid = spUtil.getString(UUID_UTIL_SP_UUID_VALUE)
            if (uuid.isEmpty()) {
                val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                uuid = java.util.UUID.nameUUIDFromBytes(androidId.toByteArray()).toString()
                spUtil.putString(UUID_UTIL_SP_UUID_VALUE, uuid)
            }
            return uuid
        }

    companion object {
        private const val UUID_UTIL_SP_UUID_VALUE = "UUID_UTIL_SP_UUID_VALUE"
        private val reference = AtomicReference<UUIDUtil>()

        fun getInstance(context: Context): UUIDUtil {
            while (true) {
                var uuidUtil: UUIDUtil? = reference.get()
                if (uuidUtil != null)
                    return uuidUtil

                uuidUtil = UUIDUtil(context)
                if (reference.compareAndSet(null, uuidUtil))
                    return uuidUtil
            }
        }
    }
}