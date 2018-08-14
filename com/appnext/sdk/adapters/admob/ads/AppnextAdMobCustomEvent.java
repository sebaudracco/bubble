package com.appnext.sdk.adapters.admob.ads;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.appnext.core.Ad;
import com.appnext.core.AppnextError;
import com.appnext.core.callbacks.OnAdClicked;
import com.appnext.core.callbacks.OnAdClosed;
import com.appnext.core.callbacks.OnAdError;
import com.appnext.core.callbacks.OnAdLoaded;
import com.appnext.core.callbacks.OnAdOpened;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitial;
import com.google.android.gms.ads.mediation.customevent.CustomEventInterstitialListener;

public abstract class AppnextAdMobCustomEvent implements CustomEventInterstitial {
    public static final String AppnextConfigurationExtraKey = "AppnextConfiguration";
    public static final String AppnextRewardPostbackExtraKey = "AppnextRewardPostback";
    protected Ad mAd;
    protected CustomEventInterstitialListener mListener;

    class C11531 implements OnAdLoaded {
        C11531() {
        }

        public void adLoaded(String str) {
            AppnextAdMobCustomEvent.this.mListener.onAdLoaded();
        }
    }

    class C11542 implements OnAdOpened {
        C11542() {
        }

        public void adOpened() {
            AppnextAdMobCustomEvent.this.mListener.onAdOpened();
        }
    }

    class C11553 implements OnAdClicked {
        C11553() {
        }

        public void adClicked() {
            AppnextAdMobCustomEvent.this.mListener.onAdClicked();
            AppnextAdMobCustomEvent.this.mListener.onAdLeftApplication();
        }
    }

    class C11564 implements OnAdClosed {
        C11564() {
        }

        public void onAdClosed() {
            AppnextAdMobCustomEvent.this.mListener.onAdClosed();
        }
    }

    class C11575 implements OnAdError {
        C11575() {
        }

        public void adError(String str) {
            int i = -1;
            switch (str.hashCode()) {
                case -1958363695:
                    if (str.equals(AppnextError.NO_ADS)) {
                        i = 3;
                        break;
                    }
                    break;
                case -1477010874:
                    if (str.equals(AppnextError.CONNECTION_ERROR)) {
                        i = 2;
                        break;
                    }
                    break;
                case 350741825:
                    if (str.equals("Timeout")) {
                        i = 1;
                        break;
                    }
                    break;
                case 844170097:
                    if (str.equals(AppnextError.SLOW_CONNECTION)) {
                        i = 0;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                    AppnextAdMobCustomEvent.this.mListener.onAdFailedToLoad(2);
                    return;
                case 3:
                    AppnextAdMobCustomEvent.this.mListener.onAdFailedToLoad(3);
                    return;
                default:
                    AppnextAdMobCustomEvent.this.mListener.onAdFailedToLoad(0);
                    return;
            }
        }
    }

    protected abstract Ad createAd(Context context, String str, Bundle bundle);

    public void requestInterstitialAd(Context context, CustomEventInterstitialListener customEventInterstitialListener, String str, MediationAdRequest mediationAdRequest, Bundle bundle) {
        try {
            this.mAd = createAd(context, str, bundle);
            if (this.mAd == null) {
                customEventInterstitialListener.onAdFailedToLoad(0);
                return;
            }
            this.mListener = customEventInterstitialListener;
            this.mAd.setOnAdLoadedCallback(new C11531());
            this.mAd.setOnAdOpenedCallback(new C11542());
            this.mAd.setOnAdClickedCallback(new C11553());
            this.mAd.setOnAdClosedCallback(new C11564());
            this.mAd.setOnAdErrorCallback(new C11575());
            this.mAd.loadAd();
        } catch (Throwable th) {
            Log.e("AppnextAdMob", "requestInterstitialAd: " + th.getMessage());
            this.mListener.onAdFailedToLoad(0);
        }
    }

    public void showInterstitial() {
        try {
            if (this.mAd.isAdLoaded()) {
                this.mAd.showAd();
            }
        } catch (Throwable th) {
            this.mListener.onAdFailedToLoad(3);
            Log.e("AppnextAdMob", "showInterstitial: " + th.getMessage());
        }
    }

    public void onDestroy() {
        this.mAd.destroy();
    }

    public void onPause() {
    }

    public void onResume() {
    }
}
