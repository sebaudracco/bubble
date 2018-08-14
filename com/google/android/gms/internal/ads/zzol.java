package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final class zzol implements zzv<Object> {
    private final /* synthetic */ zzok zzbhr;

    zzol(zzok com_google_android_gms_internal_ads_zzok) {
        this.zzbhr = com_google_android_gms_internal_ads_zzok;
    }

    public final void zza(Object obj, Map<String, String> map) {
        try {
            this.zzbhr.zzbhp = Long.valueOf(Long.parseLong((String) map.get("timestamp")));
        } catch (NumberFormatException e) {
            zzakb.e("Failed to call parse unconfirmedClickTimestamp.");
        }
        this.zzbhr.zzbho = (String) map.get("id");
        String str = (String) map.get("asset_id");
        if (zzok.zza(this.zzbhr) == null) {
            zzakb.zzck("Received unconfirmed click but UnconfirmedClickListener is null.");
            return;
        }
        try {
            zzok.zza(this.zzbhr).onUnconfirmedClickReceived(str);
        } catch (Throwable e2) {
            zzane.zzd("#007 Could not call remote method.", e2);
        }
    }
}
