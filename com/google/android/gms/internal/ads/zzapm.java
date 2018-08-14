package com.google.android.gms.internal.ads;

final class zzapm implements Runnable {
    private final /* synthetic */ zzapi zzcyd;
    private final /* synthetic */ boolean zzcye;

    zzapm(zzapi com_google_android_gms_internal_ads_zzapi, boolean z) {
        this.zzcyd = com_google_android_gms_internal_ads_zzapi;
        this.zzcye = z;
    }

    public final void run() {
        zzapi.zza(this.zzcyd, "windowVisibilityChanged", new String[]{"isVisible", String.valueOf(this.zzcye)});
    }
}
