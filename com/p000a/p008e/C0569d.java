package com.p000a.p008e;

public class C0569d {
    public static int[] m493(char[] cArr, int[] iArr, boolean z) {
        int i = (cArr[0] << 16) + cArr[1];
        int i2 = cArr[3] + (cArr[2] << 16);
        if (!z) {
            C0569d.m494(iArr);
        }
        int i3 = 0;
        while (i3 < 16) {
            i ^= iArr[i3];
            C0568b c0568b = C0568b.f303;
            int i4 = (c0568b.f304[0][i >>> 24] + c0568b.f304[1][(i >>> 16) & 255]) ^ c0568b.f304[2][(i >>> 8) & 255];
            i3++;
            int i5 = i;
            i = i2 ^ (c0568b.f304[3][i & 255] + i4);
            i2 = i5;
        }
        i3 = iArr[16] ^ i;
        i2 ^= iArr[17];
        int[] iArr2 = new int[]{i2, i3};
        cArr[0] = i2 >>> 16;
        cArr[1] = (char) i2;
        cArr[2] = i3 >>> 16;
        cArr[3] = (char) i3;
        if (!z) {
            C0569d.m494(iArr);
        }
        return iArr2;
    }

    private static void m494(int[] iArr) {
        for (int i = 0; i < iArr.length / 2; i++) {
            int i2 = iArr[i];
            iArr[i] = iArr[(iArr.length - i) - 1];
            iArr[(iArr.length - i) - 1] = i2;
        }
    }
}
