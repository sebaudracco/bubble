package com.google.android.exoplayer2.extractor.wav;

final class WavHeader {
    private final int averageBytesPerSecond;
    private final int bitsPerSample;
    private final int blockAlignment;
    private long dataSize;
    private long dataStartPosition;
    private final int encoding;
    private final int numChannels;
    private final int sampleRateHz;

    public WavHeader(int numChannels, int sampleRateHz, int averageBytesPerSecond, int blockAlignment, int bitsPerSample, int encoding) {
        this.numChannels = numChannels;
        this.sampleRateHz = sampleRateHz;
        this.averageBytesPerSecond = averageBytesPerSecond;
        this.blockAlignment = blockAlignment;
        this.bitsPerSample = bitsPerSample;
        this.encoding = encoding;
    }

    public long getDurationUs() {
        return (1000000 * (this.dataSize / ((long) this.blockAlignment))) / ((long) this.sampleRateHz);
    }

    public int getBytesPerFrame() {
        return this.blockAlignment;
    }

    public int getBitrate() {
        return (this.sampleRateHz * this.bitsPerSample) * this.numChannels;
    }

    public int getSampleRateHz() {
        return this.sampleRateHz;
    }

    public int getNumChannels() {
        return this.numChannels;
    }

    public long getPosition(long timeUs) {
        return Math.min((((((long) this.averageBytesPerSecond) * timeUs) / 1000000) / ((long) this.blockAlignment)) * ((long) this.blockAlignment), this.dataSize - ((long) this.blockAlignment)) + this.dataStartPosition;
    }

    public long getTimeUs(long position) {
        return (1000000 * position) / ((long) this.averageBytesPerSecond);
    }

    public boolean hasDataBounds() {
        return (this.dataStartPosition == 0 || this.dataSize == 0) ? false : true;
    }

    public void setDataBounds(long dataStartPosition, long dataSize) {
        this.dataStartPosition = dataStartPosition;
        this.dataSize = dataSize;
    }

    public int getEncoding() {
        return this.encoding;
    }
}
