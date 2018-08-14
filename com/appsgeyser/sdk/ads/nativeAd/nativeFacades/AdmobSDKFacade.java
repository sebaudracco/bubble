package com.appsgeyser.sdk.ads.nativeAd.nativeFacades;

import android.content.Context;
import android.view.View;
import com.appsgeyser.sdk.C1195R;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;

public class AdmobSDKFacade implements NativeAdFacade {
    private NativeAppInstallAd nativeAd;

    public AdmobSDKFacade(NativeAppInstallAd nativeAd) {
        this.nativeAd = nativeAd;
    }

    public String getButtonText() {
        return this.nativeAd.getCallToAction().toString();
    }

    public String getAdTitle() {
        return this.nativeAd.getHeadline().toString();
    }

    public String getImageUrl() {
        return this.nativeAd.getIcon().getUri().toString();
    }

    public String getAdDescription() {
        return this.nativeAd.getBody().toString();
    }

    public String getUniqueAdString() {
        return this.nativeAd.getHeadline().toString();
    }

    public void adClicked(ConfigPhp configPhp, Context context) {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_ADMOB_NATIVE_SDK_CLICK, StatController.generateQueryParametersForSdk(configPhp, context, AdsType.NATIVE, "native admob Ad", StatController.KEY_ADMOB), context, false);
    }

    public void adImpression(ConfigPhp configPhp, Context context) {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_ADMOB_NATIVE_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(configPhp, context, AdsType.NATIVE, "native admob Ad", StatController.KEY_ADMOB), context, false);
    }

    public void registerViewForAd(View viewToRegister) {
        ((NativeAppInstallAdView) viewToRegister.findViewById(C1195R.id.appsgeysersdk_admobRootView)).setNativeAd(this.nativeAd);
    }

    public void viewRecycled(View itemView) {
    }

    public NativeAd getNativeAd() {
        return this.nativeAd;
    }
}
