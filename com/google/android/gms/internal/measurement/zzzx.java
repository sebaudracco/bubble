package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzzx<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zzbsv;

    public zzzx(Iterator<Entry<K, Object>> it) {
        this.zzbsv = it;
    }

    public final boolean hasNext() {
        return this.zzbsv.hasNext();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zzbsv.next();
        return entry.getValue() instanceof zzzu ? new zzzw(entry) : entry;
    }

    public final void remove() {
        this.zzbsv.remove();
    }
}
