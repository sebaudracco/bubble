package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzbdr implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzdyp;
    private final /* synthetic */ zzbdp zzdyq;

    private zzbdr(zzbdp com_google_android_gms_internal_ads_zzbdp) {
        this.zzdyq = com_google_android_gms_internal_ads_zzbdp;
        this.pos = this.zzdyq.zzdyk.size();
    }

    private final Iterator<Entry<K, V>> zzafx() {
        if (this.zzdyp == null) {
            this.zzdyp = this.zzdyq.zzdyn.entrySet().iterator();
        }
        return this.zzdyp;
    }

    public final boolean hasNext() {
        return (this.pos > 0 && this.pos <= this.zzdyq.zzdyk.size()) || zzafx().hasNext();
    }

    public final /* synthetic */ Object next() {
        if (zzafx().hasNext()) {
            return (Entry) zzafx().next();
        }
        List zzb = this.zzdyq.zzdyk;
        int i = this.pos - 1;
        this.pos = i;
        return (Entry) zzb.get(i);
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
