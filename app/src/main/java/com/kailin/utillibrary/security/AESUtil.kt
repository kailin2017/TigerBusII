package com.kailin.utillibrary.security

import android.security.keystore.KeyProperties
import java.security.Key
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESUtil private constructor(private val keyString: String, private val ivString: String) : CipherUtil() {

    override val key: Key
        get() = SecretKeySpec(keyString.toByteArray(), KeyProperties.KEY_ALGORITHM_AES)

    override val ivParameterSpec: IvParameterSpec
        get() = IvParameterSpec(ivString.toByteArray())

    override val transformation: String
        get() = "${KeyProperties.KEY_ALGORITHM_AES}/${KeyProperties.BLOCK_MODE_CBC}/PKCS5Padding"

    companion object {
        private val referenceMap = ConcurrentHashMap<String, AtomicReference<AESUtil>>()

         fun getInstance(keyString: String, ivString: String): AESUtil {
            var reference = referenceMap[keyString]
            if (reference == null) {
                reference = AtomicReference()
                referenceMap[keyString] = reference
            }

            while (true) {
                var util = reference.get()
                if (util != null) {
                    return util
                }

                util = AESUtil(keyString, ivString)
                if (reference.compareAndSet(null, util)){
                    return util
                }
            }
        }
    }
}