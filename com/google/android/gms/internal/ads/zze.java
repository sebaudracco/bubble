package com.google.android.gms.internal.ads;

final class zze implements Runnable {
    private final /* synthetic */ zzr zzn;
    private final /* synthetic */ zzd zzo;

    zze(zzd com_google_android_gms_internal_ads_zzd, zzr com_google_android_gms_internal_ads_zzr) {
        this.zzo = com_google_android_gms_internal_ads_zzd;
        this.zzn = com_google_android_gms_internal_ads_zzr;
    }

    public final void run() {
        try {
            this.zzo.zzi.put(this.zzn);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
