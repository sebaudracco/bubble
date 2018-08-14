package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;

@zzadh
public final class zza {
    private static boolean zza(Context context, Intent intent, zzt com_google_android_gms_ads_internal_overlay_zzt) {
        try {
            String str = "Launching an intent: ";
            String valueOf = String.valueOf(intent.toURI());
            zzakb.m3428v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            zzbv.zzek();
            zzakk.zza(context, intent);
            if (com_google_android_gms_ads_internal_overlay_zzt != null) {
                com_google_android_gms_ads_internal_overlay_zzt.zzbl();
            }
            return true;
        } catch (ActivityNotFoundException e) {
            zzane.zzdk(e.getMessage());
            return false;
        }
    }

    public static boolean zza(Context context, zzc com_google_android_gms_ads_internal_overlay_zzc, zzt com_google_android_gms_ads_internal_overlay_zzt) {
        if (com_google_android_gms_ads_internal_overlay_zzc == null) {
            zzane.zzdk("No intent data for launcher overlay.");
            return false;
        }
        zznk.initialize(context);
        if (com_google_android_gms_ads_internal_overlay_zzc.intent != null) {
            return zza(context, com_google_android_gms_ads_internal_overlay_zzc.intent, com_google_android_gms_ads_internal_overlay_zzt);
        }
        Intent intent = new Intent();
        if (TextUtils.isEmpty(com_google_android_gms_ads_internal_overlay_zzc.url)) {
            zzane.zzdk("Open GMSG did not contain a URL.");
            return false;
        }
        if (TextUtils.isEmpty(com_google_android_gms_ads_internal_overlay_zzc.mimeType)) {
            intent.setData(Uri.parse(com_google_android_gms_ads_internal_overlay_zzc.url));
        } else {
            intent.setDataAndType(Uri.parse(com_google_android_gms_ads_internal_overlay_zzc.url), com_google_android_gms_ads_internal_overlay_zzc.mimeType);
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty(com_google_android_gms_ads_internal_overlay_zzc.packageName)) {
            intent.setPackage(com_google_android_gms_ads_internal_overlay_zzc.packageName);
        }
        if (!TextUtils.isEmpty(com_google_android_gms_ads_internal_overlay_zzc.zzbxj)) {
            String[] split = com_google_android_gms_ads_internal_overlay_zzc.zzbxj.split(BridgeUtil.SPLIT_MARK, 2);
            if (split.length < 2) {
                String str = "Could not parse component name from open GMSG: ";
                String valueOf = String.valueOf(com_google_android_gms_ads_internal_overlay_zzc.zzbxj);
                zzane.zzdk(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        Object obj = com_google_android_gms_ads_internal_overlay_zzc.zzbxk;
        if (!TextUtils.isEmpty(obj)) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(obj);
            } catch (NumberFormatException e) {
                zzane.zzdk("Could not parse intent flags.");
                parseInt = 0;
            }
            intent.addFlags(parseInt);
        }
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbea)).booleanValue()) {
            intent.addFlags(ErrorDialogData.BINDER_CRASH);
            intent.putExtra("android.support.customtabs.extra.user_opt_out", true);
        } else {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbdz)).booleanValue()) {
                zzbv.zzek();
                zzakk.zzb(context, intent);
            }
        }
        return zza(context, intent, com_google_android_gms_ads_internal_overlay_zzt);
    }
}
