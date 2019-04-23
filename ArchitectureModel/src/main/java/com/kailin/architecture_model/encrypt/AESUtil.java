package com.kailin.architecture_model.encrypt;

import android.security.keystore.KeyProperties;

import javax.crypto.spec.IvParameterSpec;

public class AESUtil extends CipherUtil {

    private static volatile AESUtil util;

    public static AESUtil getInstance() {
        if (util == null) {
            synchronized (CipherUtil.class) {
                if (util == null)
                    util = new AESUtil();
            }
        }
        return util;
    }

    public static AESUtil createInstance(String keyString, String ivParameterSpec) {
        synchronized (CipherUtil.class) {
            util = new AESUtil(keyString, ivParameterSpec);
        }
        return util;
    }

    protected String keyString;
    protected IvParameterSpec ivParameterSpec;
    protected final String algorithm = KeyProperties.KEY_ALGORITHM_AES;
    protected final String transformation = String.format(ALGORITHM_FORMAT,
            KeyProperties.KEY_ALGORITHM_AES,
            KeyProperties.BLOCK_MODE_CBC,
            "PKCS5Padding");

    protected AESUtil() {
        keyString = "kailin12kailin12kailin12";
        ivParameterSpec = new IvParameterSpec("kailin12kailin12".getBytes());
    }

    protected AESUtil(String keyString, String ivParameterSpec) {
        this.keyString = keyString;
        this.ivParameterSpec = new IvParameterSpec(ivParameterSpec.getBytes());
    }

    @Override
    protected String getKeyString() {
        return keyString;
    }

    @Override
    protected IvParameterSpec getIvParameterSpec() {
        return ivParameterSpec;
    }

    @Override
    protected String getAlgorithm() {
        return algorithm;
    }

    @Override
    protected String getTransformation() {
        return transformation;
    }
}
