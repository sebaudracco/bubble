package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;
import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzawe extends zzbbo<zzawe, zza> implements zzbcw {
    private static volatile zzbdf<zzawe> zzakh;
    private static final zzawe zzdiz = new zzawe();
    private int zzdih;
    private zzbah zzdip = zzbah.zzdpq;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzawe, zza> implements zzbcw {
        private zza() {
            super(zzawe.zzdiz);
        }

        public final zza zzao(int i) {
            zzadh();
            ((zzawe) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzs(zzbah com_google_android_gms_internal_ads_zzbah) {
            zzadh();
            ((zzawe) this.zzdtx).zzk(com_google_android_gms_internal_ads_zzbah);
            return this;
        }
    }

    static {
        zzbbo.zza(zzawe.class, zzdiz);
    }

    private zzawe() {
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

    public static zzawe zzr(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzawe) zzbbo.zza(zzdiz, com_google_android_gms_internal_ads_zzbah);
    }

    public static zza zzxk() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdiz.zza(zze.zzdue, null, null));
    }

    public final int getVersion() {
        return this.zzdih;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzawf.zzakf[i - 1]) {
            case 1:
                return new zzawe();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdih", "zzdip"};
                return zzbbo.zza(zzdiz, "\u0000\u0002\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0003\n", objArr);
            case 4:
                return zzdiz;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzawe.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdiz);
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
