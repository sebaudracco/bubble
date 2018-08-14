package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0535d;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.C0563r;
import com.p000a.p001a.p002a.C0444b;
import com.p000a.p001a.p002a.C0445c;
import com.p000a.p001a.p003b.C0501b;
import com.p000a.p001a.p003b.C0502h;
import com.p000a.p001a.p003b.C0512c;
import com.p000a.p001a.p003b.C0514d;
import com.p000a.p001a.p003b.C0525i;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p006d.C0544b;
import com.p000a.p001a.p007c.C0542a;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class C0473i implements C0449u {
    private final C0512c f86a;
    private final C0535d f87b;
    private final C0514d f88c;
    private final C0458d f89d;

    static abstract class C0470b {
        final String f74h;
        final boolean f75i;
        final boolean f76j;

        protected C0470b(String str, boolean z, boolean z2) {
            this.f74h = str;
            this.f75i = z;
            this.f76j = z2;
        }

        abstract void mo1827a(C0460a c0460a, Object obj);

        abstract void mo1828a(C0463c c0463c, Object obj);

        abstract boolean mo1829a(Object obj);
    }

    public static final class C0472a<T> extends C0452t<T> {
        private final C0502h<T> f84a;
        private final Map<String, C0470b> f85b;

        C0472a(C0502h<T> c0502h, Map<String, C0470b> map) {
            this.f84a = c0502h;
            this.f85b = map;
        }

        public void mo1793a(C0463c c0463c, T t) {
            if (t == null) {
                c0463c.mo1825f();
                return;
            }
            c0463c.mo1823d();
            try {
                for (C0470b c0470b : this.f85b.values()) {
                    if (c0470b.mo1829a(t)) {
                        c0463c.mo1816a(c0470b.f74h);
                        c0470b.mo1828a(c0463c, (Object) t);
                    }
                }
                c0463c.mo1824e();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }

        public T mo1794b(C0460a c0460a) {
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            T a = this.f84a.mo1830a();
            try {
                c0460a.mo1797c();
                while (c0460a.mo1800e()) {
                    C0470b c0470b = (C0470b) this.f85b.get(c0460a.mo1802g());
                    if (c0470b == null || !c0470b.f76j) {
                        c0460a.mo1809n();
                    } else {
                        c0470b.mo1827a(c0460a, (Object) a);
                    }
                }
                c0460a.mo1799d();
                return a;
            } catch (Throwable e) {
                throw new C0563r(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    public C0473i(C0512c c0512c, C0535d c0535d, C0514d c0514d, C0458d c0458d) {
        this.f86a = c0512c;
        this.f87b = c0535d;
        this.f88c = c0514d;
        this.f89d = c0458d;
    }

    private C0470b m154a(C0552e c0552e, Field field, String str, C0542a<?> c0542a, boolean z, boolean z2) {
        final boolean a = C0525i.m380a(c0542a.m403a());
        C0444b c0444b = (C0444b) field.getAnnotation(C0444b.class);
        C0452t c0452t = null;
        if (c0444b != null) {
            c0452t = this.f89d.m36a(this.f86a, c0552e, c0542a, c0444b);
        }
        final boolean z3 = c0452t != null;
        if (c0452t == null) {
            c0452t = c0552e.m438a((C0542a) c0542a);
        }
        final Field field2 = field;
        final C0552e c0552e2 = c0552e;
        final C0542a<?> c0542a2 = c0542a;
        return new C0470b(this, str, z, z2) {
            final /* synthetic */ C0473i f83g;

            void mo1827a(C0460a c0460a, Object obj) {
                Object b = c0452t.mo1794b(c0460a);
                if (b != null || !a) {
                    field2.set(obj, b);
                }
            }

            void mo1828a(C0463c c0463c, Object obj) {
                (z3 ? c0452t : new C0483m(c0552e2, c0452t, c0542a2.m404b())).mo1793a(c0463c, field2.get(obj));
            }

            public boolean mo1829a(Object obj) {
                return this.i && field2.get(obj) != obj;
            }
        };
    }

    private List<String> m155a(Field field) {
        C0445c c0445c = (C0445c) field.getAnnotation(C0445c.class);
        if (c0445c == null) {
            return Collections.singletonList(this.f87b.mo1832a(field));
        }
        String a = c0445c.m7a();
        String[] b = c0445c.m8b();
        if (b.length == 0) {
            return Collections.singletonList(a);
        }
        List<String> arrayList = new ArrayList(b.length + 1);
        arrayList.add(a);
        for (Object add : b) {
            arrayList.add(add);
        }
        return arrayList;
    }

    private Map<String, C0470b> m156a(C0552e c0552e, C0542a<?> c0542a, Class<?> cls) {
        Map<String, C0470b> linkedHashMap = new LinkedHashMap();
        if (cls.isInterface()) {
            return linkedHashMap;
        }
        Type b = c0542a.m404b();
        Class a;
        while (a != Object.class) {
            for (Field field : a.getDeclaredFields()) {
                boolean a2 = m159a(field, true);
                boolean a3 = m159a(field, false);
                if (a2 || a3) {
                    field.setAccessible(true);
                    Type a4 = C0501b.m317a(r20.m404b(), a, field.getGenericType());
                    List a5 = m155a(field);
                    C0470b c0470b = null;
                    int size = a5.size();
                    int i = 0;
                    while (i < size) {
                        String str = (String) a5.get(i);
                        if (i != 0) {
                            a2 = false;
                        }
                        C0470b c0470b2 = (C0470b) linkedHashMap.put(str, m154a(c0552e, field, str, C0542a.m400a(a4), a2, a3));
                        if (c0470b != null) {
                            c0470b2 = c0470b;
                        }
                        i++;
                        c0470b = c0470b2;
                    }
                    if (c0470b != null) {
                        throw new IllegalArgumentException(b + " declares multiple JSON fields named " + c0470b.f74h);
                    }
                }
            }
            C0542a a6 = C0542a.m400a(C0501b.m317a(a6.m404b(), a, a.getGenericSuperclass()));
            a = a6.m403a();
        }
        return linkedHashMap;
    }

    static boolean m157a(Field field, boolean z, C0514d c0514d) {
        return (c0514d.m360a(field.getType(), z) || c0514d.m361a(field, z)) ? false : true;
    }

    public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
        Class a = c0542a.m403a();
        return !Object.class.isAssignableFrom(a) ? null : new C0472a(this.f86a.m348a((C0542a) c0542a), m156a(c0552e, (C0542a) c0542a, a));
    }

    public boolean m159a(Field field, boolean z) {
        return C0473i.m157a(field, z, this.f88c);
    }
}
