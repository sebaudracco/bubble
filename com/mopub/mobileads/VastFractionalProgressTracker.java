package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import java.io.Serializable;
import java.util.Locale;

public class VastFractionalProgressTracker extends VastTracker implements Comparable<VastFractionalProgressTracker>, Serializable {
    private static final long serialVersionUID = 0;
    private final float mFraction;

    public VastFractionalProgressTracker(@NonNull MessageType messageType, @NonNull String content, float trackingFraction) {
        super(messageType, content);
        Preconditions.checkArgument(trackingFraction >= 0.0f);
        this.mFraction = trackingFraction;
    }

    public VastFractionalProgressTracker(@NonNull String trackingUrl, float trackingFraction) {
        this(MessageType.TRACKING_URL, trackingUrl, trackingFraction);
    }

    public float trackingFraction() {
        return this.mFraction;
    }

    public int compareTo(@NonNull VastFractionalProgressTracker other) {
        return Double.compare((double) trackingFraction(), (double) other.trackingFraction());
    }

    public String toString() {
        return String.format(Locale.US, "%2f: %s", new Object[]{Float.valueOf(this.mFraction), getContent()});
    }
}
