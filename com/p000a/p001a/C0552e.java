package com.p000a.p001a;

import com.p000a.p001a.p003b.C0512c;
import com.p000a.p001a.p003b.C0514d;
import com.p000a.p001a.p003b.C0525i;
import com.p000a.p001a.p003b.C0528j;
import com.p000a.p001a.p003b.p004a.C0453a;
import com.p000a.p001a.p003b.p004a.C0455b;
import com.p000a.p001a.p003b.p004a.C0457c;
import com.p000a.p001a.p003b.p004a.C0458d;
import com.p000a.p001a.p003b.p004a.C0466g;
import com.p000a.p001a.p003b.p004a.C0469h;
import com.p000a.p001a.p003b.p004a.C0473i;
import com.p000a.p001a.p003b.p004a.C0475j;
import com.p000a.p001a.p003b.p004a.C0477k;
import com.p000a.p001a.p003b.p004a.C0496n;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p006d.C0544b;
import com.p000a.p001a.p007c.C0542a;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public final class C0552e {
    private static final C0542a<?> f281a = C0542a.m402b(Object.class);
    private final ThreadLocal<Map<C0542a<?>, C0551a<?>>> f282b;
    private final Map<C0542a<?>, C0452t<?>> f283c;
    private final List<C0449u> f284d;
    private final C0512c f285e;
    private final C0514d f286f;
    private final C0535d f287g;
    private final boolean f288h;
    private final boolean f289i;
    private final boolean f290j;
    private final boolean f291k;
    private final boolean f292l;
    private final C0458d f293m;

    class C05461 extends C0452t<Number> {
        final /* synthetic */ C0552e f276a;

        C05461(C0552e c0552e) {
            this.f276a = c0552e;
        }

        public Double m406a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return Double.valueOf(c0460a.mo1806k());
            }
            c0460a.mo1805j();
            return null;
        }

        public void m407a(C0463c c0463c, Number number) {
            if (number == null) {
                c0463c.mo1825f();
                return;
            }
            C0552e.m432a(number.doubleValue());
            c0463c.mo1815a(number);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m406a(c0460a);
        }
    }

    class C05472 extends C0452t<Number> {
        final /* synthetic */ C0552e f277a;

        C05472(C0552e c0552e) {
            this.f277a = c0552e;
        }

        public Float m410a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return Float.valueOf((float) c0460a.mo1806k());
            }
            c0460a.mo1805j();
            return null;
        }

        public void m411a(C0463c c0463c, Number number) {
            if (number == null) {
                c0463c.mo1825f();
                return;
            }
            C0552e.m432a((double) number.floatValue());
            c0463c.mo1815a(number);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m410a(c0460a);
        }
    }

    static class C05483 extends C0452t<Number> {
        C05483() {
        }

        public Number m414a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return Long.valueOf(c0460a.mo1807l());
            }
            c0460a.mo1805j();
            return null;
        }

        public void m415a(C0463c c0463c, Number number) {
            if (number == null) {
                c0463c.mo1825f();
            } else {
                c0463c.mo1820b(number.toString());
            }
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m414a(c0460a);
        }
    }

    static class C0551a<T> extends C0452t<T> {
        private C0452t<T> f280a;

        C0551a() {
        }

        public void mo1793a(C0463c c0463c, T t) {
            if (this.f280a == null) {
                throw new IllegalStateException();
            }
            this.f280a.mo1793a(c0463c, t);
        }

        public void m427a(C0452t<T> c0452t) {
            if (this.f280a != null) {
                throw new AssertionError();
            }
            this.f280a = c0452t;
        }

        public T mo1794b(C0460a c0460a) {
            if (this.f280a != null) {
                return this.f280a.mo1794b(c0460a);
            }
            throw new IllegalStateException();
        }
    }

    public C0552e() {
        this(C0514d.f211a, C0536c.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, C0564s.f299a, Collections.emptyList());
    }

    C0552e(C0514d c0514d, C0535d c0535d, Map<Type, C0553f<?>> map, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, C0564s c0564s, List<C0449u> list) {
        this.f282b = new ThreadLocal();
        this.f283c = new ConcurrentHashMap();
        this.f285e = new C0512c(map);
        this.f286f = c0514d;
        this.f287g = c0535d;
        this.f288h = z;
        this.f290j = z3;
        this.f289i = z4;
        this.f291k = z5;
        this.f292l = z6;
        List arrayList = new ArrayList();
        arrayList.add(C0496n.f146Y);
        arrayList.add(C0469h.f72a);
        arrayList.add(c0514d);
        arrayList.addAll(list);
        arrayList.add(C0496n.f125D);
        arrayList.add(C0496n.f160m);
        arrayList.add(C0496n.f154g);
        arrayList.add(C0496n.f156i);
        arrayList.add(C0496n.f158k);
        C0452t a = C0552e.m429a(c0564s);
        arrayList.add(C0496n.m305a(Long.TYPE, Long.class, a));
        arrayList.add(C0496n.m305a(Double.TYPE, Double.class, m431a(z7)));
        arrayList.add(C0496n.m305a(Float.TYPE, Float.class, m435b(z7)));
        arrayList.add(C0496n.f171x);
        arrayList.add(C0496n.f162o);
        arrayList.add(C0496n.f164q);
        arrayList.add(C0496n.m304a(AtomicLong.class, C0552e.m430a(a)));
        arrayList.add(C0496n.m304a(AtomicLongArray.class, C0552e.m434b(a)));
        arrayList.add(C0496n.f166s);
        arrayList.add(C0496n.f173z);
        arrayList.add(C0496n.f127F);
        arrayList.add(C0496n.f129H);
        arrayList.add(C0496n.m304a(BigDecimal.class, C0496n.f123B));
        arrayList.add(C0496n.m304a(BigInteger.class, C0496n.f124C));
        arrayList.add(C0496n.f131J);
        arrayList.add(C0496n.f133L);
        arrayList.add(C0496n.f137P);
        arrayList.add(C0496n.f139R);
        arrayList.add(C0496n.f144W);
        arrayList.add(C0496n.f135N);
        arrayList.add(C0496n.f151d);
        arrayList.add(C0457c.f23a);
        arrayList.add(C0496n.f142U);
        arrayList.add(C0477k.f92a);
        arrayList.add(C0475j.f90a);
        arrayList.add(C0496n.f140S);
        arrayList.add(C0453a.f17a);
        arrayList.add(C0496n.f149b);
        arrayList.add(new C0455b(this.f285e));
        arrayList.add(new C0466g(this.f285e, z2));
        this.f293m = new C0458d(this.f285e);
        arrayList.add(this.f293m);
        arrayList.add(C0496n.f147Z);
        arrayList.add(new C0473i(this.f285e, c0535d, c0514d, this.f293m));
        this.f284d = Collections.unmodifiableList(arrayList);
    }

    private static C0452t<Number> m429a(C0564s c0564s) {
        return c0564s == C0564s.f299a ? C0496n.f167t : new C05483();
    }

    private static C0452t<AtomicLong> m430a(final C0452t<Number> c0452t) {
        return new C0452t<AtomicLong>() {
            public AtomicLong m418a(C0460a c0460a) {
                return new AtomicLong(((Number) c0452t.mo1794b(c0460a)).longValue());
            }

            public void m420a(C0463c c0463c, AtomicLong atomicLong) {
                c0452t.mo1793a(c0463c, Long.valueOf(atomicLong.get()));
            }

            public /* synthetic */ Object mo1794b(C0460a c0460a) {
                return m418a(c0460a);
            }
        }.m20a();
    }

    private C0452t<Number> m431a(boolean z) {
        return z ? C0496n.f169v : new C05461(this);
    }

    static void m432a(double d) {
        if (Double.isNaN(d) || Double.isInfinite(d)) {
            throw new IllegalArgumentException(d + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    private static void m433a(Object obj, C0460a c0460a) {
        if (obj != null) {
            try {
                if (c0460a.mo1801f() != C0544b.END_DOCUMENT) {
                    throw new C0558k("JSON document was not fully consumed.");
                }
            } catch (Throwable e) {
                throw new C0563r(e);
            } catch (Throwable e2) {
                throw new C0558k(e2);
            }
        }
    }

    private static C0452t<AtomicLongArray> m434b(final C0452t<Number> c0452t) {
        return new C0452t<AtomicLongArray>() {
            public AtomicLongArray m422a(C0460a c0460a) {
                List arrayList = new ArrayList();
                c0460a.mo1795a();
                while (c0460a.mo1800e()) {
                    arrayList.add(Long.valueOf(((Number) c0452t.mo1794b(c0460a)).longValue()));
                }
                c0460a.mo1796b();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            }

            public void m424a(C0463c c0463c, AtomicLongArray atomicLongArray) {
                c0463c.mo1819b();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    c0452t.mo1793a(c0463c, Long.valueOf(atomicLongArray.get(i)));
                }
                c0463c.mo1821c();
            }

            public /* synthetic */ Object mo1794b(C0460a c0460a) {
                return m422a(c0460a);
            }
        }.m20a();
    }

    private C0452t<Number> m435b(boolean z) {
        return z ? C0496n.f168u : new C05472(this);
    }

    public C0460a m436a(Reader reader) {
        C0460a c0460a = new C0460a(reader);
        c0460a.m55a(this.f292l);
        return c0460a;
    }

    public C0463c m437a(Writer writer) {
        if (this.f290j) {
            writer.write(")]}'\n");
        }
        C0463c c0463c = new C0463c(writer);
        if (this.f291k) {
            c0463c.m113c("  ");
        }
        c0463c.m116d(this.f288h);
        return c0463c;
    }

    public <T> C0452t<T> m438a(C0542a<T> c0542a) {
        Object obj;
        Map map = this.f283c;
        if (c0542a == null) {
            obj = f281a;
        } else {
            C0542a<T> c0542a2 = c0542a;
        }
        C0452t<T> c0452t = (C0452t) map.get(obj);
        if (c0452t == null) {
            Map map2;
            Map map3 = (Map) this.f282b.get();
            Object obj2 = null;
            if (map3 == null) {
                HashMap hashMap = new HashMap();
                this.f282b.set(hashMap);
                map2 = hashMap;
                obj2 = 1;
            } else {
                map2 = map3;
            }
            C0551a c0551a = (C0551a) map2.get(c0542a);
            if (c0551a == null) {
                try {
                    C0551a c0551a2 = new C0551a();
                    map2.put(c0542a, c0551a2);
                    for (C0449u a : this.f284d) {
                        c0452t = a.mo1792a(this, c0542a);
                        if (c0452t != null) {
                            c0551a2.m427a(c0452t);
                            this.f283c.put(c0542a, c0452t);
                            map2.remove(c0542a);
                            if (obj2 != null) {
                                this.f282b.remove();
                            }
                        }
                    }
                    throw new IllegalArgumentException("GSON cannot handle " + c0542a);
                } catch (Throwable th) {
                    map2.remove(c0542a);
                    if (obj2 != null) {
                        this.f282b.remove();
                    }
                }
            }
        }
        return c0452t;
    }

    public <T> C0452t<T> m439a(C0449u c0449u, C0542a<T> c0542a) {
        if (!this.f284d.contains(c0449u)) {
            c0449u = this.f293m;
        }
        Object obj = null;
        for (C0449u c0449u2 : this.f284d) {
            if (obj != null) {
                C0452t<T> a = c0449u2.mo1792a(this, c0542a);
                if (a != null) {
                    return a;
                }
            } else if (c0449u2 == c0449u) {
                obj = 1;
            }
        }
        throw new IllegalArgumentException("GSON cannot serialize " + c0542a);
    }

    public <T> C0452t<T> m440a(Class<T> cls) {
        return m438a(C0542a.m402b(cls));
    }

    public <T> T m441a(C0460a c0460a, Type type) {
        boolean z = true;
        boolean q = c0460a.m70q();
        c0460a.m55a(true);
        try {
            c0460a.mo1801f();
            z = false;
            T b = m438a(C0542a.m400a(type)).mo1794b(c0460a);
            c0460a.m55a(q);
            return b;
        } catch (Throwable e) {
            if (z) {
                c0460a.m55a(q);
                return null;
            }
            throw new C0563r(e);
        } catch (Throwable e2) {
            throw new C0563r(e2);
        } catch (Throwable e22) {
            throw new C0563r(e22);
        } catch (Throwable th) {
            c0460a.m55a(q);
        }
    }

    public <T> T m442a(Reader reader, Type type) {
        C0460a a = m436a(reader);
        Object a2 = m441a(a, type);
        C0552e.m433a(a2, a);
        return a2;
    }

    public <T> T m443a(String str, Class<T> cls) {
        return C0525i.m378a((Class) cls).cast(m444a(str, (Type) cls));
    }

    public <T> T m444a(String str, Type type) {
        return str == null ? null : m442a(new StringReader(str), type);
    }

    public String m445a(C0554j c0554j) {
        Appendable stringWriter = new StringWriter();
        m449a(c0554j, stringWriter);
        return stringWriter.toString();
    }

    public String m446a(Object obj) {
        return obj == null ? m445a(C0559l.f295a) : m447a(obj, obj.getClass());
    }

    public String m447a(Object obj, Type type) {
        Appendable stringWriter = new StringWriter();
        m451a(obj, type, stringWriter);
        return stringWriter.toString();
    }

    public void m448a(C0554j c0554j, C0463c c0463c) {
        boolean g = c0463c.m119g();
        c0463c.m111b(true);
        boolean h = c0463c.m120h();
        c0463c.m114c(this.f289i);
        boolean i = c0463c.m121i();
        c0463c.m116d(this.f288h);
        try {
            C0528j.m383a(c0554j, c0463c);
            c0463c.m111b(g);
            c0463c.m114c(h);
            c0463c.m116d(i);
        } catch (Throwable e) {
            throw new C0558k(e);
        } catch (Throwable th) {
            c0463c.m111b(g);
            c0463c.m114c(h);
            c0463c.m116d(i);
        }
    }

    public void m449a(C0554j c0554j, Appendable appendable) {
        try {
            m448a(c0554j, m437a(C0528j.m382a(appendable)));
        } catch (Throwable e) {
            throw new C0558k(e);
        }
    }

    public void m450a(Object obj, Type type, C0463c c0463c) {
        C0452t a = m438a(C0542a.m400a(type));
        boolean g = c0463c.m119g();
        c0463c.m111b(true);
        boolean h = c0463c.m120h();
        c0463c.m114c(this.f289i);
        boolean i = c0463c.m121i();
        c0463c.m116d(this.f288h);
        try {
            a.mo1793a(c0463c, obj);
            c0463c.m111b(g);
            c0463c.m114c(h);
            c0463c.m116d(i);
        } catch (Throwable e) {
            throw new C0558k(e);
        } catch (Throwable th) {
            c0463c.m111b(g);
            c0463c.m114c(h);
            c0463c.m116d(i);
        }
    }

    public void m451a(Object obj, Type type, Appendable appendable) {
        try {
            m450a(obj, type, m437a(C0528j.m382a(appendable)));
        } catch (Throwable e) {
            throw new C0558k(e);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.f288h + ",factories:" + this.f284d + ",instanceCreators:" + this.f285e + "}";
    }
}
