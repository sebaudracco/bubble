package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzaxg extends zzbbo<zzaxg, zza> implements zzbcw {
    private static volatile zzbdf<zzaxg> zzakh;
    private static final zzaxg zzdkr = new zzaxg();
    private int zzdkp;
    private int zzdkq;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzaxg, zza> implements zzbcw {
        private zza() {
            super(zzaxg.zzdkr);
        }
    }

    static {
        zzbbo.zza(zzaxg.class, zzdkr);
    }

    private zzaxg() {
    }

    public static zzaxg zzyu() {
        return zzdkr;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzaxh.zzakf[i - 1]) {
            case 1:
                return new zzaxg();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdkp", "zzdkq"};
                return zzbbo.zza(zzdkr, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\f\u0002\u000b", objArr);
            case 4:
                return zzdkr;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzaxg.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdkr);
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

    public final zzaxa zzys() {
        zzaxa zzau = zzaxa.zzau(this.zzdkp);
        return zzau == null ? zzaxa.UNRECOGNIZED : zzau;
    }

    public final int zzyt() {
        return this.zzdkq;
    }
}
