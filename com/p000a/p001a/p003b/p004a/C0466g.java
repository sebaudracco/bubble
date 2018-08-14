package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.C0554j;
import com.p000a.p001a.C0561o;
import com.p000a.p001a.C0563r;
import com.p000a.p001a.p003b.C0501b;
import com.p000a.p001a.p003b.C0502h;
import com.p000a.p001a.p003b.C0512c;
import com.p000a.p001a.p003b.C0515e;
import com.p000a.p001a.p003b.C0528j;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p006d.C0544b;
import com.p000a.p001a.p007c.C0542a;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class C0466g implements C0449u {
    final boolean f69a;
    private final C0512c f70b;

    private final class C0465a<K, V> extends C0452t<Map<K, V>> {
        final /* synthetic */ C0466g f65a;
        private final C0452t<K> f66b;
        private final C0452t<V> f67c;
        private final C0502h<? extends Map<K, V>> f68d;

        public C0465a(C0466g c0466g, C0552e c0552e, Type type, C0452t<K> c0452t, Type type2, C0452t<V> c0452t2, C0502h<? extends Map<K, V>> c0502h) {
            this.f65a = c0466g;
            this.f66b = new C0483m(c0552e, c0452t, type);
            this.f67c = new C0483m(c0552e, c0452t2, type2);
            this.f68d = c0502h;
        }

        private String m136a(C0554j c0554j) {
            if (c0554j.m461i()) {
                C0561o m = c0554j.m465m();
                if (m.m488p()) {
                    return String.valueOf(m.mo1834a());
                }
                if (m.m487o()) {
                    return Boolean.toString(m.mo1839f());
                }
                if (m.m489q()) {
                    return m.mo1835b();
                }
                throw new AssertionError();
            } else if (c0554j.m462j()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }

        public Map<K, V> m137a(C0460a c0460a) {
            C0544b f = c0460a.mo1801f();
            if (f == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            Map<K, V> map = (Map) this.f68d.mo1830a();
            Object b;
            if (f == C0544b.BEGIN_ARRAY) {
                c0460a.mo1795a();
                while (c0460a.mo1800e()) {
                    c0460a.mo1795a();
                    b = this.f66b.mo1794b(c0460a);
                    if (map.put(b, this.f67c.mo1794b(c0460a)) != null) {
                        throw new C0563r("duplicate key: " + b);
                    }
                    c0460a.mo1796b();
                }
                c0460a.mo1796b();
                return map;
            }
            c0460a.mo1797c();
            while (c0460a.mo1800e()) {
                C0515e.f218a.mo1833a(c0460a);
                b = this.f66b.mo1794b(c0460a);
                if (map.put(b, this.f67c.mo1794b(c0460a)) != null) {
                    throw new C0563r("duplicate key: " + b);
                }
            }
            c0460a.mo1799d();
            return map;
        }

        public void m139a(C0463c c0463c, Map<K, V> map) {
            int i = 0;
            if (map == null) {
                c0463c.mo1825f();
            } else if (this.f65a.f69a) {
                List arrayList = new ArrayList(map.size());
                List arrayList2 = new ArrayList(map.size());
                int i2 = 0;
                for (Entry entry : map.entrySet()) {
                    C0554j a = this.f66b.m19a(entry.getKey());
                    arrayList.add(a);
                    arrayList2.add(entry.getValue());
                    int i3 = (a.m459g() || a.m460h()) ? 1 : 0;
                    i2 = i3 | i2;
                }
                if (i2 != 0) {
                    c0463c.mo1819b();
                    i2 = arrayList.size();
                    while (i < i2) {
                        c0463c.mo1819b();
                        C0528j.m383a((C0554j) arrayList.get(i), c0463c);
                        this.f67c.mo1793a(c0463c, arrayList2.get(i));
                        c0463c.mo1821c();
                        i++;
                    }
                    c0463c.mo1821c();
                    return;
                }
                c0463c.mo1823d();
                i2 = arrayList.size();
                while (i < i2) {
                    c0463c.mo1816a(m136a((C0554j) arrayList.get(i)));
                    this.f67c.mo1793a(c0463c, arrayList2.get(i));
                    i++;
                }
                c0463c.mo1824e();
            } else {
                c0463c.mo1823d();
                for (Entry entry2 : map.entrySet()) {
                    c0463c.mo1816a(String.valueOf(entry2.getKey()));
                    this.f67c.mo1793a(c0463c, entry2.getValue());
                }
                c0463c.mo1824e();
            }
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m137a(c0460a);
        }
    }

    public C0466g(C0512c c0512c, boolean z) {
        this.f70b = c0512c;
        this.f69a = z;
    }

    private C0452t<?> m141a(C0552e c0552e, Type type) {
        return (type == Boolean.TYPE || type == Boolean.class) ? C0496n.f153f : c0552e.m438a(C0542a.m400a(type));
    }

    public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
        Type b = c0542a.m404b();
        if (!Map.class.isAssignableFrom(c0542a.m403a())) {
            return null;
        }
        Type[] b2 = C0501b.m323b(b, C0501b.m326e(b));
        C0452t a = m141a(c0552e, b2[0]);
        C0452t a2 = c0552e.m438a(C0542a.m400a(b2[1]));
        C0502h a3 = this.f70b.m348a((C0542a) c0542a);
        return new C0465a(this, c0552e, b2[0], a, b2[1], a2, a3);
    }
}
