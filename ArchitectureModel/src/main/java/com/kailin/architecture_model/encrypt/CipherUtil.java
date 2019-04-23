package com.kailin.architecture_model.encrypt;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

abstract class CipherUtil {

    protected final String ALGORITHM_FORMAT = "%s/%s/%s";

    protected abstract String getKeyString();

    protected abstract IvParameterSpec getIvParameterSpec();

    protected abstract String getAlgorithm();

    protected abstract String getTransformation();

    public String encrypt(String value) {
        try {
            Cipher cipher = initCipher(Cipher.ENCRYPT_MODE);
            byte[] result = cipher.doFinal(value.getBytes());
            return Base64.encodeToString(result, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String decrypt(String value) {
        try {
            Cipher cipher = initCipher(Cipher.DECRYPT_MODE);
            byte[] result = cipher.doFinal(Base64.decode(value, Base64.DEFAULT));
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private Cipher initCipher(int mode) throws Exception {
        Cipher cipher = Cipher.getInstance(getTransformation());
        cipher.init(mode, getKey(), getIvParameterSpec());
        return cipher;
    }

    protected Key getKey(){
        return new SecretKeySpec(getKeyString().getBytes(), getAlgorithm());
    }
}
