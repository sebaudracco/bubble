package com.google.android.gms.internal.measurement;

import java.util.Map.Entry;

final class zzzw<K> implements Entry<K, Object> {
    private Entry<K, zzzu> zzbsu;

    private zzzw(Entry<K, zzzu> entry) {
        this.zzbsu = entry;
    }

    public final K getKey() {
        return this.zzbsu.getKey();
    }

    public final Object getValue() {
        return ((zzzu) this.zzbsu.getValue()) == null ? null : zzzu.zzto();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzaal) {
            return ((zzzu) this.zzbsu.getValue()).zzc((zzaal) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
