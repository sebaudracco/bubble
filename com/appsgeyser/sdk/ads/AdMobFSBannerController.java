package com.appsgeyser.sdk.ads;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.admobutils.AdMobParameters;
import com.appsgeyser.sdk.admobutils.ParameterizedRequest;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;
import com.appsgeyser.sdk.ui.AdvertisingTermsDialog;
import com.appsgeyser.sdk.utils.ThreadRunner;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.yandex.metrica.YandexMetrica;

class AdMobFSBannerController extends AdListener {
    private static final int ADMOB_ERROR_CODE_INTERNAL_ERROR = 0;
    private static final int ADMOB_ERROR_CODE_NO_FILL = 3;
    private static final String APPSGEYSER_SDK_DEBUG = "APPSGEYSER_SDK_DEBUG";
    private static final long LOADING_TIMEOUT = 6000;
    private final Context context;
    private boolean error = false;
    private final Handler handler = new Handler();
    private InterstitialAd interstitial = null;
    private boolean isActive;
    private boolean isTimeoutExpired = false;
    private IFullScreenBannerListener listener = null;
    private final Runnable showInterstitialRunnable = new C11972();
    private final Runnable timeoutExpiredRunnable = new C11961();

    class C11961 implements Runnable {
        C11961() {
        }

        public void run() {
            AdMobFSBannerController.this.error = true;
            if (AdMobFSBannerController.this.listener != null) {
                AdMobFSBannerController.this.listener.onAdFailedToLoad(AdMobFSBannerController.this.context, InternalEntryPoint.getInstance().getFullScreenBanner(AdMobFSBannerController.this.context).getLoadTagBanner());
            }
        }
    }

    class C11972 implements Runnable {
        C11972() {
        }

        public void run() {
            AdMobFSBannerController.this.interstitial.show();
            AdMobFSBannerController.this.handler.removeCallbacksAndMessages(null);
        }
    }

    AdMobFSBannerController(Context context) {
        this.context = context;
    }

    void load(String adUnitID, String keywords, String genderString, String birthday, String latitude, String longitude, FullScreenBanner banner) {
        final String str = adUnitID;
        final String str2 = keywords;
        final String str3 = genderString;
        final String str4 = birthday;
        final String str5 = latitude;
        final String str6 = longitude;
        final FullScreenBanner fullScreenBanner = banner;
        ThreadRunner.runOnUiThread(this.context, new Runnable() {

            class C11981 extends AdListener {
                C11981() {
                }

                public void onAdClosed() {
                    Log.d(AdMobFSBannerController.APPSGEYSER_SDK_DEBUG, "admob_fs_onAdClosed");
                    super.onAdClosed();
                    if (AdMobFSBannerController.this.listener != null) {
                        AdMobFSBannerController.this.listener.onAdHided(AdMobFSBannerController.this.context, fullScreenBanner.getLoadTagBanner());
                    }
                }

                public void onAdFailedToLoad(int errorCode) {
                    Log.d(AdMobFSBannerController.APPSGEYSER_SDK_DEBUG, "admob_fs_onAdFailedToLoad" + errorCode);
                    super.onAdFailedToLoad(errorCode);
                    if (errorCode == 3 || errorCode == 0) {
                        fullScreenBanner.setAdmobFailedLoad(true);
                        fullScreenBanner.skipNonHTMLBanner(fullScreenBanner.getLoadTagBanner());
                    }
                    if (!AdMobFSBannerController.this.error) {
                        AdMobFSBannerController.this.error = true;
                        AdMobFSBannerController.this.handler.removeCallbacksAndMessages(null);
                    }
                }

                public void onAdLeftApplication() {
                    Log.d(AdMobFSBannerController.APPSGEYSER_SDK_DEBUG, "admob_fs_onAdLeftApplication");
                    super.onAdLeftApplication();
                    fullScreenBanner.setBannerClicked(true);
                    AppsgeyserServerClient.getInstance().sendClickInfo(AppsgeyserSDK.getFullScreenBanner(AdMobFSBannerController.this.context).getClickUrl(), AdMobFSBannerController.this.context);
                    YandexMetrica.reportEvent(FullScreenBanner.FULLSCREEN_CLICKED);
                }

                public void onAdLoaded() {
                    Log.d(AdMobFSBannerController.APPSGEYSER_SDK_DEBUG, "admob_fs_onAdLoaded");
                    super.onAdLoaded();
                    fullScreenBanner.setAdmobFailedLoad(false);
                    AppsgeyserServerClient.getInstance().sendImpression(InternalEntryPoint.getInstance().getFullScreenBanner(AdMobFSBannerController.this.context).getImpressionUrl(), fullScreenBanner.getWebView().getContext());
                    if (AdMobFSBannerController.this.interstitial.isLoaded() && !AdMobFSBannerController.this.error && AdMobFSBannerController.this.listener != null) {
                        AdMobFSBannerController.this.listener.onLoadFinished(fullScreenBanner);
                    } else if (AdMobFSBannerController.this.interstitial.isLoaded()) {
                        AdMobFSBannerController.this.setActive(true);
                        if (!AdMobFSBannerController.this.isTimeoutExpired || AdvertisingTermsDialog.isOnScreen()) {
                            AdMobFSBannerController.this.handler.postDelayed(AdMobFSBannerController.this.showInterstitialRunnable, Constants.getFullScreenDelay());
                        }
                    }
                }

                public void onAdOpened() {
                    Log.d(AdMobFSBannerController.APPSGEYSER_SDK_DEBUG, "admob_fs_onAdOpened");
                    super.onAdOpened();
                }
            }

            public void run() {
                AdMobFSBannerController.this.error = false;
                String internalAdUnitId = str;
                if (internalAdUnitId == null) {
                    internalAdUnitId = "";
                }
                AdMobFSBannerController.this.interstitial = new InterstitialAd(AdMobFSBannerController.this.context);
                AdMobFSBannerController.this.interstitial.setAdUnitId(internalAdUnitId);
                ParameterizedRequest parameterizedRequest = new ParameterizedRequest(new AdMobParameters(internalAdUnitId, str2, str3, str4, str5, str6));
                AdMobFSBannerController.this.interstitial.setAdListener(new C11981());
                AdMobFSBannerController.this.interstitial.loadAd(parameterizedRequest.getRequest());
                AdMobFSBannerController.this.handler.postDelayed(AdMobFSBannerController.this.timeoutExpiredRunnable, AdMobFSBannerController.LOADING_TIMEOUT);
            }
        });
    }

    void showBanner() {
        Log.d(APPSGEYSER_SDK_DEBUG, "admob_fs_showBanner");
        if (this.interstitial.isLoaded()) {
            this.interstitial.show();
        }
    }

    public boolean isActive() {
        return this.isActive;
    }

    private void setActive(boolean active) {
        this.isActive = active;
    }

    protected void setListener(IFullScreenBannerListener listener) {
        this.listener = listener;
    }

    public boolean isTimeoutExpired() {
        return this.isTimeoutExpired;
    }

    public void setTimeoutExpired(boolean timeoutExpired) {
        this.isTimeoutExpired = timeoutExpired;
    }
}
