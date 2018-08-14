package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.Map;
import java.util.TreeMap;

final class zzbu {
    private final String zzabb;
    private final Map<String, String> zzabc = new TreeMap();
    private String zzabd;
    private String zzabe;

    public zzbu(String str) {
        this.zzabb = str;
    }

    public final String getQuery() {
        return this.zzabd;
    }

    public final void zza(zzjj com_google_android_gms_internal_ads_zzjj, zzang com_google_android_gms_internal_ads_zzang) {
        this.zzabd = com_google_android_gms_internal_ads_zzjj.zzaqd.zzatn;
        Bundle bundle = com_google_android_gms_internal_ads_zzjj.zzaqg != null ? com_google_android_gms_internal_ads_zzjj.zzaqg.getBundle(AdMobAdapter.class.getName()) : null;
        if (bundle != null) {
            String str = (String) zzkb.zzik().zzd(zznk.zzbda);
            for (String str2 : bundle.keySet()) {
                if (str.equals(str2)) {
                    this.zzabe = bundle.getString(str2);
                } else if (str2.startsWith("csa_")) {
                    this.zzabc.put(str2.substring(4), bundle.getString(str2));
                }
            }
            this.zzabc.put("SDKVersion", com_google_android_gms_internal_ads_zzang.zzcw);
        }
    }

    public final String zzec() {
        return this.zzabe;
    }

    public final String zzed() {
        return this.zzabb;
    }

    public final Map<String, String> zzee() {
        return this.zzabc;
    }
}
