package com.appsgeyser.sdk.ads.nativeAd.nativeFacades;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import com.appsgeyser.sdk.C1195R;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;
import com.mobfox.sdk.customevents.CustomEventNative;
import com.mobfox.sdk.nativeads.ImageItem;
import com.mobfox.sdk.nativeads.NativeAd;
import com.mobfox.sdk.nativeads.TextItem;

public class MobfoxSDKFacade implements NativeAdFacade {
    private CustomEventNative customEventNative;
    private NativeAd nativeAd;

    public MobfoxSDKFacade(NativeAd nativeAd, CustomEventNative customEventNative) {
        this.nativeAd = nativeAd;
        this.customEventNative = customEventNative;
    }

    public String getButtonText() {
        String buttonText = "";
        for (TextItem text : this.nativeAd.getTexts()) {
            if (text.getType().equals("ctatext")) {
                buttonText = text.getText();
            }
        }
        return buttonText;
    }

    public String getAdTitle() {
        String title = "";
        for (TextItem text : this.nativeAd.getTexts()) {
            if (text.getType().equals("title")) {
                title = text.getText();
            }
        }
        return title;
    }

    public String getImageUrl() {
        String url = "";
        for (ImageItem image : this.nativeAd.getImages()) {
            if (image.getType().equals("icon")) {
                url = image.getUrl();
            }
        }
        return url;
    }

    public String getAdDescription() {
        String description = "";
        for (TextItem text : this.nativeAd.getTexts()) {
            if (text.getType().equals("desc")) {
                description = text.getText();
            }
        }
        return description;
    }

    public String getUniqueAdString() {
        return getAdTitle();
    }

    public void adClicked(ConfigPhp configPhp, Context context) {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_MOBFOX_NATIVE_SDK_CLICK, StatController.generateQueryParametersForSdk(configPhp, context, AdsType.NATIVE, "native mobfox Ad", StatController.KEY_MOBFOX), context, false);
    }

    public void adImpression(ConfigPhp configPhp, Context context) {
        this.nativeAd.fireTrackers(context);
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_MOBFOX_NATIVE_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(configPhp, context, AdsType.NATIVE, "native mobfox Ad", StatController.KEY_MOBFOX), context, false);
    }

    public void registerViewForAd(View viewToRegister) {
        this.customEventNative.registerViewForInteraction((Button) viewToRegister.findViewById(C1195R.id.appsgeysersdk_adsActionButton));
    }

    public void viewRecycled(View itemView) {
    }
}
