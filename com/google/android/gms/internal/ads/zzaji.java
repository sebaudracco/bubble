package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import org.json.JSONObject;

@zzadh
public final class zzaji {
    public final int errorCode;
    @Nullable
    public final zzjn zzacv;
    public final zzaef zzcgs;
    @Nullable
    public final JSONObject zzcob;
    public final zzwy zzcod;
    public final long zzcoh;
    public final long zzcoi;
    public final zzhs zzcoq;
    public final boolean zzcor;
    public final zzaej zzcos;

    public zzaji(zzaef com_google_android_gms_internal_ads_zzaef, zzaej com_google_android_gms_internal_ads_zzaej, zzwy com_google_android_gms_internal_ads_zzwy, zzjn com_google_android_gms_internal_ads_zzjn, int i, long j, long j2, JSONObject jSONObject, zzhs com_google_android_gms_internal_ads_zzhs, @Nullable Boolean bool) {
        this.zzcgs = com_google_android_gms_internal_ads_zzaef;
        this.zzcos = com_google_android_gms_internal_ads_zzaej;
        this.zzcod = com_google_android_gms_internal_ads_zzwy;
        this.zzacv = com_google_android_gms_internal_ads_zzjn;
        this.errorCode = i;
        this.zzcoh = j;
        this.zzcoi = j2;
        this.zzcob = jSONObject;
        this.zzcoq = com_google_android_gms_internal_ads_zzhs;
        if (bool != null) {
            this.zzcor = bool.booleanValue();
        } else if (zzamm.zzo(com_google_android_gms_internal_ads_zzaef.zzccv)) {
            this.zzcor = true;
        } else {
            this.zzcor = false;
        }
    }

    public zzaji(zzaef com_google_android_gms_internal_ads_zzaef, zzaej com_google_android_gms_internal_ads_zzaej, zzwy com_google_android_gms_internal_ads_zzwy, zzjn com_google_android_gms_internal_ads_zzjn, int i, long j, long j2, JSONObject jSONObject, zzhx com_google_android_gms_internal_ads_zzhx) {
        this.zzcgs = com_google_android_gms_internal_ads_zzaef;
        this.zzcos = com_google_android_gms_internal_ads_zzaej;
        this.zzcod = null;
        this.zzacv = null;
        this.errorCode = i;
        this.zzcoh = j;
        this.zzcoi = j2;
        this.zzcob = null;
        this.zzcoq = new zzhs(com_google_android_gms_internal_ads_zzhx);
        this.zzcor = false;
    }
}
