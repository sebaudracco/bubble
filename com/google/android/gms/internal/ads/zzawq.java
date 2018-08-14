package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zzb;

public final class zzawq extends zzbbo<zzawq, zza> implements zzbcw {
    private static volatile zzbdf<zzawq> zzakh;
    private static final zzawq zzdjo = new zzawq();
    private zzaww zzdjl;
    private zzawm zzdjm;
    private int zzdjn;

    public static final class zza extends com.google.android.gms.internal.ads.zzbbo.zza<zzawq, zza> implements zzbcw {
        private zza() {
            super(zzawq.zzdjo);
        }
    }

    static {
        zzbbo.zza(zzawq.class, zzdjo);
    }

    private zzawq() {
    }

    public static zzawq zzxx() {
        return zzdjo;
    }

    protected final Object zza(int i, Object obj, Object obj2) {
        switch (zzawr.zzakf[i - 1]) {
            case 1:
                return new zzawq();
            case 2:
                return new zza();
            case 3:
                Object[] objArr = new Object[]{"zzdjl", "zzdjm", "zzdjn"};
                return zzbbo.zza(zzdjo, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0004\u0000\u0000\u0000\u0001\t\u0002\t\u0003\f", objArr);
            case 4:
                return zzdjo;
            case 5:
                zzbdf com_google_android_gms_internal_ads_zzbdf = zzakh;
                if (com_google_android_gms_internal_ads_zzbdf != null) {
                    return com_google_android_gms_internal_ads_zzbdf;
                }
                Object obj3;
                synchronized (zzawq.class) {
                    obj3 = zzakh;
                    if (obj3 == null) {
                        obj3 = new zzb(zzdjo);
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

    public final zzaww zzxu() {
        return this.zzdjl == null ? zzaww.zzyk() : this.zzdjl;
    }

    public final zzawm zzxv() {
        return this.zzdjm == null ? zzawm.zzxq() : this.zzdjm;
    }

    public final zzawk zzxw() {
        zzawk zzaq = zzawk.zzaq(this.zzdjn);
        return zzaq == null ? zzawk.UNRECOGNIZED : zzaq;
    }
}
