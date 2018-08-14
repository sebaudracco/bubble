package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;

@zzadh
@TargetApi(14)
public final class zzapz implements OnAudioFocusChangeListener {
    private final AudioManager mAudioManager;
    private boolean zzcxs;
    private final zzaqa zzdaq;
    private boolean zzdar;
    private boolean zzdas;
    private float zzdat = 1.0f;

    public zzapz(Context context, zzaqa com_google_android_gms_internal_ads_zzaqa) {
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.zzdaq = com_google_android_gms_internal_ads_zzaqa;
    }

    private final void zztw() {
        boolean z = true;
        boolean z2 = this.zzcxs && !this.zzdas && this.zzdat > 0.0f;
        if (z2 && !this.zzdar) {
            if (!(this.mAudioManager == null || this.zzdar)) {
                if (this.mAudioManager.requestAudioFocus(this, 3, 2) != 1) {
                    z = false;
                }
                this.zzdar = z;
            }
            this.zzdaq.zzst();
        } else if (!z2 && this.zzdar) {
            if (this.mAudioManager != null && this.zzdar) {
                if (this.mAudioManager.abandonAudioFocus(this) != 0) {
                    z = false;
                }
                this.zzdar = z;
            }
            this.zzdaq.zzst();
        }
    }

    public final float getVolume() {
        return this.zzdar ? this.zzdas ? 0.0f : this.zzdat : 0.0f;
    }

    public final void onAudioFocusChange(int i) {
        this.zzdar = i > 0;
        this.zzdaq.zzst();
    }

    public final void setMuted(boolean z) {
        this.zzdas = z;
        zztw();
    }

    public final void setVolume(float f) {
        this.zzdat = f;
        zztw();
    }

    public final void zztt() {
        this.zzcxs = true;
        zztw();
    }

    public final void zztu() {
        this.zzcxs = false;
        zztw();
    }
}
