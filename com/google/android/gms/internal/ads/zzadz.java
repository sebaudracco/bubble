package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public abstract class zzadz implements zzadx, zzalc<Void> {
    private final Object mLock = new Object();
    private final zzaol<zzaef> zzccp;
    private final zzadx zzccq;

    public zzadz(zzaol<zzaef> com_google_android_gms_internal_ads_zzaol_com_google_android_gms_internal_ads_zzaef, zzadx com_google_android_gms_internal_ads_zzadx) {
        this.zzccp = com_google_android_gms_internal_ads_zzaol_com_google_android_gms_internal_ads_zzaef;
        this.zzccq = com_google_android_gms_internal_ads_zzadx;
    }

    public final void cancel() {
        zznz();
    }

    public final void zza(zzaej com_google_android_gms_internal_ads_zzaej) {
        synchronized (this.mLock) {
            this.zzccq.zza(com_google_android_gms_internal_ads_zzaej);
            zznz();
        }
    }

    @VisibleForTesting
    final boolean zza(zzaen com_google_android_gms_internal_ads_zzaen, zzaef com_google_android_gms_internal_ads_zzaef) {
        try {
            com_google_android_gms_internal_ads_zzaen.zza(com_google_android_gms_internal_ads_zzaef, new zzaei(this));
            return true;
        } catch (Throwable th) {
            zzane.zzc("Could not fetch ad response from ad request service due to an Exception.", th);
            zzbv.zzeo().zza(th, "AdRequestClientTask.getAdResponseFromService");
            this.zzccq.zza(new zzaej(0));
            return false;
        }
    }

    public final /* synthetic */ Object zznt() {
        zzaen zzoa = zzoa();
        if (zzoa == null) {
            this.zzccq.zza(new zzaej(0));
            zznz();
        } else {
            this.zzccp.zza(new zzaea(this, zzoa), new zzaeb(this));
        }
        return null;
    }

    public abstract void zznz();

    public abstract zzaen zzoa();
}
