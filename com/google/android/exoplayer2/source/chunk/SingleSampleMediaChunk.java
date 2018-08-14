package com.google.android.exoplayer2.source.chunk;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

public final class SingleSampleMediaChunk extends BaseMediaChunk {
    private volatile int bytesLoaded;
    private volatile boolean loadCanceled;
    private volatile boolean loadCompleted;
    private final Format sampleFormat;
    private final int trackType;

    public SingleSampleMediaChunk(DataSource dataSource, DataSpec dataSpec, Format trackFormat, int trackSelectionReason, Object trackSelectionData, long startTimeUs, long endTimeUs, int chunkIndex, int trackType, Format sampleFormat) {
        super(dataSource, dataSpec, trackFormat, trackSelectionReason, trackSelectionData, startTimeUs, endTimeUs, chunkIndex);
        this.trackType = trackType;
        this.sampleFormat = sampleFormat;
    }

    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    public long bytesLoaded() {
        return (long) this.bytesLoaded;
    }

    public void cancelLoad() {
        this.loadCanceled = true;
    }

    public boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    public void load() throws IOException, InterruptedException {
        try {
            long length = this.dataSource.open(Util.getRemainderDataSpec(this.dataSpec, this.bytesLoaded));
            if (length != -1) {
                length += (long) this.bytesLoaded;
            }
            ExtractorInput extractorInput = new DefaultExtractorInput(this.dataSource, (long) this.bytesLoaded, length);
            BaseMediaChunkOutput output = getOutput();
            output.setSampleOffsetUs(0);
            TrackOutput trackOutput = output.track(0, this.trackType);
            trackOutput.format(this.sampleFormat);
            for (int result = 0; result != -1; result = trackOutput.sampleData(extractorInput, Integer.MAX_VALUE, true)) {
                this.bytesLoaded += result;
            }
            trackOutput.sampleMetadata(this.startTimeUs, 1, this.bytesLoaded, 0, null);
            this.loadCompleted = true;
        } finally {
            Util.closeQuietly(this.dataSource);
        }
    }
}
