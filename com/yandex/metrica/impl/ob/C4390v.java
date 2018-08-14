package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.C4372h;

public abstract class C4390v<T> {
    private final aa<T> f11896a;

    protected abstract boolean mo7042a(C4372h c4372h, C4504x<T> c4504x);

    protected C4390v(aa<T> aaVar) {
        this.f11896a = aaVar;
    }

    public boolean m15139a(C4372h c4372h) {
        return mo7042a(c4372h, m15141b(c4372h));
    }

    C4504x<T> m15141b(C4372h c4372h) {
        return this.f11896a.mo7118a(c4372h.m15062c());
    }
}
