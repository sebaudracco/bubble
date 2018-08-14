package com.google.android.gms.internal.ads;

final class zzbac {
    private static final Class<?> zzdpj = zzel("libcore.io.Memory");
    private static final boolean zzdpk = (zzel("org.robolectric.Robolectric") != null);

    static boolean zzabb() {
        return (zzdpj == null || zzdpk) ? false : true;
    }

    static Class<?> zzabc() {
        return zzdpj;
    }

    private static <T> Class<T> zzel(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }
}
