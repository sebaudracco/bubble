package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;

@zzadh
public final class zzalb {
    private float zzcdn = 1.0f;
    private boolean zzcdt = false;

    public static float zzay(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager == null) {
            return 0.0f;
        }
        int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        return streamMaxVolume == 0 ? 0.0f : ((float) audioManager.getStreamVolume(3)) / ((float) streamMaxVolume);
    }

    private final synchronized boolean zzrr() {
        return this.zzcdn >= 0.0f;
    }

    public final synchronized void setAppMuted(boolean z) {
        this.zzcdt = z;
    }

    public final synchronized void setAppVolume(float f) {
        this.zzcdn = f;
    }

    public final synchronized float zzdo() {
        return zzrr() ? this.zzcdn : 1.0f;
    }

    public final synchronized boolean zzdp() {
        return this.zzcdt;
    }
}
