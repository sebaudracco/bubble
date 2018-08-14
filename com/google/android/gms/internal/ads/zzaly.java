package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzaly extends zzav {
    private final /* synthetic */ byte[] zzctk;
    private final /* synthetic */ Map zzctl;
    private final /* synthetic */ zzamy zzctm;

    zzaly(zzalt com_google_android_gms_internal_ads_zzalt, int i, String str, zzz com_google_android_gms_internal_ads_zzz, zzy com_google_android_gms_internal_ads_zzy, byte[] bArr, Map map, zzamy com_google_android_gms_internal_ads_zzamy) {
        this.zzctk = bArr;
        this.zzctl = map;
        this.zzctm = com_google_android_gms_internal_ads_zzamy;
        super(i, str, com_google_android_gms_internal_ads_zzz, com_google_android_gms_internal_ads_zzy);
    }

    public final Map<String, String> getHeaders() throws zza {
        return this.zzctl == null ? super.getHeaders() : this.zzctl;
    }

    protected final /* synthetic */ void zza(Object obj) {
        zzh((String) obj);
    }

    public final byte[] zzg() throws zza {
        return this.zzctk == null ? super.zzg() : this.zzctk;
    }

    protected final void zzh(String str) {
        this.zzctm.zzdg(str);
        super.zzh(str);
    }
}
