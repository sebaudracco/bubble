package com.google.android.gms.internal.ads;

final class zzabg implements Runnable {
    private final /* synthetic */ zzabf zzbzj;

    zzabg(zzabf com_google_android_gms_internal_ads_zzabf) {
        this.zzbzj = com_google_android_gms_internal_ads_zzabf;
    }

    public final void run() {
        if (zzabf.zza(this.zzbzj).get()) {
            zzakb.e("Timed out waiting for WebView to finish loading.");
            this.zzbzj.cancel();
        }
    }
}
