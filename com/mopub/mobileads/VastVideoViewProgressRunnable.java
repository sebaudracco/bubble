package com.mopub.mobileads;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;
import com.mopub.common.Preconditions;
import com.mopub.network.TrackingRequest;
import java.util.ArrayList;
import java.util.List;

public class VastVideoViewProgressRunnable extends RepeatingHandlerRunnable {
    @NonNull
    private final VastVideoConfig mVastVideoConfig;
    @NonNull
    private final VastVideoViewController mVideoViewController;

    public VastVideoViewProgressRunnable(@NonNull VastVideoViewController videoViewController, @NonNull VastVideoConfig vastVideoConfig, @NonNull Handler handler) {
        super(handler);
        Preconditions.checkNotNull(videoViewController);
        Preconditions.checkNotNull(vastVideoConfig);
        this.mVideoViewController = videoViewController;
        this.mVastVideoConfig = vastVideoConfig;
        List<VastFractionalProgressTracker> trackers = new ArrayList();
        trackers.add(new VastFractionalProgressTracker(MessageType.QUARTILE_EVENT, VideoEvent.AD_STARTED.name(), 0.0f));
        trackers.add(new VastFractionalProgressTracker(MessageType.QUARTILE_EVENT, VideoEvent.AD_IMPRESSED.name(), 0.0f));
        trackers.add(new VastFractionalProgressTracker(MessageType.QUARTILE_EVENT, VideoEvent.AD_VIDEO_FIRST_QUARTILE.name(), 0.25f));
        trackers.add(new VastFractionalProgressTracker(MessageType.QUARTILE_EVENT, VideoEvent.AD_VIDEO_MIDPOINT.name(), 0.5f));
        trackers.add(new VastFractionalProgressTracker(MessageType.QUARTILE_EVENT, VideoEvent.AD_VIDEO_THIRD_QUARTILE.name(), 0.75f));
        this.mVastVideoConfig.addFractionalTrackers(trackers);
    }

    public void doWork() {
        int videoLength = this.mVideoViewController.getDuration();
        int currentPosition = this.mVideoViewController.getCurrentPosition();
        this.mVideoViewController.updateProgressBar();
        if (videoLength > 0) {
            List<VastTracker> trackersToTrack = this.mVastVideoConfig.getUntriggeredTrackersBefore(currentPosition, videoLength);
            if (!trackersToTrack.isEmpty()) {
                List<String> trackUrls = new ArrayList();
                for (VastTracker tracker : trackersToTrack) {
                    if (tracker.getMessageType() == MessageType.TRACKING_URL) {
                        trackUrls.add(tracker.getContent());
                    } else if (tracker.getMessageType() == MessageType.QUARTILE_EVENT) {
                        this.mVideoViewController.handleViewabilityQuartileEvent(tracker.getContent());
                    }
                    tracker.setTracked();
                }
                TrackingRequest.makeTrackingHttpRequest(new VastMacroHelper(trackUrls).withAssetUri(this.mVideoViewController.getNetworkMediaFileUrl()).withContentPlayHead(Integer.valueOf(currentPosition)).getUris(), this.mVideoViewController.getContext());
            }
            this.mVideoViewController.handleIconDisplay(currentPosition);
        }
    }
}
