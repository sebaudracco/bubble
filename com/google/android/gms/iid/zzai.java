package com.google.android.gms.iid;

import javax.annotation.concurrent.GuardedBy;

abstract class zzai {
    @GuardedBy("SdkFlagFactory.class")
    private static zzai zzcy;

    zzai() {
    }

    static synchronized zzai zzw() {
        zzai com_google_android_gms_iid_zzai;
        synchronized (zzai.class) {
            if (zzcy == null) {
                zzcy = new zzac();
            }
            com_google_android_gms_iid_zzai = zzcy;
        }
        return com_google_android_gms_iid_zzai;
    }

    abstract zzaj<Boolean> zzd(String str, boolean z);
}
