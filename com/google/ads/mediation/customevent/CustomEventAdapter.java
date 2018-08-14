package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdRequest$ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzane;

@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter<CustomEventExtras, CustomEventServerParameters>, MediationInterstitialAdapter<CustomEventExtras, CustomEventServerParameters> {
    private View zzhq;
    @VisibleForTesting
    private CustomEventBanner zzhr;
    @VisibleForTesting
    private CustomEventInterstitial zzhs;

    @VisibleForTesting
    static final class zza implements CustomEventBannerListener {
        private final CustomEventAdapter zzht;
        private final MediationBannerListener zzhu;

        public zza(CustomEventAdapter customEventAdapter, MediationBannerListener mediationBannerListener) {
            this.zzht = customEventAdapter;
            this.zzhu = mediationBannerListener;
        }

        public final void onClick() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhu.onClick(this.zzht);
        }

        public final void onDismissScreen() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhu.onDismissScreen(this.zzht);
        }

        public final void onFailedToReceiveAd() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhu.onFailedToReceiveAd(this.zzht, AdRequest$ErrorCode.NO_FILL);
        }

        public final void onLeaveApplication() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhu.onLeaveApplication(this.zzht);
        }

        public final void onPresentScreen() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhu.onPresentScreen(this.zzht);
        }

        public final void onReceivedAd(View view) {
            zzane.zzck("Custom event adapter called onReceivedAd.");
            this.zzht.zza(view);
            this.zzhu.onReceivedAd(this.zzht);
        }
    }

    @VisibleForTesting
    class zzb implements CustomEventInterstitialListener {
        private final CustomEventAdapter zzht;
        private final MediationInterstitialListener zzhv;
        private final /* synthetic */ CustomEventAdapter zzhw;

        public zzb(CustomEventAdapter customEventAdapter, CustomEventAdapter customEventAdapter2, MediationInterstitialListener mediationInterstitialListener) {
            this.zzhw = customEventAdapter;
            this.zzht = customEventAdapter2;
            this.zzhv = mediationInterstitialListener;
        }

        public final void onDismissScreen() {
            zzane.zzck("Custom event adapter called onDismissScreen.");
            this.zzhv.onDismissScreen(this.zzht);
        }

        public final void onFailedToReceiveAd() {
            zzane.zzck("Custom event adapter called onFailedToReceiveAd.");
            this.zzhv.onFailedToReceiveAd(this.zzht, AdRequest$ErrorCode.NO_FILL);
        }

        public final void onLeaveApplication() {
            zzane.zzck("Custom event adapter called onLeaveApplication.");
            this.zzhv.onLeaveApplication(this.zzht);
        }

        public final void onPresentScreen() {
            zzane.zzck("Custom event adapter called onPresentScreen.");
            this.zzhv.onPresentScreen(this.zzht);
        }

        public final void onReceivedAd() {
            zzane.zzck("Custom event adapter called onReceivedAd.");
            this.zzhv.onReceivedAd(this.zzhw);
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

    public final void destroy() {
        if (this.zzhr != null) {
            this.zzhr.destroy();
        }
        if (this.zzhs != null) {
            this.zzhs.destroy();
        }
    }

    public final Class<CustomEventExtras> getAdditionalParametersType() {
        return CustomEventExtras.class;
    }

    public final View getBannerView() {
        return this.zzhq;
    }

    public final Class<CustomEventServerParameters> getServerParametersType() {
        return CustomEventServerParameters.class;
    }

    public final void requestBannerAd(MediationBannerListener mediationBannerListener, Activity activity, CustomEventServerParameters customEventServerParameters, AdSize adSize, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        this.zzhr = (CustomEventBanner) zzi(customEventServerParameters.className);
        if (this.zzhr == null) {
            mediationBannerListener.onFailedToReceiveAd(this, AdRequest$ErrorCode.INTERNAL_ERROR);
        } else {
            this.zzhr.requestBannerAd(new zza(this, mediationBannerListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, adSize, mediationAdRequest, customEventExtras == null ? null : customEventExtras.getExtra(customEventServerParameters.label));
        }
    }

    public final void requestInterstitialAd(MediationInterstitialListener mediationInterstitialListener, Activity activity, CustomEventServerParameters customEventServerParameters, MediationAdRequest mediationAdRequest, CustomEventExtras customEventExtras) {
        this.zzhs = (CustomEventInterstitial) zzi(customEventServerParameters.className);
        if (this.zzhs == null) {
            mediationInterstitialListener.onFailedToReceiveAd(this, AdRequest$ErrorCode.INTERNAL_ERROR);
        } else {
            this.zzhs.requestInterstitialAd(new zzb(this, this, mediationInterstitialListener), activity, customEventServerParameters.label, customEventServerParameters.parameter, mediationAdRequest, customEventExtras == null ? null : customEventExtras.getExtra(customEventServerParameters.label));
        }
    }

    public final void showInterstitial() {
        this.zzhs.showInterstitial();
    }
}
