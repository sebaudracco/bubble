package com.google.android.gms.ads.identifier;

import com.google.android.gms.common.annotation.KeepForSdkWithMembers;

@KeepForSdkWithMembers
public final class AdvertisingIdClient$Info {
    private final String zzq;
    private final boolean zzr;

    public AdvertisingIdClient$Info(String str, boolean z) {
        this.zzq = str;
        this.zzr = z;
    }

    public final String getId() {
        return this.zzq;
    }

    public final boolean isLimitAdTrackingEnabled() {
        return this.zzr;
    }

    public final String toString() {
        String str = this.zzq;
        return new StringBuilder(String.valueOf(str).length() + 7).append("{").append(str).append("}").append(this.zzr).toString();
    }
}
