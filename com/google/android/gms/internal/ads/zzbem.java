package com.google.android.gms.internal.ads;

final class zzbem {
    private static final zzben zzdzz;

    static {
        Object obj = (zzbek.zzagf() && zzbek.zzagg()) ? 1 : null;
        zzdzz = obj != null ? new zzbeq() : new zzbeo();
    }

    static int zza(CharSequence charSequence) {
        int i = 0;
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < '') {
            i2++;
        }
        int i3 = length;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt < 'ࠀ') {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 'ࠀ') {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if ('?' <= charAt2 && charAt2 <= '?') {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new zzbep(i2, length2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i2 = i3 + i;
                if (i2 < length) {
                    return i2;
                }
                throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
            }
        }
        i2 = i3;
        if (i2 < length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + 4294967296L));
    }

    static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return zzdzz.zzb(charSequence, bArr, i, i2);
    }

    private static int zzda(int i) {
        return i > -12 ? -1 : i;
    }

    private static int zzf(int i, int i2, int i3) {
        return (i > -12 || i2 > -65 || i3 > -65) ? -1 : ((i2 << 8) ^ i) ^ (i3 << 16);
    }

    public static boolean zzf(byte[] bArr, int i, int i2) {
        return zzdzz.zzf(bArr, i, i2);
    }

    private static int zzg(byte[] bArr, int i, int i2) {
        int i3 = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return zzda(i3);
            case 1:
                return zzz(i3, bArr[i]);
            case 2:
                return zzf(i3, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }

    public static boolean zzs(byte[] bArr) {
        return zzdzz.zzf(bArr, 0, bArr.length);
    }

    private static int zzz(int i, int i2) {
        return (i > -12 || i2 > -65) ? -1 : (i2 << 8) ^ i;
    }
}
