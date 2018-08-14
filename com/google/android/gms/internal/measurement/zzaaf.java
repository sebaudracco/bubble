package com.google.android.gms.internal.measurement;

final class zzaaf implements zzaak {
    private zzaak[] zzbtf;

    zzaaf(zzaak... com_google_android_gms_internal_measurement_zzaakArr) {
        this.zzbtf = com_google_android_gms_internal_measurement_zzaakArr;
    }

    public final boolean zzd(Class<?> cls) {
        for (zzaak zzd : this.zzbtf) {
            if (zzd.zzd(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzaaj zze(Class<?> cls) {
        for (zzaak com_google_android_gms_internal_measurement_zzaak : this.zzbtf) {
            if (com_google_android_gms_internal_measurement_zzaak.zzd(cls)) {
                return com_google_android_gms_internal_measurement_zzaak.zze(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
