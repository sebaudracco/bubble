package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.p003b.C0501b;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p006d.C0544b;
import com.p000a.p001a.p007c.C0542a;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class C0453a<E> extends C0452t<Object> {
    public static final C0449u f17a = new C04501();
    private final Class<E> f18b;
    private final C0452t<E> f19c;

    static class C04501 implements C0449u {
        C04501() {
        }

        public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
            Type b = c0542a.m404b();
            if (!(b instanceof GenericArrayType) && (!(b instanceof Class) || !((Class) b).isArray())) {
                return null;
            }
            b = C0501b.m328g(b);
            return new C0453a(c0552e, c0552e.m438a(C0542a.m400a(b)), C0501b.m326e(b));
        }
    }

    public C0453a(C0552e c0552e, C0452t<E> c0452t, Class<E> cls) {
        this.f19c = new C0483m(c0552e, c0452t, cls);
        this.f18b = cls;
    }

    public void mo1793a(C0463c c0463c, Object obj) {
        if (obj == null) {
            c0463c.mo1825f();
            return;
        }
        c0463c.mo1819b();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            this.f19c.mo1793a(c0463c, Array.get(obj, i));
        }
        c0463c.mo1821c();
    }

    public Object mo1794b(C0460a c0460a) {
        if (c0460a.mo1801f() == C0544b.NULL) {
            c0460a.mo1805j();
            return null;
        }
        List arrayList = new ArrayList();
        c0460a.mo1795a();
        while (c0460a.mo1800e()) {
            arrayList.add(this.f19c.mo1794b(c0460a));
        }
        c0460a.mo1796b();
        int size = arrayList.size();
        Object newInstance = Array.newInstance(this.f18b, size);
        for (int i = 0; i < size; i++) {
            Array.set(newInstance, i, arrayList.get(i));
        }
        return newInstance;
    }
}
