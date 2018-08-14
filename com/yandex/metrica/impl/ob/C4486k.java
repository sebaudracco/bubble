package com.yandex.metrica.impl.ob;

public class C4486k<T extends C4325i> {
    private final C4350j<T> f12451a;
    private final C4445h<T> f12452b;

    public static final class C4485a<T extends C4325i> {
        public C4445h<T> f12449a;
        private C4350j<T> f12450b;

        C4485a(C4350j<T> c4350j) {
            this.f12450b = c4350j;
        }

        public C4485a<T> m16094a(C4445h<T> c4445h) {
            this.f12449a = c4445h;
            return this;
        }

        public C4486k<T> m16095a() {
            return new C4486k();
        }
    }

    private C4486k(C4485a c4485a) {
        this.f12451a = c4485a.f12450b;
        this.f12452b = c4485a.f12449a;
    }

    final void m16097a(C4325i c4325i) {
        this.f12451a.mo7019a(c4325i);
    }

    final boolean m16098b(C4325i c4325i) {
        if (this.f12452b == null) {
            return false;
        }
        return this.f12452b.mo7076a(c4325i);
    }

    public static <T extends C4325i> C4485a<T> m16096a(C4350j<T> c4350j) {
        return new C4485a(c4350j);
    }
}
