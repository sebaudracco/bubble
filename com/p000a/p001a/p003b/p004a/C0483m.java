package com.p000a.p001a.p003b.p004a;

import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.p003b.p004a.C0473i.C0472a;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p007c.C0542a;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

final class C0483m<T> extends C0452t<T> {
    private final C0552e f102a;
    private final C0452t<T> f103b;
    private final Type f104c;

    C0483m(C0552e c0552e, C0452t<T> c0452t, Type type) {
        this.f102a = c0552e;
        this.f103b = c0452t;
        this.f104c = type;
    }

    private Type m173a(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }

    public void mo1793a(C0463c c0463c, T t) {
        C0452t c0452t = this.f103b;
        Type a = m173a(this.f104c, (Object) t);
        if (a != this.f104c) {
            c0452t = this.f102a.m438a(C0542a.m400a(a));
            if ((c0452t instanceof C0472a) && !(this.f103b instanceof C0472a)) {
                c0452t = this.f103b;
            }
        }
        c0452t.mo1793a(c0463c, t);
    }

    public T mo1794b(C0460a c0460a) {
        return this.f103b.mo1794b(c0460a);
    }
}
