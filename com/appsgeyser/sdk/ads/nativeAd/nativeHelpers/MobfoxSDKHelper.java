package com.appsgeyser.sdk.ads.nativeAd.nativeHelpers;

import android.content.Context;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.MobfoxSDKFacade;
import com.appsgeyser.sdk.ads.nativeAd.nativeFacades.NativeAdFacade;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper.NativeAdsListener;
import com.appsgeyser.sdk.ads.nativeAd.nativeHelpers.NativeSdkHelper.OnAdOpenedListener;
import com.appsgeyser.sdk.configuration.models.AdNetworkSdkModel;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.mobfox.sdk.customevents.CustomEventNative;
import com.mobfox.sdk.nativeads.Native;
import com.mobfox.sdk.nativeads.NativeAd;
import com.mobfox.sdk.nativeads.NativeListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MobfoxSDKHelper extends AbstractSDKHelper {
    private OnAdOpenedListener adOpenedListener;
    private int adsCountRequested;
    private ConfigPhp configPhp;
    private Context context;
    private HashMap<NativeAd, NativeAdFacade> nativeAdFacades;
    private NativeAdsListener nativeAdsListener;
    private int receivedAdsCount;
    private AdNetworkSdkModel sdkModel;

    class C12721 implements NativeListener {
        C12721() {
        }

        public void onNativeReady(Native aNative, CustomEventNative customEventNative, NativeAd nativeAd) {
            MobfoxSDKHelper.this.nativeAdFacades.put(nativeAd, new MobfoxSDKFacade(nativeAd, customEventNative));
            MobfoxSDKHelper.this.receivedAdsCount = MobfoxSDKHelper.this.receivedAdsCount + 1;
            if (MobfoxSDKHelper.this.isLastAdReceived() && MobfoxSDKHelper.this.nativeAdsListener != null) {
                MobfoxSDKHelper.this.createListOfUniqueAdsAndAddToExisting(new ArrayList(MobfoxSDKHelper.this.nativeAdFacades.values()));
                MobfoxSDKHelper.this.nativeAdsListener.onAdsLoaded(MobfoxSDKHelper.this.ads);
            }
        }

        public void onNativeError(Exception e) {
            MobfoxSDKHelper.this.receivedAdsCount = MobfoxSDKHelper.this.receivedAdsCount + 1;
            if (MobfoxSDKHelper.this.isLastAdReceived() && MobfoxSDKHelper.this.nativeAdsListener != null) {
                MobfoxSDKHelper.this.createListOfUniqueAdsAndAddToExisting(new ArrayList(MobfoxSDKHelper.this.nativeAdFacades.values()));
                MobfoxSDKHelper.this.nativeAdsListener.onAdsLoaded(MobfoxSDKHelper.this.ads);
            }
        }

        public void onNativeClick(NativeAd nativeAd) {
            Iterator it = MobfoxSDKHelper.this.nativeAdFacades.values().iterator();
            if (it.hasNext()) {
                ((NativeAdFacade) it.next()).adClicked(MobfoxSDKHelper.this.configPhp, MobfoxSDKHelper.this.context);
            }
            if (MobfoxSDKHelper.this.adOpenedListener != null) {
                MobfoxSDKHelper.this.adOpenedListener.onAdOpened();
            }
        }
    }

    public MobfoxSDKHelper(Context context, AdNetworkSdkModel sdkModel, ConfigPhp configPhp) {
        this.context = context;
        this.sdkModel = sdkModel;
        this.configPhp = configPhp;
        init();
    }

    public void onDestroy() {
    }

    public void init() {
        this.nativeAdFacades = new HashMap();
        Native.setLoc(true);
    }

    public void loadAds(int adsCount) {
        this.receivedAdsCount = 0;
        this.adsCountRequested = adsCount;
        for (int i = 0; i < adsCount; i++) {
            Native mobfoxNative = new Native(this.context);
            mobfoxNative.setListener(new C12721());
            mobfoxNative.load(this.sdkModel.getPlacementId());
        }
    }

    private boolean isLastAdReceived() {
        return this.receivedAdsCount == this.adsCountRequested;
    }

    public void setAdsListener(NativeAdsListener listener) {
        this.nativeAdsListener = listener;
    }

    public void setOnAdOpenedListener(OnAdOpenedListener onAdOpenedListener) {
        this.adOpenedListener = onAdOpenedListener;
    }
}
