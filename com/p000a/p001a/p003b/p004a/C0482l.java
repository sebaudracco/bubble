package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0479h;
import com.p000a.p001a.C0480p;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.C0554j;
import com.p000a.p001a.C0556i;
import com.p000a.p001a.C0562q;
import com.p000a.p001a.p003b.C0528j;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p007c.C0542a;

public final class C0482l<T> extends C0452t<T> {
    final C0552e f95a;
    private final C0562q<T> f96b;
    private final C0556i<T> f97c;
    private final C0542a<T> f98d;
    private final C0449u f99e;
    private final C0481a f100f = new C0481a();
    private C0452t<T> f101g;

    private final class C0481a implements C0479h, C0480p {
        final /* synthetic */ C0482l f94a;

        private C0481a(C0482l c0482l) {
            this.f94a = c0482l;
        }
    }

    public C0482l(C0562q<T> c0562q, C0556i<T> c0556i, C0552e c0552e, C0542a<T> c0542a, C0449u c0449u) {
        this.f96b = c0562q;
        this.f97c = c0556i;
        this.f95a = c0552e;
        this.f98d = c0542a;
        this.f99e = c0449u;
    }

    private C0452t<T> m170b() {
        C0452t<T> c0452t = this.f101g;
        if (c0452t != null) {
            return c0452t;
        }
        c0452t = this.f95a.m439a(this.f99e, this.f98d);
        this.f101g = c0452t;
        return c0452t;
    }

    public void mo1793a(C0463c c0463c, T t) {
        if (this.f96b == null) {
            m170b().mo1793a(c0463c, t);
        } else if (t == null) {
            c0463c.mo1825f();
        } else {
            C0528j.m383a(this.f96b.m490a(t, this.f98d.m404b(), this.f100f), c0463c);
        }
    }

    public T mo1794b(C0460a c0460a) {
        if (this.f97c == null) {
            return m170b().mo1794b(c0460a);
        }
        C0554j a = C0528j.m381a(c0460a);
        return a.m462j() ? null : this.f97c.m474a(a, this.f98d.m404b(), this.f100f);
    }
}
