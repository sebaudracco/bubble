package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;

final /* synthetic */ class zzanv implements Runnable {
    private final zzaoj zzbnu;
    private final zzanz zzcvk;

    zzanv(zzaoj com_google_android_gms_internal_ads_zzaoj, zzanz com_google_android_gms_internal_ads_zzanz) {
        this.zzbnu = com_google_android_gms_internal_ads_zzaoj;
        this.zzcvk = com_google_android_gms_internal_ads_zzanz;
    }

    public final void run() {
        zzaoj com_google_android_gms_internal_ads_zzaoj = this.zzbnu;
        try {
            com_google_android_gms_internal_ads_zzaoj.set(this.zzcvk.get());
        } catch (ExecutionException e) {
            com_google_android_gms_internal_ads_zzaoj.setException(e.getCause());
        } catch (Throwable e2) {
            Thread.currentThread().interrupt();
            com_google_android_gms_internal_ads_zzaoj.setException(e2);
        } catch (Throwable e22) {
            com_google_android_gms_internal_ads_zzaoj.setException(e22);
        }
    }
}
