package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import com.appsgeyser.sdk.BrowserActivity;
import com.appsgeyser.sdk.C1195R;
import com.appsgeyser.sdk.GuidGenerator;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.FullscreenListener;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.RewardedVideoListener;
import com.appsgeyser.sdk.server.StatController;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.MoPubConversionTracker;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideos;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView.BannerAdListener;
import java.util.Random;
import java.util.Set;

public class FastTrackMopubAdapter extends FastTrackBaseAdapter {
    private MoPubView bannerView;
    private Integer bannerViewRefreshRate = Integer.valueOf(10000);
    private Runnable bannerViewRefreshRunnable = new C12452();
    private int fullscreenIntensityPoints = 100;
    private Runnable fullscreenPendingRequestCancelRunnable = FastTrackMopubAdapter$$Lambda$1.lambdaFactory$(this);
    private MoPubInterstitial interstitialAd;
    private boolean isMopubRewardedVideosActive;
    private Runnable rewardedVideoShowCancelRunnable = FastTrackMopubAdapter$$Lambda$2.lambdaFactory$(this);

    class C12452 implements Runnable {
        C12452() {
        }

        public void run() {
            if (FastTrackMopubAdapter.this.bannerView != null) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner attempt to load");
                FastTrackMopubAdapter.this.bannerDetails.put("details", "banner id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getBannerPlacementId());
                FastTrackMopubAdapter.this.bannerDetails.put(BrowserActivity.KEY_UNIQ_ID, GuidGenerator.generateNewGuid());
                FastTrackMopubAdapter.this.bannerView.loadAd();
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_REQUEST, FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                FastTrackMopubAdapter.this.handler.postDelayed(FastTrackMopubAdapter.this.bannerViewRefreshRunnable, (long) FastTrackMopubAdapter.this.bannerViewRefreshRate.intValue());
                return;
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner attempt to load failed: bannerView null");
        }
    }

    class C12463 implements InterstitialAdListener {
        C12463() {
        }

        public void onInterstitialLoaded(MoPubInterstitial interstitial) {
            if (FastTrackMopubAdapter.this.pendingFullscreenRequest) {
                FastTrackMopubAdapter.this.pendingFullscreenRequest = false;
                Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen loaded, pending request processing");
                FastTrackMopubAdapter.this.handler.removeCallbacks(FastTrackMopubAdapter.this.fullscreenPendingRequestCancelRunnable);
                FastTrackMopubAdapter.this.progressDialog.show(FastTrackMopubAdapter.this.context);
                FastTrackMopubAdapter.this.handler.postDelayed(FastTrackMopubAdapter$3$$Lambda$1.lambdaFactory$(this), 1000);
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen onInterstitialLoaded");
        }

        static /* synthetic */ void lambda$onInterstitialLoaded$0(C12463 this_) {
            FastTrackMopubAdapter.this.progressDialog.dismiss();
            FastTrackMopubAdapter.this.interstitialAd.show();
        }

        public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
            if (errorCode == MoPubErrorCode.NO_FILL) {
                FastTrackMopubAdapter.this.interstitialDetails.put("details", "fs id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getFullscreenPlacementId());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_NOFILL, FastTrackMopubAdapter.this.interstitialDetails, FastTrackMopubAdapter.this.context, true);
            } else {
                FastTrackMopubAdapter.this.interstitialDetails.put("details", "fs id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getFullscreenPlacementId() + "; error_desc: error code " + errorCode.toString());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_ERROR, FastTrackMopubAdapter.this.interstitialDetails, FastTrackMopubAdapter.this.context, true);
            }
            FastTrackMopubAdapter.this.handler.postDelayed(FastTrackMopubAdapter$3$$Lambda$4.lambdaFactory$(this), 30000);
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen onInterstitialFailed: " + errorCode.toString());
        }

        public void onInterstitialShown(MoPubInterstitial interstitial) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen onInterstitialShown");
            FastTrackMopubAdapter.this.interstitialDetails.put("details", "fs id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getFullscreenPlacementId());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_IMPRESSION, FastTrackMopubAdapter.this.interstitialDetails, FastTrackMopubAdapter.this.context, true);
            if (FastTrackMopubAdapter.this.fullscreenListener != null) {
                FastTrackMopubAdapter.this.fullscreenListener.onShow();
            }
        }

        public void onInterstitialClicked(MoPubInterstitial interstitial) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen onInterstitialClicked");
            FastTrackMopubAdapter.this.interstitialDetails.put("details", "fs id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getFullscreenPlacementId());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_CLICK, FastTrackMopubAdapter.this.interstitialDetails, FastTrackMopubAdapter.this.context, true);
        }

        public void onInterstitialDismissed(MoPubInterstitial interstitial) {
            FastTrackMopubAdapter.this.loadFullscreen();
            if (FastTrackMopubAdapter.this.progressDialog.isShowing()) {
                FastTrackMopubAdapter.this.progressDialog.dismiss();
            }
            if (FastTrackMopubAdapter.this.fullscreenListener != null) {
                FastTrackMopubAdapter.this.fullscreenListener.onClose();
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen onInterstitialDismissed");
        }
    }

    class C12474 implements MoPubRewardedVideoListener {
        C12474() {
        }

        public void onRewardedVideoLoadSuccess(@NonNull String adUnitId) {
            FastTrackMopubAdapter.this.videoDownloadError = false;
            if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                FastTrackMopubAdapter.this.showRewardedVideo(FastTrackMopubAdapter.this.rewardedVideoListener);
            }
            FastTrackMopubAdapter.this.handler.removeCallbacks(FastTrackMopubAdapter.this.rewardedVideoShowCancelRunnable);
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub rewarded onRewardedVideoLoadSuccess");
        }

        public void onRewardedVideoLoadFailure(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
            FastTrackMopubAdapter.this.videoDownloadError = true;
            if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                FastTrackMopubAdapter.this.rewardedVideoListener = null;
            }
            if (errorCode == MoPubErrorCode.NO_FILL) {
                FastTrackMopubAdapter.this.rewardedDetails.put("details", "rewarded id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getRewardedVideoPlacementId());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_NOFILL, FastTrackMopubAdapter.this.rewardedDetails, FastTrackMopubAdapter.this.context, true);
            } else {
                FastTrackMopubAdapter.this.rewardedDetails.put("details", "rewarded id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getRewardedVideoPlacementId() + "; error_desc: error code " + errorCode.toString());
                StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_ERROR, FastTrackMopubAdapter.this.rewardedDetails, FastTrackMopubAdapter.this.context, true);
            }
            FastTrackMopubAdapter.this.handler.postDelayed(FastTrackMopubAdapter$4$$Lambda$1.lambdaFactory$(this), 30000);
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub rewarded onRewardedVideoLoadFailure: " + errorCode.toString());
        }

        public void onRewardedVideoStarted(@NonNull String adUnitId) {
            FastTrackMopubAdapter.this.progressDialog.dismiss();
            FastTrackMopubAdapter.this.rewardedDetails.put("details", "rewarded id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getRewardedVideoPlacementId());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_IMPRESSION, FastTrackMopubAdapter.this.rewardedDetails, FastTrackMopubAdapter.this.context, true);
            if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                FastTrackMopubAdapter.this.rewardedVideoListener.onVideoOpened();
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub rewarded onRewardedVideoStarted");
        }

        public void onRewardedVideoPlaybackError(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
            if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                FastTrackMopubAdapter.this.rewardedVideoListener.onVideoError(FastTrackMopubAdapter.this.context.getResources().getString(C1195R.string.appsgeysersdk_fasttrack_no_rew_video));
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub rewarded onRewardedVideoPlaybackError: " + errorCode.toString());
        }

        public void onRewardedVideoClicked(@NonNull String adUnitId) {
            FastTrackMopubAdapter.this.rewardedDetails.put("details", "rewarded id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getRewardedVideoPlacementId());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_CLICK, FastTrackMopubAdapter.this.rewardedDetails, FastTrackMopubAdapter.this.context, true);
            if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                FastTrackMopubAdapter.this.rewardedVideoListener.onVideoClicked();
            }
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub rewarded onRewardedVideoClicked");
        }

        public void onRewardedVideoClosed(@NonNull String adUnitId) {
            if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                FastTrackMopubAdapter.this.rewardedVideoListener.onVideoClosed();
                FastTrackMopubAdapter.this.rewardedVideoListener = null;
            }
            FastTrackMopubAdapter.this.loadRewardedVideo();
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub rewarded onRewardedVideoClosed");
        }

        public void onRewardedVideoCompleted(@NonNull Set<String> set, @NonNull MoPubReward reward) {
            if (FastTrackMopubAdapter.this.rewardedVideoListener != null) {
                FastTrackMopubAdapter.this.rewardedVideoListener.onVideoFinished();
            }
            FastTrackMopubAdapter.this.rewardedDetails.put("details", "rewarded id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getRewardedVideoPlacementId());
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_COMPLETION, FastTrackMopubAdapter.this.rewardedDetails, FastTrackMopubAdapter.this.context, true);
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub rewarded onRewardedVideoCompleted");
        }
    }

    public FastTrackMopubAdapter(FastTrackSdkModel model, Context context) {
        super(model, context);
    }

    void init() {
        new MoPubConversionTracker().reportAppOpen(this.context);
        if (this.fastTrackSdkModel.getFullscreenPlacementId() != null) {
            this.interstitialDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomFullscreenActivated()) {
                this.interstitialDetails.put("ad_source", StatController.FT_NETWORK_MOPUB_CUSTOM);
                this.interstitialDetails.put("net_name", StatController.FT_NETWORK_MOPUB_CUSTOM);
                this.interstitialDetails.put("net_name_FS", StatController.FT_NETWORK_MOPUB_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen: custom");
            } else {
                this.interstitialDetails.put("ad_source", StatController.FT_NETWORK_MOPUB);
                this.interstitialDetails.put("net_name", StatController.FT_NETWORK_MOPUB);
                this.interstitialDetails.put("net_name_FS", StatController.FT_NETWORK_MOPUB);
                Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen: platform");
            }
        }
        if (this.fastTrackSdkModel.getBannerPlacementId() != null) {
            this.bannerDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomBannerActivated()) {
                this.bannerDetails.put("ad_source", StatController.FT_NETWORK_MOPUB_CUSTOM);
                this.bannerDetails.put("net_name", StatController.FT_NETWORK_MOPUB_CUSTOM);
                this.bannerDetails.put("net_name_FS", StatController.FT_NETWORK_MOPUB_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner: custom");
            } else {
                this.bannerDetails.put("ad_source", StatController.FT_NETWORK_MOPUB);
                this.bannerDetails.put("net_name", StatController.FT_NETWORK_MOPUB);
                this.bannerDetails.put("net_name_FS", StatController.FT_NETWORK_MOPUB);
                Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner: platform");
            }
        }
        if (this.fastTrackSdkModel.getRewardedVideoPlacementId() != null) {
            this.rewardedDetails.putAll(this.appDetails);
            if (this.fastTrackSdkModel.isCustomRewardedActivated()) {
                this.rewardedDetails.put("ad_source", StatController.FT_NETWORK_MOPUB_CUSTOM);
                this.rewardedDetails.put("net_name", StatController.FT_NETWORK_MOPUB_CUSTOM);
                this.rewardedDetails.put("net_name_FS", StatController.FT_NETWORK_MOPUB_CUSTOM);
                Log.d(FastTrackAdsController.fastTrackLogTag, "mopub rewarded: custom");
                return;
            }
            this.rewardedDetails.put("ad_source", StatController.FT_NETWORK_MOPUB);
            this.rewardedDetails.put("net_name", StatController.FT_NETWORK_MOPUB);
            this.rewardedDetails.put("net_name_FS", StatController.FT_NETWORK_MOPUB);
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub rewarded: platform");
        }
    }

    public void initBannerView(final ViewGroup bannerViewContainer) {
        if (this.fastTrackSdkModel.getBannerRefreshTimer() != null) {
            this.bannerViewRefreshRate = this.fastTrackSdkModel.getBannerRefreshTimer();
        }
        if (this.fastTrackSdkModel.getBannerPlacementId() != null) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner initializing: " + this.fastTrackSdkModel.getBannerPlacementId());
            this.bannerView = new MoPubView(this.context);
            this.bannerView.setAutorefreshEnabled(false);
            this.bannerView.setLayoutParams(new LayoutParams(-1, (int) TypedValue.applyDimension(1, 50.0f, this.context.getResources().getDisplayMetrics())));
            this.bannerView.setAdUnitId(this.fastTrackSdkModel.getBannerPlacementId());
            this.bannerView.setBannerAdListener(new BannerAdListener() {
                public void onBannerLoaded(MoPubView banner) {
                    if (bannerViewContainer != null) {
                        bannerViewContainer.setVisibility(0);
                        FastTrackMopubAdapter.this.bannerDetails.put("details", "banner id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getBannerPlacementId());
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_IMPRESSION, FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                    } else {
                        Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner loaded, but bannerViewContainer is null");
                    }
                    Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner onBannerLoaded");
                }

                public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner onBannerFailed: " + errorCode.toString());
                    if (errorCode == MoPubErrorCode.NO_FILL) {
                        FastTrackMopubAdapter.this.bannerDetails.put("details", "banner id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getBannerPlacementId());
                        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_NOFILL, FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                        return;
                    }
                    FastTrackMopubAdapter.this.bannerDetails.put("details", "banner id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getBannerPlacementId() + "; error_desc: error code " + errorCode.toString());
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_ERROR, FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                }

                public void onBannerClicked(MoPubView banner) {
                    FastTrackMopubAdapter.this.bannerDetails.put("details", "banner id: " + FastTrackMopubAdapter.this.fastTrackSdkModel.getBannerPlacementId());
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_BANNER_SDK_CLICK, FastTrackMopubAdapter.this.bannerDetails, FastTrackMopubAdapter.this.context, true);
                    Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner onBannerClicked");
                }

                public void onBannerExpanded(MoPubView banner) {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner onBannerExpanded");
                }

                public void onBannerCollapsed(MoPubView banner) {
                    Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner onBannerCollapsed");
                }
            });
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner attempt to attach bannerView to container");
            this.bannerViewContainer = bannerViewContainer;
            this.bannerViewContainer.addView(this.bannerView);
            this.handler.post(this.bannerViewRefreshRunnable);
        }
    }

    public void loadFullscreen() {
        if (this.fastTrackSdkModel.getFullscreenPlacementId() != null) {
            this.interstitialAd = new MoPubInterstitial((Activity) this.context, this.fastTrackSdkModel.getFullscreenPlacementId());
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen initializing: " + this.fastTrackSdkModel.getFullscreenPlacementId());
            this.interstitialAd.setInterstitialAdListener(new C12463());
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen attempt to load");
            this.interstitialDetails.put("details", "fs id: " + this.fastTrackSdkModel.getFullscreenPlacementId());
            this.interstitialDetails.put(BrowserActivity.KEY_UNIQ_ID, GuidGenerator.generateNewGuid());
            this.interstitialAd.load();
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_INTERSTITIAL_SDK_REQUEST, this.interstitialDetails, this.context, true);
        }
    }

    public void showFullscreen(String loadTag) {
        Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen show request");
        if (this.fullscreenListener != null) {
            this.fullscreenListener.onRequest();
        }
        if (this.fastTrackSdkModel.getFullscreenIntensity() != null && this.fastTrackSdkModel.getFullscreenIntensity().intValue() >= 0) {
            this.fullscreenIntensityPoints = this.fastTrackSdkModel.getFullscreenIntensity().intValue();
        }
        if (new Random().nextInt(100) + 1 > this.fullscreenIntensityPoints) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen attempt to show canceled due to intensity settings");
            if (this.fullscreenListener != null) {
                this.fullscreenListener.onFailedToShow();
            }
        } else if (this.interstitialAd == null) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen disabled");
            if (this.fullscreenListener != null) {
                this.fullscreenListener.onFailedToShow();
            }
        } else if (this.interstitialAd.isReady()) {
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen attempt to show");
            this.progressDialog.show(this.context);
            this.handler.postDelayed(FastTrackMopubAdapter$$Lambda$3.lambdaFactory$(this), 1000);
        } else {
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen not loaded yet, waiting for load");
            this.pendingFullscreenRequest = true;
            this.handler.postDelayed(this.fullscreenPendingRequestCancelRunnable, 10000);
        }
    }

    static /* synthetic */ void lambda$showFullscreen$0(FastTrackMopubAdapter this_) {
        this_.progressDialog.dismiss();
        this_.interstitialAd.show();
    }

    static /* synthetic */ void lambda$new$1(FastTrackMopubAdapter this_) {
        this_.pendingFullscreenRequest = false;
        if (this_.fullscreenListener != null) {
            this_.fullscreenListener.onFailedToShow();
        }
        Log.d(FastTrackAdsController.fastTrackLogTag, "mopub fullscreen not loaded, cancelling wait");
    }

    public void setFullscreenListener(FullscreenListener fullscreenListener) {
        this.fullscreenListener = fullscreenListener;
    }

    public void loadRewardedVideo() {
        if (this.fastTrackSdkModel.getRewardedVideoPlacementId() != null) {
            MoPubRewardedVideos.initializeRewardedVideo((Activity) this.context, new MediationSettings[0]);
            MoPub.onCreate((Activity) this.context);
            this.isMopubRewardedVideosActive = true;
            MoPubRewardedVideos.setRewardedVideoListener(new C12474());
            Log.d(FastTrackAdsController.fastTrackLogTag, "mopub rewarded attempt to load");
            this.rewardedDetails.put("details", "rewarded id: " + this.fastTrackSdkModel.getRewardedVideoPlacementId());
            this.rewardedDetails.put(BrowserActivity.KEY_UNIQ_ID, GuidGenerator.generateNewGuid());
            MoPubRewardedVideos.loadRewardedVideo(this.fastTrackSdkModel.getRewardedVideoPlacementId(), new MediationSettings[0]);
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_FT_REWARDED_SDK_REQUEST, this.rewardedDetails, this.context, true);
        }
    }

    public void showRewardedVideo(RewardedVideoListener rewardedVideoListener) {
        this.rewardedVideoListener = rewardedVideoListener;
        if (!this.isMopubRewardedVideosActive) {
            this.rewardedVideoListener.onVideoError(this.context.getResources().getString(C1195R.string.appsgeysersdk_fasttrack_no_rew_video));
            this.rewardedVideoListener = null;
        } else if (MoPubRewardedVideos.hasRewardedVideo(this.fastTrackSdkModel.getRewardedVideoPlacementId())) {
            MoPubRewardedVideos.showRewardedVideo(this.fastTrackSdkModel.getRewardedVideoPlacementId());
        } else if (this.videoDownloadError) {
            this.rewardedVideoListener.onVideoError(this.context.getResources().getString(C1195R.string.appsgeysersdk_fasttrack_no_rew_video));
            this.rewardedVideoListener = null;
        } else {
            this.progressDialog.show(this.context);
            this.handler.postDelayed(this.rewardedVideoShowCancelRunnable, 10000);
        }
    }

    static /* synthetic */ void lambda$new$2(FastTrackMopubAdapter this_) {
        this_.progressDialog.dismiss();
        this_.rewardedVideoListener.onVideoError(this_.context.getResources().getString(C1195R.string.appsgeysersdk_fasttrack_no_rew_video));
        this_.rewardedVideoListener = null;
    }

    public void onResume(Context context) {
        super.onResume(context);
        if (this.isMopubRewardedVideosActive) {
            MoPub.onResume((Activity) this.context);
        }
    }

    public void onPause() {
        if (this.bannerView != null) {
            if (this.bannerViewContainer != null) {
                Log.d(FastTrackAdsController.fastTrackLogTag, "mopub banner attempt to detach bannerView from container");
                this.bannerViewContainer.removeView(this.bannerView);
                this.bannerViewContainer = null;
            }
            this.bannerView.destroy();
            this.bannerView = null;
        }
        this.handler.removeCallbacks(this.bannerViewRefreshRunnable);
        if (this.isMopubRewardedVideosActive) {
            MoPub.onPause((Activity) this.context);
        }
    }

    public void onDestroy() {
        if (this.isMopubRewardedVideosActive) {
            MoPub.onDestroy((Activity) this.context);
        }
    }
}
