package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzawg extends zzbbo<zzawg, zza> implements zzbcw {
    private static volatile zzbdf<zzawg> zzakh;
    private static final zzawg zzdja = new zzawg();
    private int zzdir;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzawg, zza> implements zzbcw {
        private zza() {
            super(zzawg.zzdja);
        }
    }

    static {
        zzbbo.zza(zzawg.class, zzdja);
    }

    private zzawg() {
    }

    public static zzawg zzt(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzawg) zzbbo.zza(zzdja, com_google_android_gms_internal_ads_zzbah);
    }

    public final int getKeySize() {
        return this.zzdir;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzawh.zzakf[i - 1]) {
            case 1:
                return new zzawg();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdir"};
                return zzbbo.zza(zzdja, "\u0000\u0001\u0000\u0000\u0002\u0002\u0001\u0003\u0000\u0000\u0000\u0002\u000b", objArr);
            case 4:
                return zzdja;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzawg.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdja);
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
}
