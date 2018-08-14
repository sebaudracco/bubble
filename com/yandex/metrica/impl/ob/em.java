package com.yandex.metrica.impl.ob;

import java.io.IOException;

class em implements ey {
    private final fb f12333a;
    private final fb f12334b;

    public em(fe feVar, String str) throws IOException {
        ev enVar = new en(feVar.m16000b(), Integer.toString(str.hashCode()));
        this.f12333a = new fb(enVar, "LIB-BLACK");
        this.f12334b = new fb(enVar, "LIB-TRUST");
    }

    public fb mo7091a() {
        return this.f12333a;
    }

    public fb mo7092b() {
        throw new UnsupportedOperationException("white list isn't supported in custom container");
    }

    public fb mo7093c() {
        return this.f12334b;
    }
}
