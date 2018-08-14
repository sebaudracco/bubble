package com.google.android.gms.internal.ads;

import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzbek {
    private static final Logger logger = Logger.getLogger(zzbek.class.getName());
    private static final Class<?> zzdpj = zzbac.zzabc();
    private static final boolean zzdqm = zzagi();
    private static final Unsafe zzdwf = zzagh();
    private static final boolean zzdze = zzi(Long.TYPE);
    private static final boolean zzdzf = zzi(Integer.TYPE);
    private static final zzd zzdzg;
    private static final boolean zzdzh = zzagj();
    private static final long zzdzi = ((long) zzg(byte[].class));
    private static final long zzdzj = ((long) zzg(boolean[].class));
    private static final long zzdzk = ((long) zzh(boolean[].class));
    private static final long zzdzl = ((long) zzg(int[].class));
    private static final long zzdzm = ((long) zzh(int[].class));
    private static final long zzdzn = ((long) zzg(long[].class));
    private static final long zzdzo = ((long) zzh(long[].class));
    private static final long zzdzp = ((long) zzg(float[].class));
    private static final long zzdzq = ((long) zzh(float[].class));
    private static final long zzdzr = ((long) zzg(double[].class));
    private static final long zzdzs = ((long) zzh(double[].class));
    private static final long zzdzt = ((long) zzg(Object[].class));
    private static final long zzdzu = ((long) zzh(Object[].class));
    private static final long zzdzv = zzb(zzagk());
    private static final long zzdzw;
    private static final boolean zzdzx = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);

    static abstract class zzd {
        Unsafe zzdzy;

        zzd(Unsafe unsafe) {
            this.zzdzy = unsafe;
        }

        public final long zza(Field field) {
            return this.zzdzy.objectFieldOffset(field);
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public final void zza(Object obj, long j, long j2) {
            this.zzdzy.putLong(obj, j, j2);
        }

        public abstract void zza(Object obj, long j, boolean z);

        public final void zzb(Object obj, long j, int i) {
            this.zzdzy.putInt(obj, j, i);
        }

        public abstract void zze(Object obj, long j, byte b);

        public final int zzk(Object obj, long j) {
            return this.zzdzy.getInt(obj, j);
        }

        public final long zzl(Object obj, long j) {
            return this.zzdzy.getLong(obj, j);
        }

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);
    }

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzbek.zzdzx) {
                zzbek.zzb(obj, j, z);
            } else {
                zzbek.zzc(obj, j, z);
            }
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzbek.zzdzx) {
                zzbek.zza(obj, j, b);
            } else {
                zzbek.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            return zzbek.zzdzx ? zzbek.zzs(obj, j) : zzbek.zzt(obj, j);
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final byte zzy(Object obj, long j) {
            return zzbek.zzdzx ? zzbek.zzq(obj, j) : zzbek.zzr(obj, j);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzbek.zzdzx) {
                zzbek.zzb(obj, j, z);
            } else {
                zzbek.zzc(obj, j, z);
            }
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzbek.zzdzx) {
                zzbek.zza(obj, j, b);
            } else {
                zzbek.zzb(obj, j, b);
            }
        }

        public final boolean zzm(Object obj, long j) {
            return zzbek.zzdzx ? zzbek.zzs(obj, j) : zzbek.zzt(obj, j);
        }

        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        public final byte zzy(Object obj, long j) {
            return zzbek.zzdzx ? zzbek.zzq(obj, j) : zzbek.zzr(obj, j);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzdzy.putDouble(obj, j, d);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzdzy.putFloat(obj, j, f);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzdzy.putBoolean(obj, j, z);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzdzy.putByte(obj, j, b);
        }

        public final boolean zzm(Object obj, long j) {
            return this.zzdzy.getBoolean(obj, j);
        }

        public final float zzn(Object obj, long j) {
            return this.zzdzy.getFloat(obj, j);
        }

        public final double zzo(Object obj, long j) {
            return this.zzdzy.getDouble(obj, j);
        }

        public final byte zzy(Object obj, long j) {
            return this.zzdzy.getByte(obj, j);
        }
    }

    static {
        Field field = null;
        zzd com_google_android_gms_internal_ads_zzbek_zzb = zzdwf == null ? null : zzbac.zzabb() ? zzdze ? new zzb(zzdwf) : zzdzf ? new zza(zzdwf) : null : new zzc(zzdwf);
        zzdzg = com_google_android_gms_internal_ads_zzbek_zzb;
        Field zzb = zzb(String.class, FirebaseAnalytics$Param.VALUE);
        if (zzb != null && zzb.getType() == char[].class) {
            field = zzb;
        }
        zzdzw = zzb(field);
    }

    private zzbek() {
    }

    static byte zza(byte[] bArr, long j) {
        return zzdzg.zzy(bArr, zzdzi + j);
    }

    static long zza(Field field) {
        return zzdzg.zza(field);
    }

    private static void zza(Object obj, long j, byte b) {
        int i = ((((int) j) ^ -1) & 3) << 3;
        zzb(obj, j & -4, (zzk(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    static void zza(Object obj, long j, double d) {
        zzdzg.zza(obj, j, d);
    }

    static void zza(Object obj, long j, float f) {
        zzdzg.zza(obj, j, f);
    }

    static void zza(Object obj, long j, long j2) {
        zzdzg.zza(obj, j, j2);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzdzg.zzdzy.putObject(obj, j, obj2);
    }

    static void zza(Object obj, long j, boolean z) {
        zzdzg.zza(obj, j, z);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzdzg.zze(bArr, zzdzi + j, b);
    }

    static boolean zzagf() {
        return zzdqm;
    }

    static boolean zzagg() {
        return zzdzh;
    }

    static Unsafe zzagh() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzbel());
        } catch (Throwable th) {
            return null;
        }
    }

    private static boolean zzagi() {
        if (zzdwf == null) {
            return false;
        }
        try {
            Class cls = zzdwf.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzbac.zzabb()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static boolean zzagj() {
        if (zzdwf == null) {
            return false;
        }
        try {
            Class cls = zzdwf.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzagk() == null) {
                return false;
            }
            if (zzbac.zzabb()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static Field zzagk() {
        Field zzb;
        if (zzbac.zzabb()) {
            zzb = zzb(Buffer.class, "effectiveDirectAddress");
            if (zzb != null) {
                return zzb;
            }
        }
        zzb = zzb(Buffer.class, "address");
        return (zzb == null || zzb.getType() != Long.TYPE) ? null : zzb;
    }

    private static long zzb(Field field) {
        return (field == null || zzdzg == null) ? -1 : zzdzg.zza(field);
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable th) {
            return null;
        }
    }

    private static void zzb(Object obj, long j, byte b) {
        int i = (((int) j) & 3) << 3;
        zzb(obj, j & -4, (zzk(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    static void zzb(Object obj, long j, int i) {
        zzdzg.zzb(obj, j, i);
    }

    private static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, (byte) (z ? 1 : 0));
    }

    private static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, (byte) (z ? 1 : 0));
    }

    private static int zzg(Class<?> cls) {
        return zzdqm ? zzdzg.zzdzy.arrayBaseOffset(cls) : -1;
    }

    private static int zzh(Class<?> cls) {
        return zzdqm ? zzdzg.zzdzy.arrayIndexScale(cls) : -1;
    }

    private static boolean zzi(Class<?> cls) {
        if (!zzbac.zzabb()) {
            return false;
        }
        try {
            Class cls2 = zzdpj;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    static int zzk(Object obj, long j) {
        return zzdzg.zzk(obj, j);
    }

    static long zzl(Object obj, long j) {
        return zzdzg.zzl(obj, j);
    }

    static boolean zzm(Object obj, long j) {
        return zzdzg.zzm(obj, j);
    }

    static float zzn(Object obj, long j) {
        return zzdzg.zzn(obj, j);
    }

    static double zzo(Object obj, long j) {
        return zzdzg.zzo(obj, j);
    }

    static Object zzp(Object obj, long j) {
        return zzdzg.zzdzy.getObject(obj, j);
    }

    private static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) (((-1 ^ j) & 3) << 3)));
    }

    private static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, -4 & j) >>> ((int) ((3 & j) << 3)));
    }

    private static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != (byte) 0;
    }

    private static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != (byte) 0;
    }
}
