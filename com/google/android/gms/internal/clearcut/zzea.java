package com.google.android.gms.internal.clearcut;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzea {
    private static final zzea zznc = new zzea();
    private final zzeg zznd;
    private final ConcurrentMap<Class<?>, zzef<?>> zzne = new ConcurrentHashMap();

    private zzea() {
        zzeg com_google_android_gms_internal_clearcut_zzeg = null;
        String[] strArr = new String[]{"com.google.protobuf.AndroidProto3SchemaFactory"};
        for (int i = 0; i <= 0; i++) {
            com_google_android_gms_internal_clearcut_zzeg = zzk(strArr[0]);
            if (com_google_android_gms_internal_clearcut_zzeg != null) {
                break;
            }
        }
        if (com_google_android_gms_internal_clearcut_zzeg == null) {
            com_google_android_gms_internal_clearcut_zzeg = new zzdd();
        }
        this.zznd = com_google_android_gms_internal_clearcut_zzeg;
    }

    public static zzea zzcm() {
        return zznc;
    }

    private static zzeg zzk(String str) {
        try {
            return (zzeg) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public final <T> zzef<T> zze(Class<T> cls) {
        zzci.zza((Object) cls, "messageType");
        zzef<T> com_google_android_gms_internal_clearcut_zzef_T = (zzef) this.zzne.get(cls);
        if (com_google_android_gms_internal_clearcut_zzef_T != null) {
            return com_google_android_gms_internal_clearcut_zzef_T;
        }
        zzef<T> zzd = this.zznd.zzd(cls);
        zzci.zza((Object) cls, "messageType");
        zzci.zza((Object) zzd, "schema");
        com_google_android_gms_internal_clearcut_zzef_T = (zzef) this.zzne.putIfAbsent(cls, zzd);
        return com_google_android_gms_internal_clearcut_zzef_T != null ? com_google_android_gms_internal_clearcut_zzef_T : zzd;
    }

    public final <T> zzef<T> zzp(T t) {
        return zze(t.getClass());
    }
}
