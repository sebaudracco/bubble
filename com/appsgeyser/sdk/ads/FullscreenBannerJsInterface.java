package com.appsgeyser.sdk.ads;

import android.content.Context;
import android.webkit.JavascriptInterface;
import com.appsgeyser.sdk.ads.sdk.JavascriptSdkController;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.utils.WebViewScreenShooter;

class FullscreenBannerJsInterface extends JavascriptSdkController {
    static String JS_INTERFACE_NAME = "AppsgeyserBanner";
    private FullScreenBanner fullScreenBanner = null;

    FullscreenBannerJsInterface(FullScreenBanner fullScreenBanner, Context context) {
        super(context);
        this.fullScreenBanner = fullScreenBanner;
    }

    @JavascriptInterface
    public void stayAlive() {
        this.fullScreenBanner.stayAlive();
    }

    @JavascriptInterface
    public void showAdMobFullScreenBanner(String adUnitID, String keywords, String genderString, String birthday, String latitude, String longitude) {
        this.fullScreenBanner.showAdMobFSBanner(adUnitID, keywords, genderString, birthday, latitude, longitude);
    }

    @JavascriptInterface
    public void showMoPubFullScreenBanner(String adUnitID) {
        this.fullScreenBanner.showMoPubFSBanner(adUnitID);
    }

    @JavascriptInterface
    public void close() {
        this.fullScreenBanner.close();
    }

    @JavascriptInterface
    public void setClickUrl(String clickUrl, String hashCode) {
        if (checkSecurityCode(hashCode, this.fullScreenBanner.getContext())) {
            this.fullScreenBanner.setClickUrl(clickUrl);
        }
    }

    @JavascriptInterface
    public String takeScreenShot() {
        return WebViewScreenShooter.takeScreenShotInBase64(this.fullScreenBanner.getWebView());
    }

    @JavascriptInterface
    public void showVideoAd(String hash, String vastXml) {
        if (checkSecurityCode(hash, this.fullScreenBanner.getContext())) {
            this.fullScreenBanner.showVASTBanner(vastXml);
        }
    }

    @JavascriptInterface
    public void setStatUrls(String jsonParameters) {
    }

    @JavascriptInterface
    public void forceOpenInNativeBrowser(boolean openInNativeBrowser) {
        this.fullScreenBanner.forceOpenInNativeBrowser(openInNativeBrowser);
    }

    @JavascriptInterface
    public void setBackKeyLocked(boolean locked) {
        this.fullScreenBanner.setBackKeyLocked(locked);
    }

    @JavascriptInterface
    public void trackCrossClick() {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_CROSS_BANNER);
    }

    @JavascriptInterface
    public void trackBannerClick() {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_HTML_TAP_START);
    }

    @JavascriptInterface
    public void trackTimerClick() {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_CLICK_TIMER_BANNER);
    }

    @JavascriptInterface
    public void showTimer(int seconds) {
        this.fullScreenBanner.setShowTimer((long) (seconds * 1000));
    }

    @JavascriptInterface
    public void setUniqueId(String uniqueId) {
        this.fullScreenBanner.setUniqueId(uniqueId);
    }

    @JavascriptInterface
    public void dismissAdMobOnTimeout(String receivedTimeout) {
    }

    @JavascriptInterface
    public void dismissMoPubOnTimeout(String receivedTimeout) {
    }

    @JavascriptInterface
    public void saveImpressionUrl(String impressionUrl) {
        this.fullScreenBanner.setImpressionUrl(impressionUrl);
    }
}
