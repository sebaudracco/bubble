package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.text.cea.Cea608Decoder;
import com.google.android.exoplayer2.text.cea.Cea708Decoder;
import com.google.android.exoplayer2.text.dvb.DvbDecoder;
import com.google.android.exoplayer2.text.subrip.SubripDecoder;
import com.google.android.exoplayer2.text.ttml.TtmlDecoder;
import com.google.android.exoplayer2.text.tx3g.Tx3gDecoder;
import com.google.android.exoplayer2.text.webvtt.Mp4WebvttDecoder;
import com.google.android.exoplayer2.text.webvtt.WebvttDecoder;

public interface SubtitleDecoderFactory {
    public static final SubtitleDecoderFactory DEFAULT = new C27621();

    static class C27621 implements SubtitleDecoderFactory {
        C27621() {
        }

        public boolean supportsFormat(Format format) {
            String mimeType = format.sampleMimeType;
            if ("text/vtt".equals(mimeType) || "application/ttml+xml".equals(mimeType) || "application/x-mp4-vtt".equals(mimeType) || "application/x-subrip".equals(mimeType) || "application/x-quicktime-tx3g".equals(mimeType) || "application/cea-608".equals(mimeType) || "application/x-mp4-cea-608".equals(mimeType) || "application/cea-708".equals(mimeType) || "application/dvbsubs".equals(mimeType)) {
                return true;
            }
            return false;
        }

        public SubtitleDecoder createDecoder(Format format) {
            String str = format.sampleMimeType;
            Object obj = -1;
            switch (str.hashCode()) {
                case -1351681404:
                    if (str.equals("application/dvbsubs")) {
                        obj = 8;
                        break;
                    }
                    break;
                case -1026075066:
                    if (str.equals("application/x-mp4-vtt")) {
                        obj = 1;
                        break;
                    }
                    break;
                case -1004728940:
                    if (str.equals("text/vtt")) {
                        obj = null;
                        break;
                    }
                    break;
                case 691401887:
                    if (str.equals("application/x-quicktime-tx3g")) {
                        obj = 4;
                        break;
                    }
                    break;
                case 930165504:
                    if (str.equals("application/x-mp4-cea-608")) {
                        obj = 6;
                        break;
                    }
                    break;
                case 1566015601:
                    if (str.equals("application/cea-608")) {
                        obj = 5;
                        break;
                    }
                    break;
                case 1566016562:
                    if (str.equals("application/cea-708")) {
                        obj = 7;
                        break;
                    }
                    break;
                case 1668750253:
                    if (str.equals("application/x-subrip")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 1693976202:
                    if (str.equals("application/ttml+xml")) {
                        obj = 2;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return new WebvttDecoder();
                case 1:
                    return new Mp4WebvttDecoder();
                case 2:
                    return new TtmlDecoder();
                case 3:
                    return new SubripDecoder();
                case 4:
                    return new Tx3gDecoder(format.initializationData);
                case 5:
                case 6:
                    return new Cea608Decoder(format.sampleMimeType, format.accessibilityChannel);
                case 7:
                    return new Cea708Decoder(format.accessibilityChannel);
                case 8:
                    return new DvbDecoder(format.initializationData);
                default:
                    throw new IllegalArgumentException("Attempted to create decoder for unsupported format");
            }
        }
    }

    SubtitleDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}
