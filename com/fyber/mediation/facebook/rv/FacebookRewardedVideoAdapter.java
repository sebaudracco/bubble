package com.fyber.mediation.facebook.rv;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.fyber.ads.videos.mediation.RewardedVideoMediationAdapter;
import com.fyber.ads.videos.mediation.TPNVideoValidationResult;
import com.fyber.mediation.facebook.FacebookMediationAdapter;
import com.fyber.utils.FyberLogger;
import java.lang.ref.SoftReference;

public class FacebookRewardedVideoAdapter extends RewardedVideoMediationAdapter<FacebookMediationAdapter> implements RewardedVideoAdListener {
    private static final String TAG = FacebookRewardedVideoAdapter.class.getSimpleName();
    private SoftReference<Activity> mActivityRef;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private RewardedVideoAd mRewardedVideoAd;
    private final String mRvPlacementId;

    class ShowVideo implements Runnable {
        ShowVideo() {
        }

        public void run() {
            FacebookRewardedVideoAdapter.this.mRewardedVideoAd.show();
        }
    }

    class VideoRequest implements Runnable {
        VideoRequest() {
        }

        public void run() {
            if (FacebookRewardedVideoAdapter.this.mRewardedVideoAd == null) {
                FacebookRewardedVideoAdapter.this.mRewardedVideoAd = new RewardedVideoAd((Context) FacebookRewardedVideoAdapter.this.mActivityRef.get(), FacebookRewardedVideoAdapter.this.mRvPlacementId);
                FacebookRewardedVideoAdapter.this.mRewardedVideoAd.setAdListener(FacebookRewardedVideoAdapter.this);
            }
            FacebookRewardedVideoAdapter.this.mRewardedVideoAd.loadAd();
        }
    }

    public FacebookRewardedVideoAdapter(FacebookMediationAdapter facebookMediationAdapter, Activity activity, String placementId) {
        super(facebookMediationAdapter);
        this.mRvPlacementId = placementId;
        this.mActivityRef = new SoftReference(activity);
    }

    public void videosAvailable(Context context) {
        if (this.mRewardedVideoAd == null || !this.mRewardedVideoAd.isAdLoaded()) {
            fetchVideo();
        } else {
            sendValidationEvent(TPNVideoValidationResult.Success);
        }
    }

    public void startVideo(Activity activity) {
        FyberLogger.m8448d(TAG, "Starting video...");
        if (this.mRewardedVideoAd == null || !this.mRewardedVideoAd.isAdLoaded()) {
            FyberLogger.m8449e(TAG, "Error: Trying to show non existing or not loaded video ad.");
            notifyVideoError();
            return;
        }
        this.mHandler.post(new ShowVideo());
    }

    public void startPrecaching() {
    }

    private void fetchVideo() {
        this.mHandler.post(new VideoRequest());
    }

    public void onRewardedVideoCompleted() {
        setVideoPlayed();
    }

    public void onLoggingImpression(Ad ad) {
        notifyVideoStarted();
    }

    public void onRewardedVideoClosed() {
        notifyCloseEngagement();
        this.mRewardedVideoAd = null;
    }

    public void onError(Ad ad, AdError adError) {
        switch (adError.getErrorCode()) {
            case 1000:
                FyberLogger.m8449e(TAG, "Ad error (" + adError.getErrorCode() + "): " + adError.getErrorMessage());
                sendValidationEvent(TPNVideoValidationResult.NetworkError);
                return;
            case 1001:
                sendValidationEvent(TPNVideoValidationResult.NoVideoAvailable);
                FyberLogger.m8451i(TAG, "Callback message from Facebook (code " + adError.getErrorCode() + "): " + adError.getErrorMessage());
                return;
            case 1002:
            case 2000:
            case AdError.INTERNAL_ERROR_CODE /*2001*/:
            case AdError.MEDIATION_ERROR_CODE /*3001*/:
                FyberLogger.m8449e(TAG, "Ad error (" + adError.getErrorCode() + "): " + adError.getErrorMessage());
                sendValidationEvent(TPNVideoValidationResult.Error);
                return;
            default:
                FyberLogger.m8449e(TAG, "Unknown Ad error occurred (" + adError.getErrorCode() + "): " + adError.getErrorMessage());
                return;
        }
    }

    public void onAdLoaded(Ad ad) {
        this.mRewardedVideoAd = (RewardedVideoAd) ad;
        sendValidationEvent(TPNVideoValidationResult.Success);
    }

    public void onAdClicked(Ad ad) {
    }
}
