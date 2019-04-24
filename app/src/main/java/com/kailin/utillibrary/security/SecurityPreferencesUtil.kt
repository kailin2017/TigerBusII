package com.kailin.utillibrary.security

import android.content.Context
import android.content.SharedPreferences
import com.kailin.utillibrary.BuildConfig
import java.util.concurrent.atomic.AtomicReference

class SecurityPreferencesUtil private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.applicationContext.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    private val aesUtil = AESUtil.getInstance("kailin12kailin12kailin12", "kailin12kailin12")

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(encrypt(key), value).commit()
    }

    fun getBoolean(key: String, defaultValue: Boolean = true): Boolean {
        return sharedPreferences.getBoolean(encrypt(key), defaultValue)
    }

    fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(encrypt(key), value).commit()
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(encrypt(key), 0)
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(encrypt(key), encrypt(value)).commit()
    }

    fun getString(key: String): String {
        return decrypt(sharedPreferences.getString(encrypt(key), ""))
    }

    private fun encrypt(str: String): String {
        return aesUtil.encrypt(str)
    }

    private fun decrypt(str: String): String {
        return aesUtil.decrypt(str)
    }

    companion object {

        private val reference = AtomicReference<SecurityPreferencesUtil>()

        fun getInstance(context: Context): SecurityPreferencesUtil {
            while (true) {
                var instance: SecurityPreferencesUtil? = reference.get()
                if (instance != null)
                    return instance

                instance = SecurityPreferencesUtil(context)
                if (reference.compareAndSet(null, instance))
                    return instance
            }
        }
    }
}
