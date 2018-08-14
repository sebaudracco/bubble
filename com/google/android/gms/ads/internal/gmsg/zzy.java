package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzane;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzadh
public final class zzy implements zzv<Object> {
    private final zzz zzbmu;

    public zzy(zzz com_google_android_gms_ads_internal_gmsg_zzz) {
        this.zzbmu = com_google_android_gms_ads_internal_gmsg_zzz;
    }

    public final void zza(Object obj, Map<String, String> map) {
        float parseFloat;
        boolean equals = SchemaSymbols.ATTVAL_TRUE_1.equals(map.get("transparentBackground"));
        boolean equals2 = SchemaSymbols.ATTVAL_TRUE_1.equals(map.get("blur"));
        try {
            if (map.get("blurRadius") != null) {
                parseFloat = Float.parseFloat((String) map.get("blurRadius"));
                this.zzbmu.zzd(equals);
                this.zzbmu.zza(equals2, parseFloat);
            }
        } catch (Throwable e) {
            zzane.zzb("Fail to parse float", e);
        }
        parseFloat = 0.0f;
        this.zzbmu.zzd(equals);
        this.zzbmu.zza(equals2, parseFloat);
    }
}
