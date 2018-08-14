package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzayb extends zzbbo<zzayb, zza> implements zzbcw {
    private static volatile zzbdf<zzayb> zzakh;
    private static final zzayb zzdmj = new zzayb();
    private String zzdmh = "";
    private zzaxn zzdmi;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzayb, zza> implements zzbcw {
        private zza() {
            super(zzayb.zzdmj);
        }
    }

    static {
        zzbbo.zza(zzayb.class, zzdmj);
    }

    private zzayb() {
    }

    public static zzayb zzaaj() {
        return zzdmj;
    }

    public static zzayb zzam(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzayb) zzbbo.zza(zzdmj, com_google_android_gms_internal_ads_zzbah);
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzayc.zzakf[i - 1]) {
            case 1:
                return new zzayb();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdmh", "zzdmi"};
                return zzbbo.zza(zzdmj, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001Ȉ\u0002\t", objArr);
            case 4:
                return zzdmj;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzayb.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdmj);
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

    public final String zzaah() {
        return this.zzdmh;
    }

    public final zzaxn zzaai() {
        return this.zzdmi == null ? zzaxn.zzzc() : this.zzdmi;
    }
}
