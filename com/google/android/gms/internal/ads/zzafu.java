package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final class zzafu implements zzv<Object> {
    private final /* synthetic */ zzaft zzchv;

    zzafu(zzaft com_google_android_gms_internal_ads_zzaft) {
        this.zzchv = com_google_android_gms_internal_ads_zzaft;
    }

    public final void zza(Object obj, Map<String, String> map) {
        synchronized (zzaft.zza(this.zzchv)) {
            if (zzaft.zzb(this.zzchv).isDone()) {
            } else if (zzaft.zzc(this.zzchv).equals(map.get("request_id"))) {
                zzafz com_google_android_gms_internal_ads_zzafz = new zzafz(1, map);
                String type = com_google_android_gms_internal_ads_zzafz.getType();
                String valueOf = String.valueOf(com_google_android_gms_internal_ads_zzafz.zzoh());
                zzakb.zzdk(new StringBuilder((String.valueOf(type).length() + 24) + String.valueOf(valueOf).length()).append("Invalid ").append(type).append(" request error: ").append(valueOf).toString());
                zzaft.zzb(this.zzchv).set(com_google_android_gms_internal_ads_zzafz);
            }
        }
    }
}
