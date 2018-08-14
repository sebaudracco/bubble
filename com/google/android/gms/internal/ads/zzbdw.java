package com.google.android.gms.internal.ads;

import java.util.Map.Entry;

final class zzbdw implements Comparable<zzbdw>, Entry<K, V> {
    private V value;
    private final /* synthetic */ zzbdp zzdyq;
    private final K zzdyt;

    zzbdw(zzbdp com_google_android_gms_internal_ads_zzbdp, K k, V v) {
        this.zzdyq = com_google_android_gms_internal_ads_zzbdp;
        this.zzdyt = k;
        this.value = v;
    }

    zzbdw(zzbdp com_google_android_gms_internal_ads_zzbdp, Entry<K, V> entry) {
        this(com_google_android_gms_internal_ads_zzbdp, (Comparable) entry.getKey(), entry.getValue());
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zzbdw) obj).getKey());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        return equals(this.zzdyt, entry.getKey()) && equals(this.value, entry.getValue());
    }

    public final /* synthetic */ Object getKey() {
        return this.zzdyt;
    }

    public final V getValue() {
        return this.value;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = this.zzdyt == null ? 0 : this.zzdyt.hashCode();
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return hashCode ^ i;
    }

    public final V setValue(V v) {
        this.zzdyq.zzafv();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzdyt);
        String valueOf2 = String.valueOf(this.value);
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append("=").append(valueOf2).toString();
    }
}
