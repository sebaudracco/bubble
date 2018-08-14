package com.google.android.gms.internal.ads;

import java.util.Map;

public final class zzamb extends zzr<zzp> {
    private final zzaoj<zzp> zzctn;
    private final Map<String, String> zzcto;
    private final zzamy zzctp;

    public zzamb(String str, zzaoj<zzp> com_google_android_gms_internal_ads_zzaoj_com_google_android_gms_internal_ads_zzp) {
        this(str, null, com_google_android_gms_internal_ads_zzaoj_com_google_android_gms_internal_ads_zzp);
    }

    private zzamb(String str, Map<String, String> map, zzaoj<zzp> com_google_android_gms_internal_ads_zzaoj_com_google_android_gms_internal_ads_zzp) {
        super(0, str, new zzamc(com_google_android_gms_internal_ads_zzaoj_com_google_android_gms_internal_ads_zzp));
        this.zzcto = null;
        this.zzctn = com_google_android_gms_internal_ads_zzaoj_com_google_android_gms_internal_ads_zzp;
        this.zzctp = new zzamy();
        this.zzctp.zza(str, "GET", null, null);
    }

    protected final zzx<zzp> zza(zzp com_google_android_gms_internal_ads_zzp) {
        return zzx.zza(com_google_android_gms_internal_ads_zzp, zzap.zzb(com_google_android_gms_internal_ads_zzp));
    }

    protected final /* synthetic */ void zza(Object obj) {
        zzp com_google_android_gms_internal_ads_zzp = (zzp) obj;
        this.zzctp.zza(com_google_android_gms_internal_ads_zzp.zzab, com_google_android_gms_internal_ads_zzp.statusCode);
        zzamy com_google_android_gms_internal_ads_zzamy = this.zzctp;
        byte[] bArr = com_google_android_gms_internal_ads_zzp.data;
        if (zzamy.isEnabled() && bArr != null) {
            com_google_android_gms_internal_ads_zzamy.zzf(bArr);
        }
        this.zzctn.set(com_google_android_gms_internal_ads_zzp);
    }
}
