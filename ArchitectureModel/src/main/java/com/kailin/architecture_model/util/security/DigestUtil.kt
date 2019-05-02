package com.kailin.utillibrary.security

import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.atomic.AtomicReference

class DigestUtil {

    fun encrypt(value: String, mode: String): String {
        return encrypt(value, MessageDigest.getInstance(mode))
    }

    private fun encrypt(value: String, digest: MessageDigest): String {
        digest.reset()
        digest.update(value.toByteArray(Charsets.UTF_8))
        return String.format("%040x", BigInteger(1, digest.digest()))
    }

    companion object {

        const val SHA_1 = "SHA"
        const val SHA_256 = "SHA-256"
        const val SHA_384 = "SHA-384"
        const val SHA_512 = "SHA-512"

        private val reference = AtomicReference<DigestUtil>()

        fun instance(): DigestUtil {
            while (true) {
                var instance = reference.get()
                if (instance != null)
                    return instance

                instance = DigestUtil()
                if (reference.compareAndSet(null, instance))
                    return instance
            }
        }
    }
}