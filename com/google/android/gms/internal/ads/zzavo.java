package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;
import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzavo extends zzbbo<zzavo, zza> implements zzbcw {
    private static volatile zzbdf<zzavo> zzakh;
    private static final zzavo zzdik = new zzavo();
    private int zzdih;
    private zzavs zzdii;
    private zzaxc zzdij;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzavo, zza> implements zzbcw {
        private zza() {
            super(zzavo.zzdik);
        }

        public final zza zzal(int i) {
            zzadh();
            ((zzavo) this.zzdtx).setVersion(i);
            return this;
        }

        public final zza zzb(zzavs com_google_android_gms_internal_ads_zzavs) {
            zzadh();
            ((zzavo) this.zzdtx).zza(com_google_android_gms_internal_ads_zzavs);
            return this;
        }

        public final zza zzb(zzaxc com_google_android_gms_internal_ads_zzaxc) {
            zzadh();
            ((zzavo) this.zzdtx).zza(com_google_android_gms_internal_ads_zzaxc);
            return this;
        }
    }

    static {
        zzbbo.zza(zzavo.class, zzdik);
    }

    private zzavo() {
    }

    private final void setVersion(int i) {
        this.zzdih = i;
    }

    private final void zza(zzavs com_google_android_gms_internal_ads_zzavs) {
        if (com_google_android_gms_internal_ads_zzavs == null) {
            throw new NullPointerException();
        }
        this.zzdii = com_google_android_gms_internal_ads_zzavs;
    }

    private final void zza(zzaxc com_google_android_gms_internal_ads_zzaxc) {
        if (com_google_android_gms_internal_ads_zzaxc == null) {
            throw new NullPointerException();
        }
        this.zzdij = com_google_android_gms_internal_ads_zzaxc;
    }

    public static zzavo zzi(zzbah com_google_android_gms_internal_ads_zzbah) throws zzbbu {
        return (zzavo) zzbbo.zza(zzdik, com_google_android_gms_internal_ads_zzbah);
    }

    public static zza zzwp() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdik.zza(zze.zzdue, null, null));
    }

    public final int getVersion() {
        return this.zzdih;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzavp.zzakf[i - 1]) {
            case 1:
                return new zzavo();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdih", "zzdii", "zzdij"};
                return zzbbo.zza(zzdik, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\u000b\u0002\t\u0003\t", objArr);
            case 4:
                return zzdik;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzavo.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdik);
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

    public final zzavs zzwn() {
        return this.zzdii == null ? zzavs.zzwx() : this.zzdii;
    }

    public final zzaxc zzwo() {
        return this.zzdij == null ? zzaxc.zzyo() : this.zzdij;
    }
}
