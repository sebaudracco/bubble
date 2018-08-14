package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdListener;

@zzadh
public class zzkd extends AdListener {
    private final Object lock = new Object();
    private AdListener zzasi;

    public void onAdClosed() {
        synchronized (this.lock) {
            if (this.zzasi != null) {
                this.zzasi.onAdClosed();
            }
        }
    }

    public void onAdFailedToLoad(int i) {
        synchronized (this.lock) {
            if (this.zzasi != null) {
                this.zzasi.onAdFailedToLoad(i);
            }
        }
    }

    public void onAdLeftApplication() {
        synchronized (this.lock) {
            if (this.zzasi != null) {
                this.zzasi.onAdLeftApplication();
            }
        }
    }

    public void onAdLoaded() {
        synchronized (this.lock) {
            if (this.zzasi != null) {
                this.zzasi.onAdLoaded();
            }
        }
    }

    public void onAdOpened() {
        synchronized (this.lock) {
            if (this.zzasi != null) {
                this.zzasi.onAdOpened();
            }
        }
    }

    public final void zza(AdListener adListener) {
        synchronized (this.lock) {
            this.zzasi = adListener;
        }
    }
}
