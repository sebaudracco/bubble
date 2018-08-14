package com.appnext.sdk.adapters.mopub.ads;

import android.content.Context;
import android.util.Log;
import com.appnext.ads.AdsError;
import com.appnext.core.Ad;
import com.appnext.core.AppnextError;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.MoPubErrorCode;
import java.util.Map;

public abstract class AppnextMoPubCustomEvent extends CustomEventInterstitial {
    protected static final String AppnextAutoPlayExtraKey = "AppnextAutoPlay";
    protected static final String AppnextBackButtonCanCloseExtraKey = "AppnextBackButtonCanClose";
    protected static final String AppnextBannerSizeExtraKey = "AppnextBannerSize";
    protected static final String AppnextButtonColorExtraKey = "AppnextButtonColor";
    protected static final String AppnextCategoriesExtraKey = "AppnextCategories";
    protected static final String AppnextCloseDelayExtraKey = "AppnextCloseDelay";
    public static final String AppnextConfigurationExtraKey = "AppnextConfiguration";
    protected static final String AppnextCreativeTypeExtraKey = "AppnextCreativeType";
    protected static final String AppnextMaxVideoLenExtraKey = "AppnextMaxVideoLen";
    protected static final String AppnextMinVideoLenExtraKey = "AppnextMinVideoLen";
    protected static final String AppnextMultiTimerLengthExtraKey = "AppnextMultiTimerLength";
    protected static final String AppnextMuteExtraKey = "AppnextMute";
    protected static final String AppnextOrientationExtraKey = "AppnextOrientation";
    protected static final String AppnextPlacementIdExtraKey = "AppnextPlacementId";
    protected static final String AppnextPostbackExtraKey = "AppnextPostback";
    protected static final String AppnextProgressColorExtraKey = "AppnextProgressColor";
    protected static final String AppnextProgressTypeExtraKey = "AppnextProgressType";
    public static final String AppnextRewardPostbackExtraKey = "AppnextRewardPostback";
    protected static final String AppnextRollCapTimeExtraKey = "AppnextRollCapTime";
    protected static final String AppnextShowCloseExtraKey = "AppnextShowClose";
    protected static final String AppnextShowCtaExtrakey = "AppnextShowCta";
    protected static final String AppnextSkipTextExtraKey = "AppnextSkipText";
    protected static final String AppnextVideoLengthExtraKey = "AppnextVideoLength";
    protected static final String AppnextVideoModeExtraKey = "AppnextVideoMode";
    protected Ad mAd;
    private CustomEventInterstitialListener mInterstitialListener;

    class C11661 implements OnAdLoaded {
        C11661() {
        }

        public void adLoaded(String str) {
            AppnextMoPubCustomEvent.this.mInterstitialListener.onInterstitialLoaded();
        }
    }

    class C11672 implements OnAdOpened {
        C11672() {
        }

        public void adOpened() {
            AppnextMoPubCustomEvent.this.mInterstitialListener.onInterstitialShown();
        }
    }

    class C11683 implements OnAdClicked {
        C11683() {
        }

        public void adClicked() {
            AppnextMoPubCustomEvent.this.mInterstitialListener.onLeaveApplication();
        }
    }

    class C11694 implements OnAdClosed {
        C11694() {
        }

        public void onAdClosed() {
            AppnextMoPubCustomEvent.this.mInterstitialListener.onInterstitialDismissed();
        }
    }

    class C11705 implements OnAdError {
        C11705() {
        }

        public void adError(String str) {
            Object obj = -1;
            switch (str.hashCode()) {
                case -1958363695:
                    if (str.equals(AppnextError.NO_ADS)) {
                        obj = 4;
                        break;
                    }
                    break;
                case -1477010874:
                    if (str.equals(AppnextError.CONNECTION_ERROR)) {
                        obj = 3;
                        break;
                    }
                    break;
                case 297538105:
                    if (str.equals(AdsError.AD_NOT_READY)) {
                        obj = null;
                        break;
                    }
                    break;
                case 350741825:
                    if (str.equals("Timeout")) {
                        obj = 2;
                        break;
                    }
                    break;
                case 844170097:
                    if (str.equals(AppnextError.SLOW_CONNECTION)) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    AppnextMoPubCustomEvent.this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.WARMUP);
                    return;
                case 1:
                case 2:
                    AppnextMoPubCustomEvent.this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_TIMEOUT);
                    return;
                case 3:
                    AppnextMoPubCustomEvent.this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NO_CONNECTION);
                    return;
                case 4:
                    AppnextMoPubCustomEvent.this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NO_FILL);
                    return;
                default:
                    AppnextMoPubCustomEvent.this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
                    return;
            }
        }
    }

    protected abstract Ad createAd(Context context, Map<String, Object> map, Map<String, String> map2);

    protected void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> map2) {
        this.mInterstitialListener = customEventInterstitialListener;
        this.mAd = createAd(context, map, map2);
        if (this.mAd == null) {
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
            return;
        }
        try {
            this.mAd.setOnAdLoadedCallback(new C11661());
            this.mAd.setOnAdOpenedCallback(new C11672());
            this.mAd.setOnAdClickedCallback(new C11683());
            this.mAd.setOnAdClosedCallback(new C11694());
            this.mAd.setOnAdErrorCallback(new C11705());
            this.mAd.loadAd();
        } catch (Throwable th) {
            Log.e("AppnextMoPub", "requestInterstitialAd: " + th.getMessage());
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
        }
    }

    protected void showInterstitial() {
        try {
            if (this.mAd.isAdLoaded()) {
                this.mAd.showAd();
            } else {
                this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.WARMUP);
            }
        } catch (Throwable th) {
            Log.e("AppnextMoPub", "showInterstitial: " + th.getMessage());
            this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
        }
    }

    protected void onInvalidate() {
    }
}
