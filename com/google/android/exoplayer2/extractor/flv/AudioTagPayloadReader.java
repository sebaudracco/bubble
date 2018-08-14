package com.google.android.exoplayer2.extractor.flv;

import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.flv.TagPayloadReader.UnsupportedFormatException;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;

final class AudioTagPayloadReader extends TagPayloadReader {
    private static final int AAC_PACKET_TYPE_AAC_RAW = 1;
    private static final int AAC_PACKET_TYPE_SEQUENCE_HEADER = 0;
    private static final int AUDIO_FORMAT_AAC = 10;
    private static final int AUDIO_FORMAT_ALAW = 7;
    private static final int AUDIO_FORMAT_MP3 = 2;
    private static final int AUDIO_FORMAT_ULAW = 8;
    private static final int[] AUDIO_SAMPLING_RATE_TABLE = new int[]{5512, 11025, 22050, 44100};
    private int audioFormat;
    private boolean hasOutputFormat;
    private boolean hasParsedAudioDataHeader;

    public AudioTagPayloadReader(TrackOutput output) {
        super(output);
    }

    public void seek() {
    }

    protected boolean parseHeader(ParsableByteArray data) throws UnsupportedFormatException {
        if (this.hasParsedAudioDataHeader) {
            data.skipBytes(1);
        } else {
            int header = data.readUnsignedByte();
            this.audioFormat = (header >> 4) & 15;
            if (this.audioFormat == 2) {
                this.output.format(Format.createAudioSampleFormat(null, "audio/mpeg", null, -1, -1, 1, AUDIO_SAMPLING_RATE_TABLE[(header >> 2) & 3], null, null, 0, null));
                this.hasOutputFormat = true;
            } else if (this.audioFormat == 7 || this.audioFormat == 8) {
                this.output.format(Format.createAudioSampleFormat(null, this.audioFormat == 7 ? "audio/g711-alaw" : "audio/g711-mlaw", null, -1, -1, 1, 8000, (header & 1) == 1 ? 2 : 3, null, null, 0, null));
                this.hasOutputFormat = true;
            } else if (this.audioFormat != 10) {
                throw new UnsupportedFormatException("Audio format not supported: " + this.audioFormat);
            }
            this.hasParsedAudioDataHeader = true;
        }
        return true;
    }

    protected void parsePayload(ParsableByteArray data, long timeUs) {
        if (this.audioFormat == 2) {
            int sampleSize = data.bytesLeft();
            this.output.sampleData(data, sampleSize);
            this.output.sampleMetadata(timeUs, 1, sampleSize, 0, null);
            return;
        }
        int packetType = data.readUnsignedByte();
        if (packetType == 0 && !this.hasOutputFormat) {
            byte[] audioSpecificConfig = new byte[data.bytesLeft()];
            data.readBytes(audioSpecificConfig, 0, audioSpecificConfig.length);
            Pair<Integer, Integer> audioParams = CodecSpecificDataUtil.parseAacAudioSpecificConfig(audioSpecificConfig);
            this.output.format(Format.createAudioSampleFormat(null, "audio/mp4a-latm", null, -1, -1, ((Integer) audioParams.second).intValue(), ((Integer) audioParams.first).intValue(), Collections.singletonList(audioSpecificConfig), null, 0, null));
            this.hasOutputFormat = true;
        } else if (this.audioFormat != 10 || packetType == 1) {
            sampleSize = data.bytesLeft();
            this.output.sampleData(data, sampleSize);
            this.output.sampleMetadata(timeUs, 1, sampleSize, 0, null);
        }
    }
}
