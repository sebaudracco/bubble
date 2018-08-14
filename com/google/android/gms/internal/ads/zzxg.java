package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzxg {
    private static String zza(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            str3 = "";
        }
        return str.replaceAll(str2, str3);
    }

    public static List<String> zza(JSONObject jSONObject, String str) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        List arrayList = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.getString(i));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static void zza(Context context, String str, zzajh com_google_android_gms_internal_ads_zzajh, String str2, boolean z, List<String> list) {
        if (list != null && !list.isEmpty()) {
            String str3 = z ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0;
            for (String zza : list) {
                String zza2 = zza(zza(zza(zza(zza(zza(zza(zza2, "@gw_adlocid@", str2), "@gw_adnetrefresh@", str3), "@gw_qdata@", com_google_android_gms_internal_ads_zzajh.zzcod.zzbst), "@gw_sdkver@", str), "@gw_sessid@", zzkb.zzih()), "@gw_seqnum@", com_google_android_gms_internal_ads_zzajh.zzccy), "@gw_adnetstatus@", com_google_android_gms_internal_ads_zzajh.zzcoe);
                if (com_google_android_gms_internal_ads_zzajh.zzbtw != null) {
                    zza2 = zza(zza(zza2, "@gw_adnetid@", com_google_android_gms_internal_ads_zzajh.zzbtw.zzbrs), "@gw_allocid@", com_google_android_gms_internal_ads_zzajh.zzbtw.zzbru);
                }
                zza2 = zzajb.zzb(zza2, context);
                zzbv.zzek();
                zzakk.zzd(context, str, zza2);
            }
        }
    }

    public static void zza(Context context, String str, List<String> list, String str2, @Nullable zzaig com_google_android_gms_internal_ads_zzaig) {
        if (list != null && !list.isEmpty()) {
            if (!TextUtils.isEmpty(str2) && zzamy.isEnabled()) {
                str2 = "fakeUserForAdDebugLog";
            }
            long currentTimeMillis = zzbv.zzer().currentTimeMillis();
            for (String zza : list) {
                String zza2 = zza(zza(zza2, "@gw_rwd_userid@", Uri.encode(str2)), "@gw_tmstmp@", Long.toString(currentTimeMillis));
                if (com_google_android_gms_internal_ads_zzaig != null) {
                    zza2 = zza(zza(zza2, "@gw_rwd_itm@", Uri.encode(com_google_android_gms_internal_ads_zzaig.type)), "@gw_rwd_amt@", Integer.toString(com_google_android_gms_internal_ads_zzaig.zzcmk));
                }
                zzbv.zzek();
                zzakk.zzd(context, str, zza2);
            }
        }
    }

    public static boolean zza(String str, int[] iArr) {
        if (TextUtils.isEmpty(str) || iArr.length != 2) {
            return false;
        }
        String[] split = str.split("x");
        if (split.length != 2) {
            return false;
        }
        try {
            iArr[0] = Integer.parseInt(split[0]);
            iArr[1] = Integer.parseInt(split[1]);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
