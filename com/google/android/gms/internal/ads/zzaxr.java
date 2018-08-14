package com.google.android.gms.internal.ads;

import java.util.List;

public final class zzaxr extends zzbbo<zzaxr, zza> implements zzbcw {
    private static volatile zzbdf<zzaxr> zzakh;
    private static final zzaxr zzdlt = new zzaxr();
    private int zzdlq;
    private int zzdlr;
    private zzbbt<zzb> zzdls = zzbbo.zzadd();

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzaxr, zza> implements zzbcw {
        private zza() {
            super(zzaxr.zzdlt);
        }
    }

    public static final class zzb extends zzbbo<zzb, zza> implements zzbcw {
        private static volatile zzbdf<zzb> zzakh;
        private static final zzb zzdlx = new zzb();
        private int zzdlj;
        private zzaxi zzdlu;
        private int zzdlv;
        private int zzdlw;

        public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzb, zza> implements zzbcw {
            private zza() {
                super(zzb.zzdlx);
            }
        }

        static {
            zzbbo.zza(zzb.class, zzdlx);
        }

        private zzb() {
        }

        protected final Object zza(int i, Object obj, Object obj2) {
            switch (zzaxs.zzakf[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    Object[] objArr = new Object[]{"zzdlu", "zzdlv", "zzdlw", "zzdlj"};
                    return zzbbo.zza(zzdlx, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001\t\u0002\f\u0003\u000b\u0004\f", objArr);
                case 4:
                    return zzdlx;
                case 5:
                    zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                    if (com_google_android_gms_internal_ads_zzbdf != null) {
                        return com_google_android_gms_internal_ads_zzbdf;
                    }
                    Object obj3;
                    synchronized (zzb.class) {
                        obj3 = zzakh;
                        if (obj3 == null) {
                            obj3 = new com.google.android.gms.internal.ads.zzbbo.zzb(zzdlx);
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

        public final boolean zzzo() {
            return this.zzdlu != null;
        }

        public final zzaxi zzzp() {
            return this.zzdlu == null ? zzaxi.zzza() : this.zzdlu;
        }

        public final zzaxl zzzq() {
            zzaxl zzax = zzaxl.zzax(this.zzdlv);
            return zzax == null ? zzaxl.UNRECOGNIZED : zzax;
        }

        public final int zzzr() {
            return this.zzdlw;
        }

        public final zzayd zzzs() {
            zzayd zzbg = zzayd.zzbg(this.zzdlj);
            return zzbg == null ? zzayd.UNRECOGNIZED : zzbg;
        }
    }

    static {
        zzbbo.zza(zzaxr.class, zzdlt);
    }

    private zzaxr() {
    }

    public static zzaxr zzj(byte[] bArr) throws zzbbu {
        return (zzaxr) zzbbo.zzb(zzdlt, bArr);
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzaxs.zzakf[i - 1]) {
            case 1:
                return new zzaxr();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdlq", "zzdlr", "zzdls", zzb.class};
                return zzbbo.zza(zzdlt, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0001\u0000\u0001\u000b\u0002\u001b", objArr);
            case 4:
                return zzdlt;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzaxr.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new com.google.android.gms.internal.ads.zzbbo.zzb(zzdlt);
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

    public final int zzzk() {
        return this.zzdlr;
    }

    public final List<zzb> zzzl() {
        return this.zzdls;
    }

    public final int zzzm() {
        return this.zzdls.size();
    }
}
