package com.google.android.gms.internal.measurement;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

final class zzabf extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ zzaay zzbuf;

    private zzabf(zzaay com_google_android_gms_internal_measurement_zzaay) {
        this.zzbuf = com_google_android_gms_internal_measurement_zzaay;
    }

    public final /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzbuf.zza((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public final void clear() {
        this.zzbuf.clear();
    }

    public final boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zzbuf.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzabe(this.zzbuf);
    }

    public final boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzbuf.remove(entry.getKey());
        return true;
    }

    public final int size() {
        return this.zzbuf.size();
    }
}
