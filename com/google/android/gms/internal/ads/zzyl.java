package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.UnifiedNativeAdMapper;
import com.google.android.gms.common.internal.Preconditions;

@zzadh
public final class zzyl implements MediationBannerListener, MediationInterstitialListener, MediationNativeListener {
    private final zzxt zzbuu;
    private NativeAdMapper zzbuv;
    private UnifiedNativeAdMapper zzbuw;
    private NativeCustomTemplateAd zzbux;

    public zzyl(zzxt com_google_android_gms_internal_ads_zzxt) {
        this.zzbuu = com_google_android_gms_internal_ads_zzxt;
    }

    private static void zza(MediationNativeAdapter mediationNativeAdapter, @Nullable UnifiedNativeAdMapper unifiedNativeAdMapper, @Nullable NativeAdMapper nativeAdMapper) {
        if (!(mediationNativeAdapter instanceof AdMobAdapter)) {
            VideoController videoController = new VideoController();
            videoController.zza(new zzyi());
            if (unifiedNativeAdMapper != null && unifiedNativeAdMapper.hasVideoContent()) {
                unifiedNativeAdMapper.zza(videoController);
            }
            if (nativeAdMapper != null && nativeAdMapper.hasVideoContent()) {
                nativeAdMapper.zza(videoController);
            }
        }
    }

    public final void onAdClicked(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClicked.");
        try {
            this.zzbuu.onAdClicked();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClicked(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClicked.");
        try {
            this.zzbuu.onAdClicked();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClicked(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        NativeAdMapper nativeAdMapper = this.zzbuv;
        UnifiedNativeAdMapper unifiedNativeAdMapper = this.zzbuw;
        if (this.zzbux == null) {
            if (nativeAdMapper == null && unifiedNativeAdMapper == null) {
                zzane.zzd("#007 Could not call remote method.", null);
                return;
            } else if (unifiedNativeAdMapper != null && !unifiedNativeAdMapper.getOverrideClickHandling()) {
                zzane.zzck("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            } else if (!(nativeAdMapper == null || nativeAdMapper.getOverrideClickHandling())) {
                zzane.zzck("Could not call onAdClicked since setOverrideClickHandling is not set to true");
                return;
            }
        }
        zzane.zzck("Adapter called onAdClicked.");
        try {
            this.zzbuu.onAdClicked();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClosed(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClosed.");
        try {
            this.zzbuu.onAdClosed();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClosed(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClosed.");
        try {
            this.zzbuu.onAdClosed();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdClosed(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdClosed.");
        try {
            this.zzbuu.onAdClosed();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdFailedToLoad(MediationBannerAdapter mediationBannerAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdFailedToLoad with error. " + i);
        try {
            this.zzbuu.onAdFailedToLoad(i);
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdFailedToLoad(MediationInterstitialAdapter mediationInterstitialAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdFailedToLoad with error " + i + ".");
        try {
            this.zzbuu.onAdFailedToLoad(i);
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdFailedToLoad(MediationNativeAdapter mediationNativeAdapter, int i) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdFailedToLoad with error " + i + ".");
        try {
            this.zzbuu.onAdFailedToLoad(i);
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdImpression(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        NativeAdMapper nativeAdMapper = this.zzbuv;
        UnifiedNativeAdMapper unifiedNativeAdMapper = this.zzbuw;
        if (this.zzbux == null) {
            if (nativeAdMapper == null && unifiedNativeAdMapper == null) {
                zzane.zzd("#007 Could not call remote method.", null);
                return;
            } else if (unifiedNativeAdMapper != null && !unifiedNativeAdMapper.getOverrideImpressionRecording()) {
                zzane.zzck("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            } else if (!(nativeAdMapper == null || nativeAdMapper.getOverrideImpressionRecording())) {
                zzane.zzck("Could not call onAdImpression since setOverrideImpressionRecording is not set to true");
                return;
            }
        }
        zzane.zzck("Adapter called onAdImpression.");
        try {
            this.zzbuu.onAdImpression();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLeftApplication(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLeftApplication.");
        try {
            this.zzbuu.onAdLeftApplication();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLeftApplication(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLeftApplication.");
        try {
            this.zzbuu.onAdLeftApplication();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLeftApplication(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLeftApplication.");
        try {
            this.zzbuu.onAdLeftApplication();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLoaded(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLoaded.");
        try {
            this.zzbuu.onAdLoaded();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLoaded(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLoaded.");
        try {
            this.zzbuu.onAdLoaded();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLoaded(MediationNativeAdapter mediationNativeAdapter, NativeAdMapper nativeAdMapper) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLoaded.");
        this.zzbuv = nativeAdMapper;
        this.zzbuw = null;
        zza(mediationNativeAdapter, this.zzbuw, this.zzbuv);
        try {
            this.zzbuu.onAdLoaded();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdLoaded(MediationNativeAdapter mediationNativeAdapter, UnifiedNativeAdMapper unifiedNativeAdMapper) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdLoaded.");
        this.zzbuw = unifiedNativeAdMapper;
        this.zzbuv = null;
        zza(mediationNativeAdapter, this.zzbuw, this.zzbuv);
        try {
            this.zzbuu.onAdLoaded();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdOpened(MediationBannerAdapter mediationBannerAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdOpened.");
        try {
            this.zzbuu.onAdOpened();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdOpened(MediationInterstitialAdapter mediationInterstitialAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdOpened.");
        try {
            this.zzbuu.onAdOpened();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onAdOpened(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAdOpened.");
        try {
            this.zzbuu.onAdOpened();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void onVideoEnd(MediationNativeAdapter mediationNativeAdapter) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onVideoEnd.");
        try {
            this.zzbuu.onVideoEnd();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zza(MediationBannerAdapter mediationBannerAdapter, String str, String str2) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        zzane.zzck("Adapter called onAppEvent.");
        try {
            this.zzbuu.onAppEvent(str, str2);
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zza(MediationNativeAdapter mediationNativeAdapter, NativeCustomTemplateAd nativeCustomTemplateAd) {
        Preconditions.checkMainThread("#008 Must be called on the main UI thread.");
        String str = "Adapter called onAdLoaded with template id ";
        String valueOf = String.valueOf(nativeCustomTemplateAd.getCustomTemplateId());
        zzane.zzck(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        this.zzbux = nativeCustomTemplateAd;
        try {
            this.zzbuu.onAdLoaded();
        } catch (Throwable e) {
            zzane.zzd("#007 Could not call remote method.", e);
        }
    }

    public final void zza(MediationNativeAdapter mediationNativeAdapter, NativeCustomTemplateAd nativeCustomTemplateAd, String str) {
        if (nativeCustomTemplateAd instanceof zzqv) {
            try {
                this.zzbuu.zzb(((zzqv) nativeCustomTemplateAd).zzku(), str);
                return;
            } catch (Throwable e) {
                zzane.zzd("#007 Could not call remote method.", e);
                return;
            }
        }
        zzane.zzdk("Unexpected native custom template ad type.");
    }

    public final NativeAdMapper zzmx() {
        return this.zzbuv;
    }

    public final UnifiedNativeAdMapper zzmy() {
        return this.zzbuw;
    }

    public final NativeCustomTemplateAd zzmz() {
        return this.zzbux;
    }
}
