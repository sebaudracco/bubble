package com.oneaudience.sdk.p134b;

import android.util.Base64;
import com.oneaudience.sdk.p135c.C3833d;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class C3819c {
    private static final String f9175a = C3819c.class.getSimpleName();
    private static final C3819c f9176d = new C3819c(new C38181());
    private SecretKeySpec f9177b;
    private IvParameterSpec f9178c;

    interface C3815a {
        String mo6807a();
    }

    static class C38181 implements C3815a {
        private final int[] f9174a = new int[]{99, 109, 86, 115, 90, 87, 70, 122, 90, 83, 66, 48, 97, 71, 85, 103, 97, 71, 57, 49, 98, 109, 82, 122};

        C38181() {
        }

        public String mo6807a() {
            return "oneaudience";
        }
    }

    public C3819c(C3815a c3815a) {
        byte[] b = m12211b(c3815a.mo6807a());
        try {
            this.f9177b = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(c3815a.mo6807a().getBytes("UTF-8")), "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        this.f9178c = new IvParameterSpec(b);
    }

    private byte[] m12211b(String str) {
        try {
            Object bytes = str.getBytes("UTF-8");
            Object obj = new byte[16];
            System.arraycopy(bytes, 0, obj, 0, Math.min(bytes.length, obj.length));
            return obj;
        } catch (Throwable e) {
            C3833d.m12250b(f9175a, "unable to provide key..", e);
            return null;
        }
    }

    public String m12212a(String str) {
        try {
            byte[] bytes = str.toString().getBytes("UTF-8");
            Cipher instance = Cipher.getInstance("AES/ECB/PKCS5Padding");
            instance.init(1, this.f9177b);
            byte[] bArr = new byte[instance.getOutputSize(bytes.length)];
            int update = instance.update(bytes, 0, bytes.length, bArr, 0);
            int doFinal = instance.doFinal(bArr, update) + update;
            return Base64.encodeToString(bArr, 0).replaceAll("(?:\\r\\n|\\n\\r|\\n|\\r)$", "");
        } catch (Throwable e) {
            C3833d.m12250b(f9175a, "unable to create cipher.", e);
            return null;
        } catch (Throwable e2) {
            C3833d.m12250b(f9175a, "unable to create cipher.", e2);
            return null;
        } catch (Throwable e22) {
            C3833d.m12250b(f9175a, "unable to initialize cipher.", e22);
            return null;
        } catch (Throwable e222) {
            C3833d.m12250b(f9175a, "unable to cipher input text.", e222);
            return null;
        } catch (Throwable e2222) {
            C3833d.m12250b(f9175a, "unable to cipher input text.", e2222);
            return null;
        } catch (ShortBufferException e3) {
            e3.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e4) {
            e4.printStackTrace();
            return null;
        }
    }
}
