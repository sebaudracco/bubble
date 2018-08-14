package com.appnext.sdk.adapters.mopub.banners;

import android.content.Context;
import android.util.Log;
import com.appnext.ads.AdsError;
import com.appnext.banners.BannerAdRequest;
import com.appnext.banners.BannerListener;
import com.appnext.banners.BannerView;
import com.appnext.core.AppnextError;
import com.mopub.mobileads.CustomEventBanner;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import com.mopub.mobileads.MoPubErrorCode;
import java.util.Map;

public class AppnextMoPubCustomEventBanner extends CustomEventBanner {
    public static final String AppnextConfigurationExtraKey = "AppnextConfiguration";
    protected BannerView mBanner;
    private CustomEventBannerListener mBannerListener;

    class C11771 extends BannerListener {
        C11771() {
        }

        public void onAdLoaded(String str) {
            super.onAdLoaded(str);
            AppnextMoPubCustomEventBanner.this.mBannerListener.onBannerLoaded(AppnextMoPubCustomEventBanner.this.mBanner);
        }

        public void onAdClicked() {
            super.onAdClicked();
            AppnextMoPubCustomEventBanner.this.mBannerListener.onBannerClicked();
            AppnextMoPubCustomEventBanner.this.mBannerListener.onLeaveApplication();
        }

        public void onError(AppnextError appnextError) {
            super.onError(appnextError);
            String errorMessage = appnextError.getErrorMessage();
            Object obj = -1;
            switch (errorMessage.hashCode()) {
                case -1958363695:
                    if (errorMessage.equals(AppnextError.NO_ADS)) {
                        obj = 3;
                        break;
                    }
                    break;
                case -1477010874:
                    if (errorMessage.equals(AppnextError.CONNECTION_ERROR)) {
                        obj = 2;
                        break;
                    }
                    break;
                case 297538105:
                    if (errorMessage.equals(AdsError.AD_NOT_READY)) {
                        obj = null;
                        break;
                    }
                    break;
                case 350741825:
                    if (errorMessage.equals("Timeout")) {
                        obj = 1;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    AppnextMoPubCustomEventBanner.this.mBannerListener.onBannerFailed(MoPubErrorCode.WARMUP);
                    return;
                case 1:
                    AppnextMoPubCustomEventBanner.this.mBannerListener.onBannerFailed(MoPubErrorCode.NETWORK_TIMEOUT);
                    return;
                case 2:
                    AppnextMoPubCustomEventBanner.this.mBannerListener.onBannerFailed(MoPubErrorCode.NO_CONNECTION);
                    return;
                case 3:
                    AppnextMoPubCustomEventBanner.this.mBannerListener.onBannerFailed(MoPubErrorCode.NO_FILL);
                    return;
                default:
                    AppnextMoPubCustomEventBanner.this.mBannerListener.onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
                    return;
            }
        }
    }

    protected BannerView createBannerView(Context context, Map<String, Object> map, Map<String, String> map2) {
        try {
            BannerView appnextMopubBannerView = new AppnextMopubBannerView(context);
            appnextMopubBannerView.setPlacementId(Helper.getAppnextPlacementIdExtraKey(map2));
            appnextMopubBannerView.setBannerSize(Helper.getBannerSize(map2));
            return appnextMopubBannerView;
        } catch (Throwable th) {
            Log.e("AppnextMoPub", "AppnextMoPubCustomEventBanner createAd: " + th.getMessage());
            return null;
        }
    }

    protected boolean hasAdConfigServerExtras(Map<String, String> map) {
        if (map == null) {
            return false;
        }
        if (Helper.hasAdConfigServerExtras(map) || map.containsKey("AppnextCreativeType") || map.containsKey("AppnextCategories") || map.containsKey("AppnextPostback") || map.containsKey("AppnextMute") || map.containsKey("AppnextVideoLength") || map.containsKey("AppnextMaxVideoLen") || map.containsKey("AppnextMinVideoLen") || map.containsKey("AppnextClickEnabled") || map.containsKey("AppnextAutoPlay")) {
            return true;
        }
        return false;
    }

    protected void setConfigFromExtras(BannerAdRequest bannerAdRequest, Map<String, String> map) {
        if (bannerAdRequest != null) {
            if (map.containsKey("AppnextCreativeType")) {
                try {
                    bannerAdRequest.setCreativeType((String) map.get("AppnextCreativeType"));
                    Log.e("test", ((String) map.get("AppnextCreativeType")) + "set creative");
                } catch (Throwable th) {
                    Log.e("AppnextMoPub", "setCreativeType: " + th.getMessage());
                }
            }
            if (map.containsKey("AppnextCategories")) {
                try {
                    bannerAdRequest.setCategories((String) map.get("AppnextCategories"));
                    Log.e("test", ((String) map.get("AppnextCategories")) + "set categories");
                } catch (Throwable th2) {
                    Log.e("AppnextMoPub", "setCategories: " + th2.getMessage());
                }
            }
            if (map.containsKey("AppnextPostback")) {
                try {
                    bannerAdRequest.setPostback((String) map.get("AppnextPostback"));
                } catch (Throwable th22) {
                    Log.e("AppnextMoPub", "setPostback: " + th22.getMessage());
                }
            }
            if (map.containsKey("AppnextMute")) {
                try {
                    bannerAdRequest.setMute(Boolean.parseBoolean((String) map.get("AppnextMute")));
                } catch (Throwable th222) {
                    Log.e("AppnextMoPub", "setMute: " + th222.getMessage());
                }
            }
            if (map.containsKey("AppnextVideoLength")) {
                try {
                    bannerAdRequest.setVideoLength((String) map.get("AppnextVideoLength"));
                } catch (Throwable th2222) {
                    Log.e("AppnextMoPub", "setVideoLength: " + th2222.getMessage());
                }
            }
            if (map.containsKey("AppnextMaxVideoLen")) {
                try {
                    bannerAdRequest.setVidMax(Integer.getInteger((String) map.get("AppnextMaxVideoLen")).intValue());
                } catch (Throwable th22222) {
                    Log.e("AppnextMoPub", "setVidMax: " + th22222.getMessage());
                }
            }
            if (map.containsKey("AppnextMinVideoLen")) {
                try {
                    bannerAdRequest.setVidMin(Integer.getInteger((String) map.get("AppnextMinVideoLen")).intValue());
                } catch (Throwable th222222) {
                    Log.e("AppnextMoPub", "setVidMin: " + th222222.getMessage());
                }
            }
            if (map.containsKey("AppnextAutoPlay")) {
                try {
                    bannerAdRequest.setAutoPlay(Boolean.parseBoolean((String) map.get("AppnextAutoPlay")));
                } catch (Throwable th2222222) {
                    Log.e("AppnextMoPub", "setAutoPlay: " + th2222222.getMessage());
                }
            }
            if (map.containsKey("AppnextClickEnabled")) {
                try {
                    bannerAdRequest.setClickEnabled(Boolean.parseBoolean((String) map.get("AppnextClickEnabled")));
                } catch (Throwable th22222222) {
                    Log.e("AppnextMoPub", "setClickEnabled: " + th22222222.getMessage());
                }
            }
        }
    }

    protected void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> map, Map<String, String> map2) {
        this.mBannerListener = customEventBannerListener;
        this.mBanner = createBannerView(context, map, map2);
        if (this.mBanner == null) {
            this.mBannerListener.onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
            return;
        }
        BannerAdRequest bannerAdRequest;
        BannerAdRequest bannerAdRequest2 = null;
        if (map != null) {
            bannerAdRequest2 = map.get("AppnextConfiguration");
        }
        if (bannerAdRequest2 == null) {
            bannerAdRequest2 = new BannerAdRequest();
            if (hasAdConfigServerExtras(map2)) {
                setConfigFromExtras(bannerAdRequest2, map2);
            }
            bannerAdRequest = bannerAdRequest2;
        } else {
            bannerAdRequest = bannerAdRequest2;
        }
        try {
            this.mBanner.setBannerListener(new C11771());
            this.mBanner.loadAd(bannerAdRequest);
        } catch (Throwable th) {
            Log.e("AppnextMoPub", "requestBannerAd: " + th.getMessage());
            this.mBannerListener.onBannerFailed(MoPubErrorCode.INTERNAL_ERROR);
        }
    }

    protected void onInvalidate() {
        if (this.mBanner != null) {
            this.mBanner.destroy();
        }
        this.mBanner = null;
    }
}
