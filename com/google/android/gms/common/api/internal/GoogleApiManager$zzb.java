package com.google.android.gms.common.api.internal;

import com.appnext.base.p019a.p022c.C1028c;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.Objects;

class GoogleApiManager$zzb {
    private final Feature zzdr;
    private final zzh<?> zzkn;

    private GoogleApiManager$zzb(zzh<?> com_google_android_gms_common_api_internal_zzh_, Feature feature) {
        this.zzkn = com_google_android_gms_common_api_internal_zzh_;
        this.zzdr = feature;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GoogleApiManager$zzb)) {
            return false;
        }
        GoogleApiManager$zzb googleApiManager$zzb = (GoogleApiManager$zzb) obj;
        return Objects.equal(this.zzkn, googleApiManager$zzb.zzkn) && Objects.equal(this.zzdr, googleApiManager$zzb.zzdr);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzkn, this.zzdr});
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(C1028c.gv, this.zzkn).add("feature", this.zzdr).toString();
    }
}
