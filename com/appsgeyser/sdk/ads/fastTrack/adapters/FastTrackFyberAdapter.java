package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ViewGroup;
import com.appsgeyser.sdk.BrowserActivity;
import com.appsgeyser.sdk.C1195R;
import com.appsgeyser.sdk.GuidGenerator;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.FullscreenListener;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.RewardedVideoListener;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberInterstitialClickedEvent;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberInterstitialClosedEvent;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberInterstitialErrorEvent;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberInterstitialOpenedEvent;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberVideoClosedEvent;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberVideoErrorEvent;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberVideoFinishedEvent;
import com.appsgeyser.sdk.ads.rewardedVideo.FyberCallbackActivity.fyberVideoOpenedEvent;
import com.appsgeyser.sdk.server.StatController;
import com.fyber.Fyber;
import com.fyber.ads.AdFormat;
import com.fyber.ads.banners.BannerAd;
import com.fyber.ads.banners.BannerAdListener;
import com.fyber.ads.banners.BannerAdView;
import com.fyber.mediation.admob.banner.AdMobNetworkBannerSizes;
import com.fyber.mediation.facebook.banner.FacebookNetworkBannerSizes;
import com.fyber.requesters.InterstitialRequester;
import com.fyber.requesters.RequestCallback;
import com.fyber.requesters.RequestError;
import com.fyber.requesters.RewardedVideoRequester;
import de.greenrobot.event.EventBus;
import java.util.Random;

public class FastTrackFyberAdapter extends FastTrackBaseAdapter {
    private boolean bannerRequestOngoing;
    private BannerAdView bannerView;
    private Integer bannerViewRefreshRate = Integer.valueOf(10000);
    private Runnable bannerViewRefreshRunnable = new C12413();
    private int fullscreenIntensityPoints = 100;
    private RequestCallback interstitialRequestCallback;
    private RequestCallback rewardedRequestCallback;

    class C12381 implements RequestCallback {
        C12381() {
        }

        public void onAdAvailable(Intent intent) {
            FastTrackFyberAdapter.this.progressDialog.show(FastTrackFyberAdapter.this.context);
            FastTrackFyberAdapter.this.handler.postDelayed(FastTrackFyberAdapter$1$$Lambda$1.lambdaFactory$(this, intent), 1000);
            Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen onAdAvailable");
        }

        static /* synthetic */ void lambda$onAdAvailable$0(C12381 this_, Intent intent) {
            FastTrackFyberAdapter.this.progressDialog.dismiss();
            FyberCallbackActivity.start(FastTrackFyberAdapter.this.context, intent, FyberCallbackActivity.FULLSCREEN_TYPE);
        }

        public void onAdNotAvailable(AdFormat adFormat) {
            FastTrackFyberAdapter.this.interstitialDetails.put("details", "");
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_NOFILL, FastTrackFyberAdapter.this.interstitialDetails, FastTrackFyberAdapter.this.context, true);
            if (FastTrackFyberAdapter.this.fullscreenListener != null) {
                FastTrackFyberAdapter.this.fullscreenListener.onFailedToShow();
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen onAdNotAvailable");
        }

        public void onRequestError(RequestError requestError) {
            FastTrackFyberAdapter.this.interstitialDetails.put("details", "error_desc: " + requestError.getDescription());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_ERROR, FastTrackFyberAdapter.this.interstitialDetails, FastTrackFyberAdapter.this.context, true);
            if (FastTrackFyberAdapter.this.fullscreenListener != null) {
                FastTrackFyberAdapter.this.fullscreenListener.onFailedToShow();
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen onRequestError: " + requestError.getDescription());
        }
    }

    class C12392 implements RequestCallback {
        C12392() {
        }

        public void onAdAvailable(Intent intent) {
            FastTrackFyberAdapter.this.progressDialog.dismiss();
            FyberCallbackActivity.start(FastTrackFyberAdapter.this.context, intent, FyberCallbackActivity.REWARDED_TYPE);
            Log.d(FastTrackAdsController.fastTrackLogTag, "fyber rewarded onAdAvailable");
        }

        public void onAdNotAvailable(AdFormat adFormat) {
            FastTrackFyberAdapter.this.progressDialog.dismiss();
            FastTrackFyberAdapter.this.rewardedDetails.put("details", "");
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_NOFILL, FastTrackFyberAdapter.this.rewardedDetails, FastTrackFyberAdapter.this.context, true);
            Log.d(FastTrackAdsController.fastTrackLogTag, "fyber rewarded onAdNotAvailable");
            FastTrackFyberAdapter.this.rewardedVideoListener.onVideoError(FastTrackFyberAdapter.this.context.getResources().getString(C1195R.string.appsgeysersdk_fasttrack_no_rew_video));
            FastTrackFyberAdapter.this.rewardedVideoListener = null;
        }

        public void onRequestError(RequestError requestError) {
            FastTrackFyberAdapter.this.progressDialog.dismiss();
            FastTrackFyberAdapter.this.rewardedDetails.put("details", "error_desc: " + requestError.getDescription());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_ERROR, FastTrackFyberAdapter.this.rewardedDetails, FastTrackFyberAdapter.this.context, true);
            Log.d(FastTrackAdsController.fastTrackLogTag, "fyber rewarded onRequestError: " + requestError.getDescription());
            FastTrackFyberAdapter.this.rewardedVideoListener.onVideoError(FastTrackFyberAdapter.this.context.getResources().getString(C1195R.string.appsgeysersdk_fasttrack_no_rew_video));
            FastTrackFyberAdapter.this.rewardedVideoListener = null;
        }
    }

    class C12413 implements Runnable {

        class C12401 implements BannerAdListener {
            C12401() {
            }

            public void onAdError(BannerAd bannerAd, String s) {
                if (FastTrackFyberAdapter.this.bannerViewContainer != null) {
                    if (s.equals("No banner available")) {
                        FastTrackFyberAdapter.this.bannerDetails.put("details", "");
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_NOFILL, FastTrackFyberAdapter.this.bannerDetails, FastTrackFyberAdapter.this.context, true);
                    } else {
                        FastTrackFyberAdapter.this.bannerDetails.put("details", "error_desc: " + s);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_ERROR, FastTrackFyberAdapter.this.bannerDetails, FastTrackFyberAdapter.this.context, true);
                    }
                }
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber banner onAdError: " + s);
                FastTrackFyberAdapter.this.bannerRequestOngoing = false;
            }

            public void onAdLoaded(BannerAd bannerAd) {
                if (FastTrackFyberAdapter.this.bannerViewContainer != null) {
                    FastTrackFyberAdapter.this.bannerView.setVisibility(0);
                    FastTrackFyberAdapter.this.bannerViewContainer.setVisibility(0);
                    FastTrackFyberAdapter.this.bannerDetails.put("details", "");
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_IMPRESSION, FastTrackFyberAdapter.this.bannerDetails, FastTrackFyberAdapter.this.context, true);
                }
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber banner onAdLoaded");
                FastTrackFyberAdapter.this.bannerRequestOngoing = false;
            }

            public void onAdClicked(BannerAd bannerAd) {
                FastTrackFyberAdapter.this.bannerDetails.put("details", "");
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_CLICK, FastTrackFyberAdapter.this.bannerDetails, FastTrackFyberAdapter.this.context, true);
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber banner onAdClicked");
            }

            public void onAdLeftApplication(BannerAd bannerAd) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber banner onAdLeftApplication");
            }
        }

        C12413() {
        }

        public void run() {
            if (!FastTrackFyberAdapter.this.bannerRequestOngoing) {
                if (FastTrackFyberAdapter.this.bannerView != null) {
                    FastTrackFyberAdapter.this.bannerView.destroy();
                }
                FastTrackFyberAdapter.this.bannerView = new BannerAdView((Activity) FastTrackFyberAdapter.this.context).withNetworkSize(AdMobNetworkBannerSizes.BANNER).withNetworkSize(FacebookNetworkBannerSizes.BANNER_50).withListener(new C12401());
                FastTrackFyberAdapter.this.bannerDetails.put("details", "");
                FastTrackFyberAdapter.this.bannerDetails.put(BrowserActivity.KEY_UNIQ_ID, GuidGenerator.generateNewGuid());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_REQUEST, FastTrackFyberAdapter.this.bannerDetails, FastTrackFyberAdapter.this.context, true);
                FastTrackFyberAdapter.this.bannerView.load();
                FastTrackFyberAdapter.this.bannerRequestOngoing = true;
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber banner attempt to load");
                FastTrackFyberAdapter.this.bannerViewContainer.addView(FastTrackFyberAdapter.this.bannerView);
                FastTrackFyberAdapter.this.bannerViewContainer.setVisibility(8);
                FastTrackFyberAdapter.this.bannerView.setVisibility(8);
            }
            FastTrackFyberAdapter.this.handler.postDelayed(FastTrackFyberAdapter.this.bannerViewRefreshRunnable, (long) FastTrackFyberAdapter.this.bannerViewRefreshRate.intValue());
        }
    }

    public FastTrackFyberAdapter(FastTrackSdkModel model, Context context) {
        super(model, context);
    }

    void init() {
        if (this.context instanceof Activity) {
            Fyber.with(this.fastTrackSdkModel.getAppId(), (Activity) this.context).withSecurityToken(this.fastTrackSdkModel.getToken()).start().notifyUserOnCompletion(false);
            this.interstitialDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomFullscreenActivated()) {
                this.interstitialDetails.put("ad_source", StatController.FT_NETWORK_FYBER_CUSTOM);
                this.interstitialDetails.put("net_name", StatController.FT_NETWORK_FYBER_CUSTOM);
                this.interstitialDetails.put("net_name_FS", StatController.FT_NETWORK_FYBER_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen: custom");
            } else {
                this.interstitialDetails.put("ad_source", StatController.FT_NETWORK_FYBER);
                this.interstitialDetails.put("net_name", StatController.FT_NETWORK_FYBER);
                this.interstitialDetails.put("net_name_FS", StatController.FT_NETWORK_FYBER);
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen: platform");
            }
            this.interstitialRequestCallback = new C12381();
            this.bannerDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomBannerActivated()) {
                this.bannerDetails.put("ad_source", StatController.FT_NETWORK_FYBER_CUSTOM);
                this.bannerDetails.put("net_name", StatController.FT_NETWORK_FYBER_CUSTOM);
                this.bannerDetails.put("net_name_FS", StatController.FT_NETWORK_FYBER_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber banner: custom");
            } else {
                this.bannerDetails.put("ad_source", StatController.FT_NETWORK_FYBER);
                this.bannerDetails.put("net_name", StatController.FT_NETWORK_FYBER);
                this.bannerDetails.put("net_name_FS", StatController.FT_NETWORK_FYBER);
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber banner: platform");
            }
            this.rewardedDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomRewardedActivated()) {
                this.rewardedDetails.put("ad_source", StatController.FT_NETWORK_FYBER_CUSTOM);
                this.rewardedDetails.put("net_name", StatController.FT_NETWORK_FYBER_CUSTOM);
                this.rewardedDetails.put("net_name_FS", StatController.FT_NETWORK_FYBER_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber rewarded: custom");
            } else {
                this.rewardedDetails.put("ad_source", StatController.FT_NETWORK_FYBER);
                this.rewardedDetails.put("net_name", StatController.FT_NETWORK_FYBER);
                this.rewardedDetails.put("net_name_FS", StatController.FT_NETWORK_FYBER);
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber rewarded: platform");
            }
            this.rewardedRequestCallback = new C12392();
            EventBus.getDefault().register(this);
        }
    }

    public void initBannerView(ViewGroup bannerViewContainer) {
        if (this.fastTrackSdkModel.getBannerRefreshTimer() != null) {
            this.bannerViewRefreshRate = this.fastTrackSdkModel.getBannerRefreshTimer();
        }
        Log.d(FastTrackAdsController.fastTrackLogTag, "fyber banner attempt to attach bannerView to container");
        this.bannerViewContainer = bannerViewContainer;
        this.handler.post(this.bannerViewRefreshRunnable);
    }

    public void loadFullscreen() {
    }

    public void showFullscreen(String loadTag) {
        if (this.fullscreenListener != null) {
            this.fullscreenListener.onRequest();
        }
        if (this.fastTrackSdkModel.getFullscreenIntensity() != null && this.fastTrackSdkModel.getFullscreenIntensity().intValue() >= 0) {
            this.fullscreenIntensityPoints = this.fastTrackSdkModel.getFullscreenIntensity().intValue();
        }
        if (new Random().nextInt(100) + 1 > this.fullscreenIntensityPoints) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen attempt to show canceled due to intensity settings");
        } else if (this.interstitialRequestCallback != null) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen attempt to show");
            this.interstitialDetails.put(BrowserActivity.KEY_UNIQ_ID, GuidGenerator.generateNewGuid());
            InterstitialRequester.create(this.interstitialRequestCallback).request(this.context);
        } else {
            Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen attempt to show failed: interstitialRequestCallback null");
        }
    }

    public void onEvent(fyberInterstitialClickedEvent event) {
        Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen onFullscreenClicked");
        this.interstitialDetails.put("details", "");
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_CLICK, this.interstitialDetails, this.context, true);
    }

    public void onEvent(fyberInterstitialClosedEvent event) {
        Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen onFullscreenClosed");
        if (this.progressDialog.isShowing()) {
            this.progressDialog.dismiss();
        }
        if (this.fullscreenListener != null) {
            this.fullscreenListener.onClose();
        }
    }

    public void onEvent(fyberInterstitialOpenedEvent event) {
        Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen onFullscreenOpened");
        this.interstitialDetails.put("details", "");
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_IMPRESSION, this.interstitialDetails, this.context, true);
        if (this.fullscreenListener != null) {
            this.fullscreenListener.onShow();
        }
    }

    public void onEvent(fyberInterstitialErrorEvent event) {
        this.interstitialDetails.put("details", "error_desc: " + event.errorMessage);
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_ERROR, this.interstitialDetails, this.context, true);
        Log.d(FastTrackAdsController.fastTrackLogTag, "fyber fullscreen onFullscreenError: " + event.errorMessage);
        if (this.fullscreenListener != null) {
            this.fullscreenListener.onFailedToShow();
        }
    }

    public void setFullscreenListener(FullscreenListener fullscreenListener) {
        this.fullscreenListener = fullscreenListener;
    }

    public void loadRewardedVideo() {
    }

    public void showRewardedVideo(RewardedVideoListener rewardedVideoListener) {
        this.rewardedVideoListener = rewardedVideoListener;
        if (this.rewardedRequestCallback != null) {
            this.progressDialog.show(this.context);
            RewardedVideoRequester.create(this.rewardedRequestCallback).request(this.context);
            return;
        }
        this.rewardedVideoListener.onVideoError(this.context.getResources().getString(C1195R.string.appsgeysersdk_fasttrack_no_rew_video));
    }

    public void onEvent(fyberVideoOpenedEvent event) {
        this.rewardedDetails.put("details", "");
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_IMPRESSION, this.rewardedDetails, this.context, true);
        Log.d(FastTrackAdsController.fastTrackLogTag, "fyber rewarded onRewardedVideoStarted");
        if (this.rewardedVideoListener != null) {
            this.rewardedVideoListener.onVideoOpened();
        }
    }

    public void onEvent(fyberVideoFinishedEvent event) {
        if (this.rewardedVideoListener != null) {
            this.rewardedVideoListener.onVideoFinished();
        }
        this.rewardedDetails.put("details", "");
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_COMPLETION, this.rewardedDetails, this.context, true);
        Log.d(FastTrackAdsController.fastTrackLogTag, "fyber rewarded onRewardedVideoFinished");
    }

    public void onEvent(fyberVideoClosedEvent event) {
        if (this.rewardedVideoListener != null) {
            this.rewardedVideoListener.onVideoClosed();
        }
        Log.d(FastTrackAdsController.fastTrackLogTag, "fyber rewarded onRewardedVideoClosed");
    }

    public void onEvent(fyberVideoErrorEvent event) {
        if (this.rewardedVideoListener != null) {
            this.rewardedVideoListener.onVideoError(event.errorMessage);
        }
        this.rewardedDetails.put("details", "error_desc: " + event.errorMessage);
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_ERROR, this.rewardedDetails, this.context, true);
        Log.d(FastTrackAdsController.fastTrackLogTag, "fyber rewarded onRewardedVideoError: " + event.errorMessage);
    }

    public void onResume(Context context) {
        super.onResume(context);
    }

    public void onPause() {
        if (this.bannerViewContainer != null) {
            if (this.bannerView != null) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "fyber banner attempt to detach bannerView from container");
                this.bannerViewContainer.removeView(this.bannerView);
            }
            this.bannerViewContainer = null;
        }
        this.handler.removeCallbacks(this.bannerViewRefreshRunnable);
    }

    public void onDestroy() {
    }
}
