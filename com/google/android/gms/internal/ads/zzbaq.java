package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzbaq {
    private static volatile boolean zzdqc;
    int zzdpx;
    int zzdpy;
    private int zzdpz;
    zzbat zzdqa;
    private boolean zzdqb;

    static {
        zzdqc = false;
        zzdqc = true;
    }

    private zzbaq() {
        this.zzdpy = 100;
        this.zzdpz = Integer.MAX_VALUE;
        this.zzdqb = false;
    }

    static zzbaq zza(byte[] bArr, int i, int i2, boolean z) {
        zzbaq com_google_android_gms_internal_ads_zzbas = new zzbas(bArr, i, i2, z);
        try {
            com_google_android_gms_internal_ads_zzbas.zzbr(i2);
            return com_google_android_gms_internal_ads_zzbas;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zzbu(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static long zzl(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    public abstract double readDouble() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract String readString() throws IOException;

    public abstract int zzabk() throws IOException;

    public abstract long zzabl() throws IOException;

    public abstract long zzabm() throws IOException;

    public abstract int zzabn() throws IOException;

    public abstract long zzabo() throws IOException;

    public abstract int zzabp() throws IOException;

    public abstract boolean zzabq() throws IOException;

    public abstract String zzabr() throws IOException;

    public abstract zzbah zzabs() throws IOException;

    public abstract int zzabt() throws IOException;

    public abstract int zzabu() throws IOException;

    public abstract int zzabv() throws IOException;

    public abstract long zzabw() throws IOException;

    public abstract int zzabx() throws IOException;

    public abstract long zzaby() throws IOException;

    abstract long zzabz() throws IOException;

    public abstract boolean zzaca() throws IOException;

    public abstract int zzacb();

    public abstract void zzbp(int i) throws zzbbu;

    public abstract boolean zzbq(int i) throws IOException;

    public abstract int zzbr(int i) throws zzbbu;

    public abstract void zzbs(int i);

    public abstract void zzbt(int i) throws IOException;
}
