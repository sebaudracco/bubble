package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzbca<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zzdvj;

    public zzbca(Iterator<Entry<K, Object>> it) {
        this.zzdvj = it;
    }

    public final boolean hasNext() {
        return this.zzdvj.hasNext();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zzdvj.next();
        return entry.getValue() instanceof zzbbx ? new zzbbz(entry) : entry;
    }

    public final void remove() {
        this.zzdvj.remove();
    }
}
