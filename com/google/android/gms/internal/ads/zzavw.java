package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzavw extends zzbbo<zzavw, zza> implements zzbcw {
    private static volatile zzbdf<zzavw> zzakh;
    private static final zzavw zzdiu = new zzavw();
    private int zzdit;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzavw, zza> implements zzbcw {
        private zza() {
            super(zzavw.zzdiu);
        }
    }

    static {
        zzbbo.zza(zzavw.class, zzdiu);
    }

    private zzavw() {
    }

    public static zzavw zzxc() {
        return zzdiu;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzavx.zzakf[i - 1]) {
            case 1:
                return new zzavw();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdit"};
                return zzbbo.zza(zzdiu, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0000\u0000\u0001\u000b", objArr);
            case 4:
                return zzdiu;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzavw.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdiu);
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

    public final int zzxb() {
        return this.zzdit;
    }
}
