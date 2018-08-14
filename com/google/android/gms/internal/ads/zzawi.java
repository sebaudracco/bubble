package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;
import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzawi extends zzbbo<zzawi, zza> implements zzbcw {
    private static volatile zzbdf<zzawi> zzakh;
    private static final zzawi zzdjb = new zzawi();
    private int zzdih;
    private zzbah zzdip = zzbah.zzdpq;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzawi, zza> implements zzbcw {
        private zza() {
            super(zzawi.zzdjb);
        }

        public final zza zzap(int i) {
            zzadh();
            ((zzawi) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzv(zzbah com_google_android_gms_internal_ads_zzbah) {
            zzadh();
            ((zzawi) this.zzdtx).zzk(com_google_android_gms_internal_ads_zzbah);
            return this;
        }
    }

    static {
        zzbbo.zza(zzawi.class, zzdjb);
    }

    private zzawi() {
    }

    private final void setVersion(int i) {
        this.zzdih = i;
    }

    private final void zzk(zzbah com_google_android_gms_internal_ads_zzbah) {
        if (com_google_android_gms_internal_ads_zzbah == null) {
            throw new NullPointerException();
        }
        this.zzdip = com_google_android_gms_internal_ads_zzbah;
    }

    public static zzawi zzu(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzawi) zzbbo.zza(zzdjb, com_google_android_gms_internal_ads_zzbah);
    }

    public static zza zzxn() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdjb.zza(zze.zzdue, null, null));
    }

    public final int getVersion() {
        return this.zzdih;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzawj.zzakf[i - 1]) {
            case 1:
                return new zzawi();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdih", "zzdip"};
                return zzbbo.zza(zzdjb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0003\u0000\u0000\u0000\u0001\u000b\u0002\n", objArr);
            case 4:
                return zzdjb;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzawi.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdjb);
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
}
