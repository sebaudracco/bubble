package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzaxi extends zzbbo<zzaxi, zza> implements zzbcw {
    private static volatile zzbdf<zzaxi> zzakh;
    private static final zzaxi zzdkv = new zzaxi();
    private String zzdks = "";
    private zzbah zzdkt = zzbah.zzdpq;
    private int zzdku;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzaxi, zza> implements zzbcw {
        private zza() {
            super(zzaxi.zzdkv);
        }

        public final zza zzai(zzbah com_google_android_gms_internal_ads_zzbah) {
            zzadh();
            ((zzaxi) this.zzdtx).zzah(com_google_android_gms_internal_ads_zzbah);
            return this;
        }

        public final zza zzb(zzb com_google_android_gms_internal_ads_zzaxi_zzb) {
            zzadh();
            ((zzaxi) this.zzdtx).zza(com_google_android_gms_internal_ads_zzaxi_zzb);
            return this;
        }

        public final zza zzeb(String str) {
            zzadh();
            ((zzaxi) this.zzdtx).zzea(str);
            return this;
        }
    }

    public enum zzb implements zzbbr {
        UNKNOWN_KEYMATERIAL(0),
        SYMMETRIC(1),
        ASYMMETRIC_PRIVATE(2),
        ASYMMETRIC_PUBLIC(3),
        REMOTE(4),
        UNRECOGNIZED(-1);
        
        private static final zzbbs<zzb> zzall = null;
        private final int value;

        static {
            zzall = new zzaxk();
        }

        private zzb(int i) {
            this.value = i;
        }

        public static zzb zzaw(int i) {
            switch (i) {
                case 0:
                    return UNKNOWN_KEYMATERIAL;
                case 1:
                    return SYMMETRIC;
                case 2:
                    return ASYMMETRIC_PRIVATE;
                case 3:
                    return ASYMMETRIC_PUBLIC;
                case 4:
                    return REMOTE;
                default:
                    return null;
            }
        }

        public final int zzhq() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
    }

    static {
        zzbbo.zza(zzaxi.class, zzdkv);
    }

    private zzaxi() {
    }

    private final void zza(zzb com_google_android_gms_internal_ads_zzaxi_zzb) {
        if (com_google_android_gms_internal_ads_zzaxi_zzb == null) {
            throw new NullPointerException();
        }
        this.zzdku = com_google_android_gms_internal_ads_zzaxi_zzb.zzhq();
    }

    private final void zzah(zzbah com_google_android_gms_internal_ads_zzbah) {
        if (com_google_android_gms_internal_ads_zzbah == null) {
            throw new NullPointerException();
        }
        this.zzdkt = com_google_android_gms_internal_ads_zzbah;
    }

    private final void zzea(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        this.zzdks = str;
    }

    public static zza zzyz() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdkv.zza(zze.zzdue, null, null));
    }

    public static zzaxi zzza() {
        return zzdkv;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzaxj.zzakf[i - 1]) {
            case 1:
                return new zzaxi();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdks", "zzdkt", "zzdku"};
                return zzbbo.zza(zzdkv, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001Ȉ\u0002\n\u0003\f", objArr);
            case 4:
                return zzdkv;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzaxi.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new com.google.android.gms.internal.ads.zzbbo.zzb(zzdkv);
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

    public final String zzyw() {
        return this.zzdks;
    }

    public final zzbah zzyx() {
        return this.zzdkt;
    }

    public final zzb zzyy() {
        zzb zzaw = zzb.zzaw(this.zzdku);
        return zzaw == null ? zzb.UNRECOGNIZED : zzaw;
    }
}
