package com.core42matters.android.registrar;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

final class Obfuscator {
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final byte[] IV = new byte[]{(byte) 16, (byte) 7, (byte) 71, (byte) -8, (byte) 32, (byte) 121, (byte) -40, (byte) 72, (byte) 11, (byte) -14, (byte) 50, (byte) -29, (byte) 70, (byte) 65, (byte) -102, (byte) 74};
    private static final String KEYGEN_ALGORITHM = "PBEWITHSHAAND256BITAES-CBC-BC";
    private static final String UTF8 = "UTF-8";
    private static final String header = "42matters.AESObfuscator-1|";
    private Cipher mDecryptor;
    private Cipher mEncryptor;

    final class ValidationException extends Exception {
        private static final long serialVersionUID = 1;

        public ValidationException(String s) {
            super(s);
        }
    }

    public Obfuscator(byte[] salt, String seed, boolean canEncrypt, boolean canDecrypt) {
        try {
            SecretKey secret = new SecretKeySpec(SecretKeyFactory.getInstance(KEYGEN_ALGORITHM).generateSecret(new PBEKeySpec(seed.toCharArray(), salt, 1024, 256)).getEncoded(), "AES");
            if (canEncrypt) {
                this.mEncryptor = Cipher.getInstance(CIPHER_ALGORITHM);
                this.mEncryptor.init(1, secret, new IvParameterSpec(IV));
            }
            if (canDecrypt) {
                this.mDecryptor = Cipher.getInstance(CIPHER_ALGORITHM);
                this.mDecryptor.init(2, secret, new IvParameterSpec(IV));
            }
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Invalid environment", e);
        }
    }

    public String obfuscate(String original, String key) {
        if (original == null) {
            return null;
        }
        try {
            return Base64.encodeToString(this.mEncryptor.doFinal((header + key + original).getBytes("UTF-8")), 0);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Invalid environment", e);
        } catch (GeneralSecurityException e2) {
            throw new RuntimeException("Invalid environment", e2);
        }
    }

    public String unobfuscate(String obfuscated, String key) throws ValidationException {
        if (obfuscated == null) {
            return null;
        }
        try {
            String result = new String(this.mDecryptor.doFinal(Base64.decode(obfuscated, 0)), "UTF-8");
            if (result.indexOf(header + key) == 0) {
                return result.substring(header.length() + key.length(), result.length());
            }
            throw new ValidationException("Header not found (invalid data or key):" + obfuscated);
        } catch (IllegalBlockSizeException e) {
            throw new ValidationException(e.getMessage() + ":" + obfuscated);
        } catch (BadPaddingException e2) {
            throw new ValidationException(e2.getMessage() + ":" + obfuscated);
        } catch (UnsupportedEncodingException e3) {
            throw new RuntimeException("Invalid environment", e3);
        }
    }
}
