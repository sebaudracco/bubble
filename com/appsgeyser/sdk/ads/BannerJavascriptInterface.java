package com.appsgeyser.sdk.ads;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.AppsgeyserSDK.OnAdditionalJsLoaded;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.admobutils.AdMobParameters;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.hasher.Hasher;

public class BannerJavascriptInterface {
    static final String JS_INTERFACE_NAME = "AppsgeyserBanner";
    private final AdView adView;
    private final AdsLoader adsLoader;
    private String androidId;

    class C12182 implements Runnable {
        C12182() {
        }

        public void run() {
            BannerJavascriptInterface.this.adView.hide();
        }
    }

    public BannerJavascriptInterface(AdView adView, AdsLoader loader) {
        this.adView = adView;
        this.adsLoader = loader;
    }

    @JavascriptInterface
    public void addJs(final String jsCode, final String hash) {
        this.adView.post(new Runnable() {

            class C12161 implements OnAdditionalJsLoaded {
                C12161() {
                }

                public void onJsLoaded(String s) {
                    BannerJavascriptInterface.this.adView.getBrowser().loadUrl("javascript:(function(){ " + s + " })()");
                }
            }

            public void run() {
                if (BannerJavascriptInterface.this.checkSecurityCode(hash)) {
                    BannerJavascriptInterface.this.adView.addJsCode(jsCode);
                    if (!jsCode.equals("")) {
                        AppsgeyserSDK.getAdditionalJsCode(new C12161());
                        InternalEntryPoint.getInstance().setAdditionalJsCode(jsCode);
                    }
                }
            }
        });
    }

    @JavascriptInterface
    public void close() {
        this.adView.post(new C12182());
    }

    @JavascriptInterface
    public void setClickUrl(final String url, final String hash) {
        this.adView.post(new Runnable() {
            public void run() {
                if (BannerJavascriptInterface.this.checkSecurityCode(hash)) {
                    BannerJavascriptInterface.this.adsLoader.setClickUrl(url);
                }
            }
        });
    }

    @JavascriptInterface
    public String getAndroidId(String hash) {
        if (checkSecurityCode(hash)) {
            return this.androidId;
        }
        return "";
    }

    @JavascriptInterface
    public void reload(final String hash) {
        this.adView.post(new Runnable() {
            public void run() {
                if (BannerJavascriptInterface.this.checkSecurityCode(hash)) {
                    BannerJavascriptInterface.this.adsLoader.reload();
                }
            }
        });
    }

    @JavascriptInterface
    private boolean checkSecurityCode(String hashCode) {
        Configuration config = Configuration.getInstance(this.adView.getContext());
        String appId = config.getApplicationId();
        String guid = config.getAppGuid();
        if (TextUtils.isEmpty(appId) || TextUtils.isEmpty(guid)) {
            return false;
        }
        return hashCode.equalsIgnoreCase(Hasher.md5(guid + appId));
    }

    @JavascriptInterface
    public void forceOpenInNativeBrowser(boolean openInNativeBrowser) {
        this.adsLoader.forceOpenInNativeBrowser(openInNativeBrowser);
    }

    @JavascriptInterface
    public void showAdMobAd(String publisherId, String keywords, String genderString, String birthday, String latitude, String longtitude) {
        final String str = publisherId;
        final String str2 = keywords;
        final String str3 = genderString;
        final String str4 = birthday;
        final String str5 = latitude;
        final String str6 = longtitude;
        ((Activity) this.adView.getContext()).runOnUiThread(new Runnable() {
            public void run() {
                BannerJavascriptInterface.this.adView.switchToAdMobAd(new AdMobParameters(str, str2, str3, str4, str5, str6));
            }
        });
    }

    @JavascriptInterface
    public void showAdMobAdWithAppId(final String placementId, final String applicationId) {
        ((Activity) this.adView.getContext()).runOnUiThread(new Runnable() {
            public void run() {
                BannerJavascriptInterface.this.adView.handleAdmob(placementId, applicationId);
            }
        });
    }

    @JavascriptInterface
    public void showMoPubAd(final String placementId) {
        ((Activity) this.adView.getContext()).runOnUiThread(new Runnable() {
            public void run() {
                BannerJavascriptInterface.this.adView.handleMoPub(placementId);
            }
        });
    }

    @JavascriptInterface
    public void setUniqId(String uniqId) {
        this.adView.setUniqueId(uniqId);
    }

    @JavascriptInterface
    public void proceedClick(String url) {
        this.adsLoader.proceedClick(url);
    }
}
