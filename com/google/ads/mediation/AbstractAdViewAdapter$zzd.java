package com.google.ads.mediation;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzjd;

@VisibleForTesting
final class AbstractAdViewAdapter$zzd extends AdListener implements AppEventListener, zzjd {
    @VisibleForTesting
    private final AbstractAdViewAdapter zzhh;
    @VisibleForTesting
    private final MediationBannerListener zzhi;

    public AbstractAdViewAdapter$zzd(AbstractAdViewAdapter abstractAdViewAdapter, MediationBannerListener mediationBannerListener) {
        this.zzhh = abstractAdViewAdapter;
        this.zzhi = mediationBannerListener;
    }

    public final void onAdClicked() {
        this.zzhi.onAdClicked(this.zzhh);
    }

    public final void onAdClosed() {
        this.zzhi.onAdClosed(this.zzhh);
    }

    public final void onAdFailedToLoad(int i) {
        this.zzhi.onAdFailedToLoad(this.zzhh, i);
    }

    public final void onAdLeftApplication() {
        this.zzhi.onAdLeftApplication(this.zzhh);
    }

    public final void onAdLoaded() {
        this.zzhi.onAdLoaded(this.zzhh);
    }

    public final void onAdOpened() {
        this.zzhi.onAdOpened(this.zzhh);
    }

    public final void onAppEvent(String str, String str2) {
        this.zzhi.zza(this.zzhh, str, str2);
    }
}
