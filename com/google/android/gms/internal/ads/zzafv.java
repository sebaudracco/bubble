package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;

final class zzafv implements zzv<Object> {
    private final /* synthetic */ zzaft zzchv;

    zzafv(zzaft com_google_android_gms_internal_ads_zzaft) {
        this.zzchv = com_google_android_gms_internal_ads_zzaft;
    }

    public final void zza(Object obj, Map<String, String> map) {
        synchronized (zzaft.zza(this.zzchv)) {
            if (zzaft.zzb(this.zzchv).isDone()) {
                return;
            }
            zzafz com_google_android_gms_internal_ads_zzafz = new zzafz(-2, map);
            if (zzaft.zzc(this.zzchv).equals(com_google_android_gms_internal_ads_zzafz.zzol())) {
                String url = com_google_android_gms_internal_ads_zzafz.getUrl();
                if (url == null) {
                    zzakb.zzdk("URL missing in loadAdUrl GMSG.");
                    return;
                }
                if (url.contains("%40mediation_adapters%40")) {
                    String replaceAll = url.replaceAll("%40mediation_adapters%40", zzajw.zzc(zzaft.zzd(this.zzchv), (String) map.get("check_adapters"), zzaft.zze(this.zzchv)));
                    com_google_android_gms_internal_ads_zzafz.setUrl(replaceAll);
                    url = "Ad request URL modified to ";
                    replaceAll = String.valueOf(replaceAll);
                    zzakb.v(replaceAll.length() != 0 ? url.concat(replaceAll) : new String(url));
                }
                zzaft.zzb(this.zzchv).set(com_google_android_gms_internal_ads_zzafz);
                return;
            }
        }
    }
}
