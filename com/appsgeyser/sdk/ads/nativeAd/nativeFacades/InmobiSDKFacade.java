package com.appsgeyser.sdk.ads.nativeAd.nativeFacades;

import android.content.Context;
import android.view.View;
import com.appsgeyser.sdk.ads.nativeAd.InmobiNativeModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;
import com.google.gson.JsonSyntaxException;
import com.inmobi.ads.InMobiNative;

public class InmobiSDKFacade implements NativeAdFacade {
    private InMobiNative inMobiNative;
    private InmobiNativeModel nativeModel;

    public InmobiSDKFacade(InMobiNative inMobiNative) throws JsonSyntaxException {
        this.inMobiNative = inMobiNative;
        this.nativeModel = InmobiNativeModel.parseFromJson((String) inMobiNative.getAdContent());
    }

    public String getButtonText() {
        return this.nativeModel.getCta();
    }

    public String getAdTitle() {
        return this.nativeModel.getTitle();
    }

    public String getImageUrl() {
        return this.nativeModel.getIcon().getUrl();
    }

    public String getAdDescription() {
        return this.nativeModel.getDescription();
    }

    public String getUniqueAdString() {
        return this.nativeModel.getTitle();
    }

    public void adClicked(ConfigPhp configPhp, Context context) {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INMOBI_NATIVE_SDK_CLICK, StatController.generateQueryParametersForSdk(configPhp, context, AdsType.NATIVE, "Inmobi native ad", StatController.KEY_INMOBI), context, false);
        this.inMobiNative.reportAdClickAndOpenLandingPage(null);
    }

    public void adImpression(ConfigPhp configPhp, Context context) {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INMOBI_NATIVE_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(configPhp, context, AdsType.NATIVE, "Inmobi native ad", StatController.KEY_INMOBI), context, false);
    }

    public void registerViewForAd(View viewToRegister) {
        InMobiNative.bind(viewToRegister, this.inMobiNative);
    }

    public void viewRecycled(View itemView) {
        InMobiNative.unbind(itemView);
    }
}
