package com.google.android.gms.internal.wearable;

import java.util.Arrays;

final class zzv {
    final int tag;
    final byte[] zzhm;

    zzv(int i, byte[] bArr) {
        this.tag = i;
        this.zzhm = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzv)) {
            return false;
        }
        zzv com_google_android_gms_internal_wearable_zzv = (zzv) obj;
        return this.tag == com_google_android_gms_internal_wearable_zzv.tag && Arrays.equals(this.zzhm, com_google_android_gms_internal_wearable_zzv.zzhm);
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzhm);
    }
}
