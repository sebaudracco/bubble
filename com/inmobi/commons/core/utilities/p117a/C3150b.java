package com.inmobi.commons.core.utilities.p117a;

import android.annotation.SuppressLint;
import android.util.Base64;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: RequestEncryptionUtils */
public class C3150b {
    private static final String f7767a = C3150b.class.getSimpleName();
    private static String f7768b = "AES";
    private static String f7769c = "AES/CBC/PKCS7Padding";
    private static byte[] f7770d;

    public static String m10368a(String str, byte[] bArr, byte[] bArr2, byte[] bArr3, String str2, String str3) {
        try {
            byte[] b = C3150b.m10377b(str.getBytes("UTF-8"), bArr, bArr2);
            byte[] a = C3150b.m10373a(b, bArr3);
            b = C3150b.m10371a(b);
            a = C3150b.m10371a(a);
            return new String(Base64.encode(C3150b.m10376b(C3150b.m10371a(C3150b.m10372a(C3150b.m10376b(C3150b.m10376b(C3150b.m10371a(bArr), C3150b.m10371a(bArr3)), C3150b.m10371a(bArr2)), str3, str2)), C3150b.m10376b(b, a)), 8));
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7767a, "SDK encountered unexpected error in generating secret message; " + e.getMessage());
            return null;
        }
    }

    @SuppressLint({"TrulyRandom"})
    private static byte[] m10377b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = null;
        Key secretKeySpec = new SecretKeySpec(bArr2, f7768b);
        AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        try {
            Cipher instance = Cipher.getInstance(f7769c);
            instance.init(1, secretKeySpec, ivParameterSpec);
            instance.init(1, secretKeySpec, ivParameterSpec);
            instance.init(1, secretKeySpec, ivParameterSpec);
            bArr4 = instance.doFinal(bArr);
        } catch (Throwable th) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7767a, "SDK encountered unexpected error in getting encrypted AES bytes; " + th.getMessage());
        }
        return bArr4;
    }

    public static byte[] m10369a() {
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(128, new SecureRandom());
            SecretKey generateKey = instance.generateKey();
            if (generateKey != null) {
                return generateKey.getEncoded();
            }
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7767a, "SDK encountered unexpected error in generating AES public key; " + e.getMessage());
        }
        return null;
    }

    private static byte[] m10373a(byte[] bArr, byte[] bArr2) {
        String str = "HmacSHA1";
        byte[] bArr3 = null;
        Key secretKeySpec = new SecretKeySpec(bArr2, 0, bArr2.length, "HmacSHA1");
        try {
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(secretKeySpec);
            bArr3 = instance.doFinal(bArr);
        } catch (NoSuchAlgorithmException e) {
        } catch (InvalidKeyException e2) {
        }
        return bArr3;
    }

    private static byte[] m10371a(byte[] bArr) {
        long length = (long) bArr.length;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        allocate.putLong(length);
        Object array = allocate.array();
        Object obj = new byte[(array.length + bArr.length)];
        System.arraycopy(array, 0, obj, 0, array.length);
        System.arraycopy(bArr, 0, obj, array.length, bArr.length);
        return obj;
    }

    public static byte[] m10372a(byte[] bArr, String str, String str2) {
        String str3 = "RSA";
        str3 = "RSA/ECB/nopadding";
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(str2, 16), new BigInteger(str, 16)));
            Cipher instance = Cipher.getInstance("RSA/ECB/nopadding");
            instance.init(1, rSAPublicKey);
            return instance.doFinal(bArr);
        } catch (Throwable th) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7767a, "SDK encountered unexpected error in getting encrypted RSA bytes; " + th.getMessage());
            return null;
        }
    }

    private static byte[] m10376b(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }

    public static synchronized byte[] m10375b() {
        byte[] bArr;
        synchronized (C3150b.class) {
            C3149a c3149a = new C3149a();
            if ((System.currentTimeMillis() / 1000) - c3149a.m10367c() > 86400) {
                try {
                    f7770d = C3150b.m10369a();
                    c3149a.m10365a(Base64.encodeToString(f7770d, 0));
                } catch (Exception e) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7767a, "SDK encountered unexpected error in getting AES public key");
                }
            } else if (f7770d == null) {
                try {
                    f7770d = Base64.decode(c3149a.m10366b(), 0);
                } catch (Throwable e2) {
                    Logger.m10360a(InternalLogLevel.INTERNAL, f7767a, "getAesPublicKey failed to decode the cached key.", e2);
                    f7770d = C3150b.m10369a();
                    c3149a.m10365a(Base64.encodeToString(f7770d, 0));
                }
            }
            bArr = f7770d;
        }
        return bArr;
    }

    public static byte[] m10374a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = null;
        Key secretKeySpec = new SecretKeySpec(bArr2, f7768b);
        try {
            Cipher instance = Cipher.getInstance(f7769c);
            instance.init(2, secretKeySpec, new IvParameterSpec(bArr3));
            bArr4 = instance.doFinal(bArr);
        } catch (Throwable th) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7767a, "SDK encountered unexpected error in decrypting AES response; " + th.getMessage());
        }
        return bArr4;
    }

    public static byte[] m10370a(int i) {
        byte[] bArr = new byte[i];
        try {
            new SecureRandom().nextBytes(bArr);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7767a, "SDK encountered unexpected error in generating crypto key; " + e.getMessage());
        }
        return bArr;
    }
}
