package com.google.android.exoplayer2;

public final class Timeline$Period {
    public long durationUs;
    public Object id;
    public boolean isAd;
    private long positionInWindowUs;
    public Object uid;
    public int windowIndex;

    public Timeline$Period set(Object id, Object uid, int windowIndex, long durationUs, long positionInWindowUs, boolean isAd) {
        this.id = id;
        this.uid = uid;
        this.windowIndex = windowIndex;
        this.durationUs = durationUs;
        this.positionInWindowUs = positionInWindowUs;
        this.isAd = isAd;
        return this;
    }

    public long getDurationMs() {
        return C.usToMs(this.durationUs);
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public long getPositionInWindowMs() {
        return C.usToMs(this.positionInWindowUs);
    }

    public long getPositionInWindowUs() {
        return this.positionInWindowUs;
    }
}
