package com.google.android.gms.internal.ads;

@zzadh
final class zzapy implements Runnable {
    private boolean zzahs = false;
    private zzapi zzdap;

    zzapy(zzapi com_google_android_gms_internal_ads_zzapi) {
        this.zzdap = com_google_android_gms_internal_ads_zzapi;
    }

    private final void zztv() {
        zzakk.zzcrm.removeCallbacks(this);
        zzakk.zzcrm.postDelayed(this, 250);
    }

    public final void pause() {
        this.zzahs = true;
    }

    public final void resume() {
        this.zzahs = false;
        zztv();
    }

    public final void run() {
        if (!this.zzahs) {
            this.zzdap.zzte();
            zztv();
        }
    }
}
