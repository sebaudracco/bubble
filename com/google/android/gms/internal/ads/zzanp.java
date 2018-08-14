package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

final /* synthetic */ class zzanp implements Runnable {
    private final zzanl zzcvj;
    private final zzanz zzcvk;

    zzanp(zzanl com_google_android_gms_internal_ads_zzanl, zzanz com_google_android_gms_internal_ads_zzanz) {
        this.zzcvj = com_google_android_gms_internal_ads_zzanl;
        this.zzcvk = com_google_android_gms_internal_ads_zzanz;
    }

    public final void run() {
        Throwable cause;
        zzanl com_google_android_gms_internal_ads_zzanl = this.zzcvj;
        try {
            com_google_android_gms_internal_ads_zzanl.zzh(this.zzcvk.get());
            return;
        } catch (ExecutionException e) {
            cause = e.getCause();
        } catch (InterruptedException e2) {
            cause = e2;
            Thread.currentThread().interrupt();
        } catch (Exception e3) {
            cause = e3;
        }
        com_google_android_gms_internal_ads_zzanl.zzb(cause);
    }
}
