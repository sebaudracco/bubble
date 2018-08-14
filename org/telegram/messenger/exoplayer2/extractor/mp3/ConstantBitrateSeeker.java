package org.telegram.messenger.exoplayer2.extractor.mp3;

import org.telegram.messenger.exoplayer2.util.Util;

final class ConstantBitrateSeeker implements Seeker {
    private static final int BITS_PER_BYTE = 8;
    private final int bitrate;
    private final long durationUs;
    private final long firstFramePosition;

    public ConstantBitrateSeeker(long firstFramePosition, int bitrate, long inputLength) {
        this.firstFramePosition = firstFramePosition;
        this.bitrate = bitrate;
        this.durationUs = inputLength == -1 ? -9223372036854775807L : getTimeUs(inputLength);
    }

    public boolean isSeekable() {
        return this.durationUs != -9223372036854775807L;
    }

    public long getPosition(long timeUs) {
        if (this.durationUs == -9223372036854775807L) {
            return 0;
        }
        timeUs = Util.constrainValue(timeUs, 0, this.durationUs);
        return ((((long) this.bitrate) * timeUs) / 8000000) + this.firstFramePosition;
    }

    public long getTimeUs(long position) {
        return ((Math.max(0, position - this.firstFramePosition) * 1000000) * 8) / ((long) this.bitrate);
    }

    public long getDurationUs() {
        return this.durationUs;
    }
}
