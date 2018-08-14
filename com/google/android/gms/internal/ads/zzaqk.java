package com.google.android.gms.internal.ads;

import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

final class zzaqk implements Runnable {
    private final /* synthetic */ String val$message;
    private final /* synthetic */ String zzcce;
    private final /* synthetic */ String zzdba;
    private final /* synthetic */ zzaqh zzdbe;
    private final /* synthetic */ String zzdbf;

    zzaqk(zzaqh com_google_android_gms_internal_ads_zzaqh, String str, String str2, String str3, String str4) {
        this.zzdbe = com_google_android_gms_internal_ads_zzaqh;
        this.zzcce = str;
        this.zzdba = str2;
        this.zzdbf = str3;
        this.val$message = str4;
    }

    public final void run() {
        Map hashMap = new HashMap();
        hashMap.put(NotificationCompat.CATEGORY_EVENT, "precacheCanceled");
        hashMap.put("src", this.zzcce);
        if (!TextUtils.isEmpty(this.zzdba)) {
            hashMap.put("cachedSrc", this.zzdba);
        }
        hashMap.put("type", zzaqh.zza(this.zzdbe, this.zzdbf));
        hashMap.put("reason", this.zzdbf);
        if (!TextUtils.isEmpty(this.val$message)) {
            hashMap.put("message", this.val$message);
        }
        zzaqh.zza(this.zzdbe, "onPrecacheEvent", hashMap);
    }
}
