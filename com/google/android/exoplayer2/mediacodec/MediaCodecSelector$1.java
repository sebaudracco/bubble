package com.google.android.exoplayer2.mediacodec;

import com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException;

class MediaCodecSelector$1 implements MediaCodecSelector {
    MediaCodecSelector$1() {
    }

    public MediaCodecInfo getDecoderInfo(String mimeType, boolean requiresSecureDecoder) throws DecoderQueryException {
        return MediaCodecUtil.getDecoderInfo(mimeType, requiresSecureDecoder);
    }

    public MediaCodecInfo getPassthroughDecoderInfo() throws DecoderQueryException {
        return MediaCodecUtil.getPassthroughDecoderInfo();
    }
}
