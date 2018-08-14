package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzawo extends zzbbo<zzawo, zza> implements zzbcw {
    private static volatile zzbdf<zzawo> zzakh;
    private static final zzawo zzdjk = new zzawo();
    private zzawq zzdjj;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzawo, zza> implements zzbcw {
        private zza() {
            super(zzawo.zzdjk);
        }
    }

    static {
        zzbbo.zza(zzawo.class, zzdjk);
    }

    private zzawo() {
    }

    public static zzawo zzw(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzawo) zzbbo.zza(zzdjk, com_google_android_gms_internal_ads_zzbah);
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzawp.zzakf[i - 1]) {
            case 1:
                return new zzawo();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdjj"};
                return zzbbo.zza(zzdjk, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0000\u0000\u0001\t", objArr);
            case 4:
                return zzdjk;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzawo.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdjk);
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

    public final zzawq zzxs() {
        return this.zzdjj == null ? zzawq.zzxx() : this.zzdjj;
    }
}
