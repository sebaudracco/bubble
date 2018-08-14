package com.google.android.gms.internal.clearcut;

final class zzdf implements zzdn {
    private zzdn[] zzma;

    zzdf(zzdn... com_google_android_gms_internal_clearcut_zzdnArr) {
        this.zzma = com_google_android_gms_internal_clearcut_zzdnArr;
    }

    public final boolean zza(Class<?> cls) {
        for (zzdn zza : this.zzma) {
            if (zza.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    public final zzdm zzb(Class<?> cls) {
        for (zzdn com_google_android_gms_internal_clearcut_zzdn : this.zzma) {
            if (com_google_android_gms_internal_clearcut_zzdn.zza(cls)) {
                return com_google_android_gms_internal_clearcut_zzdn.zzb(cls);
            }
        }
        String str = "No factory is available for message type: ";
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }
}
