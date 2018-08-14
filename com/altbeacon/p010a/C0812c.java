package com.altbeacon.p010a;

import android.annotation.TargetApi;

public class C0812c {
    private byte f1534a;
    private int f1535b;
    private int f1536c;
    private int f1537d;
    private byte[] f1538e;

    @TargetApi(9)
    public static C0812c m1511a(byte[] bArr, int i) {
        C0812c c0812c = null;
        if (bArr.length - i >= 2) {
            byte b = bArr[i];
            if (b > (byte) 0) {
                byte b2 = bArr[i + 1];
                int i2 = i + 2;
                if (i2 < bArr.length) {
                    c0812c = new C0812c();
                    c0812c.f1537d = i + b;
                    if (c0812c.f1537d >= bArr.length) {
                        c0812c.f1537d = bArr.length - 1;
                    }
                    c0812c.f1534a = b2;
                    c0812c.f1535b = b;
                    c0812c.f1536c = i2;
                    c0812c.f1538e = bArr;
                }
            }
        }
        return c0812c;
    }

    public byte m1512a() {
        return this.f1534a;
    }

    public int m1513b() {
        return this.f1535b;
    }

    public int m1514c() {
        return this.f1536c;
    }

    public int m1515d() {
        return this.f1537d;
    }
}
