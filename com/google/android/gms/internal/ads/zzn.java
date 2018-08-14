package com.google.android.gms.internal.ads;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public final class zzn extends Thread {
    private final zzm zzaa;
    private final zzb zzj;
    private final zzaa zzk;
    private volatile boolean zzl = false;
    private final BlockingQueue<zzr<?>> zzz;

    public zzn(BlockingQueue<zzr<?>> blockingQueue, zzm com_google_android_gms_internal_ads_zzm, zzb com_google_android_gms_internal_ads_zzb, zzaa com_google_android_gms_internal_ads_zzaa) {
        this.zzz = blockingQueue;
        this.zzaa = com_google_android_gms_internal_ads_zzm;
        this.zzj = com_google_android_gms_internal_ads_zzb;
        this.zzk = com_google_android_gms_internal_ads_zzaa;
    }

    private final void processRequest() throws InterruptedException {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        zzr com_google_android_gms_internal_ads_zzr = (zzr) this.zzz.take();
        try {
            com_google_android_gms_internal_ads_zzr.zzb("network-queue-take");
            com_google_android_gms_internal_ads_zzr.isCanceled();
            TrafficStats.setThreadStatsTag(com_google_android_gms_internal_ads_zzr.zze());
            zzp zzc = this.zzaa.zzc(com_google_android_gms_internal_ads_zzr);
            com_google_android_gms_internal_ads_zzr.zzb("network-http-complete");
            if (zzc.zzac && com_google_android_gms_internal_ads_zzr.zzl()) {
                com_google_android_gms_internal_ads_zzr.zzc("not-modified");
                com_google_android_gms_internal_ads_zzr.zzm();
                return;
            }
            zzx zza = com_google_android_gms_internal_ads_zzr.zza(zzc);
            com_google_android_gms_internal_ads_zzr.zzb("network-parse-complete");
            if (com_google_android_gms_internal_ads_zzr.zzh() && zza.zzbg != null) {
                this.zzj.zza(com_google_android_gms_internal_ads_zzr.getUrl(), zza.zzbg);
                com_google_android_gms_internal_ads_zzr.zzb("network-cache-written");
            }
            com_google_android_gms_internal_ads_zzr.zzk();
            this.zzk.zzb(com_google_android_gms_internal_ads_zzr, zza);
            com_google_android_gms_internal_ads_zzr.zza(zza);
        } catch (zzae e) {
            e.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
            this.zzk.zza(com_google_android_gms_internal_ads_zzr, e);
            com_google_android_gms_internal_ads_zzr.zzm();
        } catch (Throwable e2) {
            zzaf.zza(e2, "Unhandled exception %s", e2.toString());
            zzae com_google_android_gms_internal_ads_zzae = new zzae(e2);
            com_google_android_gms_internal_ads_zzae.zza(SystemClock.elapsedRealtime() - elapsedRealtime);
            this.zzk.zza(com_google_android_gms_internal_ads_zzr, com_google_android_gms_internal_ads_zzae);
            com_google_android_gms_internal_ads_zzr.zzm();
        }
    }

    public final void quit() {
        this.zzl = true;
        interrupt();
    }

    public final void run() {
        Process.setThreadPriority(10);
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
