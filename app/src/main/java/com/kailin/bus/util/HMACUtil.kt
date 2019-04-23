package com.kailin.bus.util

import java.util.Base64
import java.util.concurrent.atomic.AtomicReference

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class HMACUtil private constructor() {

    private val HMAC_SHA1_ALGORITHM = "HmacSHA1"

    fun sha1Encrypt(data: String, key: String): String {
        return try {
            val signingKey = SecretKeySpec(key.toByteArray(charset("UTF-8")), HMAC_SHA1_ALGORITHM)
            val mac = Mac.getInstance(HMAC_SHA1_ALGORITHM)
            mac.init(signingKey)
            val result = mac.doFinal(data.toByteArray(charset("UTF-8")))
            Base64.getEncoder().encodeToString(result)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }

    }

    companion object {

        private val reference = AtomicReference<HMACUtil>()

        val instance: HMACUtil
            get() {
                while (true) {
                    var instance: HMACUtil? = reference.get()
                    if (instance != null)
                        return instance

                    instance = HMACUtil()
                    if (reference.compareAndSet(null, instance))
                        return instance
                }
            }
    }

}
