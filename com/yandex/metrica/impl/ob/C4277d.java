package com.yandex.metrica.impl.ob;

import java.io.IOException;

public abstract class C4277d {
    protected volatile int f11447a = -1;

    public int m14316a() {
        if (this.f11447a < 0) {
            m14318b();
        }
        return this.f11447a;
    }

    public int m14318b() {
        int c = mo6967c();
        this.f11447a = c;
        return c;
    }

    protected int mo6967c() {
        return 0;
    }

    public void mo6966a(C4392b c4392b) throws IOException {
    }

    public static final byte[] m14315a(C4277d c4277d) {
        byte[] bArr = new byte[c4277d.m14318b()];
        try {
            C4392b a = C4392b.m15175a(bArr, 0, bArr.length);
            c4277d.mo6966a(a);
            a.m15208b();
            return bArr;
        } catch (Throwable e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public String toString() {
        return C4461e.m15854a(this);
    }
}
