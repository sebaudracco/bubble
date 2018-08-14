package com.google.ads.mediation;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzjd;

@VisibleForTesting
final class AbstractAdViewAdapter$zze extends AdListener implements zzjd {
    @VisibleForTesting
    private final AbstractAdViewAdapter zzhh;
    @VisibleForTesting
    private final MediationInterstitialListener zzhj;

    public AbstractAdViewAdapter$zze(AbstractAdViewAdapter abstractAdViewAdapter, MediationInterstitialListener mediationInterstitialListener) {
        this.zzhh = abstractAdViewAdapter;
        this.zzhj = mediationInterstitialListener;
    }

    public final void onAdClicked() {
        this.zzhj.onAdClicked(this.zzhh);
    }

    public final void onAdClosed() {
        this.zzhj.onAdClosed(this.zzhh);
    }

    public final void onAdFailedToLoad(int i) {
        this.zzhj.onAdFailedToLoad(this.zzhh, i);
    }

    public final void onAdLeftApplication() {
        this.zzhj.onAdLeftApplication(this.zzhh);
    }

    public final void onAdLoaded() {
        this.zzhj.onAdLoaded(this.zzhh);
    }

    public final void onAdOpened() {
        this.zzhj.onAdOpened(this.zzhh);
    }
}
