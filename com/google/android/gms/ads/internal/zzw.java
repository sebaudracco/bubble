package com.google.android.gms.ads.internal;

import android.content.Context;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaio;
import com.google.android.gms.internal.ads.zzaip;
import com.google.android.gms.internal.ads.zzaiu;
import com.google.android.gms.internal.ads.zzaph;
import com.google.android.gms.internal.ads.zzapo;
import com.google.android.gms.internal.ads.zzaqb;
import com.google.android.gms.internal.ads.zzaqm;
import com.google.android.gms.internal.ads.zzhx;

@zzadh
public final class zzw {
    public final zzaqm zzwy;
    public final zzaph zzwz;
    public final zzaiu zzxa;
    public final zzhx zzxb;

    private zzw(zzaqm com_google_android_gms_internal_ads_zzaqm, zzaph com_google_android_gms_internal_ads_zzaph, zzaiu com_google_android_gms_internal_ads_zzaiu, zzhx com_google_android_gms_internal_ads_zzhx) {
        this.zzwy = com_google_android_gms_internal_ads_zzaqm;
        this.zzwz = com_google_android_gms_internal_ads_zzaph;
        this.zzxa = com_google_android_gms_internal_ads_zzaiu;
        this.zzxb = com_google_android_gms_internal_ads_zzhx;
    }

    public static zzw zzc(Context context) {
        return new zzw(new zzaqb(), new zzapo(), new zzaio(new zzaip()), new zzhx(context));
    }
}
