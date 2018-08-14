package com.google.android.exoplayer2.source.hls.playlist;

import com.google.android.exoplayer2.Format;
import java.util.Collections;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public final class HlsMasterPlaylist extends HlsPlaylist {
    public final List<HlsUrl> audios;
    public final Format muxedAudioFormat;
    public final List<Format> muxedCaptionFormats;
    public final List<HlsUrl> subtitles;
    public final List<HlsUrl> variants;

    public static final class HlsUrl {
        public final Format format;
        public final String url;

        public static HlsUrl createMediaPlaylistHlsUrl(String url) {
            return new HlsUrl(url, Format.createContainerFormat(SchemaSymbols.ATTVAL_FALSE_0, "application/x-mpegURL", null, null, -1, 0, null));
        }

        public HlsUrl(String url, Format format) {
            this.url = url;
            this.format = format;
        }
    }

    public HlsMasterPlaylist(String baseUri, List<HlsUrl> variants, List<HlsUrl> audios, List<HlsUrl> subtitles, Format muxedAudioFormat, List<Format> muxedCaptionFormats) {
        super(baseUri);
        this.variants = Collections.unmodifiableList(variants);
        this.audios = Collections.unmodifiableList(audios);
        this.subtitles = Collections.unmodifiableList(subtitles);
        this.muxedAudioFormat = muxedAudioFormat;
        this.muxedCaptionFormats = muxedCaptionFormats != null ? Collections.unmodifiableList(muxedCaptionFormats) : null;
    }

    public static HlsMasterPlaylist createSingleVariantMasterPlaylist(String variantUrl) {
        List<HlsUrl> variant = Collections.singletonList(HlsUrl.createMediaPlaylistHlsUrl(variantUrl));
        List<HlsUrl> emptyList = Collections.emptyList();
        return new HlsMasterPlaylist(null, variant, emptyList, emptyList, null, null);
    }
}
