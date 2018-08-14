package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdListener;

@zzadh
public final class zzjf extends zzki {
    private final AdListener zzapu;

    public zzjf(AdListener adListener) {
        this.zzapu = adListener;
    }

    public final AdListener getAdListener() {
        return this.zzapu;
    }

    public final void onAdClicked() {
        this.zzapu.onAdClicked();
    }

    public final void onAdClosed() {
        this.zzapu.onAdClosed();
    }

    public final void onAdFailedToLoad(int i) {
        this.zzapu.onAdFailedToLoad(i);
    }

    public final void onAdImpression() {
        this.zzapu.onAdImpression();
    }

    public final void onAdLeftApplication() {
        this.zzapu.onAdLeftApplication();
    }

    public final void onAdLoaded() {
        this.zzapu.onAdLoaded();
    }

    public final void onAdOpened() {
        this.zzapu.onAdOpened();
    }
}
