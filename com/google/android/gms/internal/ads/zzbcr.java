package com.google.android.gms.internal.ads;

final class zzbcr {
    private static final zzbcp zzdwd = zzaeg();
    private static final zzbcp zzdwe = new zzbcq();

    static zzbcp zzaee() {
        return zzdwd;
    }

    static zzbcp zzaef() {
        return zzdwe;
    }

    private static zzbcp zzaeg() {
        try {
            return (zzbcp) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
