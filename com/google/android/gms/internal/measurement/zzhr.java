package com.google.android.gms.internal.measurement;

import com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty;

final class zzhr implements Runnable {
    private final /* synthetic */ zzhk zzanw;
    private final /* synthetic */ AppMeasurement$ConditionalUserProperty zzaob;

    zzhr(zzhk com_google_android_gms_internal_measurement_zzhk, AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty) {
        this.zzanw = com_google_android_gms_internal_measurement_zzhk;
        this.zzaob = appMeasurement$ConditionalUserProperty;
    }

    public final void run() {
        this.zzanw.zzb(this.zzaob);
    }
}
