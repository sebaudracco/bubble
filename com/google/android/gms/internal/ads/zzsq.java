package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final /* synthetic */ class zzsq implements Runnable {
    private final zzaoj zzbnu;
    private final Future zzbnv;

    zzsq(zzaoj com_google_android_gms_internal_ads_zzaoj, Future future) {
        this.zzbnu = com_google_android_gms_internal_ads_zzaoj;
        this.zzbnv = future;
    }

    public final void run() {
        zzaoj com_google_android_gms_internal_ads_zzaoj = this.zzbnu;
        Future future = this.zzbnv;
        if (com_google_android_gms_internal_ads_zzaoj.isCancelled()) {
            future.cancel(true);
        }
    }
}
