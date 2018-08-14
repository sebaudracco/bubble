package com.yandex.metrica.impl;

abstract class C4342i {
    private final C4365a f11693a;

    public interface C4365a {
        boolean mo7028a(Throwable th);
    }

    abstract void mo7011b(Throwable th);

    C4342i(C4365a c4365a) {
        this.f11693a = c4365a;
    }

    void m14705a(Throwable th) {
        if (this.f11693a.mo7028a(th)) {
            mo7011b(th);
        }
    }
}
