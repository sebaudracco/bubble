package com.google.android.gms.internal.ads;

import com.facebook.ads.AudienceNetworkActivity;

final class zzva implements Runnable {
    private final /* synthetic */ String zzbpt;
    private final /* synthetic */ zzuw zzbpu;

    zzva(zzuw com_google_android_gms_internal_ads_zzuw, String str) {
        this.zzbpu = com_google_android_gms_internal_ads_zzuw;
        this.zzbpt = str;
    }

    public final void run() {
        zzuw.zza(this.zzbpu).loadData(this.zzbpt, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
    }
}
