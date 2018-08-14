package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzw;

final /* synthetic */ class zzard implements zzanj {
    private final zzci zzbqd;
    private final Context zzdck;
    private final zzang zzdcl;
    private final zzw zzdcm;
    private final String zzdcn;

    zzard(Context context, zzci com_google_android_gms_internal_ads_zzci, zzang com_google_android_gms_internal_ads_zzang, zzw com_google_android_gms_ads_internal_zzw, String str) {
        this.zzdck = context;
        this.zzbqd = com_google_android_gms_internal_ads_zzci;
        this.zzdcl = com_google_android_gms_internal_ads_zzang;
        this.zzdcm = com_google_android_gms_ads_internal_zzw;
        this.zzdcn = str;
    }

    public final zzanz zzc(Object obj) {
        Context context = this.zzdck;
        zzci com_google_android_gms_internal_ads_zzci = this.zzbqd;
        zzang com_google_android_gms_internal_ads_zzang = this.zzdcl;
        zzw com_google_android_gms_ads_internal_zzw = this.zzdcm;
        String str = this.zzdcn;
        zzbv.zzel();
        zzaqw zza = zzarc.zza(context, zzasi.zzvq(), "", false, false, com_google_android_gms_internal_ads_zzci, com_google_android_gms_internal_ads_zzang, null, null, com_google_android_gms_ads_internal_zzw, zzhs.zzhm());
        zzanz zzj = zzaoi.zzj(zza);
        zza.zzuf().zza(new zzarf(zzj));
        zza.loadUrl(str);
        return zzj;
    }
}
