package com.p000a.p001a.p007c;

import com.p000a.p001a.p003b.C0497a;
import com.p000a.p001a.p003b.C0501b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class C0542a<T> {
    final Class<? super T> f262a;
    final Type f263b;
    final int f264c;

    protected C0542a() {
        this.f263b = C0542a.m401a(getClass());
        this.f262a = C0501b.m326e(this.f263b);
        this.f264c = this.f263b.hashCode();
    }

    C0542a(Type type) {
        this.f263b = C0501b.m325d((Type) C0497a.m308a((Object) type));
        this.f262a = C0501b.m326e(this.f263b);
        this.f264c = this.f263b.hashCode();
    }

    public static C0542a<?> m400a(Type type) {
        return new C0542a(type);
    }

    static Type m401a(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return C0501b.m325d(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public static <T> C0542a<T> m402b(Class<T> cls) {
        return new C0542a(cls);
    }

    public final Class<? super T> m403a() {
        return this.f262a;
    }

    public final Type m404b() {
        return this.f263b;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C0542a) && C0501b.m320a(this.f263b, ((C0542a) obj).f263b);
    }

    public final int hashCode() {
        return this.f264c;
    }

    public final String toString() {
        return C0501b.m327f(this.f263b);
    }
}
