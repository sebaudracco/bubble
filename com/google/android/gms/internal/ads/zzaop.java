package com.google.android.gms.internal.ads;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@zzadh
public class zzaop<T> implements zzaol<T> {
    private final Object mLock = new Object();
    private int zzbqb = 0;
    private final BlockingQueue<zzaoq> zzcwi = new LinkedBlockingQueue();
    private T zzcwj;

    public final int getStatus() {
        return this.zzbqb;
    }

    public final void reject() {
        synchronized (this.mLock) {
            if (this.zzbqb != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzbqb = -1;
            for (zzaoq com_google_android_gms_internal_ads_zzaoq : this.zzcwi) {
                com_google_android_gms_internal_ads_zzaoq.zzcwl.run();
            }
            this.zzcwi.clear();
        }
    }

    public final void zza(zzaoo<T> com_google_android_gms_internal_ads_zzaoo_T, zzaom com_google_android_gms_internal_ads_zzaom) {
        synchronized (this.mLock) {
            if (this.zzbqb == 1) {
                com_google_android_gms_internal_ads_zzaoo_T.zze(this.zzcwj);
            } else if (this.zzbqb == -1) {
                com_google_android_gms_internal_ads_zzaom.run();
            } else if (this.zzbqb == 0) {
                this.zzcwi.add(new zzaoq(this, com_google_android_gms_internal_ads_zzaoo_T, com_google_android_gms_internal_ads_zzaom));
            }
        }
    }

    public final void zzk(T t) {
        synchronized (this.mLock) {
            if (this.zzbqb != 0) {
                throw new UnsupportedOperationException();
            }
            this.zzcwj = t;
            this.zzbqb = 1;
            for (zzaoq com_google_android_gms_internal_ads_zzaoq : this.zzcwi) {
                com_google_android_gms_internal_ads_zzaoq.zzcwk.zze(t);
            }
            this.zzcwi.clear();
        }
    }
}
