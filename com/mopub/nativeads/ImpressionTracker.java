package com.mopub.nativeads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.VisibilityTracker.VisibilityChecker;
import com.mopub.nativeads.VisibilityTracker.VisibilityTrackerListener;
import java.util.Map;
import java.util.WeakHashMap;

public class ImpressionTracker {
    private static final int PERIOD = 250;
    @NonNull
    private final Handler mPollHandler;
    @NonNull
    private final PollingRunnable mPollingRunnable;
    @NonNull
    private final Map<View, TimestampWrapper<ImpressionInterface>> mPollingViews;
    @NonNull
    private final Map<View, ImpressionInterface> mTrackedViews;
    @NonNull
    private final VisibilityChecker mVisibilityChecker;
    @NonNull
    private final VisibilityTracker mVisibilityTracker;
    @Nullable
    private VisibilityTrackerListener mVisibilityTrackerListener;

    public ImpressionTracker(@NonNull Context context) {
        this(new WeakHashMap(), new WeakHashMap(), new VisibilityChecker(), new VisibilityTracker(context), new Handler(Looper.getMainLooper()));
    }

    @VisibleForTesting
    ImpressionTracker(@NonNull Map<View, ImpressionInterface> trackedViews, @NonNull Map<View, TimestampWrapper<ImpressionInterface>> pollingViews, @NonNull VisibilityChecker visibilityChecker, @NonNull VisibilityTracker visibilityTracker, @NonNull Handler handler) {
        this.mTrackedViews = trackedViews;
        this.mPollingViews = pollingViews;
        this.mVisibilityChecker = visibilityChecker;
        this.mVisibilityTracker = visibilityTracker;
        this.mVisibilityTrackerListener = new 1(this);
        this.mVisibilityTracker.setVisibilityTrackerListener(this.mVisibilityTrackerListener);
        this.mPollHandler = handler;
        this.mPollingRunnable = new PollingRunnable(this);
    }

    public void addView(View view, @NonNull ImpressionInterface impressionInterface) {
        if (this.mTrackedViews.get(view) != impressionInterface) {
            removeView(view);
            if (!impressionInterface.isImpressionRecorded()) {
                this.mTrackedViews.put(view, impressionInterface);
                this.mVisibilityTracker.addView(view, impressionInterface.getImpressionMinPercentageViewed(), impressionInterface.getImpressionMinVisiblePx());
            }
        }
    }

    public void removeView(View view) {
        this.mTrackedViews.remove(view);
        removePollingView(view);
        this.mVisibilityTracker.removeView(view);
    }

    public void clear() {
        this.mTrackedViews.clear();
        this.mPollingViews.clear();
        this.mVisibilityTracker.clear();
        this.mPollHandler.removeMessages(0);
    }

    public void destroy() {
        clear();
        this.mVisibilityTracker.destroy();
        this.mVisibilityTrackerListener = null;
    }

    @VisibleForTesting
    void scheduleNextPoll() {
        if (!this.mPollHandler.hasMessages(0)) {
            this.mPollHandler.postDelayed(this.mPollingRunnable, 250);
        }
    }

    private void removePollingView(View view) {
        this.mPollingViews.remove(view);
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    VisibilityTrackerListener getVisibilityTrackerListener() {
        return this.mVisibilityTrackerListener;
    }
}
