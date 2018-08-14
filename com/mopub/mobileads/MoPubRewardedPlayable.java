package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mraid.RewardedMraidInterstitial;
import java.util.Map;

public class MoPubRewardedPlayable extends MoPubRewardedAd {
    @NonNull
    static final String MOPUB_REWARDED_PLAYABLE_ID = "mopub_rewarded_playable_id";
    @Nullable
    private RewardedMraidInterstitial mRewardedMraidInterstitial = new RewardedMraidInterstitial();

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) throws Exception {
        super.loadWithSdkInitialized(activity, localExtras, serverExtras);
        if (this.mRewardedMraidInterstitial == null) {
            MoPubLog.w("mRewardedMraidInterstitial is null. Has this class been invalidated?");
        } else {
            this.mRewardedMraidInterstitial.loadInterstitial(activity, new MoPubRewardedPlayableListener(this), localExtras, serverExtras);
        }
    }

    @NonNull
    protected String getAdNetworkId() {
        return this.mAdUnitId != null ? this.mAdUnitId : MOPUB_REWARDED_PLAYABLE_ID;
    }

    protected void onInvalidate() {
        if (this.mRewardedMraidInterstitial != null) {
            this.mRewardedMraidInterstitial.onInvalidate();
        }
        this.mRewardedMraidInterstitial = null;
        super.onInvalidate();
    }

    protected void show() {
        if (!isReady() || this.mRewardedMraidInterstitial == null) {
            MoPubLog.d("MoPub rewarded playable not loaded. Unable to show playable.");
            return;
        }
        MoPubLog.d("Showing MoPub rewarded playable.");
        this.mRewardedMraidInterstitial.showInterstitial();
    }

    @Deprecated
    @VisibleForTesting
    void setRewardedMraidInterstitial(@NonNull RewardedMraidInterstitial rewardedMraidInterstitial) {
        this.mRewardedMraidInterstitial = rewardedMraidInterstitial;
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    RewardedMraidInterstitial getRewardedMraidInterstitial() {
        return this.mRewardedMraidInterstitial;
    }
}
