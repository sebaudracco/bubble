package com.google.android.gms.internal.ads;

import java.io.IOException;

abstract class zzbee<T, B> {
    zzbee() {
    }

    abstract void zza(B b, int i, long j);

    abstract void zza(B b, int i, zzbah com_google_android_gms_internal_ads_zzbah);

    abstract void zza(B b, int i, T t);

    abstract void zza(T t, zzbey com_google_android_gms_internal_ads_zzbey) throws IOException;

    abstract boolean zza(zzbdl com_google_android_gms_internal_ads_zzbdl);

    final boolean zza(B b, zzbdl com_google_android_gms_internal_ads_zzbdl) throws IOException {
        int tag = com_google_android_gms_internal_ads_zzbdl.getTag();
        int i = tag >>> 3;
        switch (tag & 7) {
            case 0:
                zza((Object) b, i, com_google_android_gms_internal_ads_zzbdl.zzabm());
                return true;
            case 1:
                zzb(b, i, com_google_android_gms_internal_ads_zzbdl.zzabo());
                return true;
            case 2:
                zza((Object) b, i, com_google_android_gms_internal_ads_zzbdl.zzabs());
                return true;
            case 3:
                Object zzagb = zzagb();
                int i2 = (i << 3) | 4;
                while (com_google_android_gms_internal_ads_zzbdl.zzaci() != Integer.MAX_VALUE) {
                    if (!zza(zzagb, com_google_android_gms_internal_ads_zzbdl)) {
                        if (i2 == com_google_android_gms_internal_ads_zzbdl.getTag()) {
                            throw zzbbu.zzadp();
                        }
                        zza((Object) b, i, zzv(zzagb));
                        return true;
                    }
                }
                if (i2 == com_google_android_gms_internal_ads_zzbdl.getTag()) {
                    zza((Object) b, i, zzv(zzagb));
                    return true;
                }
                throw zzbbu.zzadp();
            case 4:
                return false;
            case 5:
                zzc(b, i, com_google_android_gms_internal_ads_zzbdl.zzabp());
                return true;
            default:
                throw zzbbu.zzadq();
        }
    }

    abstract T zzac(Object obj);

    abstract B zzad(Object obj);

    abstract int zzae(T t);

    abstract B zzagb();

    abstract void zzb(B b, int i, long j);

    abstract void zzc(B b, int i, int i2);

    abstract void zzc(T t, zzbey com_google_android_gms_internal_ads_zzbey) throws IOException;

    abstract void zze(Object obj, T t);

    abstract void zzf(Object obj, B b);

    abstract T zzg(T t, T t2);

    abstract void zzo(Object obj);

    abstract T zzv(B b);

    abstract int zzy(T t);
}
