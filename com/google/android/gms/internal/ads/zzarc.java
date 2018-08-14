package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzw;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzarc {
    public static zzanz<zzaqw> zza(Context context, zzang com_google_android_gms_internal_ads_zzang, String str, zzci com_google_android_gms_internal_ads_zzci, zzw com_google_android_gms_ads_internal_zzw) {
        return zzano.zza(zzano.zzi(null), new zzard(context, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzang, com_google_android_gms_ads_internal_zzw, str), zzaoe.zzcvy);
    }

    public static zzaqw zza(Context context, zzasi com_google_android_gms_internal_ads_zzasi, String str, boolean z, boolean z2, @Nullable zzci com_google_android_gms_internal_ads_zzci, zzang com_google_android_gms_internal_ads_zzang, zznx com_google_android_gms_internal_ads_zznx, zzbo com_google_android_gms_ads_internal_zzbo, zzw com_google_android_gms_ads_internal_zzw, zzhs com_google_android_gms_internal_ads_zzhs) throws zzarg {
        zznk.initialize(context);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzaxy)).booleanValue()) {
            return zzaso.zza(context, com_google_android_gms_internal_ads_zzasi, str, z2, z, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzang, com_google_android_gms_internal_ads_zznx, com_google_android_gms_ads_internal_zzbo, com_google_android_gms_ads_internal_zzw, com_google_android_gms_internal_ads_zzhs);
        }
        try {
            return (zzaqw) zzaml.zzb(new zzare(context, com_google_android_gms_internal_ads_zzasi, str, z, z2, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzang, com_google_android_gms_internal_ads_zznx, com_google_android_gms_ads_internal_zzbo, com_google_android_gms_ads_internal_zzw, com_google_android_gms_internal_ads_zzhs));
        } catch (Throwable th) {
            zzarg com_google_android_gms_internal_ads_zzarg = new zzarg("Webview initialization failed.", th);
        }
    }
}
