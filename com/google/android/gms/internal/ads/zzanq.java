package com.google.android.gms.internal.ads;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

final /* synthetic */ class zzanq implements Runnable {
    private final zzaoj zzbnu;
    private final zzank zzcvl;
    private final zzanz zzcvm;

    zzanq(zzaoj com_google_android_gms_internal_ads_zzaoj, zzank com_google_android_gms_internal_ads_zzank, zzanz com_google_android_gms_internal_ads_zzanz) {
        this.zzbnu = com_google_android_gms_internal_ads_zzaoj;
        this.zzcvl = com_google_android_gms_internal_ads_zzank;
        this.zzcvm = com_google_android_gms_internal_ads_zzanz;
    }

    public final void run() {
        Throwable e;
        zzaoj com_google_android_gms_internal_ads_zzaoj = this.zzbnu;
        try {
            com_google_android_gms_internal_ads_zzaoj.set(this.zzcvl.apply(this.zzcvm.get()));
        } catch (CancellationException e2) {
            com_google_android_gms_internal_ads_zzaoj.cancel(true);
        } catch (ExecutionException e3) {
            e = e3;
            Throwable cause = e.getCause();
            if (cause != null) {
                e = cause;
            }
            com_google_android_gms_internal_ads_zzaoj.setException(e);
        } catch (Throwable e4) {
            Thread.currentThread().interrupt();
            com_google_android_gms_internal_ads_zzaoj.setException(e4);
        } catch (Throwable e42) {
            com_google_android_gms_internal_ads_zzaoj.setException(e42);
        }
    }
}
