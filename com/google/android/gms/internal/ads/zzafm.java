package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;

@zzadh
public final class zzafm {
    public final boolean zzcgv;
    public final zzafy zzcgw = null;
    public final zzhn zzcgx;
    public final zzajc zzcgy;
    public final zzmz zzcgz;
    public final zzagh zzcha;
    public final zzwu zzchb;
    public final zzagi zzchc;
    public final zzagj zzchd;
    public final zzaav zzche;
    public final zzajg zzchf;
    public final zzafr zzchg;

    private zzafm(zzafy com_google_android_gms_internal_ads_zzafy, zzhn com_google_android_gms_internal_ads_zzhn, zzajc com_google_android_gms_internal_ads_zzajc, zzmz com_google_android_gms_internal_ads_zzmz, zzagh com_google_android_gms_internal_ads_zzagh, zzwu com_google_android_gms_internal_ads_zzwu, zzagi com_google_android_gms_internal_ads_zzagi, zzagj com_google_android_gms_internal_ads_zzagj, zzaav com_google_android_gms_internal_ads_zzaav, zzajg com_google_android_gms_internal_ads_zzajg, boolean z, zzafr com_google_android_gms_internal_ads_zzafr) {
        this.zzcgx = com_google_android_gms_internal_ads_zzhn;
        this.zzcgy = com_google_android_gms_internal_ads_zzajc;
        this.zzcgz = com_google_android_gms_internal_ads_zzmz;
        this.zzcha = com_google_android_gms_internal_ads_zzagh;
        this.zzchb = com_google_android_gms_internal_ads_zzwu;
        this.zzchc = com_google_android_gms_internal_ads_zzagi;
        this.zzchd = com_google_android_gms_internal_ads_zzagj;
        this.zzche = com_google_android_gms_internal_ads_zzaav;
        this.zzchf = com_google_android_gms_internal_ads_zzajg;
        this.zzcgv = true;
        this.zzchg = com_google_android_gms_internal_ads_zzafr;
    }

    public static zzafm zzm(Context context) {
        zzbv.zzfi().initialize(context);
        zzafr com_google_android_gms_internal_ads_zzagn = new zzagn(context);
        return new zzafm(null, new zzhq(), new zzajd(), new zzmy(), new zzagf(context, com_google_android_gms_internal_ads_zzagn.zzog()), new zzwv(), new zzagl(), new zzagm(), new zzaau(), new zzaje(), true, com_google_android_gms_internal_ads_zzagn);
    }
}
