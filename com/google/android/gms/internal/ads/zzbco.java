package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class zzbco<K, V> extends LinkedHashMap<K, V> {
    private static final zzbco zzdwc;
    private boolean zzdpi = true;

    static {
        zzbco com_google_android_gms_internal_ads_zzbco = new zzbco();
        zzdwc = com_google_android_gms_internal_ads_zzbco;
        com_google_android_gms_internal_ads_zzbco.zzdpi = false;
    }

    private zzbco() {
    }

    private zzbco(Map<K, V> map) {
        super(map);
    }

    public static <K, V> zzbco<K, V> zzaeb() {
        return zzdwc;
    }

    private final void zzaed() {
        if (!this.zzdpi) {
            throw new UnsupportedOperationException();
        }
    }

    private static int zzr(Object obj) {
        if (obj instanceof byte[]) {
            return zzbbq.hashCode((byte[]) obj);
        }
        if (!(obj instanceof zzbbr)) {
            return obj.hashCode();
        }
        throw new UnsupportedOperationException();
    }

    public final void clear() {
        zzaed();
        super.clear();
    }

    public final Set<Entry<K, V>> entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Map) {
            Object obj2;
            obj = (Map) obj;
            if (this != obj) {
                if (size() == obj.size()) {
                    for (Entry entry : entrySet()) {
                        if (!obj.containsKey(entry.getKey())) {
                            obj2 = null;
                            break;
                        }
                        boolean equals;
                        Object value = entry.getValue();
                        Object obj3 = obj.get(entry.getKey());
                        if ((value instanceof byte[]) && (obj3 instanceof byte[])) {
                            equals = Arrays.equals((byte[]) value, (byte[]) obj3);
                            continue;
                        } else {
                            equals = value.equals(obj3);
                            continue;
                        }
                        if (!equals) {
                            obj2 = null;
                            break;
                        }
                    }
                }
                obj2 = null;
                if (obj2 != null) {
                    return true;
                }
            }
            int i = 1;
            if (obj2 != null) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        for (Entry entry : entrySet()) {
            i = (zzr(entry.getValue()) ^ zzr(entry.getKey())) + i;
        }
        return i;
    }

    public final boolean isMutable() {
        return this.zzdpi;
    }

    public final V put(K k, V v) {
        zzaed();
        zzbbq.checkNotNull(k);
        zzbbq.checkNotNull(v);
        return super.put(k, v);
    }

    public final void putAll(Map<? extends K, ? extends V> map) {
        zzaed();
        for (Object next : map.keySet()) {
            zzbbq.checkNotNull(next);
            zzbbq.checkNotNull(map.get(next));
        }
        super.putAll(map);
    }

    public final V remove(Object obj) {
        zzaed();
        return super.remove(obj);
    }

    public final void zza(zzbco<K, V> com_google_android_gms_internal_ads_zzbco_K__V) {
        zzaed();
        if (!com_google_android_gms_internal_ads_zzbco_K__V.isEmpty()) {
            putAll(com_google_android_gms_internal_ads_zzbco_K__V);
        }
    }

    public final void zzaaz() {
        this.zzdpi = false;
    }

    public final zzbco<K, V> zzaec() {
        return isEmpty() ? new zzbco() : new zzbco(this);
    }
}
