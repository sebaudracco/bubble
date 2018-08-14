package com.google.android.gms.internal.ads;

final class zzlz extends zzkd {
    private final /* synthetic */ zzly zzatc;

    zzlz(zzly com_google_android_gms_internal_ads_zzly) {
        this.zzatc = com_google_android_gms_internal_ads_zzly;
    }

    public final void onAdFailedToLoad(int i) {
        zzly.zza(this.zzatc).zza(this.zzatc.zzbc());
        super.onAdFailedToLoad(i);
    }

    public final void onAdLoaded() {
        zzly.zza(this.zzatc).zza(this.zzatc.zzbc());
        super.onAdLoaded();
    }
}
