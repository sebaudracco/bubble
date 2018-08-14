package com.yandex.metrica.impl.ob;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public final class C4392b {
    private final byte[] f11905a;
    private final int f11906b;
    private int f11907c;

    public static class C4391a extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        C4391a(int i, int i2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
        }
    }

    private C4392b(byte[] bArr, int i, int i2) {
        this.f11905a = bArr;
        this.f11907c = i;
        this.f11906b = i + i2;
    }

    public static C4392b m15175a(byte[] bArr, int i, int i2) {
        return new C4392b(bArr, i, i2);
    }

    public void m15196a(int i, double d) throws IOException {
        m15220g(i, 1);
        m15194a(d);
    }

    public void m15198a(int i, long j) throws IOException {
        m15220g(i, 0);
        m15203a(j);
    }

    public void m15211b(int i, long j) throws IOException {
        m15220g(i, 0);
        m15212b(j);
    }

    public void m15197a(int i, int i2) throws IOException {
        m15220g(i, 0);
        m15195a(i2);
    }

    public void m15201a(int i, boolean z) throws IOException {
        m15220g(i, 0);
        m15206a(z);
    }

    public void m15200a(int i, String str) throws IOException {
        m15220g(i, 2);
        m15205a(str);
    }

    public void m15199a(int i, C4277d c4277d) throws IOException {
        m15220g(i, 2);
        m15204a(c4277d);
    }

    public void m15202a(int i, byte[] bArr) throws IOException {
        m15220g(i, 2);
        m15207a(bArr);
    }

    public void m15210b(int i, int i2) throws IOException {
        m15220g(i, 0);
        m15209b(i2);
    }

    public void m15216c(int i, int i2) throws IOException {
        m15220g(i, 0);
        m15215c(i2);
    }

    public void m15194a(double d) throws IOException {
        m15218e(Double.doubleToLongBits(d));
    }

    public void m15203a(long j) throws IOException {
        m15217c(j);
    }

    public void m15212b(long j) throws IOException {
        m15217c(j);
    }

    public void m15195a(int i) throws IOException {
        if (i >= 0) {
            m15221h(i);
        } else {
            m15217c((long) i);
        }
    }

    public void m15206a(boolean z) throws IOException {
        m15219f(z ? 1 : 0);
    }

    public void m15205a(String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        m15221h(bytes.length);
        m15213b(bytes);
    }

    public void m15204a(C4277d c4277d) throws IOException {
        m15221h(c4277d.m14316a());
        c4277d.mo6966a(this);
    }

    public void m15207a(byte[] bArr) throws IOException {
        m15221h(bArr.length);
        m15213b(bArr);
    }

    public void m15209b(int i) throws IOException {
        m15221h(i);
    }

    public void m15215c(int i) throws IOException {
        m15221h(C4392b.m15191j(i));
    }

    public static int m15182d(int i) {
        return C4392b.m15189g(i) + 8;
    }

    public static int m15181c(int i, long j) {
        return C4392b.m15189g(i) + C4392b.m15185d(j);
    }

    public static int m15184d(int i, long j) {
        return C4392b.m15189g(i) + C4392b.m15185d(j);
    }

    public static int m15183d(int i, int i2) {
        int i3;
        int g = C4392b.m15189g(i);
        if (i2 >= 0) {
            i3 = C4392b.m15190i(i2);
        } else {
            i3 = 10;
        }
        return i3 + g;
    }

    public static int m15186e(int i) {
        return C4392b.m15189g(i) + 1;
    }

    public static int m15177b(int i, String str) {
        return C4392b.m15189g(i) + C4392b.m15180b(str);
    }

    public static int m15176b(int i, C4277d c4277d) {
        return C4392b.m15189g(i) + C4392b.m15179b(c4277d);
    }

    public static int m15178b(int i, byte[] bArr) {
        return C4392b.m15189g(i) + (C4392b.m15190i(bArr.length) + bArr.length);
    }

    public static int m15187e(int i, int i2) {
        return C4392b.m15189g(i) + C4392b.m15190i(i2);
    }

    public static int m15188f(int i, int i2) {
        return C4392b.m15189g(i) + C4392b.m15190i(C4392b.m15191j(i2));
    }

    public static int m15180b(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return bytes.length + C4392b.m15190i(bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public static int m15179b(C4277d c4277d) {
        int b = c4277d.m14318b();
        return b + C4392b.m15190i(b);
    }

    public int m15192a() {
        return this.f11906b - this.f11907c;
    }

    public void m15208b() {
        if (m15192a() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void m15193a(byte b) throws IOException {
        if (this.f11907c == this.f11906b) {
            throw new C4391a(this.f11907c, this.f11906b);
        }
        byte[] bArr = this.f11905a;
        int i = this.f11907c;
        this.f11907c = i + 1;
        bArr[i] = b;
    }

    public void m15219f(int i) throws IOException {
        m15193a((byte) i);
    }

    public void m15213b(byte[] bArr) throws IOException {
        m15214b(bArr, 0, bArr.length);
    }

    public void m15214b(byte[] bArr, int i, int i2) throws IOException {
        if (this.f11906b - this.f11907c >= i2) {
            System.arraycopy(bArr, i, this.f11905a, this.f11907c, i2);
            this.f11907c += i2;
            return;
        }
        throw new C4391a(this.f11907c, this.f11906b);
    }

    public void m15220g(int i, int i2) throws IOException {
        m15221h(C4469f.m15977a(i, i2));
    }

    public static int m15189g(int i) {
        return C4392b.m15190i(C4469f.m15977a(i, 0));
    }

    public void m15221h(int i) throws IOException {
        while ((i & -128) != 0) {
            m15219f((i & 127) | 128);
            i >>>= 7;
        }
        m15219f(i);
    }

    public static int m15190i(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((-268435456 & i) == 0) {
            return 4;
        }
        return 5;
    }

    public void m15217c(long j) throws IOException {
        while ((-128 & j) != 0) {
            m15219f((((int) j) & 127) | 128);
            j >>>= 7;
        }
        m15219f((int) j);
    }

    public static int m15185d(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        if ((Long.MIN_VALUE & j) == 0) {
            return 9;
        }
        return 10;
    }

    public void m15218e(long j) throws IOException {
        m15219f(((int) j) & 255);
        m15219f(((int) (j >> 8)) & 255);
        m15219f(((int) (j >> 16)) & 255);
        m15219f(((int) (j >> 24)) & 255);
        m15219f(((int) (j >> 32)) & 255);
        m15219f(((int) (j >> 40)) & 255);
        m15219f(((int) (j >> 48)) & 255);
        m15219f(((int) (j >> 56)) & 255);
    }

    public static int m15191j(int i) {
        return (i << 1) ^ (i >> 31);
    }
}
