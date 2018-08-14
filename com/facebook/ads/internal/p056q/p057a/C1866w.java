package com.facebook.ads.internal.p056q.p057a;

import java.lang.ref.WeakReference;

public abstract class C1866w<T> implements Runnable {
    private final WeakReference<T> f4091a;

    public C1866w(T t) {
        this.f4091a = new WeakReference(t);
    }

    public T m5564a() {
        return this.f4091a.get();
    }
}
