package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzbbq {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final byte[] zzduq;
    private static final ByteBuffer zzdur;
    private static final zzbaq zzdus;

    static {
        byte[] bArr = new byte[0];
        zzduq = bArr;
        zzdur = ByteBuffer.wrap(bArr);
        bArr = zzduq;
        zzdus = zzbaq.zza(bArr, 0, bArr.length, false);
    }

    static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        length = zza(length, bArr, 0, length);
        return length == 0 ? 1 : length;
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static Object zza(Object obj, Object obj2) {
        return ((zzbcu) obj).zzade().zzd((zzbcu) obj2).zzadj();
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static int zzar(boolean z) {
        return z ? 1231 : 1237;
    }

    static boolean zzi(zzbcu com_google_android_gms_internal_ads_zzbcu) {
        return false;
    }

    public static boolean zzs(byte[] bArr) {
        return zzbem.zzs(bArr);
    }

    public static String zzt(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int zzv(long j) {
        return (int) ((j >>> 32) ^ j);
    }
}
