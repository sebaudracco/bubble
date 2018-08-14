package com.moat.analytics.mobile.inm.base.functional;

import java.util.NoSuchElementException;

public final class C3378a<T> {
    private static final C3378a<?> f8575a = new C3378a();
    private final T f8576b;

    private C3378a() {
        this.f8576b = null;
    }

    private C3378a(T t) {
        if (t == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.f8576b = t;
    }

    public static <T> C3378a<T> m11558a() {
        return f8575a;
    }

    public static <T> C3378a<T> m11559a(T t) {
        return new C3378a(t);
    }

    public static <T> C3378a<T> m11560b(T t) {
        return t == null ? C3378a.m11558a() : C3378a.m11559a(t);
    }

    public T m11561b() {
        if (this.f8576b != null) {
            return this.f8576b;
        }
        throw new NoSuchElementException("No value present");
    }

    public T m11562c(T t) {
        return this.f8576b != null ? this.f8576b : t;
    }

    public boolean m11563c() {
        return this.f8576b != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3378a)) {
            return false;
        }
        C3378a c3378a = (C3378a) obj;
        return this.f8576b != c3378a.f8576b ? (this.f8576b == null || c3378a.f8576b == null) ? false : this.f8576b.equals(c3378a.f8576b) : true;
    }

    public int hashCode() {
        return this.f8576b == null ? 0 : this.f8576b.hashCode();
    }

    public String toString() {
        if (this.f8576b == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{this.f8576b});
    }
}
