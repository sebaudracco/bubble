package com.appsgeyser.sdk.ads.nativeAd.nativeHelpers;

import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.NativeAdFacade;
import java.util.ArrayList;

public interface NativeSdkHelper {

    public interface NativeAdsListener {
        void onAdsLoaded(ArrayList<NativeAdFacade> arrayList);

        void onError(String str);
    }

    public interface OnAdOpenedListener {
        void onAdOpened();

        void onError(String str);
    }

    void init();

    boolean isMoreAdsAvailable();

    void loadAds(int i);

    void onDestroy();

    void setAdsListener(NativeAdsListener nativeAdsListener);

    void setOnAdOpenedListener(OnAdOpenedListener onAdOpenedListener);
}
