package com.google.android.exoplayer2.extractor.mp4;

interface AtomParsers$SampleSizeBox {
    int getSampleCount();

    boolean isFixedSampleSize();

    int readNextSampleSize();
}
