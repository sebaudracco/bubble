package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zze;

public final class zzaxt extends zzbbo<zzaxt, zza> implements zzbcw {
    private static volatile zzbdf<zzaxt> zzakh;
    private static final zzaxt zzdlz = new zzaxt();
    private int zzdlq;
    private int zzdlr;
    private zzbbt<zzb> zzdly = zzbbo.zzadd();

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzaxt, zza> implements zzbcw {
        private zza() {
            super(zzaxt.zzdlz);
        }

        public final zza zzb(zzb com_google_android_gms_internal_ads_zzaxt_zzb) {
            zzadh();
            ((zzaxt) this.zzdtx).zza(com_google_android_gms_internal_ads_zzaxt_zzb);
            return this;
        }

        public final zza zzbb(int i) {
            zzadh();
            ((zzaxt) this.zzdtx).zzba(i);
            return this;
        }
    }

    public static final class zzb extends zzbbo<zzb, zza> implements zzbcw {
        private static volatile zzbdf<zzb> zzakh;
        private static final zzb zzdma = new zzb();
        private String zzdks = "";
        private int zzdlj;
        private int zzdlv;
        private int zzdlw;

        public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzb, zza> implements zzbcw {
            private zza() {
                super(zzb.zzdma);
            }

            public final zza zzb(zzaxl com_google_android_gms_internal_ads_zzaxl) {
                zzadh();
                ((zzb) this.zzdtx).zza(com_google_android_gms_internal_ads_zzaxl);
                return this;
            }

            public final zza zzb(zzayd com_google_android_gms_internal_ads_zzayd) {
                zzadh();
                ((zzb) this.zzdtx).zza(com_google_android_gms_internal_ads_zzayd);
                return this;
            }

            public final zza zzbd(int i) {
                zzadh();
                ((zzb) this.zzdtx).zzbc(i);
                return this;
            }

            public final zza zzeh(String str) {
                zzadh();
                ((zzb) this.zzdtx).zzea(str);
                return this;
            }
        }

        static {
            zzbbo.zza(zzb.class, zzdma);
        }

        private zzb() {
        }

        private final void zza(zzaxl com_google_android_gms_internal_ads_zzaxl) {
            if (com_google_android_gms_internal_ads_zzaxl == null) {
                throw new NullPointerException();
            }
            this.zzdlv = com_google_android_gms_internal_ads_zzaxl.zzhq();
        }

        private final void zza(zzayd com_google_android_gms_internal_ads_zzayd) {
            if (com_google_android_gms_internal_ads_zzayd == null) {
                throw new NullPointerException();
            }
            this.zzdlj = com_google_android_gms_internal_ads_zzayd.zzhq();
        }

        private final void zzbc(int i) {
            this.zzdlw = i;
        }

        private final void zzea(String str) {
            if (str == null) {
                throw new NullPointerException();
            }
            this.zzdks = str;
        }

        public static zza zzzw() {
            return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdma.zza(zze.zzdue, null, null));
        }

        protected final Object zza(int i, Object obj, Object obj2) {
            switch (zzaxu.zzakf[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new zza();
                case 3:
                    Object[] objArr = new Object[]{"zzdks", "zzdlv", "zzdlw", "zzdlj"};
                    return zzbbo.zza(zzdma, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0005\u0000\u0000\u0000\u0001Ȉ\u0002\f\u0003\u000b\u0004\f", objArr);
                case 4:
                    return zzdma;
                case 5:
                    zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                    if (com_google_android_gms_internal_ads_zzbdf != null) {
                        return com_google_android_gms_internal_ads_zzbdf;
                    }
                    Object obj3;
                    synchronized (zzb.class) {
                        obj3 = zzakh;
                        if (obj3 == null) {
                            obj3 = new com.google.android.gms.internal.ads.zzbbo.zzb(zzdma);
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
    }

    static {
        zzbbo.zza(zzaxt.class, zzdlz);
    }

    private zzaxt() {
    }

    private final void zza(zzb com_google_android_gms_internal_ads_zzaxt_zzb) {
        if (com_google_android_gms_internal_ads_zzaxt_zzb == null) {
            throw new NullPointerException();
        }
        if (!this.zzdly.zzaay()) {
            zzbbt com_google_android_gms_internal_ads_zzbbt = this.zzdly;
            int size = com_google_android_gms_internal_ads_zzbbt.size();
            this.zzdly = com_google_android_gms_internal_ads_zzbbt.zzbm(size == 0 ? 10 : size << 1);
        }
        this.zzdly.add(com_google_android_gms_internal_ads_zzaxt_zzb);
    }

    private final void zzba(int i) {
        this.zzdlr = i;
    }

    public static zza zzzu() {
        return (zza) ((com.google.android.gms.internal.ads.zzbbo.zza) zzdlz.zza(zze.zzdue, null, null));
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzaxu.zzakf[i - 1]) {
            case 1:
                return new zzaxt();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdlq", "zzdlr", "zzdly", zzb.class};
                return zzbbo.zza(zzdlz, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0003\u0000\u0001\u0000\u0001\u000b\u0002\u001b", objArr);
            case 4:
                return zzdlz;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzaxt.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new com.google.android.gms.internal.ads.zzbbo.zzb(zzdlz);
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
}
