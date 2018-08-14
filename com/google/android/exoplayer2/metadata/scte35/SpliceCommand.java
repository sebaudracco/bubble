package com.google.android.exoplayer2.metadata.scte35;

import com.google.android.exoplayer2.metadata.Metadata$Entry;

public abstract class SpliceCommand implements Metadata$Entry {
    public int describeContents() {
        return 0;
    }
}
