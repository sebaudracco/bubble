package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzawc extends zzbbo<zzawc, zza> implements zzbcw {
    private static volatile zzbdf<zzawc> zzakh;
    private static final zzawc zzdiy = new zzawc();
    private int zzdit;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzawc, zza> implements zzbcw {
        private zza() {
            super(zzawc.zzdiy);
        }
    }

    static {
        zzbbo.zza(zzawc.class, zzdiy);
    }

    private zzawc() {
    }

    public static zzawc zzxi() {
        return zzdiy;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzawd.zzakf[i - 1]) {
            case 1:
                return new zzawc();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdit"};
                return zzbbo.zza(zzdiy, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0000\u0000\u0001\u000b", objArr);
            case 4:
                return zzdiy;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzawc.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdiy);
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
