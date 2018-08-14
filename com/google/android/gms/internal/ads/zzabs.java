package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzas;
import java.util.concurrent.CountDownLatch;

final class zzabs implements Runnable {
    private final /* synthetic */ zzabr zzbzt;
    private final /* synthetic */ CountDownLatch zzwd;

    zzabs(zzabr com_google_android_gms_internal_ads_zzabr, CountDownLatch countDownLatch) {
        this.zzbzt = com_google_android_gms_internal_ads_zzabr;
        this.zzwd = countDownLatch;
    }

    public final void run() {
        synchronized (this.zzbzt.zzbzh) {
            zzabr.zza(this.zzbzt, zzas.zza(zzabr.zza(this.zzbzt), this.zzbzt.zzbzr, this.zzwd));
        }
    }
}
