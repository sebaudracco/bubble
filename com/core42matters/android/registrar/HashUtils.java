package com.core42matters.android.registrar;

import android.util.Base64;
import com.google.android.gms.common.util.AndroidUtilsLight;
import java.security.MessageDigest;
import java.security.SignatureException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

final class HashUtils {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

    private HashUtils() {
        throw new AssertionError();
    }

    static String hexString(byte[] raw) {
        StringBuilder sb = new StringBuilder();
        for (byte aRaw : raw) {
            sb.append(Integer.toString((aRaw & 255) + 256, 16).substring(1));
        }
        return sb.toString();
    }

    static String md5(String raw) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(raw.getBytes());
            return hexString(md.digest());
        } catch (Exception e) {
            return null;
        }
    }

    static String sha1(String raw) {
        return sha1(raw.getBytes());
    }

    static String sha1(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA1);
            md.update(bytes);
            return hexString(md.digest());
        } catch (Exception e) {
            return null;
        }
    }

    static String sha256(String raw) {
        return sha256(raw.getBytes());
    }

    static String sha256(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA256");
            md.update(bytes);
            return hexString(md.digest());
        } catch (Exception e) {
            return null;
        }
    }

    static String sha1Base64(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance(AndroidUtilsLight.DIGEST_ALGORITHM_SHA1);
            md.update(bytes);
            return Base64.encodeToString(md.digest(), 0);
        } catch (Exception e) {
            return null;
        }
    }

    static String sha256Base64(byte[] bytes) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA256");
            md.update(bytes);
            return Base64.encodeToString(md.digest(), 0);
        } catch (Exception e) {
            return null;
        }
    }

    static String hmacsha1(String raw, String key) throws SignatureException {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            return hexString(mac.doFinal(raw.getBytes()));
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
    }

    static String hmacsha256(String raw, String key) throws SignatureException {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA256_ALGORITHM);
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            return hexString(mac.doFinal(raw.getBytes()));
        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }
    }
}
