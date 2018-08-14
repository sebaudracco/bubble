package com.p000a.p001a.p003b;

import com.p000a.p001a.C0448a;
import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0534b;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.p002a.C0443a;
import com.p000a.p001a.p002a.C0446d;
import com.p000a.p001a.p002a.C0447e;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p007c.C0542a;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public final class C0514d implements C0449u, Cloneable {
    public static final C0514d f211a = new C0514d();
    private double f212b = -1.0d;
    private int f213c = 136;
    private boolean f214d = true;
    private boolean f215e;
    private List<C0448a> f216f = Collections.emptyList();
    private List<C0448a> f217g = Collections.emptyList();

    private boolean m352a(C0446d c0446d) {
        return c0446d == null || c0446d.m9a() <= this.f212b;
    }

    private boolean m353a(C0446d c0446d, C0447e c0447e) {
        return m352a(c0446d) && m354a(c0447e);
    }

    private boolean m354a(C0447e c0447e) {
        return c0447e == null || c0447e.m10a() > this.f212b;
    }

    private boolean m355a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean m356b(Class<?> cls) {
        return cls.isMemberClass() && !m357c(cls);
    }

    private boolean m357c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    protected C0514d m358a() {
        try {
            return (C0514d) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
        Class a = c0542a.m403a();
        final boolean a2 = m360a(a, true);
        final boolean a3 = m360a(a, false);
        if (!a2 && !a3) {
            return null;
        }
        final C0552e c0552e2 = c0552e;
        final C0542a<T> c0542a2 = c0542a;
        return new C0452t<T>(this) {
            final /* synthetic */ C0514d f209e;
            private C0452t<T> f210f;

            private C0452t<T> m349b() {
                C0452t<T> c0452t = this.f210f;
                if (c0452t != null) {
                    return c0452t;
                }
                c0452t = c0552e2.m439a(this.f209e, c0542a2);
                this.f210f = c0452t;
                return c0452t;
            }

            public void mo1793a(C0463c c0463c, T t) {
                if (a2) {
                    c0463c.mo1825f();
                } else {
                    m349b().mo1793a(c0463c, t);
                }
            }

            public T mo1794b(C0460a c0460a) {
                if (!a3) {
                    return m349b().mo1794b(c0460a);
                }
                c0460a.mo1809n();
                return null;
            }
        };
    }

    public boolean m360a(Class<?> cls, boolean z) {
        if (this.f212b != -1.0d && !m353a((C0446d) cls.getAnnotation(C0446d.class), (C0447e) cls.getAnnotation(C0447e.class))) {
            return true;
        }
        if (!this.f214d && m356b(cls)) {
            return true;
        }
        if (m355a((Class) cls)) {
            return true;
        }
        for (C0448a a : z ? this.f216f : this.f217g) {
            if (a.m12a((Class) cls)) {
                return true;
            }
        }
        return false;
    }

    public boolean m361a(Field field, boolean z) {
        if ((this.f213c & field.getModifiers()) != 0) {
            return true;
        }
        if (this.f212b != -1.0d && !m353a((C0446d) field.getAnnotation(C0446d.class), (C0447e) field.getAnnotation(C0447e.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (this.f215e) {
            C0443a c0443a = (C0443a) field.getAnnotation(C0443a.class);
            if (c0443a == null || (z ? !c0443a.m3a() : !c0443a.m4b())) {
                return true;
            }
        }
        if (!this.f214d && m356b(field.getType())) {
            return true;
        }
        if (m355a(field.getType())) {
            return true;
        }
        List<C0448a> list = z ? this.f216f : this.f217g;
        if (!list.isEmpty()) {
            C0534b c0534b = new C0534b(field);
            for (C0448a a : list) {
                if (a.m11a(c0534b)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected /* synthetic */ Object clone() {
        return m358a();
    }
}
