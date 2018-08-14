package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzjj;

final class zzaj implements Runnable {
    private final /* synthetic */ zzjj zzyh;
    private final /* synthetic */ zzah zzyi;
    private final /* synthetic */ int zzyj;

    zzaj(zzah com_google_android_gms_ads_internal_zzah, zzjj com_google_android_gms_internal_ads_zzjj, int i) {
        this.zzyi = com_google_android_gms_ads_internal_zzah;
        this.zzyh = com_google_android_gms_internal_ads_zzjj;
        this.zzyj = i;
    }

    public final void run() {
        synchronized (zzah.zza(this.zzyi)) {
            zzah.zza(this.zzyi, this.zzyh, this.zzyj);
        }
    }
}
