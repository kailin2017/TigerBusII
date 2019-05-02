package com.kailin.utillibrary.security

import android.content.Context
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.*
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec

class RSAUtil private constructor(
    context: Context,
    publicKeyString: String?,
    privateKeyString: String?
) : CipherUtil() {

    private var privateKey: PrivateKey? = null
    private var publicKey: PublicKey? = null

    init {
        val sp = SecurityPreferencesUtil.getInstance(context)
        if (publicKeyString != null || privateKeyString != null) {
            createKey(publicKeyString, privateKeyString)
        } else if (sp.getString(SP_PUBLIC_KEY).isEmpty() || sp.getString(SP_PRIVATE_KEY).isEmpty()) {
            val keyPairGen = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA)
            keyPairGen.initialize(1024, SecureRandom())
            val keyPair = keyPairGen.generateKeyPair()
            publicKey = keyPair.public
            privateKey = keyPair.private
        } else {
            createKey(sp.getString(SP_PUBLIC_KEY), sp.getString(SP_PRIVATE_KEY))
        }
    }

    private fun createKey(publicKeyString: String?, privateKeyString: String?) {
        val keyFactory = KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_RSA)
        if (publicKeyString != null) {
            val publicKeyBuffer = Base64.decode(publicKeyString, Base64.DEFAULT)
            val x509EncodedKeySpec = X509EncodedKeySpec(publicKeyBuffer)
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec)
        }
        if (privateKeyString != null) {
            val privateKeyBuffer = Base64.decode(privateKeyString, Base64.DEFAULT)
            val privateCrtKeySpec = PKCS8EncodedKeySpec(privateKeyBuffer)
            privateKey = keyFactory.generatePrivate(privateCrtKeySpec)
        }
    }

    override val key: Key?
        get() = null

    override val ivParameterSpec: IvParameterSpec?
        get() = null

    override val transformation: String
        get() = "${KeyProperties.KEY_ALGORITHM_RSA}/${KeyProperties.BLOCK_MODE_ECB}/${KeyProperties.ENCRYPTION_PADDING_RSA_OAEP}"

    override fun initCipher(mode: Int): Cipher {
        val cipher = Cipher.getInstance(transformation)
        if (mode == Cipher.ENCRYPT_MODE) {
            cipher.init(mode, publicKey)
        } else {
            cipher.init(mode, privateKey)
        }
        return cipher
    }

    companion object {
        private val referenceMap = ConcurrentHashMap<String, AtomicReference<RSAUtil>>()
        private const val SP_PRIVATE_KEY = "SP_PRIVATE_KEY"
        private const val SP_PUBLIC_KEY = "SP_PUBLIC_KEY"

        fun getInstance(context: Context, publicKeyString: String?, privateKeyString: String?): RSAUtil {
            val mapKey = publicKeyString ?: "DEFAULT"
            var reference = referenceMap[mapKey]
            if (reference == null) {
                reference = AtomicReference()
                referenceMap[mapKey] = reference
            }

            while (true) {
                var util = reference.get()
                if (util != null)
                    return util

                util = RSAUtil(context, publicKeyString, privateKeyString)
                if (reference.compareAndSet(null, util))
                    return util
            }
        }
    }
}