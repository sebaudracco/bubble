package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

@zzadh
public final class zzog extends zzoe {
    private final OnCustomRenderedAdLoadedListener zzasz;

    public zzog(OnCustomRenderedAdLoadedListener onCustomRenderedAdLoadedListener) {
        this.zzasz = onCustomRenderedAdLoadedListener;
    }

    public final void zza(zzoa com_google_android_gms_internal_ads_zzoa) {
        this.zzasz.onCustomRenderedAdLoaded(new zznz(com_google_android_gms_internal_ads_zzoa));
    }
}
