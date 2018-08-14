package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;

@zzadh
public final class zzry extends zzra {
    private final OnContentAdLoadedListener zzblc;

    public zzry(OnContentAdLoadedListener onContentAdLoadedListener) {
        this.zzblc = onContentAdLoadedListener;
    }

    public final void zza(zzqo com_google_android_gms_internal_ads_zzqo) {
        this.zzblc.onContentAdLoaded(new zzqr(com_google_android_gms_internal_ads_zzqo));
    }
}
