package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final class zzafw implements zzv<Object> {
    private final /* synthetic */ zzaft zzchv;

    zzafw(zzaft com_google_android_gms_internal_ads_zzaft) {
        this.zzchv = com_google_android_gms_internal_ads_zzaft;
    }

    public final void zza(Object obj, Map<String, String> map) {
        synchronized (zzaft.zza(this.zzchv)) {
            if (zzaft.zzb(this.zzchv).isDone()) {
                return;
            }
            zzafz com_google_android_gms_internal_ads_zzafz = new zzafz(-2, map);
            if (zzaft.zzc(this.zzchv).equals(com_google_android_gms_internal_ads_zzafz.zzol())) {
                zzaft.zzb(this.zzchv).set(com_google_android_gms_internal_ads_zzafz);
                return;
            }
        }
    }
}
