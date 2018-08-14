package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzaxe extends zzbbo<zzaxe, zza> implements zzbcw {
    private static volatile zzbdf<zzaxe> zzakh;
    private static final zzaxe zzdko = new zzaxe();
    private int zzdir;
    private zzaxg zzdkm;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzaxe, zza> implements zzbcw {
        private zza() {
            super(zzaxe.zzdko);
        }
    }

    static {
        zzbbo.zza(zzaxe.class, zzdko);
    }

    private zzaxe() {
    }

    public static zzaxe zzag(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzaxe) zzbbo.zza(zzdko, com_google_android_gms_internal_ads_zzbah);
    }

    public static zzaxe zzyq() {
        return zzdko;
    }

    public final int getKeySize() {
        return this.zzdir;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzaxf.zzakf[i - 1]) {
            case 1:
                return new zzaxe();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdkm", "zzdir"};
                return zzbbo.zza(zzdko, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\t\u0002\u000b", objArr);
            case 4:
                return zzdko;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzaxe.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdko);
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

    public final zzaxg zzym() {
        return this.zzdkm == null ? zzaxg.zzyu() : this.zzdkm;
    }
}
