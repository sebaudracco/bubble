package com.unit.three.p143e.p144a;

import org.altbeacon.bluetooth.Pdu;

public final class C4085a {
    private static final byte[] f9461a = new byte[]{(byte) 65, (byte) 66, (byte) 67, (byte) 68, (byte) 69, (byte) 70, (byte) 71, (byte) 72, (byte) 73, (byte) 74, (byte) 75, (byte) 76, (byte) 77, (byte) 78, (byte) 79, (byte) 80, (byte) 81, (byte) 82, (byte) 83, (byte) 84, (byte) 85, (byte) 86, (byte) 87, (byte) 88, (byte) 89, (byte) 90, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106, (byte) 107, (byte) 108, (byte) 109, (byte) 110, (byte) 111, (byte) 112, (byte) 113, (byte) 114, (byte) 115, (byte) 116, (byte) 117, (byte) 118, (byte) 119, (byte) 120, (byte) 121, (byte) 122, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 43, (byte) 47};
    private static final byte[] f9462b = new byte[]{(byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -5, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -5, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 62, (byte) -9, (byte) -9, (byte) -9, (byte) 63, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 61, (byte) -9, (byte) -9, (byte) -9, (byte) -1, (byte) -9, (byte) -9, (byte) -9, (byte) 0, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 13, (byte) 14, (byte) 15, (byte) 16, (byte) 17, (byte) 18, (byte) 19, (byte) 20, (byte) 21, Pdu.GATT_SERVICE_UUID_PDU_TYPE, (byte) 23, (byte) 24, (byte) 25, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) -9, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 31, (byte) 32, (byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 37, (byte) 38, (byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) -9, (byte) -9, (byte) -9, (byte) -9};

    private static int m12604a(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (bArr[2] == (byte) 61) {
            bArr2[i2] = (byte) ((((f9462b[bArr[0]] & 255) << 18) | ((f9462b[bArr[1]] & 255) << 12)) >>> 16);
            return 1;
        } else if (bArr[3] == (byte) 61) {
            r0 = (((f9462b[bArr[1]] & 255) << 12) | ((f9462b[bArr[0]] & 255) << 18)) | ((f9462b[bArr[2]] & 255) << 6);
            bArr2[i2] = (byte) (r0 >>> 16);
            bArr2[i2 + 1] = (byte) (r0 >>> 8);
            return 2;
        } else {
            try {
                r0 = ((((f9462b[bArr[0]] & 255) << 18) | ((f9462b[bArr[1]] & 255) << 12)) | ((f9462b[bArr[2]] & 255) << 6)) | (f9462b[bArr[3]] & 255);
                bArr2[i2] = (byte) (r0 >> 16);
                bArr2[i2 + 1] = (byte) (r0 >> 8);
                bArr2[i2 + 2] = (byte) r0;
                return 3;
            } catch (Exception e) {
                return -1;
            }
        }
    }

    public static String m12605a(byte[] bArr) {
        int length = bArr.length;
        int i = (length << 2) / 3;
        byte[] bArr2 = new byte[(((length % 3 > 0 ? 4 : 0) + i) + (i / 76))];
        int i2 = length - 2;
        int i3 = 0;
        i = 0;
        int i4 = 0;
        while (i4 < i2) {
            C4085a.m12607a(bArr, i4, 3, bArr2, i);
            i3 += 4;
            if (i3 == 76) {
                bArr2[i + 4] = (byte) 10;
                i++;
                i3 = 0;
            }
            i4 += 3;
            i += 4;
        }
        if (i4 < length) {
            C4085a.m12607a(bArr, i4, length - i4, bArr2, i);
            i += 4;
        }
        return new String(bArr2, 0, i, "iso-8859-1");
    }

    public static byte[] m12606a(String str) {
        int i;
        byte[] bytes = str.getBytes("iso-8859-1");
        int length = bytes.length;
        Object obj = new byte[((length * 3) / 4)];
        byte[] bArr = new byte[4];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            byte b = (byte) (bytes[i2] & 127);
            byte b2 = f9462b[b];
            if (b2 < (byte) -5) {
                return null;
            }
            if (b2 >= (byte) -1) {
                i = i3 + 1;
                bArr[i3] = b;
                if (i > 3) {
                    i = C4085a.m12604a(bArr, 0, obj, i4) + i4;
                    if (b == (byte) 61) {
                        break;
                    }
                    i3 = i;
                    i = 0;
                } else {
                    i3 = i4;
                }
            } else {
                i = i3;
                i3 = i4;
            }
            i2++;
            i4 = i3;
            i3 = i;
        }
        i = i4;
        Object obj2 = new byte[i];
        System.arraycopy(obj, 0, obj2, 0, i);
        return obj2;
    }

    private static byte[] m12607a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        int i5 = (i2 > 1 ? (bArr[i + 1] << 24) >>> 16 : 0) | (i2 > 0 ? (bArr[i] << 24) >>> 8 : 0);
        if (i2 > 2) {
            i4 = (bArr[i + 2] << 24) >>> 24;
        }
        i4 |= i5;
        switch (i2) {
            case 1:
                bArr2[i3] = f9461a[i4 >>> 18];
                bArr2[i3 + 1] = f9461a[(i4 >>> 12) & 63];
                bArr2[i3 + 2] = (byte) 61;
                bArr2[i3 + 3] = (byte) 61;
                break;
            case 2:
                bArr2[i3] = f9461a[i4 >>> 18];
                bArr2[i3 + 1] = f9461a[(i4 >>> 12) & 63];
                bArr2[i3 + 2] = f9461a[(i4 >>> 6) & 63];
                bArr2[i3 + 3] = (byte) 61;
                break;
            case 3:
                bArr2[i3] = f9461a[i4 >>> 18];
                bArr2[i3 + 1] = f9461a[(i4 >>> 12) & 63];
                bArr2[i3 + 2] = f9461a[(i4 >>> 6) & 63];
                bArr2[i3 + 3] = f9461a[i4 & 63];
                break;
        }
        return bArr2;
    }
}
