package com.unit.two.p147c;

import com.facebook.ads.AudienceNetworkActivity;
import org.altbeacon.bluetooth.Pdu;

public final class C4098c {
    private static byte[] f9547a = new byte[]{(byte) 65, (byte) 66, (byte) 69, (byte) 78, (byte) 50, (byte) 48, (byte) 49, (byte) 50, (byte) 48, (byte) 57, (byte) 51, (byte) 48, (byte) 49, (byte) 56, (byte) 51, (byte) 48};
    private static final byte[] f9548b = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -5, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 62, (byte) -9, (byte) -9, (byte) -9, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, Pdu.GATT_SERVICE_UUID_PDU_TYPE, (byte) 23, (byte) 24, (byte) 25, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -9, (byte) -9, (byte) -9, (byte) -9};

    static {
        byte[] bArr = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    }

    private static int m12673a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (bArr[2] == (byte) 61) {
            bArr2[i2] = (byte) ((((f9548b[bArr[0]] & 255) << 18) | ((f9548b[bArr[1]] & 255) << 12)) >>> 16);
            return 1;
        } else if (bArr[3] == (byte) 61) {
            r0 = (((f9548b[bArr[1]] & 255) << 12) | ((f9548b[bArr[0]] & 255) << 18)) | ((f9548b[bArr[2]] & 255) << 6);
            bArr2[i2] = (byte) (r0 >>> 16);
            bArr2[i2 + 1] = (byte) (r0 >>> 8);
            return 2;
        } else {
            try {
                r0 = ((((f9548b[bArr[0]] & 255) << 18) | ((f9548b[bArr[1]] & 255) << 12)) | ((f9548b[bArr[2]] & 255) << 6)) | (f9548b[bArr[3]] & 255);
                bArr2[i2] = (byte) (r0 >> 16);
                bArr2[i2 + 1] = (byte) (r0 >> 8);
                bArr2[i2 + 2] = (byte) r0;
                return 3;
            } catch (Exception e) {
                return -1;
            }
        }
    }

    public static String m12674a(String str) {
        int i = 0;
        try {
            Object obj;
            byte[] bytes = str.getBytes("iso-8859-1");
            int length = bytes.length;
            Object obj2 = new byte[((length * 3) / 4)];
            byte[] bArr = new byte[4];
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < length) {
                byte b = (byte) (bytes[i2] & 127);
                byte b2 = f9548b[b];
                if (b2 < (byte) -5) {
                    obj = null;
                    break;
                }
                int i5;
                if (b2 >= (byte) -1) {
                    i5 = i3 + 1;
                    bArr[i3] = b;
                    if (i5 > 3) {
                        i5 = C4098c.m12673a(bArr, 0, obj2, i4) + i4;
                        if (b == (byte) 61) {
                            i4 = i5;
                            break;
                        }
                        i3 = i5;
                        i5 = 0;
                    } else {
                        i3 = i4;
                    }
                } else {
                    i5 = i3;
                    i3 = i4;
                }
                i2++;
                i4 = i3;
                i3 = i5;
            }
            obj = new byte[i4];
            System.arraycopy(obj2, 0, obj, 0, i4);
            byte[] bArr2 = f9547a;
            byte[] bArr3 = obj;
            while (i < bArr2.length) {
                i++;
                bArr3 = C4098c.m12675a(bArr3, bArr2[i]);
            }
            return new String(bArr3, AudienceNetworkActivity.WEBVIEW_ENCODING);
        } catch (Exception e) {
            return null;
        }
    }

    private static byte[] m12675a(byte[] bArr, byte b) {
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
}
