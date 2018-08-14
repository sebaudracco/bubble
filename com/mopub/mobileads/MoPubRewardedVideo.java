package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.util.Map;

public class MoPubRewardedVideo extends MoPubRewardedAd {
    @NonNull
    static final String MOPUB_REWARDED_VIDEO_ID = "mopub_rewarded_video_id";
    @Nullable
    private RewardedVastVideoInterstitial mRewardedVastVideoInterstitial = new RewardedVastVideoInterstitial();

    @NonNull
    protected String getAdNetworkId() {
        return this.mAdUnitId != null ? this.mAdUnitId : MOPUB_REWARDED_VIDEO_ID;
    }

    protected void onInvalidate() {
        if (this.mRewardedVastVideoInterstitial != null) {
            this.mRewardedVastVideoInterstitial.onInvalidate();
        }
        this.mRewardedVastVideoInterstitial = null;
        super.onInvalidate();
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) throws Exception {
        super.loadWithSdkInitialized(activity, localExtras, serverExtras);
        if (this.mRewardedVastVideoInterstitial == null) {
            MoPubLog.w("mRewardedVastVideoInterstitial is null. Has this class been invalidated?");
        } else {
            this.mRewardedVastVideoInterstitial.loadInterstitial(activity, new MoPubRewardedVideoListener(this), localExtras, serverExtras);
        }
    }

    protected void show() {
        if (!isReady() || this.mRewardedVastVideoInterstitial == null) {
            MoPubLog.d("Unable to show MoPub rewarded video");
            return;
        }
        MoPubLog.d("Showing MoPub rewarded video.");
        this.mRewardedVastVideoInterstitial.showInterstitial();
    }

    @Deprecated
    @VisibleForTesting
    void setRewardedVastVideoInterstitial(@Nullable RewardedVastVideoInterstitial rewardedVastVideoInterstitial) {
        this.mRewardedVastVideoInterstitial = rewardedVastVideoInterstitial;
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    RewardedVastVideoInterstitial getRewardedVastVideoInterstitial() {
        return this.mRewardedVastVideoInterstitial;
    }
}
