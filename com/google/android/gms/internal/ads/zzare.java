package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbo;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;
import java.util.concurrent.Callable;

final /* synthetic */ class zzare implements Callable {
    private final String zzcah;
    private final Context zzdck;
    private final zzasi zzdco;
    private final boolean zzdcp;
    private final boolean zzdcq;
    private final zzci zzdcr;
    private final zzang zzdcs;
    private final zznx zzdct;
    private final zzbo zzdcu;
    private final zzw zzdcv;
    private final zzhs zzdcw;

    zzare(Context context, zzasi com_google_android_gms_internal_ads_zzasi, String str, boolean z, boolean z2, zzci com_google_android_gms_internal_ads_zzci, zzang com_google_android_gms_internal_ads_zzang, zznx com_google_android_gms_internal_ads_zznx, zzbo com_google_android_gms_ads_internal_zzbo, zzw com_google_android_gms_ads_internal_zzw, zzhs com_google_android_gms_internal_ads_zzhs) {
        this.zzdck = context;
        this.zzdco = com_google_android_gms_internal_ads_zzasi;
        this.zzcah = str;
        this.zzdcp = z;
        this.zzdcq = z2;
        this.zzdcr = com_google_android_gms_internal_ads_zzci;
        this.zzdcs = com_google_android_gms_internal_ads_zzang;
        this.zzdct = com_google_android_gms_internal_ads_zznx;
        this.zzdcu = com_google_android_gms_ads_internal_zzbo;
        this.zzdcv = com_google_android_gms_ads_internal_zzw;
        this.zzdcw = com_google_android_gms_internal_ads_zzhs;
    }

    public final Object call() {
        Context context = this.zzdck;
        zzasi com_google_android_gms_internal_ads_zzasi = this.zzdco;
        String str = this.zzcah;
        boolean z = this.zzdcp;
        boolean z2 = this.zzdcq;
        zzaqw com_google_android_gms_internal_ads_zzarh = new zzarh(zzari.zzb(context, com_google_android_gms_internal_ads_zzasi, str, z, z2, this.zzdcr, this.zzdcs, this.zzdct, this.zzdcu, this.zzdcv, this.zzdcw));
        com_google_android_gms_internal_ads_zzarh.setWebViewClient(zzbv.zzem().zza(com_google_android_gms_internal_ads_zzarh, z2));
        com_google_android_gms_internal_ads_zzarh.setWebChromeClient(new zzaqo(com_google_android_gms_internal_ads_zzarh));
        return com_google_android_gms_internal_ads_zzarh;
    }
}
