package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzzr {
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final byte[] zzbsq;
    private static final ByteBuffer zzbsr;
    private static final zzze zzbss;

    static {
        byte[] bArr = new byte[0];
        zzbsq = bArr;
        zzbsr = ByteBuffer.wrap(bArr);
        bArr = zzbsq;
        zzbss = zzze.zza(bArr, 0, bArr.length, false);
    }

    static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static int zza(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    static <T> T zza(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }
}
