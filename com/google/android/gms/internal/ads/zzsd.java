package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;

@zzadh
public final class zzsd extends zzrm {
    private final OnUnifiedNativeAdLoadedListener zzblj;

    public zzsd(OnUnifiedNativeAdLoadedListener onUnifiedNativeAdLoadedListener) {
        this.zzblj = onUnifiedNativeAdLoadedListener;
    }

    public final void zza(zzrr com_google_android_gms_internal_ads_zzrr) {
        this.zzblj.onUnifiedNativeAdLoaded(new zzru(com_google_android_gms_internal_ads_zzrr));
    }
}
