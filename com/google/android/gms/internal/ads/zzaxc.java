package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;
import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzaxc extends zzbbo<zzaxc, zza> implements zzbcw {
    private static volatile zzbdf<zzaxc> zzakh;
    private static final zzaxc zzdkn = new zzaxc();
    private int zzdih;
    private zzbah zzdip = zzbah.zzdpq;
    private zzaxg zzdkm;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzaxc, zza> implements zzbcw {
        private zza() {
            super(zzaxc.zzdkn);
        }

        public final zza zzaf(zzbah com_google_android_gms_internal_ads_zzbah) {
            zzadh();
            ((zzaxc) this.zzdtx).zzk(com_google_android_gms_internal_ads_zzbah);
            return this;
        }

        public final zza zzav(int i) {
            zzadh();
            ((zzaxc) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzc(zzaxg com_google_android_gms_internal_ads_zzaxg) {
            zzadh();
            ((zzaxc) this.zzdtx).zzb(com_google_android_gms_internal_ads_zzaxg);
            return this;
        }
    }

    static {
        zzbbo.zza(zzaxc.class, zzdkn);
    }

    private zzaxc() {
    }

    private final void setVersion(int i) {
        this.zzdih = i;
    }

    public static zzaxc zzae(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzaxc) zzbbo.zza(zzdkn, com_google_android_gms_internal_ads_zzbah);
    }

    private final void zzb(zzaxg com_google_android_gms_internal_ads_zzaxg) {
        if (com_google_android_gms_internal_ads_zzaxg == null) {
            throw new NullPointerException();
        }
        this.zzdkm = com_google_android_gms_internal_ads_zzaxg;
    }

    private final void zzk(zzbah com_google_android_gms_internal_ads_zzbah) {
        if (com_google_android_gms_internal_ads_zzbah == null) {
            throw new NullPointerException();
        }
        this.zzdip = com_google_android_gms_internal_ads_zzbah;
    }

    public static zza zzyn() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdkn.zza(zze.zzdue, null, null));
    }

    public static zzaxc zzyo() {
        return zzdkn;
    }

    public final int getVersion() {
        return this.zzdih;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzaxd.zzakf[i - 1]) {
            case 1:
                return new zzaxc();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdih", "zzdkm", "zzdip"};
                return zzbbo.zza(zzdkn, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", objArr);
            case 4:
                return zzdkn;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzaxc.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdkn);
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

    public final zzbah zzwv() {
        return this.zzdip;
    }

    public final zzaxg zzym() {
        return this.zzdkm == null ? zzaxg.zzyu() : this.zzdkm;
    }
}
