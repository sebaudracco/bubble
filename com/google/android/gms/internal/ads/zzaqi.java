package com.google.android.gms.internal.ads;

import android.support.v4.app.NotificationCompat;
import java.util.HashMap;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

final class zzaqi implements Runnable {
    private final /* synthetic */ String zzcce;
    private final /* synthetic */ String zzdba;
    private final /* synthetic */ int zzdbb;
    private final /* synthetic */ int zzdbc;
    private final /* synthetic */ boolean zzdbd = false;
    private final /* synthetic */ zzaqh zzdbe;

    zzaqi(zzaqh com_google_android_gms_internal_ads_zzaqh, String str, String str2, int i, int i2, boolean z) {
        this.zzdbe = com_google_android_gms_internal_ads_zzaqh;
        this.zzcce = str;
        this.zzdba = str2;
        this.zzdbb = i;
        this.zzdbc = i2;
    }

    public final void run() {
        Map hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "precacheProgress");
        hashMap.put("src", this.zzcce);
        hashMap.put("cachedSrc", this.zzdba);
        hashMap.put("bytesLoaded", Integer.toString(this.zzdbb));
        hashMap.put("totalBytes", Integer.toString(this.zzdbc));
        hashMap.put("cacheReady", this.zzdbd ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
        zzaqh.zza(this.zzdbe, "onPrecacheEvent", hashMap);
    }
}
