package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.appnext.base.p023b.C1042c;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.Map;

@zzadh
public final class zzah implements zzv<Object> {
    private final zzai zzbng;

    public zzah(zzai com_google_android_gms_ads_internal_gmsg_zzai) {
        this.zzbng = com_google_android_gms_ads_internal_gmsg_zzai;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get(C1042c.jL);
        if ("grant".equals(str)) {
            zzaig com_google_android_gms_internal_ads_zzaig;
            try {
                int parseInt = Integer.parseInt((String) map.get("amount"));
                str = (String) map.get("type");
                if (!TextUtils.isEmpty(str)) {
                    com_google_android_gms_internal_ads_zzaig = new zzaig(str, parseInt);
                    this.zzbng.zzb(com_google_android_gms_internal_ads_zzaig);
                }
            } catch (Throwable e) {
                zzane.zzc("Unable to parse reward amount.", e);
            }
            com_google_android_gms_internal_ads_zzaig = null;
            this.zzbng.zzb(com_google_android_gms_internal_ads_zzaig);
        } else if ("video_start".equals(str)) {
            this.zzbng.zzdk();
        } else if ("video_complete".equals(str)) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzaxv)).booleanValue()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzaxv)).booleanValue()) {
                    this.zzbng.zzdl();
                }
            }
        }
    }
}
