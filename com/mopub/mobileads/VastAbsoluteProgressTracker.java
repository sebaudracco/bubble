package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import java.io.Serializable;
import java.util.Locale;

public class VastAbsoluteProgressTracker extends VastTracker implements Comparable<VastAbsoluteProgressTracker>, Serializable {
    private static final long serialVersionUID = 0;
    private final int mTrackingMilliseconds;

    public VastAbsoluteProgressTracker(@NonNull MessageType messageType, @NonNull String content, int trackingMilliseconds) {
        super(messageType, content);
        Preconditions.checkArgument(trackingMilliseconds >= 0);
        this.mTrackingMilliseconds = trackingMilliseconds;
    }

    public VastAbsoluteProgressTracker(@NonNull String trackingUrl, int trackingMilliseconds) {
        this(MessageType.TRACKING_URL, trackingUrl, trackingMilliseconds);
    }

    public int getTrackingMilliseconds() {
        return this.mTrackingMilliseconds;
    }

    public int compareTo(@NonNull VastAbsoluteProgressTracker other) {
        return getTrackingMilliseconds() - other.getTrackingMilliseconds();
    }

    public String toString() {
        return String.format(Locale.US, "%dms: %s", new Object[]{Integer.valueOf(this.mTrackingMilliseconds), getContent()});
    }
}
