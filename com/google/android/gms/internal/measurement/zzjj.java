package com.google.android.gms.internal.measurement;

import android.support.annotation.WorkerThread;

final class zzjj extends zzem {
    private final /* synthetic */ zzjh zzapx;

    zzjj(zzjh com_google_android_gms_internal_measurement_zzjh, zzhi com_google_android_gms_internal_measurement_zzhi) {
        this.zzapx = com_google_android_gms_internal_measurement_zzjh;
        super(com_google_android_gms_internal_measurement_zzhi);
    }

    @WorkerThread
    public final void run() {
        this.zzapx.zzkk();
    }
}
