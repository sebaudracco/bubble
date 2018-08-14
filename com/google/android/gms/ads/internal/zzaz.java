package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzaoe;

final /* synthetic */ class zzaz implements Runnable {
    private final Runnable zzxi;
    private final zzay zzzx;

    zzaz(zzay com_google_android_gms_ads_internal_zzay, Runnable runnable) {
        this.zzzx = com_google_android_gms_ads_internal_zzay;
        this.zzxi = runnable;
    }

    public final void run() {
        zzaoe.zzcvy.execute(new zzba(this.zzzx, this.zzxi));
    }
}
