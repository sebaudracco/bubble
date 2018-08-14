package com.google.android.gms.internal.ads;

import java.util.Map.Entry;

final class zzbbz<K> implements Entry<K, Object> {
    private Entry<K, zzbbx> zzdvi;

    private zzbbz(Entry<K, zzbbx> entry) {
        this.zzdvi = entry;
    }

    public final K getKey() {
        return this.zzdvi.getKey();
    }

    public final Object getValue() {
        return ((zzbbx) this.zzdvi.getValue()) == null ? null : zzbbx.zzadu();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzbcu) {
            return ((zzbbx) this.zzdvi.getValue()).zzl((zzbcu) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzbbx zzadv() {
        return (zzbbx) this.zzdvi.getValue();
    }
}
