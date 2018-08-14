package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzane;

@KeepName
@KeepForSdkWithMembers
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    @VisibleForTesting
    private CustomEventBanner zzdge;
    @VisibleForTesting
    private CustomEventInterstitial zzdgf;
    @VisibleForTesting
    private CustomEventNative zzdgg;
    private View zzhq;

    @VisibleForTesting
    static final class zza implements CustomEventBannerListener {
        private final CustomEventAdapter zzdgh;
        private final MediationBannerListener zzhi;

        public zza(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.zzdgh = customEventAdapter;
            this.zzhi = mediationBannerListener;
        }

        public final void onAdClicked() {
            zzane.zzck("Custom event adapter called onAdClicked.");
            this.zzhi.onAdClicked(this.zzdgh);
        }

        public final void onAdClosed() {
            zzane.zzck("Custom event adapter called onAdClosed.");
            this.zzhi.onAdClosed(this.zzdgh);
        }

        public final void onAdFailedToLoad(int i) {
            zzane.zzck("Custom event adapter called onAdFailedToLoad.");
            this.zzhi.onAdFailedToLoad(this.zzdgh, i);
        }

        public final void onAdLeftApplication() {
            zzane.zzck("Custom event adapter called onAdLeftApplication.");
            this.zzhi.onAdLeftApplication(this.zzdgh);
        }

        public final void onAdLoaded(View view) {
            zzane.zzck("Custom event adapter called onAdLoaded.");
            this.zzdgh.zza(view);
            this.zzhi.onAdLoaded(this.zzdgh);
        }

        public final void onAdOpened() {
            zzane.zzck("Custom event adapter called onAdOpened.");
            this.zzhi.onAdOpened(this.zzdgh);
        }
    }

    @VisibleForTesting
    class zzb implements CustomEventInterstitialListener {
        private final CustomEventAdapter zzdgh;
        private final /* synthetic */ CustomEventAdapter zzdgi;
        private final MediationInterstitialListener zzhj;

        public zzb(CustomEventAdapter customEventAdapter, CustomEventAdapter customEventAdapter2, MediationInterstitialListener mediationInterstitialListener) {
            this.zzdgi = customEventAdapter;
            this.zzdgh = customEventAdapter2;
            this.zzhj = mediationInterstitialListener;
        }

        public final void onAdClicked() {
            zzane.zzck("Custom event adapter called onAdClicked.");
            this.zzhj.onAdClicked(this.zzdgh);
        }

        public final void onAdClosed() {
            zzane.zzck("Custom event adapter called onAdClosed.");
            this.zzhj.onAdClosed(this.zzdgh);
        }

        public final void onAdFailedToLoad(int i) {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhj.onAdFailedToLoad(this.zzdgh, i);
        }

        public final void onAdLeftApplication() {
            zzane.zzck("Custom event adapter called onAdLeftApplication.");
            this.zzhj.onAdLeftApplication(this.zzdgh);
        }

        public final void onAdLoaded() {
            zzane.zzck("Custom event adapter called onReceivedAd.");
            this.zzhj.onAdLoaded(this.zzdgi);
        }

        public final void onAdOpened() {
            zzane.zzck("Custom event adapter called onAdOpened.");
            this.zzhj.onAdOpened(this.zzdgh);
        }
    }

    @VisibleForTesting
    static class zzc implements CustomEventNativeListener {
        private final CustomEventAdapter zzdgh;
        private final MediationNativeListener zzhk;

        public zzc(CustomEventAdapter customEventAdapter, MediationNativeListener mediationNativeListener) {
            this.zzdgh = customEventAdapter;
            this.zzhk = mediationNativeListener;
        }

        public final void onAdClicked() {
            zzane.zzck("Custom event adapter called onAdClicked.");
            this.zzhk.onAdClicked(this.zzdgh);
        }

        public final void onAdClosed() {
            zzane.zzck("Custom event adapter called onAdClosed.");
            this.zzhk.onAdClosed(this.zzdgh);
        }

        public final void onAdFailedToLoad(int i) {
            zzane.zzck("Custom event adapter called onAdFailedToLoad.");
            this.zzhk.onAdFailedToLoad(this.zzdgh, i);
        }

        public final void onAdImpression() {
            zzane.zzck("Custom event adapter called onAdImpression.");
            this.zzhk.onAdImpression(this.zzdgh);
        }

        public final void onAdLeftApplication() {
            zzane.zzck("Custom event adapter called onAdLeftApplication.");
            this.zzhk.onAdLeftApplication(this.zzdgh);
        }

        public final void onAdLoaded(NativeAdMapper nativeAdMapper) {
            zzane.zzck("Custom event adapter called onAdLoaded.");
            this.zzhk.onAdLoaded(this.zzdgh, nativeAdMapper);
        }

        public final void onAdLoaded(UnifiedNativeAdMapper unifiedNativeAdMapper) {
            zzane.zzck("Custom event adapter called onAdLoaded.");
            this.zzhk.onAdLoaded(this.zzdgh, unifiedNativeAdMapper);
        }

        public final void onAdOpened() {
            zzane.zzck("Custom event adapter called onAdOpened.");
            this.zzhk.onAdOpened(this.zzdgh);
        }
    }

    private final void zza(View view) {
        this.zzhq = view;
    }

    private static <T> T zzi(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (Throwable th) {
            String message = th.getMessage();
            zzane.zzdk(new StringBuilder((String.valueOf(str).length() + 46) + String.valueOf(message).length()).append("Could not instantiate custom event adapter: ").append(str).append(". ").append(message).toString());
            return null;
        }
    }

    public final View getBannerView() {
        return this.zzhq;
    }

    public final void onDestroy() {
        if (this.zzdge != null) {
            this.zzdge.onDestroy();
        }
        if (this.zzdgf != null) {
            this.zzdgf.onDestroy();
        }
        if (this.zzdgg != null) {
            this.zzdgg.onDestroy();
        }
    }

    public final void onPause() {
        if (this.zzdge != null) {
            this.zzdge.onPause();
        }
        if (this.zzdgf != null) {
            this.zzdgf.onPause();
        }
        if (this.zzdgg != null) {
            this.zzdgg.onPause();
        }
    }

    public final void onResume() {
        if (this.zzdge != null) {
            this.zzdge.onResume();
        }
        if (this.zzdgf != null) {
            this.zzdgf.onResume();
        }
        if (this.zzdgg != null) {
            this.zzdgg.onResume();
        }
    }

    public final void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzdge = (CustomEventBanner) zzi(bundle.getString("class_name"));
        if (this.zzdge == null) {
            mediationBannerListener.onAdFailedToLoad(this, 0);
            return;
        }
        this.zzdge.requestBannerAd(context, new zza(this, mediationBannerListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), adSize, mediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
    }

    public final void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzdgf = (CustomEventInterstitial) zzi(bundle.getString("class_name"));
        if (this.zzdgf == null) {
            mediationInterstitialListener.onAdFailedToLoad(this, 0);
            return;
        }
        this.zzdgf.requestInterstitialAd(context, new zzb(this, this, mediationInterstitialListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), mediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
    }

    public final void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        this.zzdgg = (CustomEventNative) zzi(bundle.getString("class_name"));
        if (this.zzdgg == null) {
            mediationNativeListener.onAdFailedToLoad(this, 0);
            return;
        }
        this.zzdgg.requestNativeAd(context, new zzc(this, mediationNativeListener), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), nativeMediationAdRequest, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
    }

    public final void showInterstitial() {
        this.zzdgf.showInterstitial();
    }
}
