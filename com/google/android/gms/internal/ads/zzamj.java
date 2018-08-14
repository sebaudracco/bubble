package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzamj {
    private Object mLock = new Object();
    private long zzctx;
    @GuardedBy("mLock")
    private long zzcty = Long.MIN_VALUE;

    public zzamj(long j) {
        this.zzctx = j;
    }

    public final boolean tryAcquire() {
        boolean z;
        synchronized (this.mLock) {
            long elapsedRealtime = zzbv.zzer().elapsedRealtime();
            if (this.zzcty + this.zzctx > elapsedRealtime) {
                z = false;
            } else {
                this.zzcty = elapsedRealtime;
                z = true;
            }
        }
        return z;
    }
}
