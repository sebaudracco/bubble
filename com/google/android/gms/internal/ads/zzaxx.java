package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzaxx extends zzbbo<zzaxx, zza> implements zzbcw {
    private static volatile zzbdf<zzaxx> zzakh;
    private static final zzaxx zzdme = new zzaxx();
    private String zzdmd = "";

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzaxx, zza> implements zzbcw {
        private zza() {
            super(zzaxx.zzdme);
        }
    }

    static {
        zzbbo.zza(zzaxx.class, zzdme);
    }

    private zzaxx() {
    }

    public static zzaxx zzaac() {
        return zzdme;
    }

    public static zzaxx zzak(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzaxx) zzbbo.zza(zzdme, com_google_android_gms_internal_ads_zzbah);
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzaxy.zzakf[i - 1]) {
            case 1:
                return new zzaxx();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdmd"};
                return zzbbo.zza(zzdme, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0002\u0000\u0000\u0000\u0001Ȉ", objArr);
            case 4:
                return zzdme;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzaxx.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdme);
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

    public final String zzaab() {
        return this.zzdmd;
    }
}
