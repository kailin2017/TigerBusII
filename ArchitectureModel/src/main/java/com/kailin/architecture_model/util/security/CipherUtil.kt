package com.kailin.utillibrary.security

import com.kailin.utillibrary.CheckVersionUtil

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import java.security.Key

abstract class CipherUtil {

    protected abstract val key: Key?

    protected abstract val ivParameterSpec: IvParameterSpec?

    protected abstract val transformation: String

    protected open val encryptCipher: Cipher
        get() = initCipher(Cipher.ENCRYPT_MODE)

    protected open val decryptCipher: Cipher
        get() = initCipher(Cipher.DECRYPT_MODE)

    fun encrypt(value: String): String {
        return try {
            val result = encryptCipher!!.doFinal(value.toByteArray())
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

    fun decrypt(value: String): String {
        return try {
            val base64Decode: ByteArray = if (CheckVersionUtil.instance.isAboveO) {
                java.util.Base64.getDecoder().decode(value)
            } else {
                android.util.Base64.decode(value, android.util.Base64.NO_WRAP)
            }
            val result = decryptCipher!!.doFinal(base64Decode)
            String(result)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    @Throws(Exception::class)
    protected open fun initCipher(mode: Int): Cipher {
        val cipher = Cipher.getInstance(transformation)
        cipher.init(mode, key, ivParameterSpec)
        return cipher
    }
}
