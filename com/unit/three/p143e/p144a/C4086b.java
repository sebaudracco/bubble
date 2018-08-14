package com.unit.three.p143e.p144a;

import com.facebook.ads.AudienceNetworkActivity;
import java.io.UnsupportedEncodingException;

public final class C4086b {
    static {
        byte[] bArr = new byte[]{(byte) 65, (byte) 66, (byte) 69, (byte) 78, (byte) 50, (byte) 48, (byte) 49, (byte) 50, (byte) 48, (byte) 57, (byte) 51, (byte) 48, (byte) 49, (byte) 56, (byte) 51, (byte) 48};
    }

    public static String m12608a(String str, String str2) {
        try {
            return C4086b.m12609a(str.getBytes(AudienceNetworkActivity.WEBVIEW_ENCODING), str2);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String m12609a(byte[] bArr, String str) {
        try {
            return C4085a.m12605a(C4086b.m12611a(bArr, str.getBytes(AudienceNetworkActivity.WEBVIEW_ENCODING)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] m12610a(byte[] bArr, byte b) {
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

    private static byte[] m12611a(byte[] bArr, byte[] bArr2) {
        for (byte a : bArr2) {
            bArr = C4086b.m12610a(bArr, a);
        }
        return bArr;
    }

    public static String m12612b(String str, String str2) {
        try {
            return new String(C4086b.m12611a(C4085a.m12606a(str), str2.getBytes(AudienceNetworkActivity.WEBVIEW_ENCODING)), AudienceNetworkActivity.WEBVIEW_ENCODING);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
