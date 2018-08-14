package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;

@zzadh
public final class zzrx extends zzqx {
    private final OnAppInstallAdLoadedListener zzblb;

    public zzrx(OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
        this.zzblb = onAppInstallAdLoadedListener;
    }

    public final void zza(zzqk com_google_android_gms_internal_ads_zzqk) {
        this.zzblb.onAppInstallAdLoaded(new zzqn(com_google_android_gms_internal_ads_zzqk));
    }
}
