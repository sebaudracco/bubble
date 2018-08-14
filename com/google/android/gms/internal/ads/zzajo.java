package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzajo extends zzajx {
    private final /* synthetic */ zzajm zzcpw;

    zzajo(zzajm com_google_android_gms_internal_ads_zzajm) {
        this.zzcpw = com_google_android_gms_internal_ads_zzajm;
    }

    public final void onStop() {
    }

    public final void zzdn() {
        zznm com_google_android_gms_internal_ads_zznm = new zznm(zzajm.zza(this.zzcpw), zzajm.zzb(this.zzcpw).zzcw);
        synchronized (zzajm.zzc(this.zzcpw)) {
            try {
                zzbv.zzet();
                zznp.zza(zzajm.zzd(this.zzcpw), com_google_android_gms_internal_ads_zznm);
            } catch (Throwable e) {
                zzakb.zzc("Cannot config CSI reporter.", e);
            }
        }
    }
}
