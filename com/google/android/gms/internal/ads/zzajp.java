package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzajp {
    private final Object mLock;
    private volatile int zzcpx;
    private volatile long zzcpy;

    private zzajp() {
        this.mLock = new Object();
        this.zzcpx = zzajq.zzcpz;
        this.zzcpy = 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzd(int r6, int r7) {
        /*
        r5 = this;
        r5.zzqk();
        r0 = com.google.android.gms.ads.internal.zzbv.zzer();
        r0 = r0.currentTimeMillis();
        r2 = r5.mLock;
        monitor-enter(r2);
        r3 = r5.zzcpx;	 Catch:{ all -> 0x0020 }
        if (r3 == r6) goto L_0x0014;
    L_0x0012:
        monitor-exit(r2);	 Catch:{ all -> 0x0020 }
    L_0x0013:
        return;
    L_0x0014:
        r5.zzcpx = r7;	 Catch:{ all -> 0x0020 }
        r3 = r5.zzcpx;	 Catch:{ all -> 0x0020 }
        r4 = com.google.android.gms.internal.ads.zzajq.zzcqb;	 Catch:{ all -> 0x0020 }
        if (r3 != r4) goto L_0x001e;
    L_0x001c:
        r5.zzcpy = r0;	 Catch:{ all -> 0x0020 }
    L_0x001e:
        monitor-exit(r2);	 Catch:{ all -> 0x0020 }
        goto L_0x0013;
    L_0x0020:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x0020 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzajp.zzd(int, int):void");
    }

    private final void zzqk() {
        long currentTimeMillis = zzbv.zzer().currentTimeMillis();
        synchronized (this.mLock) {
            if (this.zzcpx == zzajq.zzcqb) {
                if (this.zzcpy + ((Long) zzkb.zzik().zzd(zznk.zzbfn)).longValue() <= currentTimeMillis) {
                    this.zzcpx = zzajq.zzcpz;
                }
            }
        }
    }

    public final void zzaa(boolean z) {
        if (z) {
            zzd(zzajq.zzcpz, zzajq.zzcqa);
        } else {
            zzd(zzajq.zzcqa, zzajq.zzcpz);
        }
    }

    public final boolean zzqa() {
        zzqk();
        return this.zzcpx == zzajq.zzcqa;
    }

    public final boolean zzqb() {
        zzqk();
        return this.zzcpx == zzajq.zzcqb;
    }

    public final void zzqc() {
        zzd(zzajq.zzcqa, zzajq.zzcqb);
    }
}
