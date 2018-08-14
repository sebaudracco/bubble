package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznw {
    private final Map<String, zznv> zzbgm = new HashMap();
    @Nullable
    private final zznx zzvr;

    public zznw(@Nullable zznx com_google_android_gms_internal_ads_zznx) {
        this.zzvr = com_google_android_gms_internal_ads_zznx;
    }

    public final void zza(String str, zznv com_google_android_gms_internal_ads_zznv) {
        this.zzbgm.put(str, com_google_android_gms_internal_ads_zznv);
    }

    public final void zza(String str, String str2, long j) {
        zznx com_google_android_gms_internal_ads_zznx = this.zzvr;
        zznv com_google_android_gms_internal_ads_zznv = (zznv) this.zzbgm.get(str2);
        String[] strArr = new String[]{str};
        if (!(com_google_android_gms_internal_ads_zznx == null || com_google_android_gms_internal_ads_zznv == null)) {
            com_google_android_gms_internal_ads_zznx.zza(com_google_android_gms_internal_ads_zznv, j, strArr);
        }
        Map map = this.zzbgm;
        zznx com_google_android_gms_internal_ads_zznx2 = this.zzvr;
        map.put(str, com_google_android_gms_internal_ads_zznx2 == null ? null : com_google_android_gms_internal_ads_zznx2.zzd(j));
    }

    @Nullable
    public final zznx zzji() {
        return this.zzvr;
    }
}
