package com.google.android.gms.internal.ads;

final class zzbba {
    private static final Class<?> zzdqq = zzaco();

    private static Class<?> zzaco() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static zzbbb zzacp() {
        if (zzdqq != null) {
            try {
                return (zzbbb) zzdqq.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e) {
            }
        }
        return zzbbb.zzdqt;
    }
}
