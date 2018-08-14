package com.google.android.gms.internal.ads;

final class zztr implements Runnable {
    private final /* synthetic */ zzts zzbob;
    private final /* synthetic */ zztt zzboc;

    zztr(zzst com_google_android_gms_internal_ads_zzst, zzts com_google_android_gms_internal_ads_zzts, zztt com_google_android_gms_internal_ads_zztt) {
        this.zzbob = com_google_android_gms_internal_ads_zzts;
        this.zzboc = com_google_android_gms_internal_ads_zztt;
    }

    public final void run() {
        try {
            this.zzbob.zzb(this.zzboc);
        } catch (Throwable e) {
            zzakb.zzc("Could not propagate interstitial ad event.", e);
        }
    }
}
