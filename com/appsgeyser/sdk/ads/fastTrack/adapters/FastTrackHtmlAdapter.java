package com.appsgeyser.sdk.ads.fastTrack.adapters;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.ads.AdView;
import com.appsgeyser.sdk.ads.FullScreenBanner;
import com.appsgeyser.sdk.ads.IFullScreenBannerListener;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackSdkModel;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.FullscreenListener;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter.RewardedVideoListener;
import com.appsgeyser.sdk.ads.rewardedVideo.rewardedFacades.RewardedVideoFacade;

public class FastTrackHtmlAdapter extends FastTrackBaseAdapter {
    private AdView adView;

    public FastTrackHtmlAdapter(FastTrackSdkModel model, Context context) {
        super(model, context);
    }

    void init() {
    }

    public void initBannerView(ViewGroup bannerViewContainer) {
        if (bannerViewContainer != null) {
            this.adView = new AdView(this.context);
            LayoutParams params = new LayoutParams(-1, (int) Math.ceil((double) (50.0f * this.context.getResources().getDisplayMetrics().density)));
            params.addRule(13, -1);
            this.adView.setLayoutParams(params);
            this.bannerViewContainer = bannerViewContainer;
            this.bannerViewContainer.addView(this.adView);
            AppsgeyserSDK.setAdView(this.adView);
            this.bannerViewContainer.setVisibility(0);
            this.adView.onResume();
        }
    }

    public void loadFullscreen() {
    }

    public void showFullscreen(String loadTag) {
        InternalEntryPoint.getInstance().getFullScreenBanner(this.context).load(loadTag);
    }

    public void setFullscreenListener(final FullscreenListener fullscreenListener) {
        AppsgeyserSDK.getFullScreenBanner(this.context).setListener(new IFullScreenBannerListener() {
            public void onLoadStarted() {
                if (fullscreenListener != null) {
                    fullscreenListener.onRequest();
                }
            }

            public void onLoadFinished(FullScreenBanner banner) {
                if (fullscreenListener != null) {
                    fullscreenListener.onShow();
                }
            }

            public void onAdFailedToLoad(Context context, String tag) {
                if (fullscreenListener != null) {
                    fullscreenListener.onFailedToShow();
                }
            }

            public void onAdHided(Context context, String tag) {
                if (fullscreenListener != null) {
                    fullscreenListener.onClose();
                }
            }
        });
    }

    public void loadRewardedVideo() {
    }

    public void showRewardedVideo(final RewardedVideoListener rewardedVideoListener) {
        AppsgeyserSDK.loadRewardedVideo(new RewardedVideoFacade.RewardedVideoListener() {
            public void onVideoLoaded() {
                AppsgeyserSDK.showRewardedVideo();
            }

            public void onVideoOpened() {
                rewardedVideoListener.onVideoOpened();
            }

            public void onVideoClicked() {
                rewardedVideoListener.onVideoClicked();
            }

            public void onVideoClosed() {
                rewardedVideoListener.onVideoClosed();
            }

            public void onVideoError(String s) {
                rewardedVideoListener.onVideoError(s);
            }

            public void onVideoFinished() {
                rewardedVideoListener.onVideoFinished();
            }
        });
    }

    public void onResume(Context context) {
        super.onResume(context);
    }

    public void onPause() {
        if (this.bannerViewContainer != null) {
            this.bannerViewContainer.removeView(this.adView);
            this.bannerViewContainer.setVisibility(8);
            this.bannerViewContainer = null;
            AppsgeyserSDK.setAdView(null);
            this.adView.onPause();
        }
    }

    public void onDestroy() {
    }
}
