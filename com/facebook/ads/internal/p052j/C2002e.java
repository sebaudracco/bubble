package com.facebook.ads.internal.p052j;

import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class C2002e<T extends C1839f, E extends C2001d> {
    private final Map<Class<E>, List<WeakReference<T>>> f4701a = new HashMap();
    private final Queue<E> f4702b = new ArrayDeque();

    private void m6325a(List<WeakReference<T>> list) {
        if (list != null) {
            int i = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                WeakReference weakReference = (WeakReference) list.get(i2);
                if (weakReference.get() != null) {
                    int i3 = i + 1;
                    list.set(i, weakReference);
                    i = i3;
                }
            }
            for (int size = list.size() - 1; size >= i; size--) {
                list.remove(size);
            }
        }
    }

    private void m6326b(E e) {
        if (this.f4701a != null) {
            List list = (List) this.f4701a.get(e.getClass());
            if (list != null) {
                m6325a(list);
                if (!list.isEmpty()) {
                    for (WeakReference weakReference : new ArrayList(list)) {
                        C1839f c1839f = (C1839f) weakReference.get();
                        if (c1839f != null && c1839f.m5461b(e)) {
                            c1839f.mo3581a(e);
                        }
                    }
                }
            }
        }
    }

    public synchronized void m6327a(E e) {
        if (this.f4702b.isEmpty()) {
            this.f4702b.add(e);
            while (!this.f4702b.isEmpty()) {
                m6326b((C2001d) this.f4702b.peek());
                this.f4702b.remove();
            }
        } else {
            this.f4702b.add(e);
        }
    }

    public synchronized void m6328a(T... tArr) {
        if (tArr != null) {
            for (C1839f a : tArr) {
                m6329a(a);
            }
        }
    }

    public synchronized boolean m6329a(T t) {
        boolean z;
        if (t == null) {
            z = false;
        } else {
            Class a = t.mo3580a();
            if (this.f4701a.get(a) == null) {
                this.f4701a.put(a, new ArrayList());
            }
            List list = (List) this.f4701a.get(a);
            m6325a(list);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (((WeakReference) list.get(i)).get() == t) {
                    z = false;
                    break;
                }
            }
            z = list.add(new WeakReference(t));
        }
        return z;
    }

    public synchronized void m6330b(T... tArr) {
        if (tArr != null) {
            for (C1839f b : tArr) {
                m6331b(b);
            }
        }
    }

    public synchronized boolean m6331b(@Nullable T t) {
        boolean z;
        if (t == null) {
            z = false;
        } else {
            List list = (List) this.f4701a.get(t.mo3580a());
            if (list == null) {
                z = false;
            } else {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    if (((WeakReference) list.get(i)).get() == t) {
                        ((WeakReference) list.get(i)).clear();
                        z = true;
                        break;
                    }
                }
                z = false;
            }
        }
        return z;
    }
}
