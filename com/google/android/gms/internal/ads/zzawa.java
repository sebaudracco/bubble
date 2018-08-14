package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzawa extends zzbbo<zzawa, zza> implements zzbcw {
    private static volatile zzbdf<zzawa> zzakh;
    private static final zzawa zzdix = new zzawa();
    private int zzdir;
    private zzawc zzdiv;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzawa, zza> implements zzbcw {
        private zza() {
            super(zzawa.zzdix);
        }
    }

    static {
        zzbbo.zza(zzawa.class, zzdix);
    }

    private zzawa() {
    }

    public static zzawa zzq(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzawa) zzbbo.zza(zzdix, com_google_android_gms_internal_ads_zzbah);
    }

    public final int getKeySize() {
        return this.zzdir;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzawb.zzakf[i - 1]) {
            case 1:
                return new zzawa();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdiv", "zzdir"};
                return zzbbo.zza(zzdix, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b", objArr);
            case 4:
                return zzdix;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzawa.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdix);
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

    public final zzawc zzxe() {
        return this.zzdiv == null ? zzawc.zzxi() : this.zzdiv;
    }
}
