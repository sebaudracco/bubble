package com.google.android.exoplayer2.util;

public final class TimestampAdjuster {
    public static final long DO_NOT_OFFSET = Long.MAX_VALUE;
    private static final long MAX_PTS_PLUS_ONE = 8589934592L;
    private long firstSampleTimestampUs;
    private volatile long lastSampleTimestamp = -9223372036854775807L;
    private long timestampOffsetUs;

    public TimestampAdjuster(long firstSampleTimestampUs) {
        setFirstSampleTimestampUs(firstSampleTimestampUs);
    }

    public synchronized void setFirstSampleTimestampUs(long firstSampleTimestampUs) {
        Assertions.checkState(this.lastSampleTimestamp == -9223372036854775807L);
        this.firstSampleTimestampUs = firstSampleTimestampUs;
    }

    public long getFirstSampleTimestampUs() {
        return this.firstSampleTimestampUs;
    }

    public long getLastAdjustedTimestampUs() {
        if (this.lastSampleTimestamp != -9223372036854775807L) {
            return this.lastSampleTimestamp;
        }
        return this.firstSampleTimestampUs != Long.MAX_VALUE ? this.firstSampleTimestampUs : -9223372036854775807L;
    }

    public long getTimestampOffsetUs() {
        if (this.firstSampleTimestampUs == Long.MAX_VALUE) {
            return 0;
        }
        return this.lastSampleTimestamp != -9223372036854775807L ? this.timestampOffsetUs : -9223372036854775807L;
    }

    public void reset() {
        this.lastSampleTimestamp = -9223372036854775807L;
    }

    public long adjustTsTimestamp(long pts) {
        if (pts == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (this.lastSampleTimestamp != -9223372036854775807L) {
            long lastPts = usToPts(this.lastSampleTimestamp);
            long closestWrapCount = (4294967296L + lastPts) / MAX_PTS_PLUS_ONE;
            long ptsWrapBelow = pts + (MAX_PTS_PLUS_ONE * (closestWrapCount - 1));
            long ptsWrapAbove = pts + (MAX_PTS_PLUS_ONE * closestWrapCount);
            if (Math.abs(ptsWrapBelow - lastPts) < Math.abs(ptsWrapAbove - lastPts)) {
                pts = ptsWrapBelow;
            } else {
                pts = ptsWrapAbove;
            }
        }
        return adjustSampleTimestamp(ptsToUs(pts));
    }

    public long adjustSampleTimestamp(long timeUs) {
        if (timeUs == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        if (this.lastSampleTimestamp != -9223372036854775807L) {
            this.lastSampleTimestamp = timeUs;
        } else {
            if (this.firstSampleTimestampUs != Long.MAX_VALUE) {
                this.timestampOffsetUs = this.firstSampleTimestampUs - timeUs;
            }
            synchronized (this) {
                this.lastSampleTimestamp = timeUs;
                notifyAll();
            }
        }
        return this.timestampOffsetUs + timeUs;
    }

    public synchronized void waitUntilInitialized() throws InterruptedException {
        while (this.lastSampleTimestamp == -9223372036854775807L) {
            wait();
        }
    }

    public static long ptsToUs(long pts) {
        return (1000000 * pts) / 90000;
    }

    public static long usToPts(long us) {
        return (90000 * us) / 1000000;
    }
}
