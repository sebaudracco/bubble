package com.appsgeyser.sdk.ads.nativeAd.nativeHelpers;

import android.app.Activity;
import android.content.Context;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.InmobiSDKFacade;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.NativeAdFacade;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper.NativeAdsListener;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper.OnAdOpenedListener;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiNative;
import com.inmobi.ads.InMobiNative.NativeAdListener;
import com.inmobi.sdk.InMobiSdk;
import java.util.ArrayList;

public class InmobiSDKHelper extends AbstractSDKHelper {
    private ConfigPhp configPhp;
    private Context context;
    private ArrayList<InMobiNative> nativeAds;
    private NativeAdsListener nativeAdsListener;
    private OnAdOpenedListener onAdOpenedListener;
    private int receivedAdsCount;
    private int requestedAdsCount;

    public InmobiSDKHelper(Context context, ConfigPhp configPhp) {
        this.context = context;
        this.configPhp = configPhp;
        init();
    }

    public void onDestroy() {
    }

    public void init() {
        InMobiSdk.init(this.context, ((AdNetworkSdkModel) this.configPhp.getAdsNetworkSdk().get(StatController.KEY_INMOBI)).getAppId());
        this.nativeAds = new ArrayList();
    }

    public void loadAds(int adsCount) {
        this.requestedAdsCount = adsCount;
        this.receivedAdsCount = 0;
        long placementId = Long.parseLong(((AdNetworkSdkModel) this.configPhp.getAdsNetworkSdk().get(StatController.KEY_INMOBI)).getPlacementId());
        final ArrayList<NativeAdFacade> facades = new ArrayList(adsCount);
        for (int i = 0; i < adsCount; i++) {
            InMobiNative nativeAd = new InMobiNative((Activity) this.context, placementId, new NativeAdListener() {
                public void onAdLoadSucceeded(InMobiNative inMobiNative) {
                    facades.add(new InmobiSDKFacade(inMobiNative));
                    InmobiSDKHelper.this.receivedAdsCount = InmobiSDKHelper.this.receivedAdsCount + 1;
                    if (InmobiSDKHelper.this.allAdsIsLoaded()) {
                        InmobiSDKHelper.this.createListOfUniqueAdsAndAddToExisting(facades);
                        InmobiSDKHelper.this.nativeAdsListener.onAdsLoaded(InmobiSDKHelper.this.ads);
                    }
                }

                public void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
                    InmobiSDKHelper.this.receivedAdsCount = InmobiSDKHelper.this.receivedAdsCount + 1;
                    if (InmobiSDKHelper.this.allAdsIsLoaded() && facades.isEmpty()) {
                        InmobiSDKHelper.this.nativeAdsListener.onError("Can not load any inmoby native ad");
                    } else if (InmobiSDKHelper.this.allAdsIsLoaded() && !facades.isEmpty()) {
                        InmobiSDKHelper.this.createListOfUniqueAdsAndAddToExisting(facades);
                        InmobiSDKHelper.this.nativeAdsListener.onAdsLoaded(InmobiSDKHelper.this.ads);
                    }
                }

                public void onAdDismissed(InMobiNative inMobiNative) {
                }

                public void onAdDisplayed(InMobiNative inMobiNative) {
                    InmobiSDKHelper.this.onAdOpenedListener.onAdOpened();
                }

                public void onUserLeftApplication(InMobiNative inMobiNative) {
                    InmobiSDKHelper.this.onAdOpenedListener.onAdOpened();
                }
            });
            this.nativeAds.add(nativeAd);
            nativeAd.load();
        }
    }

    private boolean allAdsIsLoaded() {
        return this.receivedAdsCount == this.requestedAdsCount;
    }

    public void setAdsListener(NativeAdsListener listener) {
        this.nativeAdsListener = listener;
    }

    public void setOnAdOpenedListener(OnAdOpenedListener onAdOpenedListener) {
        this.onAdOpenedListener = onAdOpenedListener;
    }
}
