package com.moat.analytics.mobile.vng.p130a.p132b;

import java.util.NoSuchElementException;

public final class C3474a<T> {
    private static final C3474a<?> f8863a = new C3474a();
    private final T f8864b;

    private C3474a() {
        this.f8864b = null;
    }

    private C3474a(T t) {
        if (t == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.f8864b = t;
    }

    public static <T> C3474a<T> m11840a() {
        return f8863a;
    }

    public static <T> C3474a<T> m11841a(T t) {
        return new C3474a(t);
    }

    public static <T> C3474a<T> m11842b(T t) {
        return t == null ? C3474a.m11840a() : C3474a.m11841a(t);
    }

    public T m11843b() {
        if (this.f8864b != null) {
            return this.f8864b;
        }
        throw new NoSuchElementException("No value present");
    }

    public T m11844c(T t) {
        return this.f8864b != null ? this.f8864b : t;
    }

    public boolean m11845c() {
        return this.f8864b != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3474a)) {
            return false;
        }
        C3474a c3474a = (C3474a) obj;
        return this.f8864b == c3474a.f8864b || !(this.f8864b == null || c3474a.f8864b == null || !this.f8864b.equals(c3474a.f8864b));
    }

    public int hashCode() {
        return this.f8864b == null ? 0 : this.f8864b.hashCode();
    }

    public String toString() {
        if (this.f8864b == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{this.f8864b});
    }
}
