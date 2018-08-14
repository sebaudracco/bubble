package com.p000a.p001a;

import com.p000a.p001a.p003b.p004a.C0464f;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p006d.C0544b;

public abstract class C0452t<T> {

    class C05671 extends C0452t<T> {
        final /* synthetic */ C0452t f302a;

        C05671(C0452t c0452t) {
            this.f302a = c0452t;
        }

        public void mo1793a(C0463c c0463c, T t) {
            if (t == null) {
                c0463c.mo1825f();
            } else {
                this.f302a.mo1793a(c0463c, t);
            }
        }

        public T mo1794b(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return this.f302a.mo1794b(c0460a);
            }
            c0460a.mo1805j();
            return null;
        }
    }

    public final C0554j m19a(T t) {
        try {
            C0463c c0464f = new C0464f();
            mo1793a(c0464f, t);
            return c0464f.mo1818a();
        } catch (Throwable e) {
            throw new C0558k(e);
        }
    }

    public final C0452t<T> m20a() {
        return new C05671(this);
    }

    public abstract void mo1793a(C0463c c0463c, T t);

    public abstract T mo1794b(C0460a c0460a);
}
