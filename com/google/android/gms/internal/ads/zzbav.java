package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzbav extends zzbag {
    private static final Logger logger = Logger.getLogger(zzbav.class.getName());
    private static final boolean zzdqm = zzbek.zzagf();
    zzbax zzdqn;

    private zzbav() {
    }

    public static int zza(int i, zzbcb com_google_android_gms_internal_ads_zzbcb) {
        int zzcd = zzcd(i);
        int zzacw = com_google_android_gms_internal_ads_zzbcb.zzacw();
        return zzcd + (zzacw + zzcf(zzacw));
    }

    public static int zza(zzbcb com_google_android_gms_internal_ads_zzbcb) {
        int zzacw = com_google_android_gms_internal_ads_zzbcb.zzacw();
        return zzacw + zzcf(zzacw);
    }

    static int zza(zzbcu com_google_android_gms_internal_ads_zzbcu, zzbdm com_google_android_gms_internal_ads_zzbdm) {
        zzazy com_google_android_gms_internal_ads_zzazy = (zzazy) com_google_android_gms_internal_ads_zzbcu;
        int zzaaw = com_google_android_gms_internal_ads_zzazy.zzaaw();
        if (zzaaw == -1) {
            zzaaw = com_google_android_gms_internal_ads_zzbdm.zzy(com_google_android_gms_internal_ads_zzazy);
            com_google_android_gms_internal_ads_zzazy.zzbj(zzaaw);
        }
        return zzaaw + zzcf(zzaaw);
    }

    public static int zzao(zzbah com_google_android_gms_internal_ads_zzbah) {
        int size = com_google_android_gms_internal_ads_zzbah.size();
        return size + zzcf(size);
    }

    public static int zzaq(boolean z) {
        return 1;
    }

    public static int zzb(int i, double d) {
        return zzcd(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzcd(i) + 4;
    }

    public static int zzb(int i, zzbcb com_google_android_gms_internal_ads_zzbcb) {
        return ((zzcd(1) << 1) + zzr(2, i)) + zza(3, com_google_android_gms_internal_ads_zzbcb);
    }

    public static int zzb(int i, zzbcu com_google_android_gms_internal_ads_zzbcu) {
        return ((zzcd(1) << 1) + zzr(2, i)) + (zzcd(3) + zzf(com_google_android_gms_internal_ads_zzbcu));
    }

    static int zzb(int i, zzbcu com_google_android_gms_internal_ads_zzbcu, zzbdm com_google_android_gms_internal_ads_zzbdm) {
        return zzcd(i) + zza(com_google_android_gms_internal_ads_zzbcu, com_google_android_gms_internal_ads_zzbdm);
    }

    public static int zzc(double d) {
        return 8;
    }

    public static int zzc(float f) {
        return 4;
    }

    public static int zzc(int i, zzbah com_google_android_gms_internal_ads_zzbah) {
        int zzcd = zzcd(i);
        int size = com_google_android_gms_internal_ads_zzbah.size();
        return zzcd + (size + zzcf(size));
    }

    @Deprecated
    static int zzc(int i, zzbcu com_google_android_gms_internal_ads_zzbcu, zzbdm com_google_android_gms_internal_ads_zzbdm) {
        int zzcd = zzcd(i) << 1;
        zzazy com_google_android_gms_internal_ads_zzazy = (zzazy) com_google_android_gms_internal_ads_zzbcu;
        int zzaaw = com_google_android_gms_internal_ads_zzazy.zzaaw();
        if (zzaaw == -1) {
            zzaaw = com_google_android_gms_internal_ads_zzbdm.zzy(com_google_android_gms_internal_ads_zzazy);
            com_google_android_gms_internal_ads_zzazy.zzbj(zzaaw);
        }
        return zzaaw + zzcd;
    }

    public static int zzcd(int i) {
        return zzcf(i << 3);
    }

    public static int zzce(int i) {
        return i >= 0 ? zzcf(i) : 10;
    }

    public static int zzcf(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzcg(int i) {
        return zzcf(zzck(i));
    }

    public static int zzch(int i) {
        return 4;
    }

    public static int zzci(int i) {
        return 4;
    }

    public static int zzcj(int i) {
        return zzce(i);
    }

    private static int zzck(int i) {
        return (i << 1) ^ (i >> 31);
    }

    @Deprecated
    public static int zzcl(int i) {
        return zzcf(i);
    }

    public static int zzd(int i, long j) {
        return zzcd(i) + zzq(j);
    }

    public static int zzd(int i, zzbah com_google_android_gms_internal_ads_zzbah) {
        return ((zzcd(1) << 1) + zzr(2, i)) + zzc(3, com_google_android_gms_internal_ads_zzbah);
    }

    public static int zze(int i, long j) {
        return zzcd(i) + zzq(j);
    }

    public static int zzeo(String str) {
        int zza;
        try {
            zza = zzbem.zza(str);
        } catch (zzbep e) {
            zza = str.getBytes(zzbbq.UTF_8).length;
        }
        return zza + zzcf(zza);
    }

    public static int zzf(int i, long j) {
        return zzcd(i) + zzq(zzu(j));
    }

    public static int zzf(zzbcu com_google_android_gms_internal_ads_zzbcu) {
        int zzacw = com_google_android_gms_internal_ads_zzbcu.zzacw();
        return zzacw + zzcf(zzacw);
    }

    public static int zzg(int i, long j) {
        return zzcd(i) + 8;
    }

    public static int zzg(int i, String str) {
        return zzcd(i) + zzeo(str);
    }

    public static int zzg(int i, boolean z) {
        return zzcd(i) + 1;
    }

    @Deprecated
    public static int zzg(zzbcu com_google_android_gms_internal_ads_zzbcu) {
        return com_google_android_gms_internal_ads_zzbcu.zzacw();
    }

    public static int zzh(int i, long j) {
        return zzcd(i) + 8;
    }

    public static int zzp(long j) {
        return zzq(j);
    }

    public static int zzq(int i, int i2) {
        return zzcd(i) + zzce(i2);
    }

    public static int zzq(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        long j2;
        int i = 2;
        if ((-34359738368L & j) != 0) {
            i = 6;
            j2 = j >>> 28;
        } else {
            j2 = j;
        }
        if ((-2097152 & j2) != 0) {
            i += 2;
            j2 >>>= 14;
        }
        return (j2 & -16384) != 0 ? i + 1 : i;
    }

    public static zzbav zzq(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzr(int i, int i2) {
        return zzcd(i) + zzcf(i2);
    }

    public static int zzr(long j) {
        return zzq(zzu(j));
    }

    public static int zzr(byte[] bArr) {
        int length = bArr.length;
        return length + zzcf(length);
    }

    public static int zzs(int i, int i2) {
        return zzcd(i) + zzcf(zzck(i2));
    }

    public static int zzs(long j) {
        return 8;
    }

    public static int zzt(int i, int i2) {
        return zzcd(i) + 4;
    }

    public static int zzt(long j) {
        return 8;
    }

    public static int zzu(int i, int i2) {
        return zzcd(i) + 4;
    }

    private static long zzu(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzv(int i, int i2) {
        return zzcd(i) + zzce(i2);
    }

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(byte b) throws IOException;

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, float f) throws IOException {
        zzp(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzbah com_google_android_gms_internal_ads_zzbah) throws IOException;

    public abstract void zza(int i, zzbcu com_google_android_gms_internal_ads_zzbcu) throws IOException;

    abstract void zza(int i, zzbcu com_google_android_gms_internal_ads_zzbcu, zzbdm com_google_android_gms_internal_ads_zzbdm) throws IOException;

    final void zza(String str, zzbep com_google_android_gms_internal_ads_zzbep) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", com_google_android_gms_internal_ads_zzbep);
        byte[] bytes = str.getBytes(zzbbq.UTF_8);
        try {
            zzca(bytes.length);
            zzb(bytes, 0, bytes.length);
        } catch (Throwable e) {
            throw new zzb(e);
        } catch (zzb e2) {
            throw e2;
        }
    }

    public abstract int zzack();

    public final void zzacl() {
        if (zzack() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void zzan(zzbah com_google_android_gms_internal_ads_zzbah) throws IOException;

    public final void zzap(boolean z) throws IOException {
        zza((byte) (z ? 1 : 0));
    }

    public final void zzb(double d) throws IOException {
        zzo(Double.doubleToRawLongBits(d));
    }

    public final void zzb(float f) throws IOException {
        zzcc(Float.floatToRawIntBits(f));
    }

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzu(j));
    }

    public abstract void zzb(int i, zzbah com_google_android_gms_internal_ads_zzbah) throws IOException;

    public abstract void zzbz(int i) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public abstract void zzca(int i) throws IOException;

    public final void zzcb(int i) throws IOException {
        zzca(zzck(i));
    }

    public abstract void zzcc(int i) throws IOException;

    public abstract void zze(zzbcu com_google_android_gms_internal_ads_zzbcu) throws IOException;

    abstract void zze(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzen(String str) throws IOException;

    public abstract void zzf(int i, String str) throws IOException;

    public abstract void zzf(int i, boolean z) throws IOException;

    public abstract void zzl(int i, int i2) throws IOException;

    public abstract void zzm(int i, int i2) throws IOException;

    public abstract void zzm(long j) throws IOException;

    public abstract void zzn(int i, int i2) throws IOException;

    public final void zzn(long j) throws IOException {
        zzm(zzu(j));
    }

    public final void zzo(int i, int i2) throws IOException {
        zzn(i, zzck(i2));
    }

    public abstract void zzo(long j) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;
}
