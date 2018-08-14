package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

final /* synthetic */ class zzanu implements Runnable {
    private final zzaoj zzbnu;
    private final zzanz zzcvk;
    private final Class zzcvp;
    private final zzanj zzcvq;
    private final Executor zzcvr;

    zzanu(zzaoj com_google_android_gms_internal_ads_zzaoj, zzanz com_google_android_gms_internal_ads_zzanz, Class cls, zzanj com_google_android_gms_internal_ads_zzanj, Executor executor) {
        this.zzbnu = com_google_android_gms_internal_ads_zzaoj;
        this.zzcvk = com_google_android_gms_internal_ads_zzanz;
        this.zzcvp = cls;
        this.zzcvq = com_google_android_gms_internal_ads_zzanj;
        this.zzcvr = executor;
    }

    public final void run() {
        zzano.zza(this.zzbnu, this.zzcvk, this.zzcvp, this.zzcvq, this.zzcvr);
    }
}
