package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final class zzpj implements zzv<Object> {
    private final /* synthetic */ zzacm zzbji;
    private final /* synthetic */ zzpf zzbjj;

    zzpj(zzpf com_google_android_gms_internal_ads_zzpf, zzacm com_google_android_gms_internal_ads_zzacm) {
        this.zzbjj = com_google_android_gms_internal_ads_zzpf;
        this.zzbji = com_google_android_gms_internal_ads_zzacm;
    }

    public final void zza(Object obj, Map<String, String> map) {
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) this.zzbjj.zzbjg.get();
        if (com_google_android_gms_internal_ads_zzaqw == null) {
            this.zzbji.zzb("/hideOverlay", this);
        } else {
            com_google_android_gms_internal_ads_zzaqw.getView().setVisibility(8);
        }
    }
}
