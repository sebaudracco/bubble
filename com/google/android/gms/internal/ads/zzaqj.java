package com.google.android.gms.internal.ads;

import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;

final class zzaqj implements Runnable {
    private final /* synthetic */ String zzcce;
    private final /* synthetic */ String zzdba;
    private final /* synthetic */ int zzdbc;
    private final /* synthetic */ zzaqh zzdbe;

    zzaqj(zzaqh com_google_android_gms_internal_ads_zzaqh, String str, String str2, int i) {
        this.zzdbe = com_google_android_gms_internal_ads_zzaqh;
        this.zzcce = str;
        this.zzdba = str2;
        this.zzdbc = i;
    }

    public final void run() {
        Map hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "precacheComplete");
        hashMap.put("src", this.zzcce);
        hashMap.put("cachedSrc", this.zzdba);
        hashMap.put("totalBytes", Integer.toString(this.zzdbc));
        zzaqh.zza(this.zzdbe, "onPrecacheEvent", hashMap);
    }
}
