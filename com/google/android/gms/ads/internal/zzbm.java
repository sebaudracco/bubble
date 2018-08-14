package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzjj;
import java.lang.ref.WeakReference;

final class zzbm implements Runnable {
    private final /* synthetic */ WeakReference zzaas;
    private final /* synthetic */ zzbl zzaat;

    zzbm(zzbl com_google_android_gms_ads_internal_zzbl, WeakReference weakReference) {
        this.zzaat = com_google_android_gms_ads_internal_zzbl;
        this.zzaas = weakReference;
    }

    public final void run() {
        zzbl.zza(this.zzaat, false);
        zza com_google_android_gms_ads_internal_zza = (zza) this.zzaas.get();
        if (com_google_android_gms_ads_internal_zza != null) {
            zzjj zzb = zzbl.zzb(this.zzaat);
            if (com_google_android_gms_ads_internal_zza.zzc(zzb)) {
                com_google_android_gms_ads_internal_zza.zzb(zzb);
                return;
            }
            zzakb.zzdj("Ad is not visible. Not refreshing ad.");
            com_google_android_gms_ads_internal_zza.zzvv.zzg(zzb);
        }
    }
}
