package com.google.android.gms.ads.internal;

import android.webkit.CookieManager;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.concurrent.Callable;

final class zzg implements Callable<String> {
    private final /* synthetic */ zzd zzwk;

    zzg(zzd com_google_android_gms_ads_internal_zzd) {
        this.zzwk = com_google_android_gms_ads_internal_zzd;
    }

    public final /* synthetic */ Object call() throws Exception {
        String str = "";
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbdj)).booleanValue()) {
            CookieManager zzax = zzbv.zzem().zzax(this.zzwk.zzvw.zzrt);
            if (zzax != null) {
                return zzax.getCookie("googleads.g.doubleclick.net");
            }
        }
        return str;
    }
}
