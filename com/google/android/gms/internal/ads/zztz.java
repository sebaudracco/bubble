package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.ads.internal.zzbv;

final class zztz {
    zzal zzbor;
    @Nullable
    zzjj zzbos;
    zzst zzbot;
    long zzbou;
    boolean zzbov;
    private final /* synthetic */ zzty zzbow;
    boolean zzwa;

    zztz(zzty com_google_android_gms_internal_ads_zzty, zzss com_google_android_gms_internal_ads_zzss) {
        this.zzbow = com_google_android_gms_internal_ads_zzty;
        this.zzbor = com_google_android_gms_internal_ads_zzss.zzaw(zzty.zza(com_google_android_gms_internal_ads_zzty));
        this.zzbot = new zzst();
        zzst com_google_android_gms_internal_ads_zzst = this.zzbot;
        zza com_google_android_gms_ads_internal_zza = this.zzbor;
        com_google_android_gms_ads_internal_zza.zza(new zzsu(com_google_android_gms_internal_ads_zzst));
        com_google_android_gms_ads_internal_zza.zza(new zztc(com_google_android_gms_internal_ads_zzst));
        com_google_android_gms_ads_internal_zza.zza(new zzte(com_google_android_gms_internal_ads_zzst));
        com_google_android_gms_ads_internal_zza.zza(new zztg(com_google_android_gms_internal_ads_zzst));
        com_google_android_gms_ads_internal_zza.zza(new zzti(com_google_android_gms_internal_ads_zzst));
    }

    zztz(zzty com_google_android_gms_internal_ads_zzty, zzss com_google_android_gms_internal_ads_zzss, zzjj com_google_android_gms_internal_ads_zzjj) {
        this(com_google_android_gms_internal_ads_zzty, com_google_android_gms_internal_ads_zzss);
        this.zzbos = com_google_android_gms_internal_ads_zzjj;
    }

    final boolean load() {
        if (this.zzwa) {
            return false;
        }
        this.zzbov = this.zzbor.zzb(zztw.zzi(this.zzbos != null ? this.zzbos : zzty.zzb(this.zzbow)));
        this.zzwa = true;
        this.zzbou = zzbv.zzer().currentTimeMillis();
        return true;
    }
}
