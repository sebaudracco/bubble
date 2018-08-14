package com.elephant.data.p048g.p049a;

import android.text.TextUtils;
import com.elephant.data.p048g.C1814b;
import java.io.UnsupportedEncodingException;

public final class C1810b {
    static {
        byte[] bArr = new byte[]{(byte) 65, (byte) 66, (byte) 69, (byte) 78, (byte) 50, (byte) 48, (byte) 49, (byte) 50, (byte) 48, (byte) 57, (byte) 51, (byte) 48, (byte) 49, (byte) 56, (byte) 51, (byte) 48};
    }

    public static String m5232a(String str, String str2) {
        try {
            return C1810b.m5233a(str.getBytes(C1814b.f3883c), str2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String m5233a(byte[] bArr, String str) {
        try {
            return C1809a.m5229a(C1810b.m5235a(bArr, str.getBytes(C1814b.f3884d)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] m5234a(byte[] bArr, byte b) {
        try {
            byte[] bArr2 = new byte[bArr.length];
            for (int i = 0; i < bArr.length; i++) {
                bArr2[i] = (byte) (bArr[i] ^ b);
            }
            return bArr2;
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] m5235a(byte[] bArr, byte[] bArr2) {
        for (byte a : bArr2) {
            bArr = C1810b.m5234a(bArr, a);
        }
        return bArr;
    }

    public static String m5236b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            return new String(C1810b.m5235a(C1809a.m5230a(str), str2.getBytes(C1814b.f3886e)), C1814b.f3887f);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
