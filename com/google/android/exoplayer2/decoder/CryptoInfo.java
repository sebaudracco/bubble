package com.google.android.exoplayer2.decoder;

import android.annotation.TargetApi;
import com.google.android.exoplayer2.util.Util;

public final class CryptoInfo {
    private final android.media.MediaCodec.CryptoInfo frameworkCryptoInfo;
    public byte[] iv;
    public byte[] key;
    public int mode;
    public int[] numBytesOfClearData;
    public int[] numBytesOfEncryptedData;
    public int numSubSamples;
    public int patternBlocksToEncrypt;
    public int patternBlocksToSkip;
    private final PatternHolderV24 patternHolder;

    public CryptoInfo() {
        android.media.MediaCodec.CryptoInfo newFrameworkCryptoInfoV16;
        PatternHolderV24 patternHolderV24 = null;
        if (Util.SDK_INT >= 16) {
            newFrameworkCryptoInfoV16 = newFrameworkCryptoInfoV16();
        } else {
            newFrameworkCryptoInfoV16 = null;
        }
        this.frameworkCryptoInfo = newFrameworkCryptoInfoV16;
        if (Util.SDK_INT >= 24) {
            patternHolderV24 = new PatternHolderV24(this.frameworkCryptoInfo, null);
        }
        this.patternHolder = patternHolderV24;
    }

    public void set(int numSubSamples, int[] numBytesOfClearData, int[] numBytesOfEncryptedData, byte[] key, byte[] iv, int mode) {
        this.numSubSamples = numSubSamples;
        this.numBytesOfClearData = numBytesOfClearData;
        this.numBytesOfEncryptedData = numBytesOfEncryptedData;
        this.key = key;
        this.iv = iv;
        this.mode = mode;
        this.patternBlocksToEncrypt = 0;
        this.patternBlocksToSkip = 0;
        if (Util.SDK_INT >= 16) {
            updateFrameworkCryptoInfoV16();
        }
    }

    public void setPattern(int patternBlocksToEncrypt, int patternBlocksToSkip) {
        this.patternBlocksToEncrypt = patternBlocksToEncrypt;
        this.patternBlocksToSkip = patternBlocksToSkip;
        if (Util.SDK_INT >= 24) {
            PatternHolderV24.access$100(this.patternHolder, patternBlocksToEncrypt, patternBlocksToSkip);
        }
    }

    @TargetApi(16)
    public android.media.MediaCodec.CryptoInfo getFrameworkCryptoInfoV16() {
        return this.frameworkCryptoInfo;
    }

    @TargetApi(16)
    private android.media.MediaCodec.CryptoInfo newFrameworkCryptoInfoV16() {
        return new android.media.MediaCodec.CryptoInfo();
    }

    @TargetApi(16)
    private void updateFrameworkCryptoInfoV16() {
        this.frameworkCryptoInfo.numSubSamples = this.numSubSamples;
        this.frameworkCryptoInfo.numBytesOfClearData = this.numBytesOfClearData;
        this.frameworkCryptoInfo.numBytesOfEncryptedData = this.numBytesOfEncryptedData;
        this.frameworkCryptoInfo.key = this.key;
        this.frameworkCryptoInfo.iv = this.iv;
        this.frameworkCryptoInfo.mode = this.mode;
        if (Util.SDK_INT >= 24) {
            PatternHolderV24.access$100(this.patternHolder, this.patternBlocksToEncrypt, this.patternBlocksToSkip);
        }
    }
}
