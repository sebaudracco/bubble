package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebResourceResponse;
import com.google.android.gms.ads.internal.zzbv;
import java.io.InputStream;
import java.util.Map;

@TargetApi(21)
public final class zzala extends zzakz {
    public final WebResourceResponse zza(String str, String str2, int i, String str3, Map<String, String> map, InputStream inputStream) {
        return new WebResourceResponse(str, str2, i, str3, map, inputStream);
    }

    public final zzaqx zza(zzaqw com_google_android_gms_internal_ads_zzaqw, boolean z) {
        return new zzarv(com_google_android_gms_internal_ads_zzaqw, z);
    }

    public final CookieManager zzax(Context context) {
        CookieManager cookieManager = null;
        if (!zzrp()) {
            try {
                cookieManager = CookieManager.getInstance();
            } catch (Throwable th) {
                zzakb.zzb("Failed to obtain CookieManager.", th);
                zzbv.zzeo().zza(th, "ApiLevelUtil.getCookieManager");
            }
        }
        return cookieManager;
    }

    public final int zzrq() {
        return 16974374;
    }
}
