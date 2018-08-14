package com.google.android.gms.internal.ads;

import org.json.JSONObject;

@zzadh
public final class zzaah {
    private final boolean zzbwr;
    private final boolean zzbws;
    private final boolean zzbwt;
    private final boolean zzbwu;
    private final boolean zzbwv;

    private zzaah(zzaaj com_google_android_gms_internal_ads_zzaaj) {
        this.zzbwr = zzaaj.zza(com_google_android_gms_internal_ads_zzaaj);
        this.zzbws = zzaaj.zzb(com_google_android_gms_internal_ads_zzaaj);
        this.zzbwt = zzaaj.zzc(com_google_android_gms_internal_ads_zzaaj);
        this.zzbwu = zzaaj.zzd(com_google_android_gms_internal_ads_zzaaj);
        this.zzbwv = zzaaj.zze(com_google_android_gms_internal_ads_zzaaj);
    }

    public final JSONObject zzng() {
        try {
            return new JSONObject().put("sms", this.zzbwr).put("tel", this.zzbws).put("calendar", this.zzbwt).put("storePicture", this.zzbwu).put("inlineVideo", this.zzbwv);
        } catch (Throwable e) {
            zzane.zzb("Error occured while obtaining the MRAID capabilities.", e);
            return null;
        }
    }
}
