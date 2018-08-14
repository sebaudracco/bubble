package com.unit.two.p151f.p152a;

import com.unit.two.p147c.C4096a;
import java.io.UnsupportedEncodingException;

public final class C4133b {
    static {
        String str = C4096a.aM;
        byte[] bArr = new byte[]{(byte) 65, (byte) 66, (byte) 69, (byte) 78, (byte) 50, (byte) 48, (byte) 49, (byte) 50, (byte) 48, (byte) 57, (byte) 51, (byte) 48, (byte) 49, (byte) 56, (byte) 51, (byte) 48};
    }

    public static String m12759a(String str, String str2) {
        try {
            return C4133b.m12760a(str.getBytes(C4096a.aN), str2);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String m12760a(byte[] bArr, String str) {
        try {
            return C4132a.m12756a(C4133b.m12762a(bArr, str.getBytes(C4096a.aO)));
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] m12761a(byte[] bArr, byte b) {
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

    private static byte[] m12762a(byte[] bArr, byte[] bArr2) {
        for (byte a : bArr2) {
            bArr = C4133b.m12761a(bArr, a);
        }
        return bArr;
    }

    public static String m12763b(String str, String str2) {
        try {
            return new String(C4133b.m12762a(C4132a.m12757a(str), str2.getBytes(C4096a.aP)), C4096a.aQ);
        } catch (Exception e) {
            return null;
        }
    }
}
