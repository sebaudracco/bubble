package com.appsgeyser.sdk.ads.sdk;

import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.ads.FullScreenBanner.BannerTypes;

public class AppNextSdkWrapper extends SdkWrapper {
    public void showFsBanner() {
        InternalEntryPoint.getInstance().getFullScreenBanner(this.context).setBannerType(BannerTypes.SDK);
    }

    public void getNativeAd() {
    }

    public boolean isAdSupported(AdType adType) {
        switch (adType) {
            case FULLSCREEN:
            case NATIVE:
                return true;
            default:
                return false;
        }
    }
}
