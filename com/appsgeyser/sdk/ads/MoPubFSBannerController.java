package com.appsgeyser.sdk.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;
import com.appsgeyser.sdk.utils.ThreadRunner;
import com.mopub.mobileads.MoPubConversionTracker;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener;
import com.yandex.metrica.YandexMetrica;
import java.util.HashMap;

public class MoPubFSBannerController {
    private static final String APPSGEYSER_MOPUB_FS_DEBUG = "MOPUB_FS_DEBUG";
    private static final long LOADING_TIMEOUT = 12000;
    private final Context context;
    private final Handler handler = new Handler();
    private MoPubInterstitial interstitial = null;
    private boolean isTimeoutExpired = false;
    private IFullScreenBannerListener listener = null;
    private TimeoutExpiredRunnable timeoutExpiredRunnable;

    abstract class TimeoutExpiredRunnable implements Runnable {
        FullScreenBanner banner;

        TimeoutExpiredRunnable(FullScreenBanner banner) {
            this.banner = banner;
        }
    }

    MoPubFSBannerController(Context context) {
        this.context = context;
        new MoPubConversionTracker().reportAppOpen(context);
    }

    void load(final String adUnitID, final FullScreenBanner banner) {
        Configuration configuration = Configuration.getInstance(this.context);
        final HashMap<String, String> details = new HashMap();
        details.put("wdid", configuration.getApplicationId());
        details.put("guid", configuration.getAppGuid());
        details.put("details", "mopub adUnit: " + adUnitID);
        ThreadRunner.runOnUiThread(this.context, new Runnable() {

            class C12311 implements InterstitialAdListener {
                C12311() {
                }

                public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
                    if (MoPubFSBannerController.this.isTimeoutExpired) {
                        Log.d(MoPubFSBannerController.APPSGEYSER_MOPUB_FS_DEBUG, "mopub_fs_onLoaded + timeout_expired");
                        return;
                    }
                    Log.d(MoPubFSBannerController.APPSGEYSER_MOPUB_FS_DEBUG, "mopub_fs_onLoaded");
                    if (MoPubFSBannerController.this.interstitial.isReady() && MoPubFSBannerController.this.listener != null) {
                        banner.setMoPubFailedLoad(false);
                        MoPubFSBannerController.this.handler.removeCallbacksAndMessages(null);
                        MoPubFSBannerController.this.listener.onLoadFinished(banner);
                    }
                }

                public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
                    if (!MoPubFSBannerController.this.isTimeoutExpired) {
                        Log.d(MoPubFSBannerController.APPSGEYSER_MOPUB_FS_DEBUG, "mopub_fs_onAdFailedToLoad " + moPubErrorCode.toString());
                        MoPubFSBannerController.this.processLoadingFailure(banner);
                        MoPubFSBannerController.this.handler.removeCallbacksAndMessages(null);
                    }
                }

                public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_MOPUB_INTERSTITIAL_SDK_IMPESSION, details, MoPubFSBannerController.this.context, true);
                    AppsgeyserServerClient.getInstance().sendImpression(InternalEntryPoint.getInstance().getFullScreenBanner(MoPubFSBannerController.this.context).getImpressionUrl(), banner.getWebView().getContext());
                    Log.d(MoPubFSBannerController.APPSGEYSER_MOPUB_FS_DEBUG, "mopub_fs_onAdOpened");
                }

                public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_MOPUB_INTERSTITIAL_SDK_CLICK, details, MoPubFSBannerController.this.context, true);
                    Log.d(MoPubFSBannerController.APPSGEYSER_MOPUB_FS_DEBUG, "mopub_fs_onAdLeftApplication");
                    banner.setBannerClicked(true);
                    AppsgeyserServerClient.getInstance().sendClickInfo(AppsgeyserSDK.getFullScreenBanner(MoPubFSBannerController.this.context).getClickUrl(), MoPubFSBannerController.this.context);
                    YandexMetrica.reportEvent(FullScreenBanner.FULLSCREEN_CLICKED);
                }

                public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
                    Log.d(MoPubFSBannerController.APPSGEYSER_MOPUB_FS_DEBUG, "mopub_fs_onAdClosed");
                    if (MoPubFSBannerController.this.listener != null) {
                        MoPubFSBannerController.this.listener.onAdHided(MoPubFSBannerController.this.context, banner.getLoadTagBanner());
                    }
                }
            }

            public void run() {
                String internalAdUnitId = adUnitID;
                if (internalAdUnitId == null) {
                    internalAdUnitId = "";
                }
                if (MoPubFSBannerController.this.context instanceof Activity) {
                    MoPubFSBannerController.this.interstitial = new MoPubInterstitial((Activity) MoPubFSBannerController.this.context, internalAdUnitId);
                    MoPubFSBannerController.this.interstitial.setInterstitialAdListener(new C12311());
                    MoPubFSBannerController.this.interstitial.load();
                    MoPubFSBannerController.this.timeoutExpiredRunnable = new TimeoutExpiredRunnable(banner) {
                        public void run() {
                            MoPubFSBannerController.this.isTimeoutExpired = true;
                            if (MoPubFSBannerController.this.listener != null) {
                                MoPubFSBannerController.this.listener.onAdFailedToLoad(MoPubFSBannerController.this.context, InternalEntryPoint.getInstance().getFullScreenBanner(MoPubFSBannerController.this.context).getLoadTagBanner());
                            }
                            Log.d(MoPubFSBannerController.APPSGEYSER_MOPUB_FS_DEBUG, "mopub_fs_onAdFailedToLoad expired timeout");
                            MoPubFSBannerController.this.processLoadingFailure(this.banner);
                        }
                    };
                    MoPubFSBannerController.this.handler.postDelayed(MoPubFSBannerController.this.timeoutExpiredRunnable, MoPubFSBannerController.LOADING_TIMEOUT);
                    return;
                }
                Log.d(MoPubFSBannerController.APPSGEYSER_MOPUB_FS_DEBUG, "mopub_fs_onAdFailedToLoad invalid context");
                MoPubFSBannerController.this.processLoadingFailure(banner);
            }
        });
    }

    private void processLoadingFailure(FullScreenBanner banner) {
        banner.setMoPubFailedLoad(true);
        banner.skipNonHTMLBanner(banner.getLoadTagBanner());
    }

    void showBanner() {
        Log.d(APPSGEYSER_MOPUB_FS_DEBUG, "mopub_fs_showBanner");
        if (this.interstitial.isReady()) {
            this.interstitial.show();
        }
    }

    protected void setListener(IFullScreenBannerListener listener) {
        this.listener = listener;
    }
}
