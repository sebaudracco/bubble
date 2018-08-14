package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.DataKeys;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.mraid.MraidWebViewDebugListener;
import com.mopub.mraid.PlacementType;
import com.mopub.mraid.RewardedMraidController;

public class RewardedMraidActivity extends MraidActivity {
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    @Nullable
    private RewardedMraidController mRewardedMraidController;

    public static void start(@NonNull Context context, @Nullable AdReport adreport, @Nullable String htmlData, long broadcastIdentifier, int rewardedDuration, boolean shouldRewardOnClick) {
        try {
            Intents.startActivity(context, createIntent(context, adreport, htmlData, broadcastIdentifier, rewardedDuration, shouldRewardOnClick));
        } catch (IntentNotResolvableException e) {
            Log.d("RewardedMraidActivity", "RewardedMraidActivity.class not found. Did you declare RewardedMraidActivity in your manifest?");
        }
    }

    @VisibleForTesting
    protected static Intent createIntent(@NonNull Context context, @Nullable AdReport adReport, @Nullable String htmlData, long broadcastIdentifier, int rewardedDuration, boolean shouldRewardOnClick) {
        Intent intent = new Intent(context, RewardedMraidActivity.class);
        intent.putExtra(DataKeys.HTML_RESPONSE_BODY_KEY, htmlData);
        intent.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, broadcastIdentifier);
        intent.putExtra(DataKeys.AD_REPORT_KEY, adReport);
        intent.putExtra(DataKeys.REWARDED_AD_DURATION_KEY, rewardedDuration);
        intent.putExtra(DataKeys.SHOULD_REWARD_ON_CLICK_KEY, shouldRewardOnClick);
        return intent;
    }

    public View getAdView() {
        Intent intent = getIntent();
        String htmlData = intent.getStringExtra(DataKeys.HTML_RESPONSE_BODY_KEY);
        if (TextUtils.isEmpty(htmlData)) {
            MoPubLog.w("RewardedMraidActivity received a null HTML body. Finishing the activity.");
            finish();
            return new View(this);
        } else if (getBroadcastIdentifier() == null) {
            MoPubLog.w("RewardedMraidActivity received a null broadcast id. Finishing the activity.");
            finish();
            return new View(this);
        } else {
            int rewardedDurationInSeconds = intent.getIntExtra(DataKeys.REWARDED_AD_DURATION_KEY, 30);
            boolean shouldRewardOnClick = intent.getBooleanExtra(DataKeys.SHOULD_REWARD_ON_CLICK_KEY, false);
            this.mRewardedMraidController = new RewardedMraidController(this, this.mAdReport, PlacementType.INTERSTITIAL, rewardedDurationInSeconds, getBroadcastIdentifier().longValue());
            this.mRewardedMraidController.setDebugListener(this.mDebugListener);
            this.mRewardedMraidController.setMraidListener(new 1(this, shouldRewardOnClick));
            this.mRewardedMraidController.fillContent(getBroadcastIdentifier(), htmlData, null);
            return this.mRewardedMraidController.getAdContainer();
        }
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mRewardedMraidController != null) {
            this.mRewardedMraidController.create(this, getCloseableLayout());
        }
    }

    protected void onPause() {
        if (this.mRewardedMraidController != null) {
            this.mRewardedMraidController.pause();
        }
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        if (this.mRewardedMraidController != null) {
            this.mRewardedMraidController.resume();
        }
    }

    protected void onDestroy() {
        if (this.mRewardedMraidController != null) {
            this.mRewardedMraidController.destroy();
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        if (this.mRewardedMraidController == null || this.mRewardedMraidController.backButtonEnabled()) {
            super.onBackPressed();
        }
    }

    @VisibleForTesting
    public void setDebugListener(@Nullable MraidWebViewDebugListener debugListener) {
        this.mDebugListener = debugListener;
        if (this.mRewardedMraidController != null) {
            this.mRewardedMraidController.setDebugListener(debugListener);
        }
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    public RewardedMraidController getRewardedMraidController() {
        return this.mRewardedMraidController;
    }
}
