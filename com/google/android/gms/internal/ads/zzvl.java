package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final class zzvl implements zzv<zzwb> {
    private final /* synthetic */ zzuu zzbqj;
    private final /* synthetic */ zzvf zzbqk;
    private final /* synthetic */ zzci zzbql;
    private final /* synthetic */ zzamk zzbqm;

    zzvl(zzvf com_google_android_gms_internal_ads_zzvf, zzci com_google_android_gms_internal_ads_zzci, zzuu com_google_android_gms_internal_ads_zzuu, zzamk com_google_android_gms_internal_ads_zzamk) {
        this.zzbqk = com_google_android_gms_internal_ads_zzvf;
        this.zzbql = com_google_android_gms_internal_ads_zzci;
        this.zzbqj = com_google_android_gms_internal_ads_zzuu;
        this.zzbqm = com_google_android_gms_internal_ads_zzamk;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        synchronized (zzvf.zza(this.zzbqk)) {
            zzakb.zzdj("JS Engine is requesting an update");
            if (zzvf.zzd(this.zzbqk) == 0) {
                zzakb.zzdj("Starting reload.");
                zzvf.zza(this.zzbqk, 2);
                this.zzbqk.zza(this.zzbql);
            }
            this.zzbqj.zzb("/requestReload", (zzv) this.zzbqm.get());
        }
    }
}
