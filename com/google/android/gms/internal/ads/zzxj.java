package com.google.android.gms.internal.ads;

final class zzxj implements Runnable {
    private final /* synthetic */ zzxh zzbuk;
    private final /* synthetic */ zzanz zzbul;

    zzxj(zzxh com_google_android_gms_internal_ads_zzxh, zzanz com_google_android_gms_internal_ads_zzanz) {
        this.zzbuk = com_google_android_gms_internal_ads_zzxh;
        this.zzbul = com_google_android_gms_internal_ads_zzanz;
    }

    public final void run() {
        for (zzanz com_google_android_gms_internal_ads_zzanz : zzxh.zze(this.zzbuk).keySet()) {
            if (com_google_android_gms_internal_ads_zzanz != this.zzbul) {
                ((zzxb) zzxh.zze(this.zzbuk).get(com_google_android_gms_internal_ads_zzanz)).cancel();
            }
        }
    }
}
