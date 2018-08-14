package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzv {
    private final zzm zzaa;
    private final AtomicInteger zzba;
    private final Set<zzr<?>> zzbb;
    private final PriorityBlockingQueue<zzr<?>> zzbc;
    private final PriorityBlockingQueue<zzr<?>> zzbd;
    private final zzn[] zzbe;
    private final List<zzw> zzbf;
    private final zzb zzj;
    private final zzaa zzk;
    private zzd zzq;

    public zzv(zzb com_google_android_gms_internal_ads_zzb, zzm com_google_android_gms_internal_ads_zzm) {
        this(com_google_android_gms_internal_ads_zzb, com_google_android_gms_internal_ads_zzm, 4);
    }

    private zzv(zzb com_google_android_gms_internal_ads_zzb, zzm com_google_android_gms_internal_ads_zzm, int i) {
        this(com_google_android_gms_internal_ads_zzb, com_google_android_gms_internal_ads_zzm, 4, new zzi(new Handler(Looper.getMainLooper())));
    }

    private zzv(zzb com_google_android_gms_internal_ads_zzb, zzm com_google_android_gms_internal_ads_zzm, int i, zzaa com_google_android_gms_internal_ads_zzaa) {
        this.zzba = new AtomicInteger();
        this.zzbb = new HashSet();
        this.zzbc = new PriorityBlockingQueue();
        this.zzbd = new PriorityBlockingQueue();
        this.zzbf = new ArrayList();
        this.zzj = com_google_android_gms_internal_ads_zzb;
        this.zzaa = com_google_android_gms_internal_ads_zzm;
        this.zzbe = new zzn[4];
        this.zzk = com_google_android_gms_internal_ads_zzaa;
    }

    public final void start() {
        int i = 0;
        if (this.zzq != null) {
            this.zzq.quit();
        }
        for (zzn com_google_android_gms_internal_ads_zzn : this.zzbe) {
            if (com_google_android_gms_internal_ads_zzn != null) {
                com_google_android_gms_internal_ads_zzn.quit();
            }
        }
        this.zzq = new zzd(this.zzbc, this.zzbd, this.zzj, this.zzk);
        this.zzq.start();
        while (i < this.zzbe.length) {
            zzn com_google_android_gms_internal_ads_zzn2 = new zzn(this.zzbd, this.zzaa, this.zzj, this.zzk);
            this.zzbe[i] = com_google_android_gms_internal_ads_zzn2;
            com_google_android_gms_internal_ads_zzn2.start();
            i++;
        }
    }

    public final <T> zzr<T> zze(zzr<T> com_google_android_gms_internal_ads_zzr_T) {
        com_google_android_gms_internal_ads_zzr_T.zza(this);
        synchronized (this.zzbb) {
            this.zzbb.add(com_google_android_gms_internal_ads_zzr_T);
        }
        com_google_android_gms_internal_ads_zzr_T.zza(this.zzba.incrementAndGet());
        com_google_android_gms_internal_ads_zzr_T.zzb("add-to-queue");
        if (com_google_android_gms_internal_ads_zzr_T.zzh()) {
            this.zzbc.add(com_google_android_gms_internal_ads_zzr_T);
        } else {
            this.zzbd.add(com_google_android_gms_internal_ads_zzr_T);
        }
        return com_google_android_gms_internal_ads_zzr_T;
    }

    final <T> void zzf(zzr<T> com_google_android_gms_internal_ads_zzr_T) {
        synchronized (this.zzbb) {
            this.zzbb.remove(com_google_android_gms_internal_ads_zzr_T);
        }
        synchronized (this.zzbf) {
            for (zzw zzg : this.zzbf) {
                zzg.zzg(com_google_android_gms_internal_ads_zzr_T);
            }
        }
    }
}
