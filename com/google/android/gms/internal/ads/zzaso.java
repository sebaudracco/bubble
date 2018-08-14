package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzaso {
    public static zzaqw zza(Context context, zzasi com_google_android_gms_internal_ads_zzasi, String str, boolean z, boolean z2, @Nullable zzci com_google_android_gms_internal_ads_zzci, zzang com_google_android_gms_internal_ads_zzang, zznx com_google_android_gms_internal_ads_zznx, zzbo com_google_android_gms_ads_internal_zzbo, zzw com_google_android_gms_ads_internal_zzw, zzhs com_google_android_gms_internal_ads_zzhs) throws zzarg {
        try {
            return (zzaqw) zzaml.zzb(new zzasp(context, com_google_android_gms_internal_ads_zzasi, str, z, z2, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzang, com_google_android_gms_internal_ads_zznx, com_google_android_gms_ads_internal_zzbo, com_google_android_gms_ads_internal_zzw, com_google_android_gms_internal_ads_zzhs));
        } catch (Throwable th) {
            zzbv.zzeo().zza(th, "AdWebViewFactory.newAdWebView2");
            zzarg com_google_android_gms_internal_ads_zzarg = new zzarg("Webview initialization failed.", th);
        }
    }
}
