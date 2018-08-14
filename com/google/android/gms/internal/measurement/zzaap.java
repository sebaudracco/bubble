package com.google.android.gms.internal.measurement;

final class zzaap<T> implements zzaav<T> {
    private final zzaal zzbtk;
    private final zzabj<?, ?> zzbtl;
    private final boolean zzbtm;
    private final zzzj<?> zzbtn;

    private zzaap(zzabj<?, ?> com_google_android_gms_internal_measurement_zzabj___, zzzj<?> com_google_android_gms_internal_measurement_zzzj_, zzaal com_google_android_gms_internal_measurement_zzaal) {
        this.zzbtl = com_google_android_gms_internal_measurement_zzabj___;
        this.zzbtm = com_google_android_gms_internal_measurement_zzzj_.zza(com_google_android_gms_internal_measurement_zzaal);
        this.zzbtn = com_google_android_gms_internal_measurement_zzzj_;
        this.zzbtk = com_google_android_gms_internal_measurement_zzaal;
    }

    static <T> zzaap<T> zza(zzabj<?, ?> com_google_android_gms_internal_measurement_zzabj___, zzzj<?> com_google_android_gms_internal_measurement_zzzj_, zzaal com_google_android_gms_internal_measurement_zzaal) {
        return new zzaap(com_google_android_gms_internal_measurement_zzabj___, com_google_android_gms_internal_measurement_zzzj_, com_google_android_gms_internal_measurement_zzaal);
    }

    public final boolean equals(T t, T t2) {
        return !this.zzbtl.zzu(t).equals(this.zzbtl.zzu(t2)) ? false : this.zzbtm ? this.zzbtn.zzs(t).equals(this.zzbtn.zzs(t2)) : true;
    }

    public final int hashCode(T t) {
        int hashCode = this.zzbtl.zzu(t).hashCode();
        return this.zzbtm ? (hashCode * 53) + this.zzbtn.zzs(t).hashCode() : hashCode;
    }
}
