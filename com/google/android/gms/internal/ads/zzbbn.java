package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zze;

final class zzbbn implements zzbct {
    private static final zzbbn zzdts = new zzbbn();

    private zzbbn() {
    }

    public static zzbbn zzadc() {
        return zzdts;
    }

    public final boolean zza(Class<?> cls) {
        return zzbbo.class.isAssignableFrom(cls);
    }

    public final zzbcs zzb(Class<?> cls) {
        if (zzbbo.class.isAssignableFrom(cls)) {
            try {
                return (zzbcs) zzbbo.zzc(cls.asSubclass(zzbbo.class)).zza(zze.zzduc, null, null);
            } catch (Throwable e) {
                Throwable th = e;
                String str = "Unable to get message info for ";
                String valueOf = String.valueOf(cls.getName());
                throw new RuntimeException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), th);
            }
        }
        String str2 = "Unsupported message type: ";
        valueOf = String.valueOf(cls.getName());
        throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
