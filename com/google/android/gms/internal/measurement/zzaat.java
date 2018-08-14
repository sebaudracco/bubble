package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzaat {
    private static final zzaat zzbtq = new zzaat();
    private final zzaaw zzbtr;
    private final ConcurrentMap<Class<?>, zzaav<?>> zzbts = new ConcurrentHashMap();

    private zzaat() {
        zzaaw com_google_android_gms_internal_measurement_zzaaw = null;
        String[] strArr = new String[]{"com.google.protobuf.AndroidProto3SchemaFactory"};
        for (int i = 0; i <= 0; i++) {
            com_google_android_gms_internal_measurement_zzaaw = zzfl(strArr[0]);
            if (com_google_android_gms_internal_measurement_zzaaw != null) {
                break;
            }
        }
        if (com_google_android_gms_internal_measurement_zzaaw == null) {
            com_google_android_gms_internal_measurement_zzaaw = new zzaad();
        }
        this.zzbtr = com_google_android_gms_internal_measurement_zzaaw;
    }

    private static zzaaw zzfl(String str) {
        try {
            return (zzaaw) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public static zzaat zzud() {
        return zzbtq;
    }

    public final <T> zzaav<T> zzt(T t) {
        Class cls = t.getClass();
        zzzr.zza(cls, "messageType");
        zzaav<T> com_google_android_gms_internal_measurement_zzaav_T = (zzaav) this.zzbts.get(cls);
        if (com_google_android_gms_internal_measurement_zzaav_T != null) {
            return com_google_android_gms_internal_measurement_zzaav_T;
        }
        zzaav<T> zzg = this.zzbtr.zzg(cls);
        zzzr.zza(cls, "messageType");
        zzzr.zza(zzg, "schema");
        com_google_android_gms_internal_measurement_zzaav_T = (zzaav) this.zzbts.putIfAbsent(cls, zzg);
        return com_google_android_gms_internal_measurement_zzaav_T != null ? com_google_android_gms_internal_measurement_zzaav_T : zzg;
    }
}
