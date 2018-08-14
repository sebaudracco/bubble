package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;

@zzadh
public final class zzaju {
    private final Object mLock;
    private final zzajv zzacn;
    private final String zzcov;
    @GuardedBy("mLock")
    private int zzcql;
    @GuardedBy("mLock")
    private int zzcqm;

    private zzaju(zzajv com_google_android_gms_internal_ads_zzajv, String str) {
        this.mLock = new Object();
        this.zzacn = com_google_android_gms_internal_ads_zzajv;
        this.zzcov = str;
    }

    public zzaju(String str) {
        this(zzbv.zzep(), str);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzaju com_google_android_gms_internal_ads_zzaju = (zzaju) obj;
        return this.zzcov != null ? this.zzcov.equals(com_google_android_gms_internal_ads_zzaju.zzcov) : com_google_android_gms_internal_ads_zzaju.zzcov == null;
    }

    public final int hashCode() {
        return this.zzcov != null ? this.zzcov.hashCode() : 0;
    }

    public final Bundle toBundle() {
        Bundle bundle;
        synchronized (this.mLock) {
            bundle = new Bundle();
            bundle.putInt("pmnli", this.zzcql);
            bundle.putInt("pmnll", this.zzcqm);
        }
        return bundle;
    }

    public final void zze(int i, int i2) {
        synchronized (this.mLock) {
            this.zzcql = i;
            this.zzcqm = i2;
            this.zzacn.zza(this);
        }
    }

    public final String zzqm() {
        return this.zzcov;
    }
}
