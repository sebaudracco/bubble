package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;
import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzaxz extends zzbbo<zzaxz, zza> implements zzbcw {
    private static volatile zzbdf<zzaxz> zzakh;
    private static final zzaxz zzdmg = new zzaxz();
    private int zzdih;
    private zzayb zzdmf;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzaxz, zza> implements zzbcw {
        private zza() {
            super(zzaxz.zzdmg);
        }

        public final zza zzb(zzayb com_google_android_gms_internal_ads_zzayb) {
            zzadh();
            ((zzaxz) this.zzdtx).zza(com_google_android_gms_internal_ads_zzayb);
            return this;
        }

        public final zza zzbf(int i) {
            zzadh();
            ((zzaxz) this.zzdtx).setVersion(0);
            return this;
        }
    }

    static {
        zzbbo.zza(zzaxz.class, zzdmg);
    }

    private zzaxz() {
    }

    private final void setVersion(int i) {
        this.zzdih = i;
    }

    private final void zza(zzayb com_google_android_gms_internal_ads_zzayb) {
        if (com_google_android_gms_internal_ads_zzayb == null) {
            throw new NullPointerException();
        }
        this.zzdmf = com_google_android_gms_internal_ads_zzayb;
    }

    public static zza zzaaf() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdmg.zza(zze.zzdue, null, null));
    }

    public static zzaxz zzal(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzaxz) zzbbo.zza(zzdmg, com_google_android_gms_internal_ads_zzbah);
    }

    public final int getVersion() {
        return this.zzdih;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzaya.zzakf[i - 1]) {
            case 1:
                return new zzaxz();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdih", "zzdmf"};
                return zzbbo.zza(zzdmg, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t", objArr);
            case 4:
                return zzdmg;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzaxz.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdmg);
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

    public final zzayb zzaae() {
        return this.zzdmf == null ? zzayb.zzaaj() : this.zzdmf;
    }
}
