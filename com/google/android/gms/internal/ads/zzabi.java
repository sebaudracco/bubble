package com.google.android.gms.internal.ads;

final class zzabi implements Runnable {
    private final /* synthetic */ zzabh zzbzk;

    zzabi(zzabh com_google_android_gms_internal_ads_zzabh) {
        this.zzbzk = com_google_android_gms_internal_ads_zzabh;
    }

    public final void run() {
        this.zzbzk.onStop();
    }
}
