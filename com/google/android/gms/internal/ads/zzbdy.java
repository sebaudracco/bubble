package com.google.android.gms.internal.ads;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

class zzbdy extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ zzbdp zzdyq;

    private zzbdy(zzbdp com_google_android_gms_internal_ads_zzbdp) {
        this.zzdyq = com_google_android_gms_internal_ads_zzbdp;
    }

    public /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzdyq.zza((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public void clear() {
        this.zzdyq.clear();
    }

    public boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zzdyq.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public Iterator<Entry<K, V>> iterator() {
        return new zzbdx(this.zzdyq, null);
    }

    public boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzdyq.remove(entry.getKey());
        return true;
    }

    public int size() {
        return this.zzdyq.size();
    }
}
