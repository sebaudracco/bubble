package com.google.android.gms.internal.measurement;

public abstract class zzze {
    private static volatile boolean zzbrg = false;
    int zzbrd;
    private int zzbre;
    private boolean zzbrf;

    private zzze() {
        this.zzbrd = 100;
        this.zzbre = Integer.MAX_VALUE;
        this.zzbrf = false;
    }

    static zzze zza(byte[] bArr, int i, int i2, boolean z) {
        zzze com_google_android_gms_internal_measurement_zzzg = new zzzg(bArr, i, i2);
        try {
            com_google_android_gms_internal_measurement_zzzg.zzaf(i2);
            return com_google_android_gms_internal_measurement_zzzg;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int zzsz();
}
