package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;
import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzavy extends zzbbo<zzavy, zza> implements zzbcw {
    private static volatile zzbdf<zzavy> zzakh;
    private static final zzavy zzdiw = new zzavy();
    private int zzdih;
    private zzbah zzdip = zzbah.zzdpq;
    private zzawc zzdiv;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzavy, zza> implements zzbcw {
        private zza() {
            super(zzavy.zzdiw);
        }

        public final zza zzan(int i) {
            zzadh();
            ((zzavy) this.zzdtx).setVersion(0);
            return this;
        }

        public final zza zzb(zzawc com_google_android_gms_internal_ads_zzawc) {
            zzadh();
            ((zzavy) this.zzdtx).zza(com_google_android_gms_internal_ads_zzawc);
            return this;
        }

        public final zza zzp(zzbah com_google_android_gms_internal_ads_zzbah) {
            zzadh();
            ((zzavy) this.zzdtx).zzk(com_google_android_gms_internal_ads_zzbah);
            return this;
        }
    }

    static {
        zzbbo.zza(zzavy.class, zzdiw);
    }

    private zzavy() {
    }

    private final void setVersion(int i) {
        this.zzdih = i;
    }

    private final void zza(zzawc com_google_android_gms_internal_ads_zzawc) {
        if (com_google_android_gms_internal_ads_zzawc == null) {
            throw new NullPointerException();
        }
        this.zzdiv = com_google_android_gms_internal_ads_zzawc;
    }

    private final void zzk(zzbah com_google_android_gms_internal_ads_zzbah) {
        if (com_google_android_gms_internal_ads_zzbah == null) {
            throw new NullPointerException();
        }
        this.zzdip = com_google_android_gms_internal_ads_zzbah;
    }

    public static zzavy zzo(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzavy) zzbbo.zza(zzdiw, com_google_android_gms_internal_ads_zzbah);
    }

    public static zza zzxf() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdiw.zza(zze.zzdue, null, null));
    }

    public final int getVersion() {
        return this.zzdih;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzavz.zzakf[i - 1]) {
            case 1:
                return new zzavy();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdih", "zzdiv", "zzdip"};
                return zzbbo.zza(zzdiw, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\n", objArr);
            case 4:
                return zzdiw;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzavy.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdiw);
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

    public final zzawc zzxe() {
        return this.zzdiv == null ? zzawc.zzxi() : this.zzdiv;
    }
}
