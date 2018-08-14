package com.appsgeyser.sdk.ads.nativeAd.nativeFacades;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.appsgeyser.sdk.ads.nativeAd.AppsgeyserNativeAdModel;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper.OnAdOpenedListener;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.StatController.AdsType;

public class AppsgeyserSDKFacade implements NativeAdFacade {
    private Context context;
    private AppsgeyserNativeAdModel nativeAdModel;
    private OnAdOpenedListener onAdOpenedListener;

    public AppsgeyserSDKFacade(Context context, AppsgeyserNativeAdModel nativeAdModel, OnAdOpenedListener onAdOpenedListener) {
        this.nativeAdModel = nativeAdModel;
        this.context = context;
        this.onAdOpenedListener = onAdOpenedListener;
    }

    public String getButtonText() {
        return this.nativeAdModel.getActionButtonText();
    }

    public String getAdTitle() {
        return this.nativeAdModel.getAdTitle();
    }

    public String getImageUrl() {
        return this.nativeAdModel.getAdIconUrl();
    }

    public String getAdDescription() {
        return this.nativeAdModel.getAdDescription();
    }

    public String getUniqueAdString() {
        return this.nativeAdModel.getAdTitle();
    }

    public void adClicked(ConfigPhp configPhp, Context context) {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_APPSGEYSER_NATIVE_SDK_CLICK, StatController.generateQueryParametersForSdk(configPhp, context, AdsType.NATIVE, "native appsgeyser Ad", StatController.KEY_APPSGEYSER), context, false);
        validateUriAndOpenIt();
    }

    private void validateUriAndOpenIt() {
        try {
            Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(this.nativeAdModel.getAdUrl()));
            this.onAdOpenedListener.onAdOpened();
            this.context.startActivity(browserIntent);
        } catch (Exception e) {
            if (this.onAdOpenedListener != null) {
                this.onAdOpenedListener.onError("Error while parsing ad url");
            }
        }
    }

    public void adImpression(ConfigPhp configPhp, Context context) {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_APPSGEYSER_NATIVE_SDK_IMPRESSION, StatController.generateQueryParametersForSdk(configPhp, context, AdsType.NATIVE, "native appsgeyser Ad", StatController.KEY_APPSGEYSER), context, false);
    }

    public void registerViewForAd(View viewToRegister) {
    }

    public void viewRecycled(View itemView) {
    }
}
