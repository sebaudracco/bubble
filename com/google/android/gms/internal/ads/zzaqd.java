package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzadh
public final class zzaqd implements zzv<zzapw> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzapw com_google_android_gms_internal_ads_zzapw = (zzapw) obj;
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbae)).booleanValue()) {
            zzarl com_google_android_gms_internal_ads_zzarl;
            zzarl zztm = com_google_android_gms_internal_ads_zzapw.zztm();
            if (zztm == null) {
                try {
                    zztm = new zzarl(com_google_android_gms_internal_ads_zzapw, Float.parseFloat((String) map.get("duration")), SchemaSymbols.ATTVAL_TRUE_1.equals(map.get("customControlsAllowed")), SchemaSymbols.ATTVAL_TRUE_1.equals(map.get("clickToExpandAllowed")));
                    com_google_android_gms_internal_ads_zzapw.zza(zztm);
                    com_google_android_gms_internal_ads_zzarl = zztm;
                } catch (NullPointerException e) {
                    Throwable e2 = e;
                    zzane.zzb("Unable to parse videoMeta message.", e2);
                    zzbv.zzeo().zza(e2, "VideoMetaGmsgHandler.onGmsg");
                    return;
                } catch (NumberFormatException e3) {
                    e2 = e3;
                    zzane.zzb("Unable to parse videoMeta message.", e2);
                    zzbv.zzeo().zza(e2, "VideoMetaGmsgHandler.onGmsg");
                    return;
                }
            }
            com_google_android_gms_internal_ads_zzarl = zztm;
            boolean equals = SchemaSymbols.ATTVAL_TRUE_1.equals(map.get("muted"));
            float parseFloat = Float.parseFloat((String) map.get("currentTime"));
            int parseInt = Integer.parseInt((String) map.get("playbackState"));
            int i = (parseInt < 0 || 3 < parseInt) ? 0 : parseInt;
            String str = (String) map.get("aspectRatio");
            float parseFloat2 = TextUtils.isEmpty(str) ? 0.0f : Float.parseFloat(str);
            if (zzane.isLoggable(3)) {
                zzane.zzck(new StringBuilder(String.valueOf(str).length() + 79).append("Video Meta GMSG: isMuted : ").append(equals).append(" , playbackState : ").append(i).append(" , aspectRatio : ").append(str).toString());
            }
            com_google_android_gms_internal_ads_zzarl.zza(parseFloat, i, equals, parseFloat2);
        }
    }
}
