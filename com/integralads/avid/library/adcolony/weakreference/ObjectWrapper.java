package com.integralads.avid.library.adcolony.weakreference;

import java.lang.ref.WeakReference;

public class ObjectWrapper<T> {
    private WeakReference<T> f8419a;

    public ObjectWrapper(T r) {
        this.f8419a = new WeakReference(r);
    }

    public T get() {
        return this.f8419a.get();
    }

    public void set(T value) {
        this.f8419a = new WeakReference(value);
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public boolean contains(Object obj) {
        Object obj2 = get();
        return (obj2 == null || obj == null || !obj2.equals(obj)) ? false : true;
    }
}
