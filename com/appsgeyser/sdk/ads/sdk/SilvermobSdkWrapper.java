package com.appsgeyser.sdk.ads.sdk;

import android.os.Handler;
import android.util.Log;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.ads.FullScreenBanner.BannerTypes;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.server.StatController;
import com.silvermob.sdk.InterstitialAd;
import com.silvermob.sdk.InterstitialAd.InterstitialAdLoadListener;
import com.silvermob.sdk.InterstitialAd.InterstitialListener;

class SilvermobSdkWrapper extends SdkWrapper {
    private static final String KEY_PLACEMENT_ID = "placement_id";
    private static final int SKIP_TIMEOUT = 5;
    private static final String TAG = "silvermob";

    SilvermobSdkWrapper() {
    }

    public void showFsBanner() {
        InternalEntryPoint.getInstance().getFullScreenBanner(this.context).setBannerType(BannerTypes.SDK);
        Log.d(TAG, "Interstitial going to start load");
        final InterstitialAd intAd = new InterstitialAd(this.context);
        intAd.setSkipTimeout(5);
        intAd.requestInterstitialAd((String) this.parameters.get(KEY_PLACEMENT_ID), new InterstitialAdLoadListener() {

            class C12891 implements Runnable {

                class C12881 implements InterstitialListener {
                    C12881() {
                    }

                    public void onShown() {
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_INTERSTITIAL_IMPRESSION_URL);
                        Log.d(SilvermobSdkWrapper.TAG, "Interstitial onAdLoaded");
                    }

                    public void onClosed() {
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_INTERSTITIAL_CLOSE_URL);
                        Log.d(SilvermobSdkWrapper.TAG, "Interstitial onClosed");
                    }

                    public void onClicked() {
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_INTERSTITIAL_CLICK_URL);
                        Log.d(SilvermobSdkWrapper.TAG, "Interstitial onClicked");
                    }

                    public void onError() {
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_INTERSTITIAL_ERROR_URL);
                        Log.d(SilvermobSdkWrapper.TAG, "Interstitial onError");
                    }
                }

                C12891() {
                }

                public void run() {
                    SilvermobSdkWrapper.this.progressDialog.dismiss();
                    intAd.showInterstitialAd(new C12881());
                }
            }

            public void onAdLoaded() {
                Log.d(SilvermobSdkWrapper.TAG, "Interstitial onAdLoaded");
                SilvermobSdkWrapper.this.progressDialog.show();
                new Handler().postDelayed(new C12891(), Constants.getFullScreenDelay());
            }

            public void onNoAd() {
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_INTERSTITIAL_NO_FILL_URL);
                Log.d(SilvermobSdkWrapper.TAG, "Interstitial onNoAd");
            }

            public void onError() {
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_INTERSTITIAL_ERROR_URL);
                Log.d(SilvermobSdkWrapper.TAG, "Interstitial onError");
            }
        });
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_SILVERMOB_INTERSTITIAL_REQUEST_URL);
    }

    public void getNativeAd() {
    }

    public boolean isAdSupported(AdType adType) {
        switch (adType) {
            case FULLSCREEN:
                return true;
            default:
                return false;
        }
    }
}
