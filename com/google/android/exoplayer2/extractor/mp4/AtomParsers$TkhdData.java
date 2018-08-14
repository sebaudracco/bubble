package com.google.android.exoplayer2.extractor.mp4;

final class AtomParsers$TkhdData {
    private final long duration;
    private final int id;
    private final int rotationDegrees;

    public AtomParsers$TkhdData(int id, long duration, int rotationDegrees) {
        this.id = id;
        this.duration = duration;
        this.rotationDegrees = rotationDegrees;
    }
}
