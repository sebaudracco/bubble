package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzab;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbc;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzx;
import org.json.JSONObject;

@zzadh
public final class zzacq implements zzacm<zzaqw> {
    private final Context mContext;
    private String zzaae;
    private final zzci zzbjc;
    private final zzbc zzcbc;
    private zzanz<zzaqw> zzcbm;
    private final zzab zzcbn = new zzab(this.mContext);
    private final zzpe zzcbo;
    private final zzang zzzw;

    public zzacq(Context context, zzbc com_google_android_gms_ads_internal_zzbc, String str, zzci com_google_android_gms_internal_ads_zzci, zzang com_google_android_gms_internal_ads_zzang) {
        zzane.zzdj("Webview loading for native ads.");
        this.mContext = context;
        this.zzcbc = com_google_android_gms_ads_internal_zzbc;
        this.zzbjc = com_google_android_gms_internal_ads_zzci;
        this.zzzw = com_google_android_gms_internal_ads_zzang;
        this.zzaae = str;
        zzbv.zzel();
        zzanz zza = zzarc.zza(this.mContext, this.zzzw, (String) zzkb.zzik().zzd(zznk.zzbbp), this.zzbjc, this.zzcbc.zzbi());
        this.zzcbo = new zzpe(com_google_android_gms_ads_internal_zzbc, str);
        this.zzcbm = zzano.zza(zza, new zzacr(this), zzaoe.zzcvz);
        zzanm.zza(this.zzcbm, "WebViewNativeAdsUtil.constructor");
    }

    final /* synthetic */ zzanz zza(JSONObject jSONObject, zzaqw com_google_android_gms_internal_ads_zzaqw) throws Exception {
        jSONObject.put("ads_id", this.zzaae);
        com_google_android_gms_internal_ads_zzaqw.zzb("google.afma.nativeAds.handleDownloadedImpressionPing", jSONObject);
        return zzano.zzi(new JSONObject());
    }

    public final void zza(String str, zzv<? super zzaqw> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw) {
        zzano.zza(this.zzcbm, new zzacx(this, str, com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw), zzaoe.zzcvy);
    }

    public final void zza(String str, JSONObject jSONObject) {
        zzano.zza(this.zzcbm, new zzacz(this, str, jSONObject), zzaoe.zzcvy);
    }

    final /* synthetic */ zzanz zzb(JSONObject jSONObject, zzaqw com_google_android_gms_internal_ads_zzaqw) throws Exception {
        jSONObject.put("ads_id", this.zzaae);
        com_google_android_gms_internal_ads_zzaqw.zzb("google.afma.nativeAds.handleImpressionPing", jSONObject);
        return zzano.zzi(new JSONObject());
    }

    public final void zzb(String str, zzv<? super zzaqw> com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw) {
        zzano.zza(this.zzcbm, new zzacy(this, str, com_google_android_gms_ads_internal_gmsg_zzv__super_com_google_android_gms_internal_ads_zzaqw), zzaoe.zzcvy);
    }

    final /* synthetic */ zzanz zzc(JSONObject jSONObject, zzaqw com_google_android_gms_internal_ads_zzaqw) throws Exception {
        jSONObject.put("ads_id", this.zzaae);
        com_google_android_gms_internal_ads_zzaqw.zzb("google.afma.nativeAds.handleClickGmsg", jSONObject);
        return zzano.zzi(new JSONObject());
    }

    final /* synthetic */ zzanz zzd(JSONObject jSONObject, zzaqw com_google_android_gms_internal_ads_zzaqw) throws Exception {
        jSONObject.put("ads_id", this.zzaae);
        zzanz com_google_android_gms_internal_ads_zzaoj = new zzaoj();
        com_google_android_gms_internal_ads_zzaqw.zza("/nativeAdPreProcess", new zzacw(this, com_google_android_gms_internal_ads_zzaqw, com_google_android_gms_internal_ads_zzaoj));
        com_google_android_gms_internal_ads_zzaqw.zzb("google.afma.nativeAds.preProcessJsonGmsg", jSONObject);
        return com_google_android_gms_internal_ads_zzaoj;
    }

    final /* synthetic */ zzanz zzh(zzaqw com_google_android_gms_internal_ads_zzaqw) throws Exception {
        zzane.zzdj("Javascript has loaded for native ads.");
        com_google_android_gms_internal_ads_zzaqw.zzuf().zza(this.zzcbc, this.zzcbc, this.zzcbc, this.zzcbc, this.zzcbc, false, null, new zzx(this.mContext, null, null), null, null);
        com_google_android_gms_internal_ads_zzaqw.zza("/logScionEvent", this.zzcbn);
        com_google_android_gms_internal_ads_zzaqw.zza("/logScionEvent", this.zzcbo);
        return zzano.zzi(com_google_android_gms_internal_ads_zzaqw);
    }

    public final zzanz<JSONObject> zzh(JSONObject jSONObject) {
        return zzano.zza(this.zzcbm, new zzacs(this, jSONObject), zzaoe.zzcvy);
    }

    public final zzanz<JSONObject> zzi(JSONObject jSONObject) {
        return zzano.zza(this.zzcbm, new zzact(this, jSONObject), zzaoe.zzcvy);
    }

    public final zzanz<JSONObject> zzj(JSONObject jSONObject) {
        return zzano.zza(this.zzcbm, new zzacu(this, jSONObject), zzaoe.zzcvy);
    }

    public final zzanz<JSONObject> zzk(JSONObject jSONObject) {
        return zzano.zza(this.zzcbm, new zzacv(this, jSONObject), zzaoe.zzcvy);
    }

    public final void zzmc() {
        zzano.zza(this.zzcbm, new zzada(this), zzaoe.zzcvy);
    }
}
