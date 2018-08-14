package com.google.android.gms.ads;

public final class VideoOptions$Builder {
    private boolean zzuz = true;
    private boolean zzva = false;
    private boolean zzvb = false;

    public final VideoOptions build() {
        return new VideoOptions(this, null);
    }

    public final VideoOptions$Builder setClickToExpandRequested(boolean z) {
        this.zzvb = z;
        return this;
    }

    public final VideoOptions$Builder setCustomControlsRequested(boolean z) {
        this.zzva = z;
        return this;
    }

    public final VideoOptions$Builder setStartMuted(boolean z) {
        this.zzuz = z;
        return this;
    }
}
