package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zzbab<E> extends AbstractList<E> implements zzbbt<E> {
    private boolean zzdpi = true;

    zzbab() {
    }

    public void add(int i, E e) {
        zzaba();
        super.add(i, e);
    }

    public boolean add(E e) {
        zzaba();
        return super.add(e);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        zzaba();
        return super.addAll(i, collection);
    }

    public boolean addAll(Collection<? extends E> collection) {
        zzaba();
        return super.addAll(collection);
    }

    public void clear() {
        zzaba();
        super.clear();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < size(); i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public E remove(int i) {
        zzaba();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        zzaba();
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        zzaba();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        zzaba();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        zzaba();
        return super.set(i, e);
    }

    public boolean zzaay() {
        return this.zzdpi;
    }

    public final void zzaaz() {
        this.zzdpi = false;
    }

    protected final void zzaba() {
        if (!this.zzdpi) {
            throw new UnsupportedOperationException();
        }
    }
}
