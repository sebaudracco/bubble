package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.gms.ads.formats.PublisherAdViewOptions;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkh;
import com.google.android.gms.internal.ads.zzkk;
import com.google.android.gms.internal.ads.zzko;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzpl;
import com.google.android.gms.internal.ads.zzqw;
import com.google.android.gms.internal.ads.zzqz;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzrf;
import com.google.android.gms.internal.ads.zzri;
import com.google.android.gms.internal.ads.zzrl;
import com.google.android.gms.internal.ads.zzxn;

@zzadh
public final class zzak extends zzko {
    private final Context mContext;
    private final zzw zzwc;
    private final zzxn zzwh;
    private zzkh zzxs;
    private zzjn zzxx;
    private PublisherAdViewOptions zzxy;
    private zzpl zzyb;
    private zzlg zzyd;
    private final String zzye;
    private final zzang zzyf;
    private zzqw zzyk;
    private zzrl zzyl;
    private zzqz zzym;
    private SimpleArrayMap<String, zzrc> zzyn = new SimpleArrayMap();
    private SimpleArrayMap<String, zzrf> zzyo = new SimpleArrayMap();
    private zzri zzyp;

    public zzak(Context context, String str, zzxn com_google_android_gms_internal_ads_zzxn, zzang com_google_android_gms_internal_ads_zzang, zzw com_google_android_gms_ads_internal_zzw) {
        this.mContext = context;
        this.zzye = str;
        this.zzwh = com_google_android_gms_internal_ads_zzxn;
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzwc = com_google_android_gms_ads_internal_zzw;
    }

    public final void zza(PublisherAdViewOptions publisherAdViewOptions) {
        this.zzxy = publisherAdViewOptions;
    }

    public final void zza(zzpl com_google_android_gms_internal_ads_zzpl) {
        this.zzyb = com_google_android_gms_internal_ads_zzpl;
    }

    public final void zza(zzqw com_google_android_gms_internal_ads_zzqw) {
        this.zzyk = com_google_android_gms_internal_ads_zzqw;
    }

    public final void zza(zzqz com_google_android_gms_internal_ads_zzqz) {
        this.zzym = com_google_android_gms_internal_ads_zzqz;
    }

    public final void zza(zzri com_google_android_gms_internal_ads_zzri, zzjn com_google_android_gms_internal_ads_zzjn) {
        this.zzyp = com_google_android_gms_internal_ads_zzri;
        this.zzxx = com_google_android_gms_internal_ads_zzjn;
    }

    public final void zza(zzrl com_google_android_gms_internal_ads_zzrl) {
        this.zzyl = com_google_android_gms_internal_ads_zzrl;
    }

    public final void zza(String str, zzrf com_google_android_gms_internal_ads_zzrf, zzrc com_google_android_gms_internal_ads_zzrc) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
        }
        this.zzyo.put(str, com_google_android_gms_internal_ads_zzrf);
        this.zzyn.put(str, com_google_android_gms_internal_ads_zzrc);
    }

    public final void zzb(zzkh com_google_android_gms_internal_ads_zzkh) {
        this.zzxs = com_google_android_gms_internal_ads_zzkh;
    }

    public final void zzb(zzlg com_google_android_gms_internal_ads_zzlg) {
        this.zzyd = com_google_android_gms_internal_ads_zzlg;
    }

    public final zzkk zzdh() {
        return new zzah(this.mContext, this.zzye, this.zzwh, this.zzyf, this.zzxs, this.zzyk, this.zzyl, this.zzym, this.zzyo, this.zzyn, this.zzyb, this.zzyd, this.zzwc, this.zzyp, this.zzxx, this.zzxy);
    }
}
