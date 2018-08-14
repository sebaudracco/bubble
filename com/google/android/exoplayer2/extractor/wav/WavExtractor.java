package com.google.android.exoplayer2.extractor.wav;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import java.io.IOException;

public final class WavExtractor implements Extractor, SeekMap {
    public static final ExtractorsFactory FACTORY = new C27211();
    private static final int MAX_INPUT_SIZE = 32768;
    private int bytesPerFrame;
    private ExtractorOutput extractorOutput;
    private int pendingBytes;
    private TrackOutput trackOutput;
    private WavHeader wavHeader;

    static class C27211 implements ExtractorsFactory {
        C27211() {
        }

        public Extractor[] createExtractors() {
            return new Extractor[]{new WavExtractor()};
        }
    }

    public boolean sniff(ExtractorInput input) throws IOException, InterruptedException {
        return WavHeaderReader.peek(input) != null;
    }

    public void init(ExtractorOutput output) {
        this.extractorOutput = output;
        this.trackOutput = output.track(0, 1);
        this.wavHeader = null;
        output.endTracks();
    }

    public void seek(long position, long timeUs) {
        this.pendingBytes = 0;
    }

    public void release() {
    }

    public int read(ExtractorInput input, PositionHolder seekPosition) throws IOException, InterruptedException {
        if (this.wavHeader == null) {
            this.wavHeader = WavHeaderReader.peek(input);
            if (this.wavHeader == null) {
                throw new ParserException("Unsupported or unrecognized wav header.");
            }
            this.trackOutput.format(Format.createAudioSampleFormat(null, "audio/raw", null, this.wavHeader.getBitrate(), 32768, this.wavHeader.getNumChannels(), this.wavHeader.getSampleRateHz(), this.wavHeader.getEncoding(), null, null, 0, null));
            this.bytesPerFrame = this.wavHeader.getBytesPerFrame();
        }
        if (!this.wavHeader.hasDataBounds()) {
            WavHeaderReader.skipToData(input, this.wavHeader);
            this.extractorOutput.seekMap(this);
        }
        int bytesAppended = this.trackOutput.sampleData(input, 32768 - this.pendingBytes, true);
        if (bytesAppended != -1) {
            this.pendingBytes += bytesAppended;
        }
        int pendingFrames = this.pendingBytes / this.bytesPerFrame;
        if (pendingFrames > 0) {
            long timeUs = this.wavHeader.getTimeUs(input.getPosition() - ((long) this.pendingBytes));
            int size = pendingFrames * this.bytesPerFrame;
            this.pendingBytes -= size;
            this.trackOutput.sampleMetadata(timeUs, 1, size, this.pendingBytes, null);
        }
        return bytesAppended == -1 ? -1 : 0;
    }

    public long getDurationUs() {
        return this.wavHeader.getDurationUs();
    }

    public boolean isSeekable() {
        return true;
    }

    public long getPosition(long timeUs) {
        return this.wavHeader.getPosition(timeUs);
    }
}
