package com.google.android.gms.internal.measurement;

import java.util.Map.Entry;

final class zzabd implements Comparable<zzabd>, Entry<K, V> {
    private V value;
    private final K zzbue;
    private final /* synthetic */ zzaay zzbuf;

    zzabd(zzaay com_google_android_gms_internal_measurement_zzaay, K k, V v) {
        this.zzbuf = com_google_android_gms_internal_measurement_zzaay;
        this.zzbue = k;
        this.value = v;
    }

    zzabd(zzaay com_google_android_gms_internal_measurement_zzaay, Entry<K, V> entry) {
        this(com_google_android_gms_internal_measurement_zzaay, (Comparable) entry.getKey(), entry.getValue());
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zzabd) obj).getKey());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        return equals(this.zzbue, entry.getKey()) && equals(this.value, entry.getValue());
    }

    public final /* synthetic */ Object getKey() {
        return this.zzbue;
    }

    public final V getValue() {
        return this.value;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = this.zzbue == null ? 0 : this.zzbue.hashCode();
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return hashCode ^ i;
    }

    public final V setValue(V v) {
        this.zzbuf.zzul();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.zzbue);
        String valueOf2 = String.valueOf(this.value);
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append("=").append(valueOf2).toString();
    }
}
