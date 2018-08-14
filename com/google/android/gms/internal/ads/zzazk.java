package com.google.android.gms.internal.ads;

import java.util.Arrays;

final class zzazk {
    private static long zza(byte[] bArr, int i, int i2) {
        return (zzd(bArr, i) >> i2) & 67108863;
    }

    private static void zza(byte[] bArr, long j, int i) {
        int i2 = 0;
        while (i2 < 4) {
            bArr[i + i2] = (byte) ((int) (255 & j));
            i2++;
            j >>= 8;
        }
    }

    private static long zzd(byte[] bArr, int i) {
        return ((long) ((((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24))) & 4294967295L;
    }

    static byte[] zze(byte[] bArr, byte[] bArr2) {
        if (bArr.length != 32) {
            throw new IllegalArgumentException("The key length in bytes must be 32.");
        }
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long zza = 67108863 & zza(bArr, 0, 0);
        long zza2 = 67108611 & zza(bArr, 3, 2);
        long zza3 = 67092735 & zza(bArr, 6, 4);
        long zza4 = 66076671 & zza(bArr, 9, 6);
        long zza5 = 1048575 & zza(bArr, 12, 8);
        long j6 = zza2 * 5;
        long j7 = zza3 * 5;
        long j8 = zza4 * 5;
        long j9 = zza5 * 5;
        byte[] bArr3 = new byte[17];
        for (int i = 0; i < bArr2.length; i += 16) {
            int min = Math.min(16, bArr2.length - i);
            System.arraycopy(bArr2, i, bArr3, 0, min);
            bArr3[min] = (byte) 1;
            if (min != 16) {
                Arrays.fill(bArr3, min + 1, 17, (byte) 0);
            }
            j += zza(bArr3, 0, 0);
            j2 += zza(bArr3, 3, 2);
            j3 += zza(bArr3, 6, 4);
            j4 += zza(bArr3, 9, 6);
            j5 += zza(bArr3, 12, 8) | ((long) (bArr3[16] << 24));
            long j10 = ((((j * zza) + (j2 * j9)) + (j3 * j8)) + (j4 * j7)) + (j5 * j6);
            long j11 = ((((j * zza2) + (j2 * zza)) + (j3 * j9)) + (j4 * j8)) + (j5 * j7);
            long j12 = ((((j * zza3) + (j2 * zza2)) + (j3 * zza)) + (j4 * j9)) + (j5 * j8);
            j3 *= zza3;
            j4 *= zza2;
            j5 *= zza;
            j5 += j4 + (j3 + ((j2 * zza4) + (j * zza5)));
            j2 = j10 & 67108863;
            j4 = (j10 >> 26) + j11;
            j10 = j4 & 67108863;
            j4 = j12 + (j4 >> 26);
            j3 = 67108863 & j4;
            j4 = (((((j * zza4) + (j2 * zza3)) + (j3 * zza2)) + (j4 * zza)) + (j5 * j9)) + (j4 >> 26);
            j = j4 >> 26;
            j4 &= 67108863;
            j5 += j;
            j = j5 >> 26;
            j5 &= 67108863;
            j2 += j * 5;
            j = 67108863 & j2;
            j2 = j10 + (j2 >> 26);
        }
        long j13 = (j2 >> 26) + j3;
        j3 = j13 >> 26;
        j13 &= 67108863;
        j4 += j3;
        j3 = j4 >> 26;
        j4 &= 67108863;
        j5 += j3;
        j3 = j5 >> 26;
        j5 &= 67108863;
        j3 = (j3 * 5) + j;
        j = j3 >> 26;
        j3 &= 67108863;
        j2 = (j2 & 67108863) + j;
        j = 5 + j3;
        zza = (j >> 26) + j2;
        zza2 = (zza >> 26) + j13;
        zza3 = (zza2 >> 26) + j4;
        zza4 = ((zza3 >> 26) + j5) - 67108864;
        zza5 = zza4 >> 63;
        j3 &= zza5;
        j2 &= zza5;
        j13 &= zza5;
        j4 &= zza5;
        j5 &= zza5;
        zza5 ^= -1;
        j2 |= (zza & 67108863) & zza5;
        j13 |= (zza2 & 67108863) & zza5;
        j4 |= (zza3 & 67108863) & zza5;
        j3 = ((j3 | ((j & 67108863) & zza5)) | (j2 << 26)) & 4294967295L;
        j2 = ((j2 >> 6) | (j13 << 20)) & 4294967295L;
        j13 = ((j13 >> 12) | (j4 << 14)) & 4294967295L;
        j5 = (((j5 | (zza4 & zza5)) << 8) | (j4 >> 18)) & 4294967295L;
        j4 = zzd(bArr, 16) + j3;
        j3 = 4294967295L & j4;
        j4 = (j4 >> 32) + (j2 + zzd(bArr, 20));
        j2 = 4294967295L & j4;
        j13 = (j13 + zzd(bArr, 24)) + (j4 >> 32);
        j4 = 4294967295L & j13;
        j13 = ((j13 >> 32) + (j5 + zzd(bArr, 28))) & 4294967295L;
        byte[] bArr4 = new byte[16];
        zza(bArr4, j3, 0);
        zza(bArr4, j2, 4);
        zza(bArr4, j4, 8);
        zza(bArr4, j13, 12);
        return bArr4;
    }
}
