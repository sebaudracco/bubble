package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final /* synthetic */ class zzanw implements Runnable {
    private final Future zzbnv;
    private final zzanz zzcvs;

    zzanw(zzanz com_google_android_gms_internal_ads_zzanz, Future future) {
        this.zzcvs = com_google_android_gms_internal_ads_zzanz;
        this.zzbnv = future;
    }

    public final void run() {
        zzanz com_google_android_gms_internal_ads_zzanz = this.zzcvs;
        Future future = this.zzbnv;
        if (com_google_android_gms_internal_ads_zzanz.isCancelled()) {
            future.cancel(true);
        }
    }
}
