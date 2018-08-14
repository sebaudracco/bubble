package com.google.android.gms.internal.ads;

public enum zzbes {
    DOUBLE(zzbex.DOUBLE, 1),
    FLOAT(zzbex.FLOAT, 5),
    INT64(zzbex.LONG, 0),
    UINT64(zzbex.LONG, 0),
    INT32(zzbex.INT, 0),
    FIXED64(zzbex.LONG, 1),
    FIXED32(zzbex.INT, 5),
    BOOL(zzbex.BOOLEAN, 0),
    STRING(zzbex.STRING, 2),
    GROUP(zzbex.MESSAGE, 3),
    MESSAGE(zzbex.MESSAGE, 2),
    BYTES(zzbex.BYTE_STRING, 2),
    UINT32(zzbex.INT, 0),
    ENUM(zzbex.ENUM, 0),
    SFIXED32(zzbex.INT, 5),
    SFIXED64(zzbex.LONG, 1),
    SINT32(zzbex.INT, 0),
    SINT64(zzbex.LONG, 0);
    
    private final zzbex zzeas;
    private final int zzeat;

    private zzbes(zzbex com_google_android_gms_internal_ads_zzbex, int i) {
        this.zzeas = com_google_android_gms_internal_ads_zzbex;
        this.zzeat = i;
    }

    public final zzbex zzagl() {
        return this.zzeas;
    }

    public final int zzagm() {
        return this.zzeat;
    }
}
