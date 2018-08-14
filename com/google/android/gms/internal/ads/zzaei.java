package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

@zzadh
public final class zzaei extends zzaer {
    private final WeakReference<zzadx> zzcen;

    public zzaei(zzadx com_google_android_gms_internal_ads_zzadx) {
        this.zzcen = new WeakReference(com_google_android_gms_internal_ads_zzadx);
    }

    public final void zza(zzaej com_google_android_gms_internal_ads_zzaej) {
        zzadx com_google_android_gms_internal_ads_zzadx = (zzadx) this.zzcen.get();
        if (com_google_android_gms_internal_ads_zzadx != null) {
            com_google_android_gms_internal_ads_zzadx.zza(com_google_android_gms_internal_ads_zzaej);
        }
    }
}
