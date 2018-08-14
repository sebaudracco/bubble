package com.google.android.gms.internal.ads;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public final class zzd extends Thread {
    private static final boolean DEBUG = zzaf.DEBUG;
    private final BlockingQueue<zzr<?>> zzh;
    private final BlockingQueue<zzr<?>> zzi;
    private final zzb zzj;
    private final zzaa zzk;
    private volatile boolean zzl = false;
    private final zzf zzm;

    public zzd(BlockingQueue<zzr<?>> blockingQueue, BlockingQueue<zzr<?>> blockingQueue2, zzb com_google_android_gms_internal_ads_zzb, zzaa com_google_android_gms_internal_ads_zzaa) {
        this.zzh = blockingQueue;
        this.zzi = blockingQueue2;
        this.zzj = com_google_android_gms_internal_ads_zzb;
        this.zzk = com_google_android_gms_internal_ads_zzaa;
        this.zzm = new zzf(this);
    }

    private final void processRequest() throws InterruptedException {
        zzr com_google_android_gms_internal_ads_zzr = (zzr) this.zzh.take();
        com_google_android_gms_internal_ads_zzr.zzb("cache-queue-take");
        com_google_android_gms_internal_ads_zzr.isCanceled();
        zzc zza = this.zzj.zza(com_google_android_gms_internal_ads_zzr.getUrl());
        if (zza == null) {
            com_google_android_gms_internal_ads_zzr.zzb("cache-miss");
            if (!this.zzm.zzb(com_google_android_gms_internal_ads_zzr)) {
                this.zzi.put(com_google_android_gms_internal_ads_zzr);
            }
        } else if (zza.zzb()) {
            com_google_android_gms_internal_ads_zzr.zzb("cache-hit-expired");
            com_google_android_gms_internal_ads_zzr.zza(zza);
            if (!this.zzm.zzb(com_google_android_gms_internal_ads_zzr)) {
                this.zzi.put(com_google_android_gms_internal_ads_zzr);
            }
        } else {
            com_google_android_gms_internal_ads_zzr.zzb("cache-hit");
            zzx zza2 = com_google_android_gms_internal_ads_zzr.zza(new zzp(zza.data, zza.zzf));
            com_google_android_gms_internal_ads_zzr.zzb("cache-hit-parsed");
            if (zza.zze < System.currentTimeMillis()) {
                com_google_android_gms_internal_ads_zzr.zzb("cache-hit-refresh-needed");
                com_google_android_gms_internal_ads_zzr.zza(zza);
                zza2.zzbi = true;
                if (!this.zzm.zzb(com_google_android_gms_internal_ads_zzr)) {
                    this.zzk.zza(com_google_android_gms_internal_ads_zzr, zza2, new zze(this, com_google_android_gms_internal_ads_zzr));
                    return;
                }
            }
            this.zzk.zzb(com_google_android_gms_internal_ads_zzr, zza2);
        }
    }

    public final void quit() {
        this.zzl = true;
        interrupt();
    }

    public final void run() {
        if (DEBUG) {
            zzaf.m8610v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.zzj.zza();
        while (true) {
            try {
                processRequest();
            } catch (InterruptedException e) {
                if (this.zzl) {
                    return;
                }
            }
        }
    }
}
