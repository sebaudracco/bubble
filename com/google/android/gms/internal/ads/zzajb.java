package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;

@zzadh
public final class zzajb {
    public static Uri zzb(Uri uri, Context context) {
        if (!zzbv.zzfh().zzx(context) || !TextUtils.isEmpty(uri.getQueryParameter("fbs_aeid"))) {
            return uri;
        }
        String zzab = zzbv.zzfh().zzab(context);
        uri = zzb(uri.toString(), "fbs_aeid", zzab);
        zzbv.zzfh().zze(context, zzab);
        return uri;
    }

    @VisibleForTesting
    private static Uri zzb(String str, String str2, String str3) {
        int indexOf = str.indexOf("&adurl");
        if (indexOf == -1) {
            indexOf = str.indexOf("?adurl");
        }
        return indexOf != -1 ? Uri.parse(new StringBuilder(str.substring(0, indexOf + 1)).append(str2).append("=").append(str3).append("&").append(str.substring(indexOf + 1)).toString()) : Uri.parse(str).buildUpon().appendQueryParameter(str2, str3).build();
    }

    public static String zzb(String str, Context context) {
        if (!zzbv.zzfh().zzs(context) || TextUtils.isEmpty(str)) {
            return str;
        }
        String zzab = zzbv.zzfh().zzab(context);
        if (zzab == null) {
            return str;
        }
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaxr)).booleanValue()) {
            String str2 = (String) zzkb.zzik().zzd(zznk.zzaxs);
            if (!str.contains(str2)) {
                return str;
            }
            if (zzbv.zzek().zzcx(str)) {
                zzbv.zzfh().zze(context, zzab);
                return str.replace(str2, zzab);
            } else if (!zzbv.zzek().zzcy(str)) {
                return str;
            } else {
                zzbv.zzfh().zzf(context, zzab);
                return str.replace(str2, zzab);
            }
        } else if (str.contains("fbs_aeid")) {
            return str;
        } else {
            if (zzbv.zzek().zzcx(str)) {
                zzbv.zzfh().zze(context, zzab);
                return zzb(str, "fbs_aeid", zzab).toString();
            } else if (!zzbv.zzek().zzcy(str)) {
                return str;
            } else {
                zzbv.zzfh().zzf(context, zzab);
                return zzb(str, "fbs_aeid", zzab).toString();
            }
        }
    }

    public static String zzc(String str, Context context) {
        if (!zzbv.zzfh().zzs(context) || TextUtils.isEmpty(str)) {
            return str;
        }
        Object zzab = zzbv.zzfh().zzab(context);
        if (zzab == null || !zzbv.zzek().zzcy(str)) {
            return str;
        }
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzaxr)).booleanValue()) {
            return !str.contains("fbs_aeid") ? zzb(str, "fbs_aeid", zzab).toString() : str;
        } else {
            String str2 = (String) zzkb.zzik().zzd(zznk.zzaxs);
            return str.contains(str2) ? str.replace(str2, zzab) : str;
        }
    }
}
