package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.Objects;

@zzadh
public final class zzagp extends zzagv {
    private final String zzclb;
    private final int zzclc;

    public zzagp(String str, int i) {
        this.zzclb = str;
        this.zzclc = i;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzagp)) {
            return false;
        }
        zzagp com_google_android_gms_internal_ads_zzagp = (zzagp) obj;
        return Objects.equal(this.zzclb, com_google_android_gms_internal_ads_zzagp.zzclb) && Objects.equal(Integer.valueOf(this.zzclc), Integer.valueOf(com_google_android_gms_internal_ads_zzagp.zzclc));
    }

    public final int getAmount() {
        return this.zzclc;
    }

    public final String getType() {
        return this.zzclb;
    }
}
