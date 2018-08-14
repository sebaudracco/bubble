package com.google.android.gms.internal.wearable;

import java.io.IOException;

public abstract class zzt {
    protected volatile int zzhl = -1;

    public static final <T extends zzt> T zza(T t, byte[] bArr, int i, int i2) throws zzs {
        try {
            zzk zza = zzk.zza(bArr, 0, i2);
            t.zza(zza);
            zza.zzc(0);
            return t;
        } catch (zzs e) {
            throw e;
        } catch (Throwable e2) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", e2);
        }
    }

    public static final byte[] zzb(zzt com_google_android_gms_internal_wearable_zzt) {
        byte[] bArr = new byte[com_google_android_gms_internal_wearable_zzt.zzx()];
        try {
            zzl zzb = zzl.zzb(bArr, 0, bArr.length);
            com_google_android_gms_internal_wearable_zzt.zza(zzb);
            zzb.zzr();
            return bArr;
        } catch (Throwable e) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", e);
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzs();
    }

    public String toString() {
        return zzu.zzc(this);
    }

    public abstract zzt zza(zzk com_google_android_gms_internal_wearable_zzk) throws IOException;

    public void zza(zzl com_google_android_gms_internal_wearable_zzl) throws IOException {
    }

    protected int zzg() {
        return 0;
    }

    public zzt zzs() throws CloneNotSupportedException {
        return (zzt) super.clone();
    }

    public final int zzx() {
        int zzg = zzg();
        this.zzhl = zzg;
        return zzg;
    }
}
