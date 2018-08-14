package com.appsgeyser.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import com.appsgeyser.sdk.ads.AdView;
import com.appsgeyser.sdk.ads.FullScreenBanner;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.ads.fullscreenSdk.FullscreenSdkHelper;
import com.appsgeyser.sdk.ads.fullscreenSdk.fullscreenFacades.FullscreenSdkFacade.FullscreenAdListener;
import com.appsgeyser.sdk.ads.rewardedVideo.RewardedAdHelper;
import com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades.RewardedVideoFacade.RewardedVideoListener;
import com.appsgeyser.sdk.server.network.OnNetworkStateChangedListener;

public final class AppsgeyserSDK {

    public interface OfferWallEnabledListener {
        void isOfferWallEnabled(boolean z);
    }

    public interface OnAboutDialogEnableListener {
        void onDialogEnableReceived(boolean z);
    }

    public interface OnAdditionalJsLoaded {
        void onJsLoaded(String str);
    }

    public static void takeOff(@NonNull Activity activity, @NonNull String APIkey, @NonNull String appMetricaOnStartEvent, @NonNull String templateVersion) {
        InternalEntryPoint.getInstance().takeOff(activity, APIkey, appMetricaOnStartEvent, templateVersion);
    }

    public static void enablePull() {
        InternalEntryPoint.getInstance().enablePull();
    }

    public static void setAdView(AdView adView) {
        InternalEntryPoint.getInstance().setAdView(adView);
    }

    public static AdView getAdView() {
        return InternalEntryPoint.getInstance().getAdView();
    }

    public static FullScreenBanner getFullScreenBanner(Context context) {
        return InternalEntryPoint.getInstance().getFullScreenBanner(context);
    }

    public static FastTrackAdsController getFastTrackAdsController() {
        return InternalEntryPoint.getInstance().getFastTrackAdsController();
    }

    public static void onPause(Context context) {
        InternalEntryPoint.getInstance().onPause(context);
    }

    public static void onResume(Context context) {
        InternalEntryPoint.getInstance().onResume(context);
    }

    public static void onDestroy(Context context) {
        InternalEntryPoint.getInstance().onDestroy(context);
    }

    public static void showAboutDialog(Activity activity) {
        InternalEntryPoint.getInstance().showAboutDialog(activity);
    }

    public static void isAboutDialogEnabled(Context context, OnAboutDialogEnableListener listener) {
        InternalEntryPoint.getInstance().getNewConfigPhp(context, listener);
    }

    public static void isOfferWallEnabled(Context context, OfferWallEnabledListener listener) {
        InternalEntryPoint.getInstance().checkIsOfferWallEnabled(context, listener);
    }

    public static void getAdditionalJsCode(OnAdditionalJsLoaded loader) {
        InternalEntryPoint.getInstance().getAdditionalJsCode(loader);
    }

    public static void addNetworkListener(Context context, OnNetworkStateChangedListener listener) {
        InternalEntryPoint.getInstance().addNetworkListener(listener, context);
    }

    public static void showAdWall(Context context) {
        NativeAdActivity.start(context);
    }

    public static NativeAdFragment getAdWallFragment(Context context) {
        return NativeAdFragment.getFragment();
    }

    public static void loadRewardedVideo(RewardedVideoListener listener) {
        RewardedAdHelper helper = InternalEntryPoint.getInstance().getRewardedAdHelper();
        if (helper != null) {
            helper.loadRewardedVideo(listener);
        }
    }

    public static void showRewardedVideo() {
        InternalEntryPoint.getInstance().getRewardedAdHelper().showRewardedVideo();
    }

    public static void loadInterstitial(FullscreenAdListener listener) {
        FullscreenSdkHelper helper = InternalEntryPoint.getInstance().getFullscreenSdkHelper();
        if (helper != null) {
            helper.loadInterstitial(listener);
        }
    }

    public static void showInterstitial() {
        InternalEntryPoint.getInstance().getFullscreenSdkHelper().showInterstitial();
    }

    public static void setApplicationInstance(Application application) {
        InternalEntryPoint.getInstance().setApplication(application);
    }
}
