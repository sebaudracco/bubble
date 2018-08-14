package com.mopub.mraid;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.IntentActions;
import com.mopub.common.VisibleForTesting;
import com.mopub.mobileads.BaseBroadcastReceiver;
import com.mopub.mobileads.RewardedMraidCountdownRunnable;
import com.mopub.mobileads.VastVideoRadialCountdownWidget;

public class RewardedMraidController extends MraidController {
    @VisibleForTesting
    static final int DEFAULT_PLAYABLE_DURATION_FOR_CLOSE_BUTTON_MILLIS = 30000;
    public static final int DEFAULT_PLAYABLE_DURATION_FOR_CLOSE_BUTTON_SECONDS = 30;
    public static final boolean DEFAULT_PLAYABLE_SHOULD_REWARD_ON_CLICK = false;
    public static final int MILLIS_IN_SECOND = 1000;
    @VisibleForTesting
    static final long PLAYABLE_COUNTDOWN_UPDATE_INTERVAL_MILLIS = 250;
    private final long mBroadcastIdentifier;
    @NonNull
    private CloseableLayout mCloseableLayout;
    @NonNull
    private RewardedMraidCountdownRunnable mCountdownRunnable;
    private int mCurrentElapsedTimeMillis;
    private boolean mIsCalibrationDone;
    private boolean mIsRewarded;
    @NonNull
    private VastVideoRadialCountdownWidget mRadialCountdownWidget;
    private final int mShowCloseButtonDelay;
    private boolean mShowCloseButtonEventFired;

    @VisibleForTesting
    public RewardedMraidController(@NonNull Context context, @Nullable AdReport adReport, @NonNull PlacementType placementType, int rewardedDurationInSeconds, long broadcastIdentifier) {
        super(context, adReport, placementType);
        int rewardedDurationInMillis = rewardedDurationInSeconds * 1000;
        if (rewardedDurationInMillis < 0 || rewardedDurationInMillis > 30000) {
            this.mShowCloseButtonDelay = 30000;
        } else {
            this.mShowCloseButtonDelay = rewardedDurationInMillis;
        }
        this.mBroadcastIdentifier = broadcastIdentifier;
    }

    public void create(@NonNull Context context, CloseableLayout closeableLayout) {
        this.mCloseableLayout = closeableLayout;
        this.mCloseableLayout.setCloseAlwaysInteractable(false);
        this.mCloseableLayout.setCloseVisible(false);
        addRadialCountdownWidget(context, 4);
        this.mRadialCountdownWidget.calibrateAndMakeVisible(this.mShowCloseButtonDelay);
        this.mIsCalibrationDone = true;
        this.mCountdownRunnable = new RewardedMraidCountdownRunnable(this, new Handler(Looper.getMainLooper()));
    }

    public void pause() {
        stopRunnables();
    }

    public void resume() {
        startRunnables();
    }

    public void destroy() {
        stopRunnables();
    }

    protected void handleCustomClose(boolean useCustomClose) {
    }

    protected void handleClose() {
        if (this.mShowCloseButtonEventFired) {
            super.handleClose();
        }
    }

    public boolean backButtonEnabled() {
        return this.mShowCloseButtonEventFired;
    }

    public boolean isPlayableCloseable() {
        return !this.mShowCloseButtonEventFired && this.mCurrentElapsedTimeMillis >= this.mShowCloseButtonDelay;
    }

    public void showPlayableCloseButton() {
        this.mShowCloseButtonEventFired = true;
        this.mRadialCountdownWidget.setVisibility(8);
        this.mCloseableLayout.setCloseVisible(true);
        if (!this.mIsRewarded) {
            BaseBroadcastReceiver.broadcastAction(getContext(), this.mBroadcastIdentifier, IntentActions.ACTION_REWARDED_PLAYABLE_COMPLETE);
            this.mIsRewarded = true;
        }
    }

    public void updateCountdown(int currentElapsedTimeMillis) {
        this.mCurrentElapsedTimeMillis = currentElapsedTimeMillis;
        if (this.mIsCalibrationDone) {
            this.mRadialCountdownWidget.updateCountdownProgress(this.mShowCloseButtonDelay, this.mCurrentElapsedTimeMillis);
        }
    }

    private void startRunnables() {
        this.mCountdownRunnable.startRepeating(PLAYABLE_COUNTDOWN_UPDATE_INTERVAL_MILLIS);
    }

    private void stopRunnables() {
        this.mCountdownRunnable.stop();
    }

    private void addRadialCountdownWidget(@NonNull Context context, int initialVisibility) {
        this.mRadialCountdownWidget = new VastVideoRadialCountdownWidget(context);
        this.mRadialCountdownWidget.setVisibility(initialVisibility);
        MarginLayoutParams lp = (MarginLayoutParams) this.mRadialCountdownWidget.getLayoutParams();
        LayoutParams widgetLayoutParams = new LayoutParams((lp.width + lp.leftMargin) + lp.rightMargin, (lp.height + lp.topMargin) + lp.bottomMargin);
        widgetLayoutParams.gravity = 53;
        this.mCloseableLayout.addView(this.mRadialCountdownWidget, widgetLayoutParams);
    }

    @Deprecated
    @VisibleForTesting
    public int getShowCloseButtonDelay() {
        return this.mShowCloseButtonDelay;
    }

    @Deprecated
    @VisibleForTesting
    public VastVideoRadialCountdownWidget getRadialCountdownWidget() {
        return this.mRadialCountdownWidget;
    }

    @Deprecated
    @VisibleForTesting
    public RewardedMraidCountdownRunnable getCountdownRunnable() {
        return this.mCountdownRunnable;
    }

    @Deprecated
    @VisibleForTesting
    public boolean isCalibrationDone() {
        return this.mIsCalibrationDone;
    }

    @Deprecated
    @VisibleForTesting
    public boolean isShowCloseButtonEventFired() {
        return this.mShowCloseButtonEventFired;
    }

    @Deprecated
    @VisibleForTesting
    public boolean isRewarded() {
        return this.mIsRewarded;
    }
}
