package com.kailin.architecture_model.encrypt;

import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.spec.IvParameterSpec;

public final class RSAUtil extends CipherUtil {

    private static volatile RSAUtil util;

    public static RSAUtil createInstance(String publicKey){
        synchronized (RSAUtil.class){
            util = new RSAUtil(publicKey);
        }
        return util;
    }

    public static RSAUtil getInstance(){
        return util;
    }

    private final String publicKey;
    private final String algorithm = KeyProperties.KEY_ALGORITHM_RSA;
    private final String transformation = String.format(ALGORITHM_FORMAT,
            KeyProperties.KEY_ALGORITHM_RSA,
            KeyProperties.BLOCK_MODE_ECB,
            KeyProperties.ENCRYPTION_PADDING_RSA_OAEP);

    private RSAUtil(String publicKey) {
        this.publicKey = publicKey;
    }

    @Override
    protected String getKeyString() {
        return null;
    }

    @Override
    protected IvParameterSpec getIvParameterSpec() {
        return null;
    }

    @Override
    protected String getAlgorithm() {
        return algorithm;
    }

    @Override
    protected String getTransformation() {
        return transformation;
    }

    @Override
    protected Key getKey() {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(publicKey, Base64.DEFAULT));
            KeyFactory keyFactory = KeyFactory.getInstance(KeyProperties.KEY_ALGORITHM_RSA);
            return keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
