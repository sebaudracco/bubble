package com.google.ads.mediation;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd.OnUnifiedNativeAdLoadedListener;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class AbstractAdViewAdapter$zzf extends AdListener implements OnAppInstallAdLoadedListener, OnContentAdLoadedListener, OnCustomClickListener, OnCustomTemplateAdLoadedListener, OnUnifiedNativeAdLoadedListener {
    @VisibleForTesting
    private final AbstractAdViewAdapter zzhh;
    @VisibleForTesting
    private final MediationNativeListener zzhk;

    public AbstractAdViewAdapter$zzf(AbstractAdViewAdapter abstractAdViewAdapter, MediationNativeListener mediationNativeListener) {
        this.zzhh = abstractAdViewAdapter;
        this.zzhk = mediationNativeListener;
    }

    public final void onAdClicked() {
        this.zzhk.onAdClicked(this.zzhh);
    }

    public final void onAdClosed() {
        this.zzhk.onAdClosed(this.zzhh);
    }

    public final void onAdFailedToLoad(int i) {
        this.zzhk.onAdFailedToLoad(this.zzhh, i);
    }

    public final void onAdImpression() {
        this.zzhk.onAdImpression(this.zzhh);
    }

    public final void onAdLeftApplication() {
        this.zzhk.onAdLeftApplication(this.zzhh);
    }

    public final void onAdLoaded() {
    }

    public final void onAdOpened() {
        this.zzhk.onAdOpened(this.zzhh);
    }

    public final void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
        this.zzhk.onAdLoaded(this.zzhh, new AbstractAdViewAdapter$zza(nativeAppInstallAd));
    }

    public final void onContentAdLoaded(NativeContentAd nativeContentAd) {
        this.zzhk.onAdLoaded(this.zzhh, new AbstractAdViewAdapter$zzb(nativeContentAd));
    }

    public final void onCustomClick(NativeCustomTemplateAd nativeCustomTemplateAd, String str) {
        this.zzhk.zza(this.zzhh, nativeCustomTemplateAd, str);
    }

    public final void onCustomTemplateAdLoaded(NativeCustomTemplateAd nativeCustomTemplateAd) {
        this.zzhk.zza(this.zzhh, nativeCustomTemplateAd);
    }

    public final void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
        this.zzhk.onAdLoaded(this.zzhh, new AbstractAdViewAdapter$zzc(unifiedNativeAd));
    }
}
