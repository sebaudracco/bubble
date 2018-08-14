package com.google.android.gms.wearable.internal;

import com.google.android.gms.wearable.CapabilityApi.CapabilityListener;
import com.google.android.gms.wearable.CapabilityInfo;

final class zzv implements CapabilityListener {
    private final String zzbc;
    private final CapabilityListener zzbs;

    zzv(CapabilityListener capabilityListener, String str) {
        this.zzbs = capabilityListener;
        this.zzbc = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzv com_google_android_gms_wearable_internal_zzv = (zzv) obj;
        return this.zzbs.equals(com_google_android_gms_wearable_internal_zzv.zzbs) ? this.zzbc.equals(com_google_android_gms_wearable_internal_zzv.zzbc) : false;
    }

    public final int hashCode() {
        return (this.zzbs.hashCode() * 31) + this.zzbc.hashCode();
    }

    public final void onCapabilityChanged(CapabilityInfo capabilityInfo) {
        this.zzbs.onCapabilityChanged(capabilityInfo);
    }
}
