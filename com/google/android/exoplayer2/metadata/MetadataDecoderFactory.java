package com.google.android.exoplayer2.metadata;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.metadata.emsg.EventMessageDecoder;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.scte35.SpliceInfoDecoder;

public interface MetadataDecoderFactory {
    public static final MetadataDecoderFactory DEFAULT = new C27231();

    static class C27231 implements MetadataDecoderFactory {
        C27231() {
        }

        public boolean supportsFormat(Format format) {
            String mimeType = format.sampleMimeType;
            return "application/id3".equals(mimeType) || "application/x-emsg".equals(mimeType) || "application/x-scte35".equals(mimeType);
        }

        public MetadataDecoder createDecoder(Format format) {
            String str = format.sampleMimeType;
            Object obj = -1;
            switch (str.hashCode()) {
                case -1248341703:
                    if (str.equals("application/id3")) {
                        obj = null;
                        break;
                    }
                    break;
                case 1154383568:
                    if (str.equals("application/x-emsg")) {
                        obj = 1;
                        break;
                    }
                    break;
                case 1652648887:
                    if (str.equals("application/x-scte35")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return new Id3Decoder();
                case 1:
                    return new EventMessageDecoder();
                case 2:
                    return new SpliceInfoDecoder();
                default:
                    throw new IllegalArgumentException("Attempted to create decoder for unsupported format");
            }
        }
    }

    MetadataDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}