package com.appsgeyser.sdk.ads.nativeAd.nativeHelpers;

import android.content.Context;
import android.os.Handler;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.AdmobSDKFacade;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.NativeAdFacade;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper.NativeAdsListener;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper.OnAdOpenedListener;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader.Builder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import java.util.ArrayList;

public class AdmobSDKHelper extends AbstractSDKHelper {
    private ConfigPhp configPhp;
    private Context context;
    private Handler mainLooperHandler;
    private NativeAdsListener nativeAdsListener;
    private Runnable noResponseTimeoutRunnable;
    private OnAdOpenedListener onAdOpenedListener;
    private int receivedAdsCount;
    private int requestedAdsCount;

    class C12671 implements Runnable {
        C12671() {
        }

        public void run() {
            AdmobSDKHelper.this.isMoreAdsAvailable = false;
            AdmobSDKHelper.this.nativeAdsListener.onError("No response received from admob");
        }
    }

    class C12682 extends AdListener {
        C12682() {
        }

        public void onAdClosed() {
            super.onAdClosed();
        }

        public void onAdFailedToLoad(int code) {
            super.onAdFailedToLoad(code);
            AdmobSDKHelper.this.mainLooperHandler.removeCallbacks(AdmobSDKHelper.this.noResponseTimeoutRunnable);
            if (AdmobSDKHelper.this.allAdsReceived()) {
                AdmobSDKHelper.this.nativeAdsListener.onError("Error when loading admob native ad");
            }
        }

        public void onAdLeftApplication() {
            super.onAdLeftApplication();
        }

        public void onAdOpened() {
            super.onAdOpened();
            AdmobSDKHelper.this.onAdOpenedListener.onAdOpened();
        }

        public void onAdLoaded() {
            super.onAdLoaded();
        }

        public void onAdClicked() {
            super.onAdClicked();
        }

        public void onAdImpression() {
            super.onAdImpression();
        }
    }

    public AdmobSDKHelper(Context context, ConfigPhp configPhp) {
        this.context = context;
        this.configPhp = configPhp;
        init();
    }

    public void onDestroy() {
    }

    public void init() {
        MobileAds.initialize(this.context, ((AdNetworkSdkModel) this.configPhp.getAdsNetworkSdk().get(StatController.KEY_ADMOB)).getAppId());
        this.noResponseTimeoutRunnable = new C12671();
        this.mainLooperHandler = new Handler(this.context.getMainLooper());
    }

    public void loadAds(int adsCount) {
        this.requestedAdsCount = adsCount;
        this.receivedAdsCount = 0;
        final ArrayList<NativeAdFacade> facades = new ArrayList(adsCount);
        for (int i = 0; i < adsCount; i++) {
            new Builder(this.context, ((AdNetworkSdkModel) this.configPhp.getAdsNetworkSdk().get(StatController.KEY_ADMOB)).getPlacementId()).forAppInstallAd(new OnAppInstallAdLoadedListener() {
                public void onAppInstallAdLoaded(NativeAppInstallAd nativeAppInstallAd) {
                    AdmobSDKHelper.this.mainLooperHandler.removeCallbacks(AdmobSDKHelper.this.noResponseTimeoutRunnable);
                    facades.add(new AdmobSDKFacade(nativeAppInstallAd));
                    AdmobSDKHelper.this.receivedAdsCount = AdmobSDKHelper.this.receivedAdsCount + 1;
                    if (AdmobSDKHelper.this.allAdsReceived()) {
                        AdmobSDKHelper.this.createListOfUniqueAdsAndAddToExisting(facades);
                        AdmobSDKHelper.this.nativeAdsListener.onAdsLoaded(AdmobSDKHelper.this.ads);
                    }
                }
            }).withAdListener(new C12682()).withNativeAdOptions(new NativeAdOptions.Builder().setReturnUrlsForImageAssets(true).build()).build().loadAd(new AdRequest.Builder().build());
        }
        this.mainLooperHandler.postDelayed(this.noResponseTimeoutRunnable, 6000);
    }

    private boolean allAdsReceived() {
        return this.receivedAdsCount == this.requestedAdsCount;
    }

    public void setAdsListener(NativeAdsListener listener) {
        this.nativeAdsListener = listener;
    }

    public void setOnAdOpenedListener(OnAdOpenedListener onAdOpenedListener) {
        this.onAdOpenedListener = onAdOpenedListener;
    }
}
