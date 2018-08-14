package com.google.android.gms.internal.ads;

public enum zzbbw {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, Integer.valueOf(0)),
    LONG(Long.TYPE, Long.class, Long.valueOf(0)),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.valueOf(false)),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzbah.class, zzbah.class, zzbah.zzdpq),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);
    
    private final Class<?> zzdve;
    private final Class<?> zzdvf;
    private final Object zzdvg;

    private zzbbw(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzdve = cls;
        this.zzdvf = cls2;
        this.zzdvg = obj;
    }

    public final Class<?> zzadt() {
        return this.zzdvf;
    }
}
