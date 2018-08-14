package com.google.android.exoplayer2.decoder;

import android.annotation.TargetApi;
import android.media.MediaCodec.CryptoInfo;
import android.media.MediaCodec.CryptoInfo.Pattern;

@TargetApi(24)
final class CryptoInfo$PatternHolderV24 {
    private final CryptoInfo frameworkCryptoInfo;
    private final Pattern pattern;

    private CryptoInfo$PatternHolderV24(CryptoInfo frameworkCryptoInfo) {
        this.frameworkCryptoInfo = frameworkCryptoInfo;
        this.pattern = new Pattern(0, 0);
    }

    private void set(int blocksToEncrypt, int blocksToSkip) {
        this.pattern.set(blocksToEncrypt, blocksToSkip);
        this.frameworkCryptoInfo.setPattern(this.pattern);
    }
}
