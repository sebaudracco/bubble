package com.integralads.avid.library.inmobi.p128h;

import java.lang.ref.WeakReference;

/* compiled from: ObjectWrapper */
public class C3320c<T> {
    private WeakReference<T> f8467a;

    public C3320c(T t) {
        this.f8467a = new WeakReference(t);
    }

    public T m11354a() {
        return this.f8467a.get();
    }

    public void m11355a(T t) {
        this.f8467a = new WeakReference(t);
    }

    public boolean m11356b() {
        return m11354a() == null;
    }

    public boolean m11357b(Object obj) {
        Object a = m11354a();
        return (a == null || obj == null || !a.equals(obj)) ? false : true;
    }
}
