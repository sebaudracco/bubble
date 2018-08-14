package com.google.android.exoplayer2.extractor.ts;

import com.stepleaderdigital.reveal.Reveal.PDUiBeacon;
import java.util.Arrays;

final class H262Reader$CsdBuffer {
    public byte[] data;
    private boolean isFilling;
    public int length;
    public int sequenceExtensionPosition;

    public H262Reader$CsdBuffer(int initialCapacity) {
        this.data = new byte[initialCapacity];
    }

    public void reset() {
        this.isFilling = false;
        this.length = 0;
        this.sequenceExtensionPosition = 0;
    }

    public boolean onStartCode(int startCodeValue, int bytesAlreadyPassed) {
        if (this.isFilling) {
            if (this.sequenceExtensionPosition == 0 && startCodeValue == PDUiBeacon.Swirl) {
                this.sequenceExtensionPosition = this.length;
            } else {
                this.length -= bytesAlreadyPassed;
                this.isFilling = false;
                return true;
            }
        } else if (startCodeValue == 179) {
            this.isFilling = true;
        }
        return false;
    }

    public void onData(byte[] newData, int offset, int limit) {
        if (this.isFilling) {
            int readLength = limit - offset;
            if (this.data.length < this.length + readLength) {
                this.data = Arrays.copyOf(this.data, (this.length + readLength) * 2);
            }
            System.arraycopy(newData, offset, this.data, this.length, readLength);
            this.length += readLength;
        }
    }
}
