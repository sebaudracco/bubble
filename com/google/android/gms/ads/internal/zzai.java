package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzjj;

final class zzai implements Runnable {
    private final /* synthetic */ zzjj zzyh;
    private final /* synthetic */ zzah zzyi;

    zzai(zzah com_google_android_gms_ads_internal_zzah, zzjj com_google_android_gms_internal_ads_zzjj) {
        this.zzyi = com_google_android_gms_ads_internal_zzah;
        this.zzyh = com_google_android_gms_internal_ads_zzjj;
    }

    public final void run() {
        synchronized (zzah.zza(this.zzyi)) {
            if (zzah.zzb(this.zzyi)) {
                zzah.zza(this.zzyi, this.zzyh);
            } else {
                zzah.zza(this.zzyi, this.zzyh, 1);
            }
        }
    }
}
