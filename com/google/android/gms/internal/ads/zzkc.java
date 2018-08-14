package com.google.android.gms.internal.ads;

import java.util.Random;

@zzadh
public final class zzkc extends zzlh {
    private Object mLock = new Object();
    private final Random zzasg = new Random();
    private long zzash;

    public zzkc() {
        zzil();
    }

    public final long getValue() {
        return this.zzash;
    }

    public final void zzil() {
        synchronized (this.mLock) {
            int i = 3;
            long j = 0;
            while (true) {
                i--;
                if (i <= 0) {
                    break;
                }
                j = ((long) this.zzasg.nextInt()) + CacheValidityPolicy.MAX_AGE;
                if (j != this.zzash && j != 0) {
                    break;
                }
            }
            this.zzash = j;
        }
    }
}
