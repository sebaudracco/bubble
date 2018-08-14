package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;
import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzaxv extends zzbbo<zzaxv, zza> implements zzbcw {
    private static volatile zzbdf<zzaxv> zzakh;
    private static final zzaxv zzdmc = new zzaxv();
    private int zzdih;
    private zzaxx zzdmb;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzaxv, zza> implements zzbcw {
        private zza() {
            super(zzaxv.zzdmc);
        }

        public final zza zzb(zzaxx com_google_android_gms_internal_ads_zzaxx) {
            zzadh();
            ((zzaxv) this.zzdtx).zza(com_google_android_gms_internal_ads_zzaxx);
            return this;
        }

        public final zza zzbe(int i) {
            zzadh();
            ((zzaxv) this.zzdtx).setVersion(0);
            return this;
        }
    }

    static {
        zzbbo.zza(zzaxv.class, zzdmc);
    }

    private zzaxv() {
    }

    private final void setVersion(int i) {
        this.zzdih = i;
    }

    private final void zza(zzaxx com_google_android_gms_internal_ads_zzaxx) {
        if (com_google_android_gms_internal_ads_zzaxx == null) {
            throw new NullPointerException();
        }
        this.zzdmb = com_google_android_gms_internal_ads_zzaxx;
    }

    public static zzaxv zzaj(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzaxv) zzbbo.zza(zzdmc, com_google_android_gms_internal_ads_zzbah);
    }

    public static zza zzzz() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdmc.zza(zze.zzdue, null, null));
    }

    public final int getVersion() {
        return this.zzdih;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzaxw.zzakf[i - 1]) {
            case 1:
                return new zzaxv();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdih", "zzdmb"};
                return zzbbo.zza(zzdmc, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\u000b\u0002\t", objArr);
            case 4:
                return zzdmc;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzaxv.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdmc);
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

    public final zzaxx zzzy() {
        return this.zzdmb == null ? zzaxx.zzaac() : this.zzdmb;
    }
}
