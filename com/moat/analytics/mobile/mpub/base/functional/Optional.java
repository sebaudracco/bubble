package com.moat.analytics.mobile.mpub.base.functional;

import java.util.NoSuchElementException;

public final class Optional<T> {
    private static final Optional<?> f8672 = new Optional();
    private final T f8673;

    private Optional() {
        this.f8673 = null;
    }

    public static <T> Optional<T> empty() {
        return f8672;
    }

    private Optional(T t) {
        if (t == null) {
            throw new NullPointerException("Optional of null value.");
        }
        this.f8673 = t;
    }

    public static <T> Optional<T> of(T t) {
        return new Optional(t);
    }

    public static <T> Optional<T> ofNullable(T t) {
        return t == null ? empty() : of(t);
    }

    public final T get() {
        if (this.f8673 != null) {
            return this.f8673;
        }
        throw new NoSuchElementException("No value present");
    }

    public final boolean isPresent() {
        return this.f8673 != null;
    }

    public final T orElse(T t) {
        return this.f8673 != null ? this.f8673 : t;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Optional)) {
            return false;
        }
        Optional optional = (Optional) obj;
        if (this.f8673 == optional.f8673) {
            return true;
        }
        if (this.f8673 == null || optional.f8673 == null || !this.f8673.equals(optional.f8673)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.f8673 == null ? 0 : this.f8673.hashCode();
    }

    public final String toString() {
        if (this.f8673 == null) {
            return "Optional.empty";
        }
        return String.format("Optional[%s]", new Object[]{this.f8673});
    }
}
