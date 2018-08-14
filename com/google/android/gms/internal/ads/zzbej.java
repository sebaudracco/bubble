package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class zzbej implements Iterator<String> {
    private final /* synthetic */ zzbeh zzdzc;
    private Iterator<String> zzdzd = this.zzdzc.zzdyz.iterator();

    zzbej(zzbeh com_google_android_gms_internal_ads_zzbeh) {
        this.zzdzc = com_google_android_gms_internal_ads_zzbeh;
    }

    public final boolean hasNext() {
        return this.zzdzd.hasNext();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzdzd.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
