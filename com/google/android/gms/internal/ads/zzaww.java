package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzaww extends zzbbo<zzaww, zza> implements zzbcw {
    private static volatile zzbdf<zzaww> zzakh;
    private static final zzaww zzdjx = new zzaww();
    private int zzdju;
    private int zzdjv;
    private zzbah zzdjw = zzbah.zzdpq;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzaww, zza> implements zzbcw {
        private zza() {
            super(zzaww.zzdjx);
        }
    }

    static {
        zzbbo.zza(zzaww.class, zzdjx);
    }

    private zzaww() {
    }

    public static zzaww zzyk() {
        return zzdjx;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzawx.zzakf[i - 1]) {
            case 1:
                return new zzaww();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdju", "zzdjv", "zzdjw"};
                return zzbbo.zza(zzdjx, "\u0000\u0003\u0000\u0000\u0001\u000b\u000b\f\u0000\u0000\u0000\u0001\f\u0002\f\u000b\n", objArr);
            case 4:
                return zzdjx;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzaww.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdjx);
                        zzakh = obj3;
                    }
                }
                return obj3;
            case 6:
                return Byte.valueOf((byte) 1);
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final zzawy zzyh() {
        zzawy zzat = zzawy.zzat(this.zzdju);
        return zzat == null ? zzawy.UNRECOGNIZED : zzat;
    }

    public final zzaxa zzyi() {
        zzaxa zzau = zzaxa.zzau(this.zzdjv);
        return zzau == null ? zzaxa.UNRECOGNIZED : zzau;
    }

    public final zzbah zzyj() {
        return this.zzdjw;
    }
}
