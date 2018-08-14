package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

@zzadh
@ParametersAreNonnullByDefault
public final class zzxa extends zzxu {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private zzxf zzbtf;
    @GuardedBy("mLock")
    private zzwz zzbtg;

    public final void onAdClicked() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzce();
            }
        }
    }

    public final void onAdClosed() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcf();
            }
        }
    }

    public final void onAdFailedToLoad(int i) {
        synchronized (this.mLock) {
            if (this.zzbtf != null) {
                this.zzbtf.zzx(i == 3 ? 1 : 2);
                this.zzbtf = null;
            }
        }
    }

    public final void onAdImpression() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcj();
            }
        }
    }

    public final void onAdLeftApplication() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcg();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onAdLoaded() {
        /*
        r3 = this;
        r1 = r3.mLock;
        monitor-enter(r1);
        r0 = r3.zzbtf;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0012;
    L_0x0007:
        r0 = r3.zzbtf;	 Catch:{ all -> 0x001d }
        r2 = 0;
        r0.zzx(r2);	 Catch:{ all -> 0x001d }
        r0 = 0;
        r3.zzbtf = r0;	 Catch:{ all -> 0x001d }
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
    L_0x0011:
        return;
    L_0x0012:
        r0 = r3.zzbtg;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001b;
    L_0x0016:
        r0 = r3.zzbtg;	 Catch:{ all -> 0x001d }
        r0.zzci();	 Catch:{ all -> 0x001d }
    L_0x001b:
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        goto L_0x0011;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxa.onAdLoaded():void");
    }

    public final void onAdOpened() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzch();
            }
        }
    }

    public final void onAppEvent(String str, String str2) {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzb(str, str2);
            }
        }
    }

    public final void onVideoEnd() {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zzcd();
            }
        }
    }

    public final void zza(@Nullable zzwz com_google_android_gms_internal_ads_zzwz) {
        synchronized (this.mLock) {
            this.zzbtg = com_google_android_gms_internal_ads_zzwz;
        }
    }

    public final void zza(zzxf com_google_android_gms_internal_ads_zzxf) {
        synchronized (this.mLock) {
            this.zzbtf = com_google_android_gms_internal_ads_zzxf;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.zzxw r4) {
        /*
        r3 = this;
        r1 = r3.mLock;
        monitor-enter(r1);
        r0 = r3.zzbtf;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0012;
    L_0x0007:
        r0 = r3.zzbtf;	 Catch:{ all -> 0x001d }
        r2 = 0;
        r0.zza(r2, r4);	 Catch:{ all -> 0x001d }
        r0 = 0;
        r3.zzbtf = r0;	 Catch:{ all -> 0x001d }
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
    L_0x0011:
        return;
    L_0x0012:
        r0 = r3.zzbtg;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001b;
    L_0x0016:
        r0 = r3.zzbtg;	 Catch:{ all -> 0x001d }
        r0.zzci();	 Catch:{ all -> 0x001d }
    L_0x001b:
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        goto L_0x0011;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzxa.zza(com.google.android.gms.internal.ads.zzxw):void");
    }

    public final void zzb(zzqs com_google_android_gms_internal_ads_zzqs, String str) {
        synchronized (this.mLock) {
            if (this.zzbtg != null) {
                this.zzbtg.zza(com_google_android_gms_internal_ads_zzqs, str);
            }
        }
    }

    public final void zzbj(String str) {
    }
}
