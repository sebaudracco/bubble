package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;

final class zzalv implements zzanj<Throwable, T> {
    private final /* synthetic */ zzalz zzcti;

    zzalv(zzalt com_google_android_gms_internal_ads_zzalt, zzalz com_google_android_gms_internal_ads_zzalz) {
        this.zzcti = com_google_android_gms_internal_ads_zzalz;
    }

    public final /* synthetic */ zzanz zzc(Object obj) throws Exception {
        Throwable th = (Throwable) obj;
        zzakb.zzb("Error occurred while dispatching http response in getter.", th);
        zzbv.zzeo().zza(th, "HttpGetter.deliverResponse.1");
        return zzano.zzi(this.zzcti.zzny());
    }
}
