package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

final class zzpf {
    private final WeakReference<zzaqw> zzbjg;
    private String zzbjh;

    public zzpf(zzaqw com_google_android_gms_internal_ads_zzaqw) {
        this.zzbjg = new WeakReference(com_google_android_gms_internal_ads_zzaqw);
    }

    public final void zza(zzacm com_google_android_gms_internal_ads_zzacm) {
        com_google_android_gms_internal_ads_zzacm.zza("/loadHtml", new zzpg(this, com_google_android_gms_internal_ads_zzacm));
        com_google_android_gms_internal_ads_zzacm.zza("/showOverlay", new zzpi(this, com_google_android_gms_internal_ads_zzacm));
        com_google_android_gms_internal_ads_zzacm.zza("/hideOverlay", new zzpj(this, com_google_android_gms_internal_ads_zzacm));
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) this.zzbjg.get();
        if (com_google_android_gms_internal_ads_zzaqw != null) {
            com_google_android_gms_internal_ads_zzaqw.zza("/sendMessageToSdk", new zzpk(this, com_google_android_gms_internal_ads_zzacm));
        }
    }
}
