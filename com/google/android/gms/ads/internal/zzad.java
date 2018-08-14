package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajl;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzanm;
import com.google.android.gms.internal.ads.zzano;
import com.google.android.gms.internal.ads.zzanz;
import com.google.android.gms.internal.ads.zzaoe;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzwf;
import com.google.android.gms.internal.ads.zzwk;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzad {
    private Context mContext;
    private final Object mLock = new Object();
    private long zzxm = 0;

    public final void zza(Context context, zzang com_google_android_gms_internal_ads_zzang, String str, @Nullable Runnable runnable) {
        zza(context, com_google_android_gms_internal_ads_zzang, true, null, str, null, runnable);
    }

    @VisibleForTesting
    final void zza(Context context, zzang com_google_android_gms_internal_ads_zzang, boolean z, @Nullable zzajl com_google_android_gms_internal_ads_zzajl, String str, @Nullable String str2, @Nullable Runnable runnable) {
        if (zzbv.zzer().elapsedRealtime() - this.zzxm < 5000) {
            zzane.zzdk("Not retrying to fetch app settings");
            return;
        }
        Object obj;
        this.zzxm = zzbv.zzer().elapsedRealtime();
        if (com_google_android_gms_internal_ads_zzajl == null) {
            obj = 1;
        } else {
            if ((zzbv.zzer().currentTimeMillis() - com_google_android_gms_internal_ads_zzajl.zzps() > ((Long) zzkb.zzik().zzd(zznk.zzbcu)).longValue() ? 1 : null) == null && com_google_android_gms_internal_ads_zzajl.zzpt()) {
                obj = null;
            } else {
                int i = 1;
            }
        }
        if (obj == null) {
            return;
        }
        if (context == null) {
            zzane.zzdk("Context not provided to fetch application settings");
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            zzane.zzdk("App settings could not be fetched. Required parameters missing");
        } else {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            this.mContext = applicationContext;
            zzwf zza = zzbv.zzey().zzb(this.mContext, com_google_android_gms_internal_ads_zzang).zza("google.afma.config.fetchAppSettings", zzwk.zzbrc, zzwk.zzbrc);
            try {
                JSONObject jSONObject = new JSONObject();
                if (!TextUtils.isEmpty(str)) {
                    jSONObject.put("app_id", str);
                } else if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("ad_unit_id", str2);
                }
                jSONObject.put("is_init", z);
                jSONObject.put("pn", context.getPackageName());
                zzanz zzf = zza.zzf(jSONObject);
                zzanz zza2 = zzano.zza(zzf, zzae.zzxn, zzaoe.zzcvz);
                if (runnable != null) {
                    zzf.zza(runnable, zzaoe.zzcvz);
                }
                zzanm.zza(zza2, "ConfigLoader.maybeFetchNewAppSettings");
            } catch (Throwable e) {
                zzane.zzb("Error requesting application settings", e);
            }
        }
    }
}
