package com.fyber.mediation.admob.rv;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.fyber.ads.videos.mediation.RewardedVideoMediationAdapter;
import com.fyber.ads.videos.mediation.TPNVideoValidationResult;
import com.fyber.mediation.admob.AdMobMediationAdapter;
import com.fyber.utils.FyberLogger;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class AdMobVideoMediationAdapter extends RewardedVideoMediationAdapter<AdMobMediationAdapter> implements RewardedVideoAdListener {
    private static final String TAG = AdMobVideoMediationAdapter.class.getSimpleName();
    private AdMobMediationAdapter adapter;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private RewardedVideoAd mRewardedVideoAd;
    private final String mRvId;

    private class VideosAvailableRunnable implements Runnable {
        Context context;

        VideosAvailableRunnable(Context context) {
            this.context = context;
        }

        public void run() {
            if (AdMobVideoMediationAdapter.this.mRewardedVideoAd == null) {
                AdMobVideoMediationAdapter.this.mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this.context);
                AdMobVideoMediationAdapter.this.mRewardedVideoAd.setRewardedVideoAdListener(AdMobVideoMediationAdapter.this);
            }
            if (AdMobVideoMediationAdapter.this.mRewardedVideoAd.isLoaded()) {
                AdMobVideoMediationAdapter.this.sendValidationEvent(TPNVideoValidationResult.Success);
            } else {
                AdMobVideoMediationAdapter.this.loadVideo();
            }
        }
    }

    public AdMobVideoMediationAdapter(AdMobMediationAdapter adapter, String rvId, Context context) {
        super(adapter);
        this.mRvId = rvId;
        this.adapter = adapter;
        if (!isPrecachingDisabled()) {
            this.mHandler.post(new VideosAvailableRunnable(context));
        }
    }

    public void videosAvailable(Context context) {
        this.mHandler.post(new VideosAvailableRunnable(context));
    }

    private void loadVideo() {
        this.mRewardedVideoAd.loadAd(this.mRvId, this.adapter.getRequestBuildHelper().generateRequest());
    }

    public void startVideo(Activity activity) {
        FyberLogger.m8451i(TAG, "starting video");
        if (this.mRewardedVideoAd == null || !this.mRewardedVideoAd.isLoaded()) {
            notifyVideoError();
        } else {
            this.mRewardedVideoAd.show();
        }
    }

    public void startPrecaching() {
    }

    public void onRewardedVideoAdLoaded() {
        FyberLogger.m8451i(TAG, "Video loaded");
        sendValidationEvent(TPNVideoValidationResult.Success);
    }

    public void onRewardedVideoAdOpened() {
        FyberLogger.m8451i(TAG, "Video opened");
    }

    public void onRewardedVideoStarted() {
        FyberLogger.m8451i(TAG, "Video started");
        notifyVideoStarted();
    }

    public void onRewardedVideoAdClosed() {
        FyberLogger.m8451i(TAG, "Video closed");
        notifyCloseEngagement();
    }

    public void onRewarded(RewardItem rewardItem) {
        FyberLogger.m8451i(TAG, "Reward: " + rewardItem.toString());
        setVideoPlayed();
    }

    public void onRewardedVideoAdLeftApplication() {
    }

    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        switch (errorCode) {
            case 0:
                FyberLogger.m8449e(TAG, "Ad request failed due to internal error.");
                sendValidationEvent(TPNVideoValidationResult.Error);
                return;
            case 1:
                FyberLogger.m8449e(TAG, "Ad request failed due to invalid request.");
                sendValidationEvent(TPNVideoValidationResult.Error);
                return;
            case 2:
                FyberLogger.m8449e(TAG, "Ad request failed due to network error.");
                sendValidationEvent(TPNVideoValidationResult.NetworkError);
                return;
            case 3:
                FyberLogger.m8449e(TAG, "Ad request failed due to code not filled error.");
                sendValidationEvent(TPNVideoValidationResult.NoVideoAvailable);
                return;
            default:
                return;
        }
    }

    public void onRewardedVideoCompleted() {
    }
}
