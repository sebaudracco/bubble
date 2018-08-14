package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.p003b.C0524g;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p007c.C0542a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class C0469h extends C0452t<Object> {
    public static final C0449u f72a = new C04671();
    private final C0552e f73b;

    static class C04671 implements C0449u {
        C04671() {
        }

        public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
            return c0542a.m403a() == Object.class ? new C0469h(c0552e) : null;
        }
    }

    C0469h(C0552e c0552e) {
        this.f73b = c0552e;
    }

    public void mo1793a(C0463c c0463c, Object obj) {
        if (obj == null) {
            c0463c.mo1825f();
            return;
        }
        C0452t a = this.f73b.m440a(obj.getClass());
        if (a instanceof C0469h) {
            c0463c.mo1823d();
            c0463c.mo1824e();
            return;
        }
        a.mo1793a(c0463c, obj);
    }

    public Object mo1794b(C0460a c0460a) {
        switch (c0460a.mo1801f()) {
            case BEGIN_ARRAY:
                List arrayList = new ArrayList();
                c0460a.mo1795a();
                while (c0460a.mo1800e()) {
                    arrayList.add(mo1794b(c0460a));
                }
                c0460a.mo1796b();
                return arrayList;
            case BEGIN_OBJECT:
                Map c0524g = new C0524g();
                c0460a.mo1797c();
                while (c0460a.mo1800e()) {
                    c0524g.put(c0460a.mo1802g(), mo1794b(c0460a));
                }
                c0460a.mo1799d();
                return c0524g;
            case STRING:
                return c0460a.mo1803h();
            case NUMBER:
                return Double.valueOf(c0460a.mo1806k());
            case BOOLEAN:
                return Boolean.valueOf(c0460a.mo1804i());
            case NULL:
                c0460a.mo1805j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }
}
