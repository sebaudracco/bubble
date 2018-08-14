package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;

@zzadh
@TargetApi(17)
public final class zzaro<WebViewT extends zzarr & zzarz & zzasb> {
    private final zzarq zzdem;
    private final WebViewT zzden;

    private zzaro(WebViewT webViewT, zzarq com_google_android_gms_internal_ads_zzarq) {
        this.zzdem = com_google_android_gms_internal_ads_zzarq;
        this.zzden = webViewT;
    }

    public static zzaro<zzaqw> zzk(zzaqw com_google_android_gms_internal_ads_zzaqw) {
        return new zzaro(com_google_android_gms_internal_ads_zzaqw, new zzarp(com_google_android_gms_internal_ads_zzaqw));
    }
}
