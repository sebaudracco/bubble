package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.appnext.base.p023b.C1042c;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.util.Map;

@zzadh
public final class zze implements zzv<zzaqw> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) obj;
        String str = (String) map.get(C1042c.jL);
        String str2;
        if ("tick".equals(str)) {
            str = (String) map.get(PlusShare.KEY_CALL_TO_ACTION_LABEL);
            str2 = (String) map.get("start_label");
            String str3 = (String) map.get("timestamp");
            if (TextUtils.isEmpty(str)) {
                zzane.zzdk("No label given for CSI tick.");
            } else if (TextUtils.isEmpty(str3)) {
                zzane.zzdk("No timestamp given for CSI tick.");
            } else {
                try {
                    long parseLong = (Long.parseLong(str3) - zzbv.zzer().currentTimeMillis()) + zzbv.zzer().elapsedRealtime();
                    if (TextUtils.isEmpty(str2)) {
                        str2 = "native:view_load";
                    }
                    com_google_android_gms_internal_ads_zzaqw.zztp().zza(str, str2, parseLong);
                } catch (Throwable e) {
                    zzane.zzc("Malformed timestamp for CSI tick.", e);
                }
            }
        } else if ("experiment".equals(str)) {
            str = (String) map.get(FirebaseAnalytics$Param.VALUE);
            if (TextUtils.isEmpty(str)) {
                zzane.zzdk("No value given for CSI experiment.");
                return;
            }
            zznx zzji = com_google_android_gms_internal_ads_zzaqw.zztp().zzji();
            if (zzji == null) {
                zzane.zzdk("No ticker for WebView, dropping experiment ID.");
            } else {
                zzji.zze("e", str);
            }
        } else if ("extra".equals(str)) {
            str = (String) map.get("name");
            str2 = (String) map.get(FirebaseAnalytics$Param.VALUE);
            if (TextUtils.isEmpty(str2)) {
                zzane.zzdk("No value given for CSI extra.");
            } else if (TextUtils.isEmpty(str)) {
                zzane.zzdk("No name given for CSI extra.");
            } else {
                zznx zzji2 = com_google_android_gms_internal_ads_zzaqw.zztp().zzji();
                if (zzji2 == null) {
                    zzane.zzdk("No ticker for WebView, dropping extra parameter.");
                } else {
                    zzji2.zze(str, str2);
                }
            }
        }
    }
}
