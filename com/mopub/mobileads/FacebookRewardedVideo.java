package com.mopub.mobileads;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSettings;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import java.util.Map;

public class FacebookRewardedVideo extends CustomEventRewardedVideo implements RewardedVideoAdListener {
    private static final int ONE_HOURS_MILLIS = 3600000;
    private Runnable mAdExpiration = new C36521();
    @NonNull
    private Handler mHandler = new Handler();
    @Nullable
    private String mPlacementId;
    @Nullable
    private RewardedVideoAd mRewardedVideoAd;

    class C36521 implements Runnable {
        C36521() {
        }

        public void run() {
            MoPubLog.m12061d("Expiring unused Facebook Rewarded Video ad due to Facebook's 60-minute expiration policy.");
            MoPubRewardedVideoManager.onRewardedVideoLoadFailure(FacebookRewardedVideo.class, FacebookRewardedVideo.this.mPlacementId, MoPubErrorCode.EXPIRED);
            FacebookRewardedVideo.this.onInvalidate();
        }
    }

    @Nullable
    protected LifecycleListener getLifecycleListener() {
        return null;
    }

    protected boolean checkAndInitializeSdk(@NonNull Activity launcherActivity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        return false;
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> serverExtras) throws Exception {
        if (!serverExtras.isEmpty()) {
            this.mPlacementId = (String) serverExtras.get("placement_id");
            if (TextUtils.isEmpty(this.mPlacementId)) {
                MoPubRewardedVideoManager.onRewardedVideoLoadFailure(FacebookRewardedVideo.class, getAdNetworkId(), MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
                MoPubLog.m12061d(MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR.toString());
                MoPubLog.m12061d("Placement ID is null or empty.");
                return;
            }
            if (this.mRewardedVideoAd != null) {
                this.mRewardedVideoAd.destroy();
                this.mRewardedVideoAd = null;
            }
            MoPubLog.m12061d("Creating a Facebook Rewarded Video instance, and registering callbacks.");
            this.mRewardedVideoAd = new RewardedVideoAd(activity, this.mPlacementId);
            this.mRewardedVideoAd.setAdListener(this);
        }
        if (this.mRewardedVideoAd.isAdLoaded()) {
            MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(FacebookRewardedVideo.class, this.mPlacementId);
        } else if (this.mRewardedVideoAd != null) {
            MoPubLog.m12061d("Sending Facebook an ad request.");
            AdSettings.setMediationService("MOPUB_4.20.0");
            this.mRewardedVideoAd.loadAd();
        }
    }

    @NonNull
    protected String getAdNetworkId() {
        return this.mRewardedVideoAd != null ? this.mRewardedVideoAd.getPlacementId() : "";
    }

    protected void onInvalidate() {
        cancelExpirationTimer();
        if (this.mRewardedVideoAd != null) {
            MoPubLog.m12061d("Performing cleanup tasks...");
            this.mRewardedVideoAd.setAdListener(null);
            this.mRewardedVideoAd.destroy();
            this.mRewardedVideoAd = null;
        }
    }

    protected boolean hasVideoAvailable() {
        return this.mRewardedVideoAd != null && this.mRewardedVideoAd.isAdLoaded();
    }

    protected void showVideo() {
        if (hasVideoAvailable()) {
            MoPubLog.m12061d("Facebook Rewarded Video creative is available. Showing...");
            this.mRewardedVideoAd.show();
            return;
        }
        MoPubRewardedVideoManager.onRewardedVideoLoadFailure(FacebookRewardedVideo.class, this.mPlacementId, MoPubErrorCode.VIDEO_NOT_AVAILABLE);
        MoPubLog.m12061d("Facebook Rewarded Video creative is not available. Try re-requesting.");
    }

    public void onRewardedVideoCompleted() {
        MoPubLog.m12061d("Facebook Rewarded Video creative is completed. Awarding the user.");
        MoPubRewardedVideoManager.onRewardedVideoCompleted(FacebookRewardedVideo.class, this.mPlacementId, MoPubReward.success("", 0));
    }

    public void onLoggingImpression(Ad ad) {
        cancelExpirationTimer();
        MoPubRewardedVideoManager.onRewardedVideoStarted(FacebookRewardedVideo.class, this.mPlacementId);
        MoPubLog.m12061d("Facebook Rewarded Video creative started playing.");
    }

    public void onRewardedVideoClosed() {
        MoPubRewardedVideoManager.onRewardedVideoClosed(FacebookRewardedVideo.class, this.mPlacementId);
        MoPubLog.m12061d("Facebook Rewarded Video creative closed.");
    }

    public void onAdLoaded(Ad ad) {
        cancelExpirationTimer();
        this.mHandler.postDelayed(this.mAdExpiration, 3600000);
        MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(FacebookRewardedVideo.class, this.mPlacementId);
        MoPubLog.m12061d("Facebook Rewarded Video creative cached.");
    }

    public void onAdClicked(Ad ad) {
        MoPubRewardedVideoManager.onRewardedVideoClicked(FacebookRewardedVideo.class, this.mPlacementId);
        MoPubLog.m12061d("Facebook Rewarded Video creative clicked.");
    }

    public void onError(Ad ad, AdError adError) {
        cancelExpirationTimer();
        MoPubRewardedVideoManager.onRewardedVideoLoadFailure(FacebookRewardedVideo.class, this.mPlacementId, mapErrorCode(adError.getErrorCode()));
        MoPubLog.m12061d("Loading/Playing Facebook Rewarded Video creative encountered an error: " + mapErrorCode(adError.getErrorCode()).toString());
    }

    @NonNull
    private static MoPubErrorCode mapErrorCode(int error) {
        switch (error) {
            case 1000:
                return MoPubErrorCode.NO_CONNECTION;
            case 1001:
                return MoPubErrorCode.NETWORK_NO_FILL;
            case AdError.INTERNAL_ERROR_CODE /*2001*/:
                return MoPubErrorCode.INTERNAL_ERROR;
            default:
                return MoPubErrorCode.UNSPECIFIED;
        }
    }

    private void cancelExpirationTimer() {
        this.mHandler.removeCallbacks(this.mAdExpiration);
    }
}
