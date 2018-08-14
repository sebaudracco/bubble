package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzavq extends zzbbo<zzavq, zza> implements zzbcw {
    private static volatile zzbdf<zzavq> zzakh;
    private static final zzavq zzdin = new zzavq();
    private zzavu zzdil;
    private zzaxe zzdim;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzavq, zza> implements zzbcw {
        private zza() {
            super(zzavq.zzdin);
        }
    }

    static {
        zzbbo.zza(zzavq.class, zzdin);
    }

    private zzavq() {
    }

    public static zzavq zzj(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzavq) zzbbo.zza(zzdin, com_google_android_gms_internal_ads_zzbah);
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzavr.zzakf[i - 1]) {
            case 1:
                return new zzavq();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdil", "zzdim"};
                return zzbbo.zza(zzdin, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\t\u0002\t", objArr);
            case 4:
                return zzdin;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzavq.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdin);
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

    public final zzavu zzwr() {
        return this.zzdil == null ? zzavu.zzwz() : this.zzdil;
    }

    public final zzaxe zzws() {
        return this.zzdim == null ? zzaxe.zzyq() : this.zzdim;
    }
}
