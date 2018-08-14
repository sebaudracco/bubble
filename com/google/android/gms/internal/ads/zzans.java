package com.google.android.gms.internal.ads;

import java.util.concurrent.TimeoutException;

final /* synthetic */ class zzans implements Runnable {
    private final zzaoj zzbnu;

    zzans(zzaoj com_google_android_gms_internal_ads_zzaoj) {
        this.zzbnu = com_google_android_gms_internal_ads_zzaoj;
    }

    public final void run() {
        this.zzbnu.setException(new TimeoutException());
    }
}
