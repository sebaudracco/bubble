package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzp;
import java.util.Arrays;

@zzadh
final class zztx {
    private final Object[] mParams;

    zztx(zzjj com_google_android_gms_internal_ads_zzjj, String str, int i) {
        this.mParams = zzp.zza((String) zzkb.zzik().zzd(zznk.zzaza), com_google_android_gms_internal_ads_zzjj, str, i, null);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zztx)) {
            return false;
        }
        return Arrays.equals(this.mParams, ((zztx) obj).mParams);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.mParams);
    }

    public final String toString() {
        String arrays = Arrays.toString(this.mParams);
        return new StringBuilder(String.valueOf(arrays).length() + 24).append("[InterstitialAdPoolKey ").append(arrays).append("]").toString();
    }
}
