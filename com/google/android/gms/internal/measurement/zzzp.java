package com.google.android.gms.internal.measurement;

final class zzzp implements zzaak {
    private static final zzzp zzbrx = new zzzp();

    private zzzp() {
    }

    public static zzzp zztl() {
        return zzbrx;
    }

    public final boolean zzd(Class<?> cls) {
        return zzzq.class.isAssignableFrom(cls);
    }

    public final zzaaj zze(Class<?> cls) {
        if (zzzq.class.isAssignableFrom(cls)) {
            try {
                return (zzaaj) zzzq.zzf(cls.asSubclass(zzzq.class)).zza(3, null, null);
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
