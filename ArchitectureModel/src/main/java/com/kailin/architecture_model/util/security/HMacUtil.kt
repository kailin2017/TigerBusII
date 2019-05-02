package com.kailin.architecture_model.util.security

import com.kailin.utillibrary.CheckVersionUtil
import java.util.concurrent.atomic.AtomicReference
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class HMacUtil {

    fun encrypt(data: String, key: String, algorithm: String): String {
        return try {
            val signingKey = SecretKeySpec(key.toByteArray(charset("UTF-8")), algorithm)
            val mac = Mac.getInstance(algorithm)
            mac.init(signingKey)
            val result = mac.doFinal(data.toByteArray(charset("UTF-8")))
            val base64Result: ByteArray = if (CheckVersionUtil.instance.isAboveO) {
                java.util.Base64.getEncoder().encode(result)
            } else {
                android.util.Base64.encode(result, android.util.Base64.NO_WRAP)
            }
            String(base64Result)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    companion object {

        const val hmac_sha1 = "HmacSHA1"
        const val hmac_sha256 = "HmacSHA256"

        private val reference = AtomicReference<HMacUtil>()

        val instance: HMacUtil
            get() {
                while (true) {
                    var instance = reference.get()
                    if (instance != null)
                        return instance

                    instance = HMacUtil()
                    if (reference.compareAndSet(null, instance))
                        return instance
                }
            }
    }
}