package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.Collections;
import java.util.List;

public interface TsPayloadReader {

    public interface Factory {
        SparseArray<TsPayloadReader> createInitialPayloadReaders();

        TsPayloadReader createPayloadReader(int i, EsInfo esInfo);
    }

    public static final class EsInfo {
        public final byte[] descriptorBytes;
        public final List<DvbSubtitleInfo> dvbSubtitleInfos;
        public final String language;
        public final int streamType;

        public EsInfo(int streamType, String language, List<DvbSubtitleInfo> dvbSubtitleInfos, byte[] descriptorBytes) {
            List emptyList;
            this.streamType = streamType;
            this.language = language;
            if (dvbSubtitleInfos == null) {
                emptyList = Collections.emptyList();
            } else {
                emptyList = Collections.unmodifiableList(dvbSubtitleInfos);
            }
            this.dvbSubtitleInfos = emptyList;
            this.descriptorBytes = descriptorBytes;
        }
    }

    void consume(ParsableByteArray parsableByteArray, boolean z);

    void init(TimestampAdjuster timestampAdjuster, ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator);

    void seek();
}
