package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzbdx implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzdyp;
    private final /* synthetic */ zzbdp zzdyq;
    private boolean zzdyu;

    private zzbdx(zzbdp com_google_android_gms_internal_ads_zzbdp) {
        this.zzdyq = com_google_android_gms_internal_ads_zzbdp;
        this.pos = -1;
    }

    private final Iterator<Entry<K, V>> zzafx() {
        if (this.zzdyp == null) {
            this.zzdyp = this.zzdyq.zzdyl.entrySet().iterator();
        }
        return this.zzdyp;
    }

    public final boolean hasNext() {
        return this.pos + 1 < this.zzdyq.zzdyk.size() || (!this.zzdyq.zzdyl.isEmpty() && zzafx().hasNext());
    }

    public final /* synthetic */ Object next() {
        this.zzdyu = true;
        int i = this.pos + 1;
        this.pos = i;
        return i < this.zzdyq.zzdyk.size() ? (Entry) this.zzdyq.zzdyk.get(this.pos) : (Entry) zzafx().next();
    }

    public final void remove() {
        if (this.zzdyu) {
            this.zzdyu = false;
            this.zzdyq.zzafv();
            if (this.pos < this.zzdyq.zzdyk.size()) {
                zzbdp com_google_android_gms_internal_ads_zzbdp = this.zzdyq;
                int i = this.pos;
                this.pos = i - 1;
                com_google_android_gms_internal_ads_zzbdp.zzcz(i);
                return;
            }
            zzafx().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}
