package com.vungle.publisher;

import android.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: vungle */
public final class yx {
    public static String m14180a(String str, String str2) {
        return Base64.encodeToString(m14181b(str, str2), 11);
    }

    public static byte[] m14181b(String str, String str2) {
        try {
            Mac instance = Mac.getInstance("HmacSHA256");
            instance.init(new SecretKeySpec(str2.getBytes(), instance.getAlgorithm()));
            return instance.doFinal(str.getBytes());
        } catch (Throwable e) {
            throw new UnsupportedOperationException(e);
        } catch (Throwable e2) {
            throw new UnsupportedOperationException(e2);
        }
    }
}
