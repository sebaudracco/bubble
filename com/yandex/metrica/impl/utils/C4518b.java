package com.yandex.metrica.impl.utils;

import android.util.Base64;
import com.yandex.metrica.impl.bk;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class C4518b {
    private final String f12574a;
    private final String f12575b;

    public C4518b() {
        this("AES/CBC/PKCS7Padding", "RSA/ECB/PKCS1Padding");
    }

    C4518b(String str, String str2) {
        this.f12574a = str;
        this.f12575b = str2;
    }

    public byte[] m16236a(byte[] bArr) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] bArr2 = new byte[16];
            byte[] bArr3 = new byte[16];
            secureRandom.nextBytes(bArr3);
            secureRandom.nextBytes(bArr2);
            return m16237a(bArr, bArr3, bArr2, KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDhmH/m2qrRjxDHP794CeaZpENQNYydf8pqyXJilo6XxK+n+pvo27VxWfB3Z1yHrtKow+eZXKLQzrQ8wZMfRgADrYCQJ20y2hGZEUCN1tGSM+xqVKMeCtVi3NvQa54Cx7mT5ECVsH5DKEs/aeScDHP56FzcgEbtOSwyRZ8dsEM0wwIDAQAB", 0))));
        } catch (InvalidKeySpecException e) {
            return null;
        } catch (NoSuchAlgorithmException e2) {
            return null;
        }
    }

    byte[] m16237a(byte[] bArr, byte[] bArr2, byte[] bArr3, PublicKey publicKey) {
        Closeable byteArrayOutputStream;
        Throwable th;
        byte[] bArr4 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(bArr2.length + bArr3.length);
            byteArrayOutputStream2.write(bArr2);
            byteArrayOutputStream2.write(bArr3);
            byte[] toByteArray = byteArrayOutputStream2.toByteArray();
            byteArrayOutputStream2.close();
            Cipher instance = Cipher.getInstance(this.f12575b);
            instance.init(1, publicKey);
            byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
            try {
                byteArrayOutputStream.write(instance.doFinal(toByteArray));
                byteArrayOutputStream.write(new C4517a(this.f12574a, bArr2, bArr3).m16235a(bArr));
                bArr4 = byteArrayOutputStream.toByteArray();
                bk.m14980a(byteArrayOutputStream);
            } catch (Exception e) {
                bk.m14980a(byteArrayOutputStream);
                return bArr4;
            } catch (Throwable th2) {
                th = th2;
                bk.m14980a(byteArrayOutputStream);
                throw th;
            }
        } catch (Exception e2) {
            byteArrayOutputStream = bArr4;
            bk.m14980a(byteArrayOutputStream);
            return bArr4;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            byteArrayOutputStream = bArr4;
            th = th4;
            bk.m14980a(byteArrayOutputStream);
            throw th;
        }
        return bArr4;
    }
}
