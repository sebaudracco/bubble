package com.google.android.gms.internal.clearcut;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzbn extends zzba {
    private static final Logger logger = Logger.getLogger(zzbn.class.getName());
    private static final boolean zzfy = zzfd.zzed();
    zzbp zzfz;

    private zzbn() {
    }

    public static int zza(int i, zzcv com_google_android_gms_internal_clearcut_zzcv) {
        int zzr = zzr(i);
        int zzas = com_google_android_gms_internal_clearcut_zzcv.zzas();
        return zzr + (zzas + zzt(zzas));
    }

    public static int zza(zzcv com_google_android_gms_internal_clearcut_zzcv) {
        int zzas = com_google_android_gms_internal_clearcut_zzcv.zzas();
        return zzas + zzt(zzas);
    }

    public static zzbn zza(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zzb(byteBuffer);
        }
        if (byteBuffer.isDirect() && !byteBuffer.isReadOnly()) {
            return zzfd.zzee() ? new zze(byteBuffer) : new zzd(byteBuffer);
        } else {
            throw new IllegalArgumentException("ByteBuffer is read-only");
        }
    }

    public static int zzb(double d) {
        return 8;
    }

    public static int zzb(float f) {
        return 4;
    }

    public static int zzb(int i, double d) {
        return zzr(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzr(i) + 4;
    }

    public static int zzb(int i, zzcv com_google_android_gms_internal_clearcut_zzcv) {
        return ((zzr(1) << 1) + zzh(2, i)) + zza(3, com_google_android_gms_internal_clearcut_zzcv);
    }

    static int zzb(int i, zzdo com_google_android_gms_internal_clearcut_zzdo, zzef com_google_android_gms_internal_clearcut_zzef) {
        return zzr(i) + zzb(com_google_android_gms_internal_clearcut_zzdo, com_google_android_gms_internal_clearcut_zzef);
    }

    public static int zzb(int i, String str) {
        return zzr(i) + zzh(str);
    }

    public static int zzb(zzbb com_google_android_gms_internal_clearcut_zzbb) {
        int size = com_google_android_gms_internal_clearcut_zzbb.size();
        return size + zzt(size);
    }

    static int zzb(zzdo com_google_android_gms_internal_clearcut_zzdo, zzef com_google_android_gms_internal_clearcut_zzef) {
        zzas com_google_android_gms_internal_clearcut_zzas = (zzas) com_google_android_gms_internal_clearcut_zzdo;
        int zzs = com_google_android_gms_internal_clearcut_zzas.zzs();
        if (zzs == -1) {
            zzs = com_google_android_gms_internal_clearcut_zzef.zzm(com_google_android_gms_internal_clearcut_zzas);
            com_google_android_gms_internal_clearcut_zzas.zzf(zzs);
        }
        return zzs + zzt(zzs);
    }

    public static int zzb(boolean z) {
        return 1;
    }

    public static int zzc(int i, zzbb com_google_android_gms_internal_clearcut_zzbb) {
        int zzr = zzr(i);
        int size = com_google_android_gms_internal_clearcut_zzbb.size();
        return zzr + (size + zzt(size));
    }

    public static int zzc(int i, zzdo com_google_android_gms_internal_clearcut_zzdo) {
        return zzr(i) + zzc(com_google_android_gms_internal_clearcut_zzdo);
    }

    @Deprecated
    static int zzc(int i, zzdo com_google_android_gms_internal_clearcut_zzdo, zzef com_google_android_gms_internal_clearcut_zzef) {
        int zzr = zzr(i) << 1;
        zzas com_google_android_gms_internal_clearcut_zzas = (zzas) com_google_android_gms_internal_clearcut_zzdo;
        int zzs = com_google_android_gms_internal_clearcut_zzas.zzs();
        if (zzs == -1) {
            zzs = com_google_android_gms_internal_clearcut_zzef.zzm(com_google_android_gms_internal_clearcut_zzas);
            com_google_android_gms_internal_clearcut_zzas.zzf(zzs);
        }
        return zzs + zzr;
    }

    public static int zzc(int i, boolean z) {
        return zzr(i) + 1;
    }

    public static int zzc(zzdo com_google_android_gms_internal_clearcut_zzdo) {
        int zzas = com_google_android_gms_internal_clearcut_zzdo.zzas();
        return zzas + zzt(zzas);
    }

    public static zzbn zzc(byte[] bArr) {
        return new zza(bArr, 0, bArr.length);
    }

    public static int zzd(int i, long j) {
        return zzr(i) + zzf(j);
    }

    public static int zzd(int i, zzbb com_google_android_gms_internal_clearcut_zzbb) {
        return ((zzr(1) << 1) + zzh(2, i)) + zzc(3, com_google_android_gms_internal_clearcut_zzbb);
    }

    public static int zzd(int i, zzdo com_google_android_gms_internal_clearcut_zzdo) {
        return ((zzr(1) << 1) + zzh(2, i)) + zzc(3, com_google_android_gms_internal_clearcut_zzdo);
    }

    @Deprecated
    public static int zzd(zzdo com_google_android_gms_internal_clearcut_zzdo) {
        return com_google_android_gms_internal_clearcut_zzdo.zzas();
    }

    public static int zzd(byte[] bArr) {
        int length = bArr.length;
        return length + zzt(length);
    }

    public static int zze(int i, long j) {
        return zzr(i) + zzf(j);
    }

    public static int zze(long j) {
        return zzf(j);
    }

    public static int zzf(int i, long j) {
        return zzr(i) + zzf(zzj(j));
    }

    public static int zzf(long j) {
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

    public static int zzg(int i, int i2) {
        return zzr(i) + zzs(i2);
    }

    public static int zzg(int i, long j) {
        return zzr(i) + 8;
    }

    public static int zzg(long j) {
        return zzf(zzj(j));
    }

    public static int zzh(int i, int i2) {
        return zzr(i) + zzt(i2);
    }

    public static int zzh(int i, long j) {
        return zzr(i) + 8;
    }

    public static int zzh(long j) {
        return 8;
    }

    public static int zzh(String str) {
        int zza;
        try {
            zza = zzff.zza(str);
        } catch (zzfi e) {
            zza = str.getBytes(zzci.UTF_8).length;
        }
        return zza + zzt(zza);
    }

    public static int zzi(int i, int i2) {
        return zzr(i) + zzt(zzy(i2));
    }

    public static int zzi(long j) {
        return 8;
    }

    public static int zzj(int i, int i2) {
        return zzr(i) + 4;
    }

    private static long zzj(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzk(int i, int i2) {
        return zzr(i) + 4;
    }

    public static int zzl(int i, int i2) {
        return zzr(i) + zzs(i2);
    }

    public static int zzr(int i) {
        return zzt(i << 3);
    }

    public static int zzs(int i) {
        return i >= 0 ? zzt(i) : 10;
    }

    public static int zzt(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzu(int i) {
        return zzt(zzy(i));
    }

    public static int zzv(int i) {
        return 4;
    }

    public static int zzw(int i) {
        return 4;
    }

    public static int zzx(int i) {
        return zzs(i);
    }

    private static int zzy(int i) {
        return (i << 1) ^ (i >> 31);
    }

    @Deprecated
    public static int zzz(int i) {
        return zzt(i);
    }

    public abstract void flush() throws IOException;

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(byte b) throws IOException;

    public final void zza(double d) throws IOException {
        zzd(Double.doubleToRawLongBits(d));
    }

    public final void zza(float f) throws IOException {
        zzq(Float.floatToRawIntBits(f));
    }

    public final void zza(int i, double d) throws IOException {
        zzc(i, Double.doubleToRawLongBits(d));
    }

    public final void zza(int i, float f) throws IOException {
        zzf(i, Float.floatToRawIntBits(f));
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzbb com_google_android_gms_internal_clearcut_zzbb) throws IOException;

    public abstract void zza(int i, zzdo com_google_android_gms_internal_clearcut_zzdo) throws IOException;

    abstract void zza(int i, zzdo com_google_android_gms_internal_clearcut_zzdo, zzef com_google_android_gms_internal_clearcut_zzef) throws IOException;

    public abstract void zza(int i, String str) throws IOException;

    public abstract void zza(zzbb com_google_android_gms_internal_clearcut_zzbb) throws IOException;

    abstract void zza(zzdo com_google_android_gms_internal_clearcut_zzdo, zzef com_google_android_gms_internal_clearcut_zzef) throws IOException;

    final void zza(String str, zzfi com_google_android_gms_internal_clearcut_zzfi) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", com_google_android_gms_internal_clearcut_zzfi);
        byte[] bytes = str.getBytes(zzci.UTF_8);
        try {
            zzo(bytes.length);
            zza(bytes, 0, bytes.length);
        } catch (Throwable e) {
            throw new zzc(e);
        } catch (zzc e2) {
            throw e2;
        }
    }

    public final void zza(boolean z) throws IOException {
        zza((byte) (z ? 1 : 0));
    }

    public abstract int zzag();

    public abstract void zzb(int i, int i2) throws IOException;

    public final void zzb(int i, long j) throws IOException {
        zza(i, zzj(j));
    }

    public abstract void zzb(int i, zzbb com_google_android_gms_internal_clearcut_zzbb) throws IOException;

    public abstract void zzb(int i, zzdo com_google_android_gms_internal_clearcut_zzdo) throws IOException;

    public abstract void zzb(int i, boolean z) throws IOException;

    public abstract void zzb(long j) throws IOException;

    public abstract void zzb(zzdo com_google_android_gms_internal_clearcut_zzdo) throws IOException;

    public abstract void zzc(int i, int i2) throws IOException;

    public abstract void zzc(int i, long j) throws IOException;

    public final void zzc(long j) throws IOException {
        zzb(zzj(j));
    }

    public abstract void zzd(int i, int i2) throws IOException;

    public abstract void zzd(long j) throws IOException;

    abstract void zzd(byte[] bArr, int i, int i2) throws IOException;

    public final void zze(int i, int i2) throws IOException {
        zzd(i, zzy(i2));
    }

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(String str) throws IOException;

    public abstract void zzn(int i) throws IOException;

    public abstract void zzo(int i) throws IOException;

    public final void zzp(int i) throws IOException {
        zzo(zzy(i));
    }

    public abstract void zzq(int i) throws IOException;
}
