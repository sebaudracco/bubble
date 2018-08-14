package com.appnext.base.p023b;

import android.text.TextUtils;
import android.util.Base64;
import com.appnext.base.C1061b;
import com.facebook.ads.AudienceNetworkActivity;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.zip.CRC32;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class C1047h {
    private static final String TAG = "Generator";
    private static final String gk = "fqJfdzGDvfwbedsKSUGty3VZ9taXxMVw";
    private static final C1047h kc = new C1047h();

    private C1047h() {
        init();
    }

    public static C1047h cx() {
        return kc;
    }

    private void init() {
    }

    public Long az(String str) {
        Long l = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                CRC32 crc32 = new CRC32();
                crc32.update(str.getBytes());
                l = Long.valueOf(crc32.getValue());
            }
        } catch (Throwable th) {
        }
        return l;
    }

    public String aA(String str) {
        String str2 = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                CRC32 crc32 = new CRC32();
                crc32.update(str.getBytes());
                str2 = Long.toHexString(crc32.getValue());
            }
        } catch (Throwable th) {
        }
        return str2;
    }

    public byte[] m2145h(String str, String str2) {
        byte[] bArr = null;
        try {
            if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                byte[] bArr2 = new byte[16];
                new SecureRandom().nextBytes(bArr2);
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(1, new SecretKeySpec(Base64.decode(str2, 2), "AES"), new IvParameterSpec(bArr2));
                bArr = m2144a(bArr2, instance.doFinal(str.getBytes("UTF-8")));
            }
        } catch (Throwable th) {
            C1061b.m2191a(th);
        }
        return bArr;
    }

    public String aB(String str) {
        String str2 = null;
        if (!TextUtils.isEmpty(str)) {
            try {
                byte[] bArr = new byte[16];
                new SecureRandom().nextBytes(bArr);
                Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
                instance.init(1, new SecretKeySpec(gk.getBytes(AudienceNetworkActivity.WEBVIEW_ENCODING), "AES"), new IvParameterSpec(bArr));
                str2 = Base64.encodeToString(m2144a(bArr, instance.doFinal(str.getBytes(AudienceNetworkActivity.WEBVIEW_ENCODING))), 2);
            } catch (Throwable th) {
                C1061b.m2191a(th);
            }
        }
        return str2;
    }

    public String aC(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] decode = Base64.decode(str, 2);
            byte[] copyOfRange = Arrays.copyOfRange(decode, 0, 16);
            byte[] copyOfRange2 = Arrays.copyOfRange(decode, 16, decode.length);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
            instance.init(2, new SecretKeySpec(gk.getBytes(AudienceNetworkActivity.WEBVIEW_ENCODING), "AES"), new IvParameterSpec(copyOfRange));
            return new String(instance.doFinal(copyOfRange2), AudienceNetworkActivity.WEBVIEW_ENCODING);
        } catch (Throwable th) {
            C1061b.m2191a(th);
            return null;
        }
    }

    private byte[] m2144a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        int i = 0;
        while (i < bArr3.length) {
            bArr3[i] = i < bArr.length ? bArr[i] : bArr2[i - bArr.length];
            i++;
        }
        return bArr3;
    }

    public String aD(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str.getBytes());
            return m2143a(instance.digest());
        } catch (Throwable th) {
            return null;
        }
    }

    private String m2143a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                stringBuffer.append('0');
            }
            stringBuffer.append(toHexString);
        }
        return stringBuffer.toString();
    }
}
