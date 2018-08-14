package com.google.android.gms.internal.ads;

import android.support.v4.internal.view.SupportMenu;

final class zzbbc {
    private final int number;
    private final Object object;

    zzbbc(Object obj, int i) {
        this.object = obj;
        this.number = i;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzbbc)) {
            return false;
        }
        zzbbc com_google_android_gms_internal_ads_zzbbc = (zzbbc) obj;
        return this.object == com_google_android_gms_internal_ads_zzbbc.object && this.number == com_google_android_gms_internal_ads_zzbbc.number;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.object) * SupportMenu.USER_MASK) + this.number;
    }
}
