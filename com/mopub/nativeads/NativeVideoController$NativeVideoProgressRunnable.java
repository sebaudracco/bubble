package com.mopub.nativeads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.TextureView;
import com.google.android.exoplayer2.ExoPlayer;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.mobileads.RepeatingHandlerRunnable;
import com.mopub.mobileads.VastTracker;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.nativeads.NativeVideoController.VisibilityTrackingEvent;
import com.mopub.nativeads.VisibilityTracker.VisibilityChecker;
import com.mopub.network.TrackingRequest;
import java.util.ArrayList;
import java.util.List;

class NativeVideoController$NativeVideoProgressRunnable extends RepeatingHandlerRunnable {
    @NonNull
    private final Context mContext;
    private long mCurrentPosition;
    private long mDuration;
    @Nullable
    private ExoPlayer mExoPlayer;
    @Nullable
    private ProgressListener mProgressListener;
    private boolean mStopRequested;
    @Nullable
    private TextureView mTextureView;
    @NonNull
    private final VastVideoConfig mVastVideoConfig;
    @NonNull
    private final VisibilityChecker mVisibilityChecker;
    @NonNull
    private final List<VisibilityTrackingEvent> mVisibilityTrackingEvents;

    NativeVideoController$NativeVideoProgressRunnable(@NonNull Context context, @NonNull Handler handler, @NonNull List<VisibilityTrackingEvent> visibilityTrackingEvents, @NonNull VastVideoConfig vastVideoConfig) {
        this(context, handler, visibilityTrackingEvents, new VisibilityChecker(), vastVideoConfig);
    }

    @VisibleForTesting
    NativeVideoController$NativeVideoProgressRunnable(@NonNull Context context, @NonNull Handler handler, @NonNull List<VisibilityTrackingEvent> visibilityTrackingEvents, @NonNull VisibilityChecker visibilityChecker, @NonNull VastVideoConfig vastVideoConfig) {
        super(handler);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(handler);
        Preconditions.checkNotNull(visibilityTrackingEvents);
        Preconditions.checkNotNull(vastVideoConfig);
        this.mContext = context.getApplicationContext();
        this.mVisibilityTrackingEvents = visibilityTrackingEvents;
        this.mVisibilityChecker = visibilityChecker;
        this.mVastVideoConfig = vastVideoConfig;
        this.mDuration = -1;
        this.mStopRequested = false;
    }

    void setExoPlayer(@Nullable ExoPlayer exoPlayer) {
        this.mExoPlayer = exoPlayer;
    }

    void setTextureView(@Nullable TextureView textureView) {
        this.mTextureView = textureView;
    }

    void setProgressListener(@Nullable ProgressListener progressListener) {
        this.mProgressListener = progressListener;
    }

    void seekTo(long currentPosition) {
        this.mCurrentPosition = currentPosition;
    }

    long getCurrentPosition() {
        return this.mCurrentPosition;
    }

    long getDuration() {
        return this.mDuration;
    }

    void requestStop() {
        this.mStopRequested = true;
    }

    void checkImpressionTrackers(boolean forceTrigger) {
        int trackedCount = 0;
        for (VisibilityTrackingEvent event : this.mVisibilityTrackingEvents) {
            if (event.isTracked) {
                trackedCount++;
            } else if (forceTrigger || this.mVisibilityChecker.isVisible(this.mTextureView, this.mTextureView, event.minimumPercentageVisible, event.minimumVisiblePx)) {
                event.totalQualifiedPlayCounter = (int) (((long) event.totalQualifiedPlayCounter) + this.mUpdateIntervalMillis);
                if (forceTrigger || event.totalQualifiedPlayCounter >= event.totalRequiredPlayTimeMs) {
                    event.strategy.execute();
                    event.isTracked = true;
                    trackedCount++;
                }
            }
        }
        if (trackedCount == this.mVisibilityTrackingEvents.size() && this.mStopRequested) {
            stop();
        }
    }

    public void doWork() {
        if (this.mExoPlayer != null && this.mExoPlayer.getPlayWhenReady()) {
            this.mCurrentPosition = this.mExoPlayer.getCurrentPosition();
            this.mDuration = this.mExoPlayer.getDuration();
            checkImpressionTrackers(false);
            if (this.mProgressListener != null) {
                this.mProgressListener.updateProgress((int) ((((float) this.mCurrentPosition) / ((float) this.mDuration)) * 1000.0f));
            }
            List<VastTracker> trackers = this.mVastVideoConfig.getUntriggeredTrackersBefore((int) this.mCurrentPosition, (int) this.mDuration);
            if (!trackers.isEmpty()) {
                List<String> trackingUrls = new ArrayList();
                for (VastTracker tracker : trackers) {
                    if (!tracker.isTracked()) {
                        trackingUrls.add(tracker.getContent());
                        tracker.setTracked();
                    }
                }
                TrackingRequest.makeTrackingHttpRequest(trackingUrls, this.mContext);
            }
        }
    }

    @Deprecated
    @VisibleForTesting
    void setUpdateIntervalMillis(long updateIntervalMillis) {
        this.mUpdateIntervalMillis = updateIntervalMillis;
    }
}
