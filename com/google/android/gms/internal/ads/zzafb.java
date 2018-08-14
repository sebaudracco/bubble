package com.google.android.gms.internal.ads;

final class zzafb implements Runnable {
    private final /* synthetic */ zzafa zzcgj;
    private final /* synthetic */ zzaji zzwg;

    zzafb(zzafa com_google_android_gms_internal_ads_zzafa, zzaji com_google_android_gms_internal_ads_zzaji) {
        this.zzcgj = com_google_android_gms_internal_ads_zzafa;
        this.zzwg = com_google_android_gms_internal_ads_zzaji;
    }

    public final void run() {
        zzafa.zza(this.zzcgj).zza(this.zzwg);
        if (zzafa.zzb(this.zzcgj) != null) {
            zzafa.zzb(this.zzcgj).release();
            zzafa.zza(this.zzcgj, null);
        }
    }
}
