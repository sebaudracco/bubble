package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzadh
public final class zzaql implements zzv<zzapw> {
    private static Integer zze(Map<String, String> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt((String) map.get(str)));
        } catch (NumberFormatException e) {
            String str2 = (String) map.get(str);
            zzane.zzdk(new StringBuilder((String.valueOf(str).length() + 39) + String.valueOf(str2).length()).append("Precache invalid numeric parameter '").append(str).append("': ").append(str2).toString());
            return null;
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzapw com_google_android_gms_internal_ads_zzapw = (zzapw) obj;
        zzbv.zzff();
        if (!map.containsKey("abort")) {
            String str = (String) map.get("src");
            if (str != null) {
                if (zzaqg.zzc(com_google_android_gms_internal_ads_zzapw) != null) {
                    zzane.zzdk("Precache task is already running.");
                    return;
                } else if (com_google_android_gms_internal_ads_zzapw.zzbi() == null) {
                    zzane.zzdk("Precache requires a dependency provider.");
                    return;
                } else {
                    zzapv com_google_android_gms_internal_ads_zzapv = new zzapv((String) map.get("flags"));
                    Integer zze = zze(map, "player");
                    if (zze == null) {
                        zze = Integer.valueOf(0);
                    }
                    new zzaqe(com_google_android_gms_internal_ads_zzapw, com_google_android_gms_internal_ads_zzapw.zzbi().zzwy.zza(com_google_android_gms_internal_ads_zzapw, zze.intValue(), null, com_google_android_gms_internal_ads_zzapv), str).zznt();
                }
            } else if (zzaqg.zzc(com_google_android_gms_internal_ads_zzapw) == null) {
                zzane.zzdk("Precache must specify a source.");
                return;
            }
            Integer zze2 = zze(map, "minBufferMs");
            if (zze2 != null) {
                zze2.intValue();
            }
            zze2 = zze(map, "maxBufferMs");
            if (zze2 != null) {
                zze2.intValue();
            }
            zze2 = zze(map, "bufferForPlaybackMs");
            if (zze2 != null) {
                zze2.intValue();
            }
            zze2 = zze(map, "bufferForPlaybackAfterRebufferMs");
            if (zze2 != null) {
                zze2.intValue();
            }
        } else if (!zzaqg.zzb(com_google_android_gms_internal_ads_zzapw)) {
            zzane.zzdk("Precache abort but no precache task running.");
        }
    }
}
