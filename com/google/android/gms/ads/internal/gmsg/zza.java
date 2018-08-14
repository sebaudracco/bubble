package com.google.android.gms.ads.internal.gmsg;

import android.os.Bundle;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzane;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

@zzadh
public final class zza implements zzv<Object> {
    private final zzb zzbll;

    public zza(zzb com_google_android_gms_ads_internal_gmsg_zzb) {
        this.zzbll = com_google_android_gms_ads_internal_gmsg_zzb;
    }

    private static Bundle zzar(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator keys = jSONObject.keys();
            Bundle bundle = new Bundle();
            while (keys.hasNext()) {
                String str2 = (String) keys.next();
                Object obj = jSONObject.get(str2);
                if (obj != null) {
                    if (obj instanceof Boolean) {
                        bundle.putBoolean(str2, ((Boolean) obj).booleanValue());
                    } else if (obj instanceof Double) {
                        bundle.putDouble(str2, ((Double) obj).doubleValue());
                    } else if (obj instanceof Integer) {
                        bundle.putInt(str2, ((Integer) obj).intValue());
                    } else if (obj instanceof Long) {
                        bundle.putLong(str2, ((Long) obj).longValue());
                    } else if (obj instanceof String) {
                        bundle.putString(str2, (String) obj);
                    } else {
                        String str3 = "Unsupported type for key:";
                        str2 = String.valueOf(str2);
                        zzane.zzdk(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                    }
                }
            }
            return bundle;
        } catch (Throwable e) {
            zzane.zzb("Failed to convert ad metadata to JSON.", e);
            return null;
        }
    }

    public final void zza(Object obj, Map<String, String> map) {
        if (this.zzbll != null) {
            String str;
            String str2 = (String) map.get("name");
            if (str2 == null) {
                zzane.zzdj("Ad metadata with no name parameter.");
                str = "";
            } else {
                str = str2;
            }
            Bundle zzar = zzar((String) map.get("info"));
            if (zzar == null) {
                zzane.m3427e("Failed to convert ad metadata to Bundle.");
            } else {
                this.zzbll.zza(str, zzar);
            }
        }
    }
}
