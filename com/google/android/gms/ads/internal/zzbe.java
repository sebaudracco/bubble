package com.google.android.gms.ads.internal;

import android.os.Bundle;
import com.google.android.gms.internal.ads.zzaef;
import com.google.android.gms.internal.ads.zzaeg;
import com.google.android.gms.internal.ads.zzaji;
import com.google.android.gms.internal.ads.zzano;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zznx;
import com.google.android.gms.internal.ads.zzpb;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;

final class zzbe implements Callable<zzpb> {
    private final /* synthetic */ zzbc zzaaf;
    private final /* synthetic */ int zzaag;
    private final /* synthetic */ JSONArray zzaah;
    private final /* synthetic */ int zzaai;
    private final /* synthetic */ zzaji zzwg;

    zzbe(zzbc com_google_android_gms_ads_internal_zzbc, int i, JSONArray jSONArray, int i2, zzaji com_google_android_gms_internal_ads_zzaji) {
        this.zzaaf = com_google_android_gms_ads_internal_zzbc;
        this.zzaag = i;
        this.zzaah = jSONArray;
        this.zzaai = i2;
        this.zzwg = com_google_android_gms_internal_ads_zzaji;
    }

    public final /* synthetic */ Object call() throws Exception {
        if (this.zzaag >= this.zzaah.length()) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(this.zzaah.get(this.zzaag));
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("ads", jSONArray);
        zza com_google_android_gms_ads_internal_zzbc = new zzbc(this.zzaaf.zzvw.zzrt, this.zzaaf.zzwc, this.zzaaf.zzvw.zzacv, this.zzaaf.zzvw.zzacp, this.zzaaf.zzwh, this.zzaaf.zzvw.zzacr, true);
        zzbc.zza(this.zzaaf, this.zzaaf.zzvw, com_google_android_gms_ads_internal_zzbc.zzvw);
        com_google_android_gms_ads_internal_zzbc.zzdq();
        com_google_android_gms_ads_internal_zzbc.zza(this.zzaaf.zzvs);
        zznx com_google_android_gms_internal_ads_zznx = com_google_android_gms_ads_internal_zzbc.zzvr;
        int i = this.zzaag;
        com_google_android_gms_internal_ads_zznx.zze("num_ads_requested", String.valueOf(this.zzaai));
        com_google_android_gms_internal_ads_zznx.zze("ad_index", String.valueOf(i));
        zzaef com_google_android_gms_internal_ads_zzaef = this.zzwg.zzcgs;
        String jSONObject2 = jSONObject.toString();
        Bundle bundle = com_google_android_gms_internal_ads_zzaef.zzccv.extras != null ? new Bundle(com_google_android_gms_internal_ads_zzaef.zzccv.extras) : new Bundle();
        bundle.putString("_ad", jSONObject2);
        com_google_android_gms_ads_internal_zzbc.zza(new zzaeg(com_google_android_gms_internal_ads_zzaef.zzccu, new zzjj(com_google_android_gms_internal_ads_zzaef.zzccv.versionCode, com_google_android_gms_internal_ads_zzaef.zzccv.zzapw, bundle, com_google_android_gms_internal_ads_zzaef.zzccv.zzapx, com_google_android_gms_internal_ads_zzaef.zzccv.zzapy, com_google_android_gms_internal_ads_zzaef.zzccv.zzapz, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqa, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqb, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqc, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqd, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqe, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqf, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqg, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqh, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqi, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqj, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqk, com_google_android_gms_internal_ads_zzaef.zzccv.zzaql), com_google_android_gms_internal_ads_zzaef.zzacv, com_google_android_gms_internal_ads_zzaef.zzacp, com_google_android_gms_internal_ads_zzaef.applicationInfo, com_google_android_gms_internal_ads_zzaef.zzccw, com_google_android_gms_internal_ads_zzaef.zzccy, com_google_android_gms_internal_ads_zzaef.zzccz, com_google_android_gms_internal_ads_zzaef.zzacr, com_google_android_gms_internal_ads_zzaef.zzcda, com_google_android_gms_internal_ads_zzaef.zzads, com_google_android_gms_internal_ads_zzaef.zzcdk, com_google_android_gms_internal_ads_zzaef.zzcdc, com_google_android_gms_internal_ads_zzaef.zzcdd, com_google_android_gms_internal_ads_zzaef.zzcde, com_google_android_gms_internal_ads_zzaef.zzcdf, com_google_android_gms_internal_ads_zzaef.zzagu, com_google_android_gms_internal_ads_zzaef.zzcdg, com_google_android_gms_internal_ads_zzaef.zzcdh, com_google_android_gms_internal_ads_zzaef.zzcdi, com_google_android_gms_internal_ads_zzaef.zzcdj, com_google_android_gms_internal_ads_zzaef.zzaco, com_google_android_gms_internal_ads_zzaef.zzadj, com_google_android_gms_internal_ads_zzaef.zzcdm, com_google_android_gms_internal_ads_zzaef.zzcdn, com_google_android_gms_internal_ads_zzaef.zzcdt, com_google_android_gms_internal_ads_zzaef.zzcdo, com_google_android_gms_internal_ads_zzaef.zzcdp, com_google_android_gms_internal_ads_zzaef.zzcdq, com_google_android_gms_internal_ads_zzaef.zzcdr, zzano.zzi(com_google_android_gms_internal_ads_zzaef.zzcds), com_google_android_gms_internal_ads_zzaef.zzcdu, com_google_android_gms_internal_ads_zzaef.zzbss, com_google_android_gms_internal_ads_zzaef.zzcdv, com_google_android_gms_internal_ads_zzaef.zzcdw, com_google_android_gms_internal_ads_zzaef.zzcdx, com_google_android_gms_internal_ads_zzaef.zzadl, com_google_android_gms_internal_ads_zzaef.zzcdy, com_google_android_gms_internal_ads_zzaef.zzcdz, com_google_android_gms_internal_ads_zzaef.zzced, zzano.zzi(com_google_android_gms_internal_ads_zzaef.zzccx), com_google_android_gms_internal_ads_zzaef.zzadn, com_google_android_gms_internal_ads_zzaef.zzcee, com_google_android_gms_internal_ads_zzaef.zzcef, 1, com_google_android_gms_internal_ads_zzaef.zzceh, com_google_android_gms_internal_ads_zzaef.zzcei, com_google_android_gms_internal_ads_zzaef.zzcej, com_google_android_gms_internal_ads_zzaef.zzcek), com_google_android_gms_ads_internal_zzbc.zzvr);
        return (zzpb) com_google_android_gms_ads_internal_zzbc.zzds().get();
    }
}
