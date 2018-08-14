package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;
import com.appsgeyser.sdk.BrowserActivity;
import com.appsgeyser.sdk.C1195R;
import com.appsgeyser.sdk.GuidGenerator;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.FullscreenListener;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.RewardedVideoListener;
import com.appsgeyser.sdk.server.StatController;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import java.util.Random;

public class FastTrackAdmobAdapter extends FastTrackBaseAdapter {
    private AdView bannerView;
    private Integer bannerViewRefreshRate = Integer.valueOf(10000);
    private Runnable bannerViewRefreshRunnable = new C12352();
    private int fullscreenIntensityPoints = 100;
    private Runnable fullscreenPendingRequestCancelRunnable = FastTrackAdmobAdapter$$Lambda$1.lambdaFactory$(this);
    private InterstitialAd interstitialAd;
    private RewardedVideoAd rewardedVideoAd;
    private Runnable rewardedVideoShowCancelRunnable = FastTrackAdmobAdapter$$Lambda$2.lambdaFactory$(this);

    class C12352 implements Runnable {
        C12352() {
        }

        public void run() {
            if (FastTrackAdmobAdapter.this.bannerView != null) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner attempt to load");
                FastTrackAdmobAdapter.this.bannerView.loadAd(new Builder().build());
                FastTrackAdmobAdapter.this.bannerDetails.put("details", "banner id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getBannerPlacementId());
                FastTrackAdmobAdapter.this.bannerDetails.put(BrowserActivity.KEY_UNIQ_ID, GuidGenerator.generateNewGuid());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_REQUEST, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                FastTrackAdmobAdapter.this.handler.postDelayed(FastTrackAdmobAdapter.this.bannerViewRefreshRunnable, (long) FastTrackAdmobAdapter.this.bannerViewRefreshRate.intValue());
            }
        }
    }

    class C12363 extends AdListener {
        C12363() {
        }

        public void onAdClosed() {
            super.onAdClosed();
            if (FastTrackAdmobAdapter.this.progressDialog.isShowing()) {
                FastTrackAdmobAdapter.this.progressDialog.dismiss();
            }
            FastTrackAdmobAdapter.this.loadFullscreen();
            if (FastTrackAdmobAdapter.this.fullscreenListener != null) {
                FastTrackAdmobAdapter.this.fullscreenListener.onClose();
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdClosed");
        }

        public void onAdFailedToLoad(int i) {
            super.onAdFailedToLoad(i);
            if (i == 3) {
                FastTrackAdmobAdapter.this.interstitialDetails.put("details", "fs id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getFullscreenPlacementId());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_NOFILL, FastTrackAdmobAdapter.this.interstitialDetails, FastTrackAdmobAdapter.this.context, true);
            } else {
                FastTrackAdmobAdapter.this.interstitialDetails.put("details", "fs id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getFullscreenPlacementId() + "; error_desc: error code " + i);
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_ERROR, FastTrackAdmobAdapter.this.interstitialDetails, FastTrackAdmobAdapter.this.context, true);
            }
            FastTrackAdmobAdapter.this.handler.postDelayed(FastTrackAdmobAdapter$3$$Lambda$1.lambdaFactory$(this), 30000);
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdFailedToLoad " + i);
        }

        public void onAdLeftApplication() {
            super.onAdLeftApplication();
            FastTrackAdmobAdapter.this.interstitialDetails.put("details", "fs id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getFullscreenPlacementId());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_CLICK, FastTrackAdmobAdapter.this.interstitialDetails, FastTrackAdmobAdapter.this.context, true);
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdLeftApplication");
        }

        public void onAdOpened() {
            super.onAdOpened();
            FastTrackAdmobAdapter.this.interstitialDetails.put("details", "fs id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getFullscreenPlacementId());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_IMPRESSION, FastTrackAdmobAdapter.this.interstitialDetails, FastTrackAdmobAdapter.this.context, true);
            if (FastTrackAdmobAdapter.this.fullscreenListener != null) {
                FastTrackAdmobAdapter.this.fullscreenListener.onShow();
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdOpened");
        }

        public void onAdLoaded() {
            super.onAdLoaded();
            if (FastTrackAdmobAdapter.this.pendingFullscreenRequest) {
                FastTrackAdmobAdapter.this.pendingFullscreenRequest = false;
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen loaded, pending request processing");
                FastTrackAdmobAdapter.this.handler.removeCallbacks(FastTrackAdmobAdapter.this.fullscreenPendingRequestCancelRunnable);
                FastTrackAdmobAdapter.this.progressDialog.show();
                FastTrackAdmobAdapter.this.handler.postDelayed(FastTrackAdmobAdapter$3$$Lambda$2.lambdaFactory$(this), 1000);
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdLoaded");
        }

        static /* synthetic */ void lambda$onAdLoaded$1(C12363 this_) {
            FastTrackAdmobAdapter.this.progressDialog.dismiss();
            FastTrackAdmobAdapter.this.interstitialAd.show();
        }

        public void onAdClicked() {
            super.onAdClicked();
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdClicked");
        }

        public void onAdImpression() {
            super.onAdImpression();
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fs onAdImpression");
        }
    }

    class C12374 implements RewardedVideoAdListener {
        C12374() {
        }

        public void onRewardedVideoAdLoaded() {
            FastTrackAdmobAdapter.this.videoDownloadError = false;
            if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                FastTrackAdmobAdapter.this.showRewardedVideo(FastTrackAdmobAdapter.this.rewardedVideoListener);
            }
            FastTrackAdmobAdapter.this.handler.removeCallbacks(FastTrackAdmobAdapter.this.rewardedVideoShowCancelRunnable);
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoAdLoaded");
        }

        public void onRewardedVideoAdOpened() {
            FastTrackAdmobAdapter.this.progressDialog.dismiss();
            FastTrackAdmobAdapter.this.rewardedDetails.put("details", "rewarded id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getRewardedVideoPlacementId());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_IMPRESSION, FastTrackAdmobAdapter.this.rewardedDetails, FastTrackAdmobAdapter.this.context, true);
            if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                FastTrackAdmobAdapter.this.rewardedVideoListener.onVideoOpened();
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoAdOpened");
        }

        public void onRewardedVideoStarted() {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoStarted");
        }

        public void onRewardedVideoAdClosed() {
            if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                FastTrackAdmobAdapter.this.rewardedVideoListener.onVideoClosed();
                FastTrackAdmobAdapter.this.rewardedVideoListener = null;
            }
            FastTrackAdmobAdapter.this.loadRewardedVideo();
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoAdClosed");
        }

        public void onRewarded(RewardItem rewardItem) {
            if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                FastTrackAdmobAdapter.this.rewardedVideoListener.onVideoFinished();
            }
            FastTrackAdmobAdapter.this.rewardedDetails.put("details", "rewarded id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getRewardedVideoPlacementId());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_COMPLETION, FastTrackAdmobAdapter.this.rewardedDetails, FastTrackAdmobAdapter.this.context, true);
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewarded");
        }

        public void onRewardedVideoAdLeftApplication() {
            FastTrackAdmobAdapter.this.rewardedDetails.put("details", "rewarded id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getRewardedVideoPlacementId());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_CLICK, FastTrackAdmobAdapter.this.rewardedDetails, FastTrackAdmobAdapter.this.context, true);
            if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                FastTrackAdmobAdapter.this.rewardedVideoListener.onVideoClicked();
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoAdLeftApplication");
        }

        public void onRewardedVideoAdFailedToLoad(int i) {
            FastTrackAdmobAdapter.this.videoDownloadError = true;
            if (FastTrackAdmobAdapter.this.rewardedVideoListener != null) {
                FastTrackAdmobAdapter.this.rewardedVideoListener = null;
            }
            if (i == 3) {
                FastTrackAdmobAdapter.this.rewardedDetails.put("details", "rewarded id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getRewardedVideoPlacementId());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_NOFILL, FastTrackAdmobAdapter.this.rewardedDetails, FastTrackAdmobAdapter.this.context, true);
            } else {
                FastTrackAdmobAdapter.this.rewardedDetails.put("details", "rewarded id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getRewardedVideoPlacementId() + "; error_desc: error code " + i);
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_ERROR, FastTrackAdmobAdapter.this.rewardedDetails, FastTrackAdmobAdapter.this.context, true);
            }
            FastTrackAdmobAdapter.this.handler.postDelayed(FastTrackAdmobAdapter$4$$Lambda$1.lambdaFactory$(this), 30000);
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoAdFailedToLoad: " + i);
        }

        public void onRewardedVideoCompleted() {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded onRewardedVideoCompleted");
        }
    }

    public FastTrackAdmobAdapter(FastTrackSdkModel model, Context context) {
        super(model, context);
    }

    void init() {
        MobileAds.initialize(this.context.getApplicationContext(), this.fastTrackSdkModel.getAppId());
        if (this.fastTrackSdkModel.getFullscreenPlacementId() != null) {
            this.interstitialDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomFullscreenActivated()) {
                this.interstitialDetails.put("ad_source", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.interstitialDetails.put("net_name", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.interstitialDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen: custom");
            } else {
                this.interstitialDetails.put("ad_source", StatController.FT_NETWORK_ADMOB);
                this.interstitialDetails.put("net_name", StatController.FT_NETWORK_ADMOB);
                this.interstitialDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen: platform");
            }
        } else {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen disabled");
        }
        if (this.fastTrackSdkModel.getBannerPlacementId() != null) {
            this.bannerDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomBannerActivated()) {
                this.bannerDetails.put("ad_source", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.bannerDetails.put("net_name", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.bannerDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner: custom");
            } else {
                this.bannerDetails.put("ad_source", StatController.FT_NETWORK_ADMOB);
                this.bannerDetails.put("net_name", StatController.FT_NETWORK_ADMOB);
                this.bannerDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner: platform");
            }
        } else {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner disabled");
        }
        if (this.fastTrackSdkModel.getRewardedVideoPlacementId() != null) {
            this.rewardedDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomRewardedActivated()) {
                this.rewardedDetails.put("ad_source", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.rewardedDetails.put("net_name", StatController.FT_NETWORK_ADMOB_CUSTOM);
                this.rewardedDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded: custom");
                return;
            }
            this.rewardedDetails.put("ad_source", StatController.FT_NETWORK_ADMOB);
            this.rewardedDetails.put("net_name", StatController.FT_NETWORK_ADMOB);
            this.rewardedDetails.put("net_name_FS", StatController.FT_NETWORK_ADMOB);
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded: platform");
            return;
        }
        Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded disabled");
    }

    public void initBannerView(final ViewGroup bannerViewContainer) {
        if (this.fastTrackSdkModel.getBannerRefreshTimer() != null) {
            this.bannerViewRefreshRate = this.fastTrackSdkModel.getBannerRefreshTimer();
        }
        if (this.fastTrackSdkModel.getBannerPlacementId() != null) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner initializing: " + this.fastTrackSdkModel.getBannerPlacementId());
            this.bannerView = new AdView(this.context);
            this.bannerView.setAdSize(AdSize.BANNER);
            this.bannerView.setAdUnitId(this.fastTrackSdkModel.getBannerPlacementId());
            this.bannerView.setAdListener(new AdListener() {
                public void onAdClosed() {
                    super.onAdClosed();
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdClosed");
                }

                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    if (i == 3) {
                        FastTrackAdmobAdapter.this.bannerDetails.put("details", "banner id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getBannerPlacementId());
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_NOFILL, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                    } else {
                        FastTrackAdmobAdapter.this.bannerDetails.put("details", "banner id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getBannerPlacementId() + "; error_desc: error code " + i);
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_ERROR, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdFailedToLoad: " + i);
                }

                public void onAdLeftApplication() {
                    super.onAdLeftApplication();
                    FastTrackAdmobAdapter.this.bannerDetails.put("details", "banner id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getBannerPlacementId());
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_CLICK, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdLeftApplication");
                }

                public void onAdOpened() {
                    super.onAdOpened();
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdOpened");
                }

                public void onAdLoaded() {
                    super.onAdLoaded();
                    if (bannerViewContainer != null) {
                        FastTrackAdmobAdapter.this.bannerDetails.put("details", "banner id: " + FastTrackAdmobAdapter.this.fastTrackSdkModel.getBannerPlacementId());
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_IMPRESSION, FastTrackAdmobAdapter.this.bannerDetails, FastTrackAdmobAdapter.this.context, true);
                        bannerViewContainer.setVisibility(0);
                    } else {
                        Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner loaded, but bannerViewContainer is null");
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdLoaded");
                }

                public void onAdClicked() {
                    super.onAdClicked();
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdClicked");
                }

                public void onAdImpression() {
                    super.onAdImpression();
                    Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner onAdImpression");
                }
            });
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner attempt to attach bannerView to container");
            this.bannerViewContainer = bannerViewContainer;
            this.bannerViewContainer.addView(this.bannerView);
            this.handler.post(this.bannerViewRefreshRunnable);
        }
    }

    public void loadFullscreen() {
        if (this.fastTrackSdkModel.getFullscreenPlacementId() != null) {
            this.interstitialAd = new InterstitialAd(this.context);
            this.interstitialAd.setAdUnitId(this.fastTrackSdkModel.getFullscreenPlacementId());
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen initializing: " + this.fastTrackSdkModel.getFullscreenPlacementId());
            this.interstitialAd.setAdListener(new C12363());
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen attempt to load");
            this.interstitialDetails.put("details", "fs id: " + this.fastTrackSdkModel.getFullscreenPlacementId());
            this.interstitialDetails.put(BrowserActivity.KEY_UNIQ_ID, GuidGenerator.generateNewGuid());
            this.interstitialAd.loadAd(new Builder().build());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_REQUEST, this.interstitialDetails, this.context, true);
        }
    }

    public void showFullscreen(String loadTag) {
        Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen show request");
        if (this.fullscreenListener != null) {
            this.fullscreenListener.onRequest();
        }
        if (this.fastTrackSdkModel.getFullscreenIntensity() != null && this.fastTrackSdkModel.getFullscreenIntensity().intValue() >= 0) {
            this.fullscreenIntensityPoints = this.fastTrackSdkModel.getFullscreenIntensity().intValue();
        }
        if (new Random().nextInt(100) + 1 > this.fullscreenIntensityPoints) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen attempt to show canceled due to intensity settings");
            if (this.fullscreenListener != null) {
                this.fullscreenListener.onFailedToShow();
            }
        } else if (this.interstitialAd == null) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen disabled");
            if (this.fullscreenListener != null) {
                this.fullscreenListener.onFailedToShow();
            }
        } else if (this.interstitialAd.isLoaded()) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen attempt to show");
            this.progressDialog.show();
            this.handler.postDelayed(FastTrackAdmobAdapter$$Lambda$3.lambdaFactory$(this), 1000);
        } else {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen not loaded yet, waiting for load");
            this.pendingFullscreenRequest = true;
            this.handler.postDelayed(this.fullscreenPendingRequestCancelRunnable, 10000);
        }
    }

    static /* synthetic */ void lambda$showFullscreen$0(FastTrackAdmobAdapter this_) {
        this_.progressDialog.dismiss();
        this_.interstitialAd.show();
    }

    static /* synthetic */ void lambda$new$1(FastTrackAdmobAdapter this_) {
        this_.pendingFullscreenRequest = false;
        if (this_.fullscreenListener != null) {
            this_.fullscreenListener.onFailedToShow();
        }
        Log.d(FastTrackAdsController.fastTrackLogTag, "admob fullscreen not loaded, cancelling wait");
    }

    public void setFullscreenListener(FullscreenListener fullscreenListener) {
        this.fullscreenListener = fullscreenListener;
    }

    public void loadRewardedVideo() {
        if (this.fastTrackSdkModel.getRewardedVideoPlacementId() != null) {
            this.rewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this.context);
            this.rewardedVideoAd.setRewardedVideoAdListener(new C12374());
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob rewarded attempt to load");
            this.rewardedDetails.put("details", "rewarded id: " + this.fastTrackSdkModel.getRewardedVideoPlacementId());
            this.rewardedDetails.put(BrowserActivity.KEY_UNIQ_ID, GuidGenerator.generateNewGuid());
            this.rewardedVideoAd.loadAd(this.fastTrackSdkModel.getRewardedVideoPlacementId(), new Builder().build());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_REQUEST, this.rewardedDetails, this.context, true);
        }
    }

    public void showRewardedVideo(RewardedVideoListener rewardedVideoListener) {
        this.rewardedVideoListener = rewardedVideoListener;
        if (this.rewardedVideoAd == null) {
            this.rewardedVideoListener.onVideoError(this.context.getResources().getString(C1195R.string.appsgeysersdk_fasttrack_no_rew_video));
            this.rewardedVideoListener = null;
        } else if (this.rewardedVideoAd.isLoaded()) {
            this.rewardedVideoAd.show();
        } else if (this.videoDownloadError) {
            this.rewardedVideoListener.onVideoError(this.context.getResources().getString(C1195R.string.appsgeysersdk_fasttrack_no_rew_video));
            this.rewardedVideoListener = null;
        } else {
            this.progressDialog.show();
            this.handler.postDelayed(this.rewardedVideoShowCancelRunnable, 10000);
        }
    }

    static /* synthetic */ void lambda$new$2(FastTrackAdmobAdapter this_) {
        this_.progressDialog.dismiss();
        this_.rewardedVideoListener.onVideoError(this_.context.getResources().getString(C1195R.string.appsgeysersdk_fasttrack_no_rew_video));
        this_.rewardedVideoListener = null;
    }

    public void onResume(Context context) {
        super.onResume(context);
        if (this.rewardedVideoAd != null) {
            this.rewardedVideoAd.resume(this.context);
        }
    }

    public void onPause() {
        if (!(this.bannerViewContainer == null || this.bannerView == null)) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "admob banner attempt to detach bannerView from container");
            this.bannerViewContainer.removeView(this.bannerView);
            this.bannerViewContainer = null;
            this.bannerView = null;
        }
        this.handler.removeCallbacks(this.bannerViewRefreshRunnable);
        if (this.rewardedVideoAd != null) {
            this.rewardedVideoAd.pause(this.context);
        }
    }

    public void onDestroy() {
        if (this.rewardedVideoAd != null) {
            this.rewardedVideoAd.destroy(this.context);
        }
    }
}
