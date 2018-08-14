package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final class zzafq implements Runnable {
    private final /* synthetic */ Future zzchn;

    zzafq(zzafn com_google_android_gms_internal_ads_zzafn, Future future) {
        this.zzchn = future;
    }

    public final void run() {
        if (!this.zzchn.isDone()) {
            this.zzchn.cancel(true);
        }
    }
}
