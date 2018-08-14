package com.integralads.avid.library.mopub.weakreference;

import java.lang.ref.WeakReference;

public class ObjectWrapper<T> {
    private WeakReference<T> weakReference;

    public ObjectWrapper(T r) {
        this.weakReference = new WeakReference(r);
    }

    public T get() {
        return this.weakReference.get();
    }

    public void set(T value) {
        this.weakReference = new WeakReference(value);
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public boolean contains(Object obj) {
        T value = get();
        return (value == null || obj == null || !value.equals(obj)) ? false : true;
    }
}
