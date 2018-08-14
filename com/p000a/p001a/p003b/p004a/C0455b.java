package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.p003b.C0501b;
import com.p000a.p001a.p003b.C0502h;
import com.p000a.p001a.p003b.C0512c;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p006d.C0544b;
import com.p000a.p001a.p007c.C0542a;
import java.lang.reflect.Type;
import java.util.Collection;

public final class C0455b implements C0449u {
    private final C0512c f22a;

    private static final class C0454a<E> extends C0452t<Collection<E>> {
        private final C0452t<E> f20a;
        private final C0502h<? extends Collection<E>> f21b;

        public C0454a(C0552e c0552e, Type type, C0452t<E> c0452t, C0502h<? extends Collection<E>> c0502h) {
            this.f20a = new C0483m(c0552e, c0452t, type);
            this.f21b = c0502h;
        }

        public Collection<E> m25a(C0460a c0460a) {
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            Collection<E> collection = (Collection) this.f21b.mo1830a();
            c0460a.mo1795a();
            while (c0460a.mo1800e()) {
                collection.add(this.f20a.mo1794b(c0460a));
            }
            c0460a.mo1796b();
            return collection;
        }

        public void m27a(C0463c c0463c, Collection<E> collection) {
            if (collection == null) {
                c0463c.mo1825f();
                return;
            }
            c0463c.mo1819b();
            for (E a : collection) {
                this.f20a.mo1793a(c0463c, a);
            }
            c0463c.mo1821c();
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m25a(c0460a);
        }
    }

    public C0455b(C0512c c0512c) {
        this.f22a = c0512c;
    }

    public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
        Type b = c0542a.m404b();
        Class a = c0542a.m403a();
        if (!Collection.class.isAssignableFrom(a)) {
            return null;
        }
        Type a2 = C0501b.m315a(b, a);
        return new C0454a(c0552e, a2, c0552e.m438a(C0542a.m400a(a2)), this.f22a.m348a((C0542a) c0542a));
    }
}
