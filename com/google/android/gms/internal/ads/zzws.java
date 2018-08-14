package com.google.android.gms.internal.ads;

final class zzws implements zzaom {
    private final /* synthetic */ zzvs zzbrl;
    private final /* synthetic */ zzaoj zzbrn;

    zzws(zzwq com_google_android_gms_internal_ads_zzwq, zzaoj com_google_android_gms_internal_ads_zzaoj, zzvs com_google_android_gms_internal_ads_zzvs) {
        this.zzbrn = com_google_android_gms_internal_ads_zzaoj;
        this.zzbrl = com_google_android_gms_internal_ads_zzvs;
    }

    public final void run() {
        this.zzbrn.setException(new zzwe("Unable to obtain a JavascriptEngine."));
        this.zzbrl.release();
    }
}
