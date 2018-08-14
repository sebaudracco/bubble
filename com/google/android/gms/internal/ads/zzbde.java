package com.google.android.gms.internal.ads;

final class zzbde {
    private static final zzbdc zzdwy = zzaen();
    private static final zzbdc zzdwz = new zzbdd();

    static zzbdc zzael() {
        return zzdwy;
    }

    static zzbdc zzaem() {
        return zzdwz;
    }

    private static zzbdc zzaen() {
        try {
            return (zzbdc) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
