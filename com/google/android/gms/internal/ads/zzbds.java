package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzbds extends zzbdy {
    private final /* synthetic */ zzbdp zzdyq;

    private zzbds(zzbdp com_google_android_gms_internal_ads_zzbdp) {
        this.zzdyq = com_google_android_gms_internal_ads_zzbdp;
        super(com_google_android_gms_internal_ads_zzbdp);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzbdr(this.zzdyq);
    }
}
