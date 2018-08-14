package com.google.android.gms.internal.ads;

final class zzgi implements Runnable {
    private final /* synthetic */ zzgh zzahx;

    zzgi(zzgh com_google_android_gms_internal_ads_zzgh) {
        this.zzahx = com_google_android_gms_internal_ads_zzgh;
    }

    public final void run() {
        synchronized (this.zzahx.mLock) {
            if (this.zzahx.zzahr && this.zzahx.zzahs) {
                this.zzahx.zzahr = false;
                zzakb.zzck("App went background");
                for (zzgj zzh : this.zzahx.zzaht) {
                    try {
                        zzh.zzh(false);
                    } catch (Throwable e) {
                        zzane.zzb("", e);
                    }
                }
            } else {
                zzakb.zzck("App is still foreground");
            }
        }
    }
}
