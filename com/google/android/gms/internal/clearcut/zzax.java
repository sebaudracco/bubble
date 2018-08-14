package com.google.android.gms.internal.clearcut;

import java.io.IOException;

final class zzax {
    static int zza(int i, byte[] bArr, int i2, int i3, zzay com_google_android_gms_internal_clearcut_zzay) throws zzco {
        if ((i >>> 3) == 0) {
            throw zzco.zzbm();
        }
        switch (i & 7) {
            case 0:
                return zzb(bArr, i2, com_google_android_gms_internal_clearcut_zzay);
            case 1:
                return i2 + 8;
            case 2:
                return zza(bArr, i2, com_google_android_gms_internal_clearcut_zzay) + com_google_android_gms_internal_clearcut_zzay.zzfd;
            case 3:
                int i4;
                int i5 = (i & -8) | 4;
                int i6 = 0;
                int i7 = i2;
                while (i7 < i3) {
                    i7 = zza(bArr, i7, com_google_android_gms_internal_clearcut_zzay);
                    i6 = com_google_android_gms_internal_clearcut_zzay.zzfd;
                    if (i6 != i5) {
                        i7 = zza(i6, bArr, i7, i3, com_google_android_gms_internal_clearcut_zzay);
                    } else {
                        i4 = i6;
                        i6 = i7;
                        i7 = i4;
                        if (i6 > i3 && i7 == i5) {
                            return i6;
                        }
                        throw zzco.zzbo();
                    }
                }
                i4 = i6;
                i6 = i7;
                i7 = i4;
                if (i6 > i3) {
                }
                throw zzco.zzbo();
            case 5:
                return i2 + 4;
            default:
                throw zzco.zzbm();
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzcn<?> com_google_android_gms_internal_clearcut_zzcn_, zzay com_google_android_gms_internal_clearcut_zzay) {
        zzch com_google_android_gms_internal_clearcut_zzch = (zzch) com_google_android_gms_internal_clearcut_zzcn_;
        int zza = zza(bArr, i2, com_google_android_gms_internal_clearcut_zzay);
        com_google_android_gms_internal_clearcut_zzch.zzac(com_google_android_gms_internal_clearcut_zzay.zzfd);
        while (zza < i3) {
            int zza2 = zza(bArr, zza, com_google_android_gms_internal_clearcut_zzay);
            if (i != com_google_android_gms_internal_clearcut_zzay.zzfd) {
                break;
            }
            zza = zza(bArr, zza2, com_google_android_gms_internal_clearcut_zzay);
            com_google_android_gms_internal_clearcut_zzch.zzac(com_google_android_gms_internal_clearcut_zzay.zzfd);
        }
        return zza;
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzey com_google_android_gms_internal_clearcut_zzey, zzay com_google_android_gms_internal_clearcut_zzay) throws IOException {
        if ((i >>> 3) == 0) {
            throw zzco.zzbm();
        }
        int zzb;
        int i4;
        switch (i & 7) {
            case 0:
                zzb = zzb(bArr, i2, com_google_android_gms_internal_clearcut_zzay);
                com_google_android_gms_internal_clearcut_zzey.zzb(i, Long.valueOf(com_google_android_gms_internal_clearcut_zzay.zzfe));
                return zzb;
            case 1:
                com_google_android_gms_internal_clearcut_zzey.zzb(i, Long.valueOf(zzd(bArr, i2)));
                return i2 + 8;
            case 2:
                zzb = zza(bArr, i2, com_google_android_gms_internal_clearcut_zzay);
                i4 = com_google_android_gms_internal_clearcut_zzay.zzfd;
                if (i4 == 0) {
                    com_google_android_gms_internal_clearcut_zzey.zzb(i, zzbb.zzfi);
                } else {
                    com_google_android_gms_internal_clearcut_zzey.zzb(i, zzbb.zzb(bArr, zzb, i4));
                }
                return zzb + i4;
            case 3:
                zzey zzeb = zzey.zzeb();
                int i5 = (i & -8) | 4;
                zzb = 0;
                int i6 = i2;
                while (i6 < i3) {
                    i6 = zza(bArr, i6, com_google_android_gms_internal_clearcut_zzay);
                    zzb = com_google_android_gms_internal_clearcut_zzay.zzfd;
                    if (zzb != i5) {
                        i6 = zza(zzb, bArr, i6, i3, zzeb, com_google_android_gms_internal_clearcut_zzay);
                    } else {
                        i4 = zzb;
                        zzb = i6;
                        if (zzb <= i3 || i4 != i5) {
                            throw zzco.zzbo();
                        }
                        com_google_android_gms_internal_clearcut_zzey.zzb(i, zzeb);
                        return zzb;
                    }
                }
                i4 = zzb;
                zzb = i6;
                if (zzb <= i3) {
                }
                throw zzco.zzbo();
            case 5:
                com_google_android_gms_internal_clearcut_zzey.zzb(i, Integer.valueOf(zzc(bArr, i2)));
                return i2 + 4;
            default:
                throw zzco.zzbm();
        }
    }

    static int zza(int i, byte[] bArr, int i2, zzay com_google_android_gms_internal_clearcut_zzay) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= (byte) 0) {
            com_google_android_gms_internal_clearcut_zzay.zzfd = i3 | (b << 7);
            return i4;
        }
        int i5 = ((b & 127) << 7) | i3;
        i3 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= (byte) 0) {
            com_google_android_gms_internal_clearcut_zzay.zzfd = (b2 << 14) | i5;
            return i3;
        }
        i4 = ((b2 & 127) << 14) | i5;
        i5 = i3 + 1;
        byte b3 = bArr[i3];
        if (b3 >= (byte) 0) {
            com_google_android_gms_internal_clearcut_zzay.zzfd = i4 | (b3 << 21);
            return i5;
        }
        i3 = ((b3 & 127) << 21) | i4;
        i4 = i5 + 1;
        b = bArr[i5];
        if (b >= (byte) 0) {
            com_google_android_gms_internal_clearcut_zzay.zzfd = i3 | (b << 28);
            return i4;
        }
        i5 = ((b & 127) << 28) | i3;
        while (true) {
            i3 = i4 + 1;
            if (bArr[i4] >= (byte) 0) {
                com_google_android_gms_internal_clearcut_zzay.zzfd = i5;
                return i3;
            }
            i4 = i3;
        }
    }

    static int zza(byte[] bArr, int i, zzay com_google_android_gms_internal_clearcut_zzay) {
        int i2 = i + 1;
        int i3 = bArr[i];
        if (i3 < (byte) 0) {
            return zza(i3, bArr, i2, com_google_android_gms_internal_clearcut_zzay);
        }
        com_google_android_gms_internal_clearcut_zzay.zzfd = i3;
        return i2;
    }

    static int zza(byte[] bArr, int i, zzcn<?> com_google_android_gms_internal_clearcut_zzcn_, zzay com_google_android_gms_internal_clearcut_zzay) throws IOException {
        zzch com_google_android_gms_internal_clearcut_zzch = (zzch) com_google_android_gms_internal_clearcut_zzcn_;
        int zza = zza(bArr, i, com_google_android_gms_internal_clearcut_zzay);
        int i2 = com_google_android_gms_internal_clearcut_zzay.zzfd + zza;
        while (zza < i2) {
            zza = zza(bArr, zza, com_google_android_gms_internal_clearcut_zzay);
            com_google_android_gms_internal_clearcut_zzch.zzac(com_google_android_gms_internal_clearcut_zzay.zzfd);
        }
        if (zza == i2) {
            return zza;
        }
        throw zzco.zzbl();
    }

    static int zzb(byte[] bArr, int i, zzay com_google_android_gms_internal_clearcut_zzay) {
        int i2 = 7;
        int i3 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            com_google_android_gms_internal_clearcut_zzay.zzfe = j;
        } else {
            j &= 127;
            int i4 = i3 + 1;
            byte b = bArr[i3];
            byte b2 = b;
            i3 = i4;
            long j2 = j | (((long) (b & 127)) << 7);
            byte b3 = b2;
            while (b3 < (byte) 0) {
                int i5 = i3 + 1;
                b3 = bArr[i3];
                i3 = i2 + 7;
                j2 |= ((long) (b3 & 127)) << i3;
                i2 = i3;
                i3 = i5;
            }
            com_google_android_gms_internal_clearcut_zzay.zzfe = j2;
        }
        return i3;
    }

    static int zzc(byte[] bArr, int i) {
        return (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16)) | ((bArr[i + 3] & 255) << 24);
    }

    static int zzc(byte[] bArr, int i, zzay com_google_android_gms_internal_clearcut_zzay) {
        int zza = zza(bArr, i, com_google_android_gms_internal_clearcut_zzay);
        int i2 = com_google_android_gms_internal_clearcut_zzay.zzfd;
        if (i2 == 0) {
            com_google_android_gms_internal_clearcut_zzay.zzff = "";
            return zza;
        }
        com_google_android_gms_internal_clearcut_zzay.zzff = new String(bArr, zza, i2, zzci.UTF_8);
        return zza + i2;
    }

    static int zzd(byte[] bArr, int i, zzay com_google_android_gms_internal_clearcut_zzay) throws IOException {
        int zza = zza(bArr, i, com_google_android_gms_internal_clearcut_zzay);
        int i2 = com_google_android_gms_internal_clearcut_zzay.zzfd;
        if (i2 == 0) {
            com_google_android_gms_internal_clearcut_zzay.zzff = "";
            return zza;
        } else if (zzff.zze(bArr, zza, zza + i2)) {
            com_google_android_gms_internal_clearcut_zzay.zzff = new String(bArr, zza, i2, zzci.UTF_8);
            return zza + i2;
        } else {
            throw zzco.zzbp();
        }
    }

    static long zzd(byte[] bArr, int i) {
        return (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48)) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    static double zze(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzd(bArr, i));
    }

    static int zze(byte[] bArr, int i, zzay com_google_android_gms_internal_clearcut_zzay) {
        int zza = zza(bArr, i, com_google_android_gms_internal_clearcut_zzay);
        int i2 = com_google_android_gms_internal_clearcut_zzay.zzfd;
        if (i2 == 0) {
            com_google_android_gms_internal_clearcut_zzay.zzff = zzbb.zzfi;
            return zza;
        }
        com_google_android_gms_internal_clearcut_zzay.zzff = zzbb.zzb(bArr, zza, i2);
        return zza + i2;
    }

    static float zzf(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzc(bArr, i));
    }
}
