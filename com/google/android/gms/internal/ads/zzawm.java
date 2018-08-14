package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzawm extends zzbbo<zzawm, zza> implements zzbcw {
    private static volatile zzbdf<zzawm> zzakh;
    private static final zzawm zzdji = new zzawm();
    private zzaxn zzdjh;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzawm, zza> implements zzbcw {
        private zza() {
            super(zzawm.zzdji);
        }
    }

    static {
        zzbbo.zza(zzawm.class, zzdji);
    }

    private zzawm() {
    }

    public static zzawm zzxq() {
        return zzdji;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzawn.zzakf[i - 1]) {
            case 1:
                return new zzawm();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdjh"};
                return zzbbo.zza(zzdji, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0003\u0000\u0000\u0000\u0002\t", objArr);
            case 4:
                return zzdji;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzawm.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdji);
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

    public final zzaxn zzxp() {
        return this.zzdjh == null ? zzaxn.zzzc() : this.zzdjh;
    }
}
