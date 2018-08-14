package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import java.io.Serializable;

public class VastTracker implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean mCalled;
    @NonNull
    private final String mContent;
    private boolean mIsRepeatable;
    @NonNull
    private final MessageType mMessageType;

    enum MessageType {
        TRACKING_URL,
        QUARTILE_EVENT
    }

    public VastTracker(@NonNull MessageType messageType, @NonNull String content) {
        Preconditions.checkNotNull(messageType);
        Preconditions.checkNotNull(content);
        this.mMessageType = messageType;
        this.mContent = content;
    }

    public VastTracker(@NonNull String trackingUrl) {
        this(MessageType.TRACKING_URL, trackingUrl);
    }

    public VastTracker(@NonNull String trackingUrl, boolean isRepeatable) {
        this(trackingUrl);
        this.mIsRepeatable = isRepeatable;
    }

    @NonNull
    public MessageType getMessageType() {
        return this.mMessageType;
    }

    @NonNull
    public String getContent() {
        return this.mContent;
    }

    public void setTracked() {
        this.mCalled = true;
    }

    public boolean isTracked() {
        return this.mCalled;
    }

    public boolean isRepeatable() {
        return this.mIsRepeatable;
    }
}
