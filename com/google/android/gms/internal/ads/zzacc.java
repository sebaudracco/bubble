package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

final class zzacc implements Runnable {
    private final /* synthetic */ AtomicInteger zzcay;
    private final /* synthetic */ int zzcaz;
    private final /* synthetic */ zzaoj zzcba;
    private final /* synthetic */ List zzcbb;

    zzacc(AtomicInteger atomicInteger, int i, zzaoj com_google_android_gms_internal_ads_zzaoj, List list) {
        this.zzcay = atomicInteger;
        this.zzcaz = i;
        this.zzcba = com_google_android_gms_internal_ads_zzaoj;
        this.zzcbb = list;
    }

    public final void run() {
        Throwable e;
        if (this.zzcay.incrementAndGet() >= this.zzcaz) {
            try {
                this.zzcba.set(zzabv.zzl(this.zzcbb));
                return;
            } catch (ExecutionException e2) {
                e = e2;
            } catch (InterruptedException e3) {
                e = e3;
            }
        } else {
            return;
        }
        zzakb.zzc("Unable to convert list of futures to a future of list", e);
    }
}
