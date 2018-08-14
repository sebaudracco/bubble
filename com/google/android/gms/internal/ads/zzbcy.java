package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.internal.ads.zzbbo.zze;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import sun.misc.Unsafe;

final class zzbcy<T> implements zzbdm<T> {
    private static final Unsafe zzdwf = zzbek.zzagh();
    private final int[] zzdwg;
    private final Object[] zzdwh;
    private final int zzdwi;
    private final int zzdwj;
    private final int zzdwk;
    private final zzbcu zzdwl;
    private final boolean zzdwm;
    private final boolean zzdwn;
    private final boolean zzdwo;
    private final boolean zzdwp;
    private final int[] zzdwq;
    private final int[] zzdwr;
    private final int[] zzdws;
    private final zzbdc zzdwt;
    private final zzbce zzdwu;
    private final zzbee<?, ?> zzdwv;
    private final zzbbd<?> zzdww;
    private final zzbcp zzdwx;

    private zzbcy(int[] iArr, Object[] objArr, int i, int i2, int i3, zzbcu com_google_android_gms_internal_ads_zzbcu, boolean z, boolean z2, int[] iArr2, int[] iArr3, int[] iArr4, zzbdc com_google_android_gms_internal_ads_zzbdc, zzbce com_google_android_gms_internal_ads_zzbce, zzbee<?, ?> com_google_android_gms_internal_ads_zzbee___, zzbbd<?> com_google_android_gms_internal_ads_zzbbd_, zzbcp com_google_android_gms_internal_ads_zzbcp) {
        this.zzdwg = iArr;
        this.zzdwh = objArr;
        this.zzdwi = i;
        this.zzdwj = i2;
        this.zzdwk = i3;
        this.zzdwn = com_google_android_gms_internal_ads_zzbcu instanceof zzbbo;
        this.zzdwo = z;
        boolean z3 = com_google_android_gms_internal_ads_zzbbd_ != null && com_google_android_gms_internal_ads_zzbbd_.zzh(com_google_android_gms_internal_ads_zzbcu);
        this.zzdwm = z3;
        this.zzdwp = false;
        this.zzdwq = iArr2;
        this.zzdwr = iArr3;
        this.zzdws = iArr4;
        this.zzdwt = com_google_android_gms_internal_ads_zzbdc;
        this.zzdwu = com_google_android_gms_internal_ads_zzbce;
        this.zzdwv = com_google_android_gms_internal_ads_zzbee___;
        this.zzdww = com_google_android_gms_internal_ads_zzbbd_;
        this.zzdwl = com_google_android_gms_internal_ads_zzbcu;
        this.zzdwx = com_google_android_gms_internal_ads_zzbcp;
    }

    private static int zza(int i, byte[] bArr, int i2, int i3, Object obj, zzbae com_google_android_gms_internal_ads_zzbae) throws IOException {
        return zzbad.zza(i, bArr, i2, i3, zzz(obj), com_google_android_gms_internal_ads_zzbae);
    }

    private static int zza(zzbdm<?> com_google_android_gms_internal_ads_zzbdm_, int i, byte[] bArr, int i2, int i3, zzbbt<?> com_google_android_gms_internal_ads_zzbbt_, zzbae com_google_android_gms_internal_ads_zzbae) throws IOException {
        int zza = zza((zzbdm) com_google_android_gms_internal_ads_zzbdm_, bArr, i2, i3, com_google_android_gms_internal_ads_zzbae);
        com_google_android_gms_internal_ads_zzbbt_.add(com_google_android_gms_internal_ads_zzbae.zzdpn);
        while (zza < i3) {
            int zza2 = zzbad.zza(bArr, zza, com_google_android_gms_internal_ads_zzbae);
            if (i != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                break;
            }
            zza = zza((zzbdm) com_google_android_gms_internal_ads_zzbdm_, bArr, zza2, i3, com_google_android_gms_internal_ads_zzbae);
            com_google_android_gms_internal_ads_zzbbt_.add(com_google_android_gms_internal_ads_zzbae.zzdpn);
        }
        return zza;
    }

    private static int zza(zzbdm com_google_android_gms_internal_ads_zzbdm, byte[] bArr, int i, int i2, int i3, zzbae com_google_android_gms_internal_ads_zzbae) throws IOException {
        zzbcy com_google_android_gms_internal_ads_zzbcy = (zzbcy) com_google_android_gms_internal_ads_zzbdm;
        Object newInstance = com_google_android_gms_internal_ads_zzbcy.newInstance();
        int zza = com_google_android_gms_internal_ads_zzbcy.zza(newInstance, bArr, i, i2, i3, com_google_android_gms_internal_ads_zzbae);
        com_google_android_gms_internal_ads_zzbcy.zzo(newInstance);
        com_google_android_gms_internal_ads_zzbae.zzdpn = newInstance;
        return zza;
    }

    private static int zza(zzbdm com_google_android_gms_internal_ads_zzbdm, byte[] bArr, int i, int i2, zzbae com_google_android_gms_internal_ads_zzbae) throws IOException {
        int i3;
        int i4 = i + 1;
        int i5 = bArr[i];
        if (i5 < (byte) 0) {
            i4 = zzbad.zza(i5, bArr, i4, com_google_android_gms_internal_ads_zzbae);
            i3 = com_google_android_gms_internal_ads_zzbae.zzdpl;
        } else {
            i3 = i5;
        }
        if (i3 < 0 || i3 > i2 - i4) {
            throw zzbbu.zzadl();
        }
        Object newInstance = com_google_android_gms_internal_ads_zzbdm.newInstance();
        com_google_android_gms_internal_ads_zzbdm.zza(newInstance, bArr, i4, i4 + i3, com_google_android_gms_internal_ads_zzbae);
        com_google_android_gms_internal_ads_zzbdm.zzo(newInstance);
        com_google_android_gms_internal_ads_zzbae.zzdpn = newInstance;
        return i4 + i3;
    }

    private static <UT, UB> int zza(zzbee<UT, UB> com_google_android_gms_internal_ads_zzbee_UT__UB, T t) {
        return com_google_android_gms_internal_ads_zzbee_UT__UB.zzy(com_google_android_gms_internal_ads_zzbee_UT__UB.zzac(t));
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzbae com_google_android_gms_internal_ads_zzbae) throws IOException {
        int i9;
        Unsafe unsafe = zzdwf;
        long j2 = (long) (this.zzdwg[i8 + 2] & 1048575);
        int zzb;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(zzbad.zzg(bArr, i)));
                    i9 = i + 8;
                    break;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(zzbad.zzh(bArr, i)));
                    i9 = i + 4;
                    break;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    i9 = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    unsafe.putObject(t, j, Long.valueOf(com_google_android_gms_internal_ads_zzbae.zzdpm));
                    break;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    i9 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    unsafe.putObject(t, j, Integer.valueOf(com_google_android_gms_internal_ads_zzbae.zzdpl));
                    break;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(zzbad.zzf(bArr, i)));
                    i9 = i + 8;
                    break;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(zzbad.zze(bArr, i)));
                    i9 = i + 4;
                    break;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    zzb = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    unsafe.putObject(t, j, Boolean.valueOf(com_google_android_gms_internal_ads_zzbae.zzdpm != 0));
                    i9 = zzb;
                    break;
                }
                return i;
            case 59:
                if (i5 != 2) {
                    return i;
                }
                i9 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                zzb = com_google_android_gms_internal_ads_zzbae.zzdpl;
                if (zzb == 0) {
                    unsafe.putObject(t, j, "");
                } else if ((ErrorDialogData.DYNAMITE_CRASH & i6) == 0 || zzbem.zzf(bArr, i9, i9 + zzb)) {
                    unsafe.putObject(t, j, new String(bArr, i9, zzb, zzbbq.UTF_8));
                    i9 += zzb;
                } else {
                    throw zzbbu.zzads();
                }
                unsafe.putInt(t, j2, i4);
                return i9;
            case 60:
                if (i5 != 2) {
                    return i;
                }
                i = zza(zzcq(i8), bArr, i, i2, com_google_android_gms_internal_ads_zzbae);
                Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                if (object == null) {
                    unsafe.putObject(t, j, com_google_android_gms_internal_ads_zzbae.zzdpn);
                } else {
                    unsafe.putObject(t, j, zzbbq.zza(object, com_google_android_gms_internal_ads_zzbae.zzdpn));
                }
                unsafe.putInt(t, j2, i4);
                return i;
            case 61:
                if (i5 != 2) {
                    return i;
                }
                i9 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                zzb = com_google_android_gms_internal_ads_zzbae.zzdpl;
                if (zzb == 0) {
                    unsafe.putObject(t, j, zzbah.zzdpq);
                } else {
                    unsafe.putObject(t, j, zzbah.zzc(bArr, i9, zzb));
                    i9 += zzb;
                }
                unsafe.putInt(t, j2, i4);
                return i9;
            case 63:
                if (i5 != 0) {
                    return i;
                }
                i9 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                zzb = com_google_android_gms_internal_ads_zzbae.zzdpl;
                zzbbs zzcs = zzcs(i8);
                if (zzcs == null || zzcs.zzq(zzb) != null) {
                    unsafe.putObject(t, j, Integer.valueOf(zzb));
                    break;
                }
                zzz(t).zzb(i3, Long.valueOf((long) zzb));
                return i9;
            case 66:
                if (i5 == 0) {
                    i9 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    unsafe.putObject(t, j, Integer.valueOf(zzbaq.zzbu(com_google_android_gms_internal_ads_zzbae.zzdpl)));
                    break;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    i9 = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    unsafe.putObject(t, j, Long.valueOf(zzbaq.zzl(com_google_android_gms_internal_ads_zzbae.zzdpm)));
                    break;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    i9 = zza(zzcq(i8), bArr, i, i2, (i3 & -8) | 4, com_google_android_gms_internal_ads_zzbae);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 != null) {
                        unsafe.putObject(t, j, zzbbq.zza(object2, com_google_android_gms_internal_ads_zzbae.zzdpn));
                        break;
                    }
                    unsafe.putObject(t, j, com_google_android_gms_internal_ads_zzbae.zzdpn);
                    break;
                }
                return i;
            default:
                return i;
        }
        unsafe.putInt(t, j2, i4);
        return i9;
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzbae com_google_android_gms_internal_ads_zzbae) throws IOException {
        zzbbt com_google_android_gms_internal_ads_zzbbt;
        zzbbt com_google_android_gms_internal_ads_zzbbt2 = (zzbbt) zzdwf.getObject(t, j2);
        if (com_google_android_gms_internal_ads_zzbbt2.zzaay()) {
            com_google_android_gms_internal_ads_zzbbt = com_google_android_gms_internal_ads_zzbbt2;
        } else {
            int size = com_google_android_gms_internal_ads_zzbbt2.size();
            com_google_android_gms_internal_ads_zzbbt = com_google_android_gms_internal_ads_zzbbt2.zzbm(size == 0 ? 10 : size << 1);
            zzdwf.putObject(t, j2, com_google_android_gms_internal_ads_zzbbt);
        }
        int i8;
        zzbci com_google_android_gms_internal_ads_zzbci;
        zzbbp com_google_android_gms_internal_ads_zzbbp;
        switch (i7) {
            case 18:
            case 35:
                zzbay com_google_android_gms_internal_ads_zzbay;
                if (i5 == 2) {
                    com_google_android_gms_internal_ads_zzbay = (zzbay) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    i8 = com_google_android_gms_internal_ads_zzbae.zzdpl + i;
                    while (i < i8) {
                        com_google_android_gms_internal_ads_zzbay.zzd(zzbad.zzg(bArr, i));
                        i += 8;
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 1) {
                    return i;
                } else {
                    com_google_android_gms_internal_ads_zzbay = (zzbay) com_google_android_gms_internal_ads_zzbbt;
                    com_google_android_gms_internal_ads_zzbay.zzd(zzbad.zzg(bArr, i));
                    i += 8;
                    while (i < i2) {
                        i8 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                            return i;
                        }
                        com_google_android_gms_internal_ads_zzbay.zzd(zzbad.zzg(bArr, i8));
                        i = i8 + 8;
                    }
                    return i;
                }
            case 19:
            case 36:
                zzbbm com_google_android_gms_internal_ads_zzbbm;
                if (i5 == 2) {
                    com_google_android_gms_internal_ads_zzbbm = (zzbbm) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    i8 = com_google_android_gms_internal_ads_zzbae.zzdpl + i;
                    while (i < i8) {
                        com_google_android_gms_internal_ads_zzbbm.zzd(zzbad.zzh(bArr, i));
                        i += 4;
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 5) {
                    return i;
                } else {
                    com_google_android_gms_internal_ads_zzbbm = (zzbbm) com_google_android_gms_internal_ads_zzbbt;
                    com_google_android_gms_internal_ads_zzbbm.zzd(zzbad.zzh(bArr, i));
                    i += 4;
                    while (i < i2) {
                        i8 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                            return i;
                        }
                        com_google_android_gms_internal_ads_zzbbm.zzd(zzbad.zzh(bArr, i8));
                        i = i8 + 4;
                    }
                    return i;
                }
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    com_google_android_gms_internal_ads_zzbci = (zzbci) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    i8 = com_google_android_gms_internal_ads_zzbae.zzdpl + i;
                    while (i < i8) {
                        i = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        com_google_android_gms_internal_ads_zzbci.zzw(com_google_android_gms_internal_ads_zzbae.zzdpm);
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    com_google_android_gms_internal_ads_zzbci = (zzbci) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    com_google_android_gms_internal_ads_zzbci.zzw(com_google_android_gms_internal_ads_zzbae.zzdpm);
                    while (i < i2) {
                        i8 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                            return i;
                        }
                        i = zzbad.zzb(bArr, i8, com_google_android_gms_internal_ads_zzbae);
                        com_google_android_gms_internal_ads_zzbci.zzw(com_google_android_gms_internal_ads_zzbae.zzdpm);
                    }
                    return i;
                }
            case 22:
            case 29:
            case 39:
            case 43:
                return i5 == 2 ? zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbbt, com_google_android_gms_internal_ads_zzbae) : i5 == 0 ? zzbad.zza(i3, bArr, i, i2, com_google_android_gms_internal_ads_zzbbt, com_google_android_gms_internal_ads_zzbae) : i;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    com_google_android_gms_internal_ads_zzbci = (zzbci) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    i8 = com_google_android_gms_internal_ads_zzbae.zzdpl + i;
                    while (i < i8) {
                        com_google_android_gms_internal_ads_zzbci.zzw(zzbad.zzf(bArr, i));
                        i += 8;
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 1) {
                    return i;
                } else {
                    com_google_android_gms_internal_ads_zzbci = (zzbci) com_google_android_gms_internal_ads_zzbbt;
                    com_google_android_gms_internal_ads_zzbci.zzw(zzbad.zzf(bArr, i));
                    i += 8;
                    while (i < i2) {
                        i8 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                            return i;
                        }
                        com_google_android_gms_internal_ads_zzbci.zzw(zzbad.zzf(bArr, i8));
                        i = i8 + 8;
                    }
                    return i;
                }
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    com_google_android_gms_internal_ads_zzbbp = (zzbbp) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    i8 = com_google_android_gms_internal_ads_zzbae.zzdpl + i;
                    while (i < i8) {
                        com_google_android_gms_internal_ads_zzbbp.zzco(zzbad.zze(bArr, i));
                        i += 4;
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 5) {
                    return i;
                } else {
                    com_google_android_gms_internal_ads_zzbbp = (zzbbp) com_google_android_gms_internal_ads_zzbbt;
                    com_google_android_gms_internal_ads_zzbbp.zzco(zzbad.zze(bArr, i));
                    i += 4;
                    while (i < i2) {
                        i8 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                            return i;
                        }
                        com_google_android_gms_internal_ads_zzbbp.zzco(zzbad.zze(bArr, i8));
                        i = i8 + 4;
                    }
                    return i;
                }
            case 25:
            case 42:
                zzbaf com_google_android_gms_internal_ads_zzbaf;
                if (i5 == 2) {
                    com_google_android_gms_internal_ads_zzbaf = (zzbaf) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    size = i + com_google_android_gms_internal_ads_zzbae.zzdpl;
                    while (i < size) {
                        i = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        com_google_android_gms_internal_ads_zzbaf.addBoolean(com_google_android_gms_internal_ads_zzbae.zzdpm != 0);
                    }
                    if (i == size) {
                        return i;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    com_google_android_gms_internal_ads_zzbaf = (zzbaf) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    com_google_android_gms_internal_ads_zzbaf.addBoolean(com_google_android_gms_internal_ads_zzbae.zzdpm != 0);
                    while (i < i2) {
                        i8 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                            return i;
                        }
                        i = zzbad.zzb(bArr, i8, com_google_android_gms_internal_ads_zzbae);
                        com_google_android_gms_internal_ads_zzbaf.addBoolean(com_google_android_gms_internal_ads_zzbae.zzdpm != 0);
                    }
                    return i;
                }
            case 26:
                if (i5 != 2) {
                    return i;
                }
                if ((536870912 & j) == 0) {
                    i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    i8 = com_google_android_gms_internal_ads_zzbae.zzdpl;
                    if (i8 == 0) {
                        com_google_android_gms_internal_ads_zzbbt.add("");
                    } else {
                        com_google_android_gms_internal_ads_zzbbt.add(new String(bArr, i, i8, zzbbq.UTF_8));
                        i += i8;
                    }
                    while (i < i2) {
                        i8 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                            return i;
                        }
                        i = zzbad.zza(bArr, i8, com_google_android_gms_internal_ads_zzbae);
                        i8 = com_google_android_gms_internal_ads_zzbae.zzdpl;
                        if (i8 == 0) {
                            com_google_android_gms_internal_ads_zzbbt.add("");
                        } else {
                            com_google_android_gms_internal_ads_zzbbt.add(new String(bArr, i, i8, zzbbq.UTF_8));
                            i += i8;
                        }
                    }
                    return i;
                }
                i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                i8 = com_google_android_gms_internal_ads_zzbae.zzdpl;
                if (i8 == 0) {
                    com_google_android_gms_internal_ads_zzbbt.add("");
                } else {
                    if (zzbem.zzf(bArr, i, i + i8)) {
                        com_google_android_gms_internal_ads_zzbbt.add(new String(bArr, i, i8, zzbbq.UTF_8));
                        i += i8;
                    } else {
                        throw zzbbu.zzads();
                    }
                }
                while (i < i2) {
                    i8 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                        return i;
                    }
                    i = zzbad.zza(bArr, i8, com_google_android_gms_internal_ads_zzbae);
                    i8 = com_google_android_gms_internal_ads_zzbae.zzdpl;
                    if (i8 == 0) {
                        com_google_android_gms_internal_ads_zzbbt.add("");
                    } else {
                        if (zzbem.zzf(bArr, i, i + i8)) {
                            com_google_android_gms_internal_ads_zzbbt.add(new String(bArr, i, i8, zzbbq.UTF_8));
                            i += i8;
                        } else {
                            throw zzbbu.zzads();
                        }
                    }
                }
                return i;
            case 27:
                return i5 == 2 ? zza(zzcq(i6), i3, bArr, i, i2, com_google_android_gms_internal_ads_zzbbt, com_google_android_gms_internal_ads_zzbae) : i;
            case 28:
                if (i5 != 2) {
                    return i;
                }
                i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                i8 = com_google_android_gms_internal_ads_zzbae.zzdpl;
                if (i8 == 0) {
                    com_google_android_gms_internal_ads_zzbbt.add(zzbah.zzdpq);
                } else {
                    com_google_android_gms_internal_ads_zzbbt.add(zzbah.zzc(bArr, i, i8));
                    i += i8;
                }
                while (i < i2) {
                    i8 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                        return i;
                    }
                    i = zzbad.zza(bArr, i8, com_google_android_gms_internal_ads_zzbae);
                    i8 = com_google_android_gms_internal_ads_zzbae.zzdpl;
                    if (i8 == 0) {
                        com_google_android_gms_internal_ads_zzbbt.add(zzbah.zzdpq);
                    } else {
                        com_google_android_gms_internal_ads_zzbbt.add(zzbah.zzc(bArr, i, i8));
                        i += i8;
                    }
                }
                return i;
            case 30:
            case 44:
                if (i5 == 2) {
                    size = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbbt, com_google_android_gms_internal_ads_zzbae);
                } else if (i5 != 0) {
                    return i;
                } else {
                    size = zzbad.zza(i3, bArr, i, i2, com_google_android_gms_internal_ads_zzbbt, com_google_android_gms_internal_ads_zzbae);
                }
                Object obj = ((zzbbo) t).zzdtt;
                if (obj == zzbef.zzagc()) {
                    obj = null;
                }
                zzbef com_google_android_gms_internal_ads_zzbef = (zzbef) zzbdo.zza(i4, com_google_android_gms_internal_ads_zzbbt, zzcs(i6), obj, this.zzdwv);
                if (com_google_android_gms_internal_ads_zzbef == null) {
                    return size;
                }
                ((zzbbo) t).zzdtt = com_google_android_gms_internal_ads_zzbef;
                return size;
            case 33:
            case 47:
                if (i5 == 2) {
                    com_google_android_gms_internal_ads_zzbbp = (zzbbp) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    i8 = com_google_android_gms_internal_ads_zzbae.zzdpl + i;
                    while (i < i8) {
                        i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        com_google_android_gms_internal_ads_zzbbp.zzco(zzbaq.zzbu(com_google_android_gms_internal_ads_zzbae.zzdpl));
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    com_google_android_gms_internal_ads_zzbbp = (zzbbp) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    com_google_android_gms_internal_ads_zzbbp.zzco(zzbaq.zzbu(com_google_android_gms_internal_ads_zzbae.zzdpl));
                    while (i < i2) {
                        i8 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                            return i;
                        }
                        i = zzbad.zza(bArr, i8, com_google_android_gms_internal_ads_zzbae);
                        com_google_android_gms_internal_ads_zzbbp.zzco(zzbaq.zzbu(com_google_android_gms_internal_ads_zzbae.zzdpl));
                    }
                    return i;
                }
            case 34:
            case 48:
                if (i5 == 2) {
                    com_google_android_gms_internal_ads_zzbci = (zzbci) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    i8 = com_google_android_gms_internal_ads_zzbae.zzdpl + i;
                    while (i < i8) {
                        i = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        com_google_android_gms_internal_ads_zzbci.zzw(zzbaq.zzl(com_google_android_gms_internal_ads_zzbae.zzdpm));
                    }
                    if (i == i8) {
                        return i;
                    }
                    throw zzbbu.zzadl();
                } else if (i5 != 0) {
                    return i;
                } else {
                    com_google_android_gms_internal_ads_zzbci = (zzbci) com_google_android_gms_internal_ads_zzbbt;
                    i = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    com_google_android_gms_internal_ads_zzbci.zzw(zzbaq.zzl(com_google_android_gms_internal_ads_zzbae.zzdpm));
                    while (i < i2) {
                        i8 = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                        if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                            return i;
                        }
                        i = zzbad.zzb(bArr, i8, com_google_android_gms_internal_ads_zzbae);
                        com_google_android_gms_internal_ads_zzbci.zzw(zzbaq.zzl(com_google_android_gms_internal_ads_zzbae.zzdpm));
                    }
                    return i;
                }
            case 49:
                if (i5 != 3) {
                    return i;
                }
                zzbdm zzcq = zzcq(i6);
                int i9 = (i3 & -8) | 4;
                i = zza(zzcq, bArr, i, i2, i9, com_google_android_gms_internal_ads_zzbae);
                com_google_android_gms_internal_ads_zzbbt.add(com_google_android_gms_internal_ads_zzbae.zzdpn);
                while (i < i2) {
                    int zza = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                    if (i3 != com_google_android_gms_internal_ads_zzbae.zzdpl) {
                        return i;
                    }
                    i = zza(zzcq, bArr, zza, i2, i9, com_google_android_gms_internal_ads_zzbae);
                    com_google_android_gms_internal_ads_zzbbt.add(com_google_android_gms_internal_ads_zzbae.zzdpn);
                }
                return i;
            default:
                return i;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <K, V> int zza(T r14, byte[] r15, int r16, int r17, int r18, int r19, long r20, com.google.android.gms.internal.ads.zzbae r22) throws java.io.IOException {
        /*
        r13 = this;
        r4 = zzdwf;
        r0 = r18;
        r5 = r13.zzcr(r0);
        r0 = r20;
        r3 = r4.getObject(r14, r0);
        r2 = r13.zzdwx;
        r2 = r2.zzu(r3);
        if (r2 == 0) goto L_0x00ba;
    L_0x0016:
        r2 = r13.zzdwx;
        r2 = r2.zzw(r5);
        r6 = r13.zzdwx;
        r6.zzb(r2, r3);
        r0 = r20;
        r4.putObject(r14, r0, r2);
    L_0x0026:
        r3 = r13.zzdwx;
        r10 = r3.zzx(r5);
        r3 = r13.zzdwx;
        r11 = r3.zzs(r2);
        r0 = r16;
        r1 = r22;
        r4 = com.google.android.gms.internal.ads.zzbad.zza(r15, r0, r1);
        r0 = r22;
        r2 = r0.zzdpl;
        if (r2 < 0) goto L_0x0044;
    L_0x0040:
        r3 = r17 - r4;
        if (r2 <= r3) goto L_0x0049;
    L_0x0044:
        r2 = com.google.android.gms.internal.ads.zzbbu.zzadl();
        throw r2;
    L_0x0049:
        r12 = r4 + r2;
        r3 = r10.zzdvz;
        r2 = r10.zzdwb;
        r8 = r2;
        r9 = r3;
        r2 = r4;
    L_0x0052:
        if (r2 >= r12) goto L_0x00af;
    L_0x0054:
        r3 = r2 + 1;
        r2 = r15[r2];
        if (r2 >= 0) goto L_0x0064;
    L_0x005a:
        r0 = r22;
        r3 = com.google.android.gms.internal.ads.zzbad.zza(r2, r15, r3, r0);
        r0 = r22;
        r2 = r0.zzdpl;
    L_0x0064:
        r4 = r2 >>> 3;
        r5 = r2 & 7;
        switch(r4) {
            case 1: goto L_0x0074;
            case 2: goto L_0x008f;
            default: goto L_0x006b;
        };
    L_0x006b:
        r0 = r17;
        r1 = r22;
        r2 = com.google.android.gms.internal.ads.zzbad.zza(r2, r15, r3, r0, r1);
        goto L_0x0052;
    L_0x0074:
        r4 = r10.zzdvy;
        r4 = r4.zzagm();
        if (r5 != r4) goto L_0x006b;
    L_0x007c:
        r5 = r10.zzdvy;
        r6 = 0;
        r2 = r15;
        r4 = r17;
        r7 = r22;
        r3 = zza(r2, r3, r4, r5, r6, r7);
        r0 = r22;
        r2 = r0.zzdpn;
        r9 = r2;
        r2 = r3;
        goto L_0x0052;
    L_0x008f:
        r4 = r10.zzdwa;
        r4 = r4.zzagm();
        if (r5 != r4) goto L_0x006b;
    L_0x0097:
        r5 = r10.zzdwa;
        r2 = r10.zzdwb;
        r6 = r2.getClass();
        r2 = r15;
        r4 = r17;
        r7 = r22;
        r3 = zza(r2, r3, r4, r5, r6, r7);
        r0 = r22;
        r2 = r0.zzdpn;
        r8 = r2;
        r2 = r3;
        goto L_0x0052;
    L_0x00af:
        if (r2 == r12) goto L_0x00b6;
    L_0x00b1:
        r2 = com.google.android.gms.internal.ads.zzbbu.zzadr();
        throw r2;
    L_0x00b6:
        r11.put(r9, r8);
        return r12;
    L_0x00ba:
        r2 = r3;
        goto L_0x0026;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.zza(java.lang.Object, byte[], int, int, int, int, long, com.google.android.gms.internal.ads.zzbae):int");
    }

    private final int zza(T t, byte[] bArr, int i, int i2, int i3, zzbae com_google_android_gms_internal_ads_zzbae) throws IOException {
        int i4;
        int i5;
        Unsafe unsafe = zzdwf;
        int i6 = -1;
        int i7 = 0;
        int i8 = 0;
        int i9 = i;
        while (i9 < i2) {
            Object obj;
            int size;
            int i10 = i9 + 1;
            i8 = bArr[i9];
            if (i8 < 0) {
                i10 = zzbad.zza(i8, bArr, i10, com_google_android_gms_internal_ads_zzbae);
                i8 = com_google_android_gms_internal_ads_zzbae.zzdpl;
            }
            int i11 = i8 >>> 3;
            i9 = i8 & 7;
            int zzcw = zzcw(i11);
            if (zzcw != -1) {
                int i12 = this.zzdwg[zzcw + 1];
                int i13 = (267386880 & i12) >>> 20;
                long j = (long) (1048575 & i12);
                if (i13 <= 17) {
                    i4 = this.zzdwg[zzcw + 2];
                    int i14 = 1 << (i4 >>> 20);
                    i4 &= 1048575;
                    if (i4 != i6) {
                        if (i6 != -1) {
                            unsafe.putInt(t, (long) i6, i7);
                        }
                        i7 = unsafe.getInt(t, (long) i4);
                        i6 = i4;
                    }
                    switch (i13) {
                        case 0:
                            if (i9 == 1) {
                                zzbek.zza((Object) t, j, zzbad.zzg(bArr, i10));
                                i9 = i10 + 8;
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 1:
                            if (i9 == 5) {
                                zzbek.zza((Object) t, j, zzbad.zzh(bArr, i10));
                                i9 = i10 + 4;
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 2:
                        case 3:
                            if (i9 == 0) {
                                i9 = zzbad.zzb(bArr, i10, com_google_android_gms_internal_ads_zzbae);
                                unsafe.putLong(t, j, com_google_android_gms_internal_ads_zzbae.zzdpm);
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 4:
                        case 11:
                            if (i9 == 0) {
                                i9 = zzbad.zza(bArr, i10, com_google_android_gms_internal_ads_zzbae);
                                unsafe.putInt(t, j, com_google_android_gms_internal_ads_zzbae.zzdpl);
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 5:
                        case 14:
                            if (i9 == 1) {
                                unsafe.putLong(t, j, zzbad.zzf(bArr, i10));
                                i9 = i10 + 8;
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 6:
                        case 13:
                            if (i9 == 5) {
                                unsafe.putInt(t, j, zzbad.zze(bArr, i10));
                                i9 = i10 + 4;
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 7:
                            if (i9 == 0) {
                                i9 = zzbad.zzb(bArr, i10, com_google_android_gms_internal_ads_zzbae);
                                zzbek.zza((Object) t, j, com_google_android_gms_internal_ads_zzbae.zzdpm != 0);
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 8:
                            if (i9 == 2) {
                                i4 = (ErrorDialogData.DYNAMITE_CRASH & i12) == 0 ? zzbad.zzc(bArr, i10, com_google_android_gms_internal_ads_zzbae) : zzbad.zzd(bArr, i10, com_google_android_gms_internal_ads_zzbae);
                                unsafe.putObject(t, j, com_google_android_gms_internal_ads_zzbae.zzdpn);
                                i7 |= i14;
                                i9 = i4;
                                continue;
                            }
                            break;
                        case 9:
                            if (i9 == 2) {
                                i9 = zza(zzcq(zzcw), bArr, i10, i2, com_google_android_gms_internal_ads_zzbae);
                                if ((i7 & i14) == 0) {
                                    unsafe.putObject(t, j, com_google_android_gms_internal_ads_zzbae.zzdpn);
                                } else {
                                    unsafe.putObject(t, j, zzbbq.zza(unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbae.zzdpn));
                                }
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 10:
                            if (i9 == 2) {
                                i9 = zzbad.zze(bArr, i10, com_google_android_gms_internal_ads_zzbae);
                                unsafe.putObject(t, j, com_google_android_gms_internal_ads_zzbae.zzdpn);
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 12:
                            if (i9 == 0) {
                                i9 = zzbad.zza(bArr, i10, com_google_android_gms_internal_ads_zzbae);
                                i4 = com_google_android_gms_internal_ads_zzbae.zzdpl;
                                zzbbs zzcs = zzcs(zzcw);
                                if (zzcs != null && zzcs.zzq(i4) == null) {
                                    zzz(t).zzb(i8, Long.valueOf((long) i4));
                                    break;
                                }
                                unsafe.putInt(t, j, i4);
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 15:
                            if (i9 == 0) {
                                i9 = zzbad.zza(bArr, i10, com_google_android_gms_internal_ads_zzbae);
                                unsafe.putInt(t, j, zzbaq.zzbu(com_google_android_gms_internal_ads_zzbae.zzdpl));
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 16:
                            if (i9 == 0) {
                                i9 = zzbad.zzb(bArr, i10, com_google_android_gms_internal_ads_zzbae);
                                unsafe.putLong(t, j, zzbaq.zzl(com_google_android_gms_internal_ads_zzbae.zzdpm));
                                i7 |= i14;
                                continue;
                            }
                            break;
                        case 17:
                            if (i9 == 3) {
                                i9 = zza(zzcq(zzcw), bArr, i10, i2, (i11 << 3) | 4, com_google_android_gms_internal_ads_zzbae);
                                if ((i7 & i14) == 0) {
                                    unsafe.putObject(t, j, com_google_android_gms_internal_ads_zzbae.zzdpn);
                                } else {
                                    unsafe.putObject(t, j, zzbbq.zza(unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbae.zzdpn));
                                }
                                i7 |= i14;
                                continue;
                            }
                            break;
                    }
                    i4 = i7;
                    i5 = i6;
                    i9 = i10;
                    if (i8 == i3 || i3 == 0) {
                        i9 = zza(i8, bArr, i9, i2, (Object) t, com_google_android_gms_internal_ads_zzbae);
                        i7 = i4;
                        i6 = i5;
                    } else {
                        if (i5 != -1) {
                            unsafe.putInt(t, (long) i5, i4);
                        }
                        if (this.zzdwr != null) {
                            obj = null;
                            for (int size2 : this.zzdwr) {
                                zzbef com_google_android_gms_internal_ads_zzbef = (zzbef) zza((Object) t, size2, obj, this.zzdwv);
                            }
                            if (obj != null) {
                                this.zzdwv.zzf(t, obj);
                            }
                        }
                        if (i3 != 0) {
                            if (i9 != i2) {
                                throw zzbbu.zzadr();
                            }
                        } else if (i9 > i2 || r17 != i3) {
                            throw zzbbu.zzadr();
                        }
                        return i9;
                    }
                } else if (i13 == 27) {
                    if (i9 == 2) {
                        zzbbt com_google_android_gms_internal_ads_zzbbt;
                        zzbbt com_google_android_gms_internal_ads_zzbbt2 = (zzbbt) unsafe.getObject(t, j);
                        if (com_google_android_gms_internal_ads_zzbbt2.zzaay()) {
                            com_google_android_gms_internal_ads_zzbbt = com_google_android_gms_internal_ads_zzbbt2;
                        } else {
                            size2 = com_google_android_gms_internal_ads_zzbbt2.size();
                            com_google_android_gms_internal_ads_zzbbt = com_google_android_gms_internal_ads_zzbbt2.zzbm(size2 == 0 ? 10 : size2 << 1);
                            unsafe.putObject(t, j, com_google_android_gms_internal_ads_zzbbt);
                        }
                        i9 = zza(zzcq(zzcw), i8, bArr, i10, i2, com_google_android_gms_internal_ads_zzbbt, com_google_android_gms_internal_ads_zzbae);
                    }
                } else if (i13 <= 49) {
                    i9 = zza((Object) t, bArr, i10, i2, i8, i11, i9, zzcw, (long) i12, i13, j, com_google_android_gms_internal_ads_zzbae);
                    if (i9 == i10) {
                        i4 = i7;
                        i5 = i6;
                        if (i8 == i3) {
                        }
                        i9 = zza(i8, bArr, i9, i2, (Object) t, com_google_android_gms_internal_ads_zzbae);
                        i7 = i4;
                        i6 = i5;
                    } else {
                        continue;
                    }
                } else if (i13 != 50) {
                    i9 = zza((Object) t, bArr, i10, i2, i8, i11, i9, i12, i13, j, zzcw, com_google_android_gms_internal_ads_zzbae);
                    if (i9 == i10) {
                        i4 = i7;
                        i5 = i6;
                        if (i8 == i3) {
                        }
                        i9 = zza(i8, bArr, i9, i2, (Object) t, com_google_android_gms_internal_ads_zzbae);
                        i7 = i4;
                        i6 = i5;
                    } else {
                        continue;
                    }
                } else if (i9 == 2) {
                    i9 = zza(t, bArr, i10, i2, zzcw, i11, j, com_google_android_gms_internal_ads_zzbae);
                    if (i9 == i10) {
                        i4 = i7;
                        i5 = i6;
                        if (i8 == i3) {
                        }
                        i9 = zza(i8, bArr, i9, i2, (Object) t, com_google_android_gms_internal_ads_zzbae);
                        i7 = i4;
                        i6 = i5;
                    } else {
                        continue;
                    }
                }
            }
            i4 = i7;
            i5 = i6;
            i9 = i10;
            if (i8 == i3) {
            }
            i9 = zza(i8, bArr, i9, i2, (Object) t, com_google_android_gms_internal_ads_zzbae);
            i7 = i4;
            i6 = i5;
        }
        i4 = i7;
        i5 = i6;
        if (i5 != -1) {
            unsafe.putInt(t, (long) i5, i4);
        }
        if (this.zzdwr != null) {
            obj = null;
            while (i4 < this.zzdwr.length) {
                zzbef com_google_android_gms_internal_ads_zzbef2 = (zzbef) zza((Object) t, size2, obj, this.zzdwv);
            }
            if (obj != null) {
                this.zzdwv.zzf(t, obj);
            }
        }
        if (i3 != 0) {
            throw zzbbu.zzadr();
        } else if (i9 != i2) {
            throw zzbbu.zzadr();
        }
        return i9;
    }

    private static int zza(byte[] bArr, int i, int i2, zzbes com_google_android_gms_internal_ads_zzbes, Class<?> cls, zzbae com_google_android_gms_internal_ads_zzbae) throws IOException {
        int zza;
        switch (zzbcz.zzdql[com_google_android_gms_internal_ads_zzbes.ordinal()]) {
            case 1:
                int zzb = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                com_google_android_gms_internal_ads_zzbae.zzdpn = Boolean.valueOf(com_google_android_gms_internal_ads_zzbae.zzdpm != 0);
                return zzb;
            case 2:
                return zzbad.zze(bArr, i, com_google_android_gms_internal_ads_zzbae);
            case 3:
                com_google_android_gms_internal_ads_zzbae.zzdpn = Double.valueOf(zzbad.zzg(bArr, i));
                return i + 8;
            case 4:
            case 5:
                com_google_android_gms_internal_ads_zzbae.zzdpn = Integer.valueOf(zzbad.zze(bArr, i));
                return i + 4;
            case 6:
            case 7:
                com_google_android_gms_internal_ads_zzbae.zzdpn = Long.valueOf(zzbad.zzf(bArr, i));
                return i + 8;
            case 8:
                com_google_android_gms_internal_ads_zzbae.zzdpn = Float.valueOf(zzbad.zzh(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                zza = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                com_google_android_gms_internal_ads_zzbae.zzdpn = Integer.valueOf(com_google_android_gms_internal_ads_zzbae.zzdpl);
                return zza;
            case 12:
            case 13:
                zza = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                com_google_android_gms_internal_ads_zzbae.zzdpn = Long.valueOf(com_google_android_gms_internal_ads_zzbae.zzdpm);
                return zza;
            case 14:
                return zza(zzbdg.zzaeo().zze(cls), bArr, i, i2, com_google_android_gms_internal_ads_zzbae);
            case 15:
                zza = zzbad.zza(bArr, i, com_google_android_gms_internal_ads_zzbae);
                com_google_android_gms_internal_ads_zzbae.zzdpn = Integer.valueOf(zzbaq.zzbu(com_google_android_gms_internal_ads_zzbae.zzdpl));
                return zza;
            case 16:
                zza = zzbad.zzb(bArr, i, com_google_android_gms_internal_ads_zzbae);
                com_google_android_gms_internal_ads_zzbae.zzdpn = Long.valueOf(zzbaq.zzl(com_google_android_gms_internal_ads_zzbae.zzdpm));
                return zza;
            case 17:
                return zzbad.zzd(bArr, i, com_google_android_gms_internal_ads_zzbae);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    static <T> zzbcy<T> zza(Class<T> cls, zzbcs com_google_android_gms_internal_ads_zzbcs, zzbdc com_google_android_gms_internal_ads_zzbdc, zzbce com_google_android_gms_internal_ads_zzbce, zzbee<?, ?> com_google_android_gms_internal_ads_zzbee___, zzbbd<?> com_google_android_gms_internal_ads_zzbbd_, zzbcp com_google_android_gms_internal_ads_zzbcp) {
        if (com_google_android_gms_internal_ads_zzbcs instanceof zzbdi) {
            int i;
            int i2;
            int i3;
            zzbdi com_google_android_gms_internal_ads_zzbdi = (zzbdi) com_google_android_gms_internal_ads_zzbcs;
            boolean z = com_google_android_gms_internal_ads_zzbdi.zzaeh() == zze.zzduj;
            if (com_google_android_gms_internal_ads_zzbdi.getFieldCount() == 0) {
                i = 0;
                i2 = 0;
                i3 = 0;
            } else {
                i = com_google_android_gms_internal_ads_zzbdi.zzaer();
                i2 = com_google_android_gms_internal_ads_zzbdi.zzaes();
                i3 = com_google_android_gms_internal_ads_zzbdi.zzaew();
            }
            int[] iArr = new int[(i3 << 2)];
            Object[] objArr = new Object[(i3 << 1)];
            int[] iArr2 = com_google_android_gms_internal_ads_zzbdi.zzaet() > 0 ? new int[com_google_android_gms_internal_ads_zzbdi.zzaet()] : null;
            int[] iArr3 = com_google_android_gms_internal_ads_zzbdi.zzaeu() > 0 ? new int[com_google_android_gms_internal_ads_zzbdi.zzaeu()] : null;
            int i4 = 0;
            int i5 = 0;
            zzbdj zzaeq = com_google_android_gms_internal_ads_zzbdi.zzaeq();
            if (zzaeq.next()) {
                int zzaci = zzaeq.zzaci();
                i3 = 0;
                while (true) {
                    int i6;
                    if (zzaci >= com_google_android_gms_internal_ads_zzbdi.zzaex() || i3 >= ((zzaci - i) << 2)) {
                        int zza;
                        if (zzaeq.zzafb()) {
                            zza = (int) zzbek.zza(zzaeq.zzafc());
                            zzaci = (int) zzbek.zza(zzaeq.zzafd());
                            i6 = 0;
                        } else {
                            zza = (int) zzbek.zza(zzaeq.zzafe());
                            if (zzaeq.zzaff()) {
                                zzaci = (int) zzbek.zza(zzaeq.zzafg());
                                i6 = zzaeq.zzafh();
                            } else {
                                zzaci = 0;
                                i6 = 0;
                            }
                        }
                        iArr[i3] = zzaeq.zzaci();
                        iArr[i3 + 1] = zza | (((zzaeq.zzafj() ? ErrorDialogData.DYNAMITE_CRASH : 0) | (zzaeq.zzafi() ? ErrorDialogData.BINDER_CRASH : 0)) | (zzaeq.zzaez() << 20));
                        iArr[i3 + 2] = zzaci | (i6 << 20);
                        if (zzaeq.zzafm() != null) {
                            objArr[(i3 / 4) << 1] = zzaeq.zzafm();
                            if (zzaeq.zzafk() != null) {
                                objArr[((i3 / 4) << 1) + 1] = zzaeq.zzafk();
                            } else if (zzaeq.zzafl() != null) {
                                objArr[((i3 / 4) << 1) + 1] = zzaeq.zzafl();
                            }
                        } else if (zzaeq.zzafk() != null) {
                            objArr[((i3 / 4) << 1) + 1] = zzaeq.zzafk();
                        } else if (zzaeq.zzafl() != null) {
                            objArr[((i3 / 4) << 1) + 1] = zzaeq.zzafl();
                        }
                        zzaci = zzaeq.zzaez();
                        if (zzaci == zzbbj.MAP.ordinal()) {
                            zzaci = i4 + 1;
                            iArr2[i4] = i3;
                            i4 = zzaci;
                        } else if (zzaci >= 18 && zzaci <= 49) {
                            zzaci = i5 + 1;
                            iArr3[i5] = iArr[i3 + 1] & 1048575;
                            i5 = zzaci;
                        }
                        if (!zzaeq.next()) {
                            break;
                        }
                        zzaci = zzaeq.zzaci();
                    } else {
                        for (i6 = 0; i6 < 4; i6++) {
                            iArr[i3 + i6] = -1;
                        }
                    }
                    i3 += 4;
                }
            }
            return new zzbcy(iArr, objArr, i, i2, com_google_android_gms_internal_ads_zzbdi.zzaex(), com_google_android_gms_internal_ads_zzbdi.zzaej(), z, false, com_google_android_gms_internal_ads_zzbdi.zzaev(), iArr2, iArr3, com_google_android_gms_internal_ads_zzbdc, com_google_android_gms_internal_ads_zzbce, com_google_android_gms_internal_ads_zzbee___, com_google_android_gms_internal_ads_zzbbd_, com_google_android_gms_internal_ads_zzbcp);
        }
        ((zzbdz) com_google_android_gms_internal_ads_zzbcs).zzaeh();
        throw new NoSuchMethodError();
    }

    private final <K, V, UT, UB> UB zza(int i, int i2, Map<K, V> map, zzbbs<?> com_google_android_gms_internal_ads_zzbbs_, UB ub, zzbee<UT, UB> com_google_android_gms_internal_ads_zzbee_UT__UB) {
        zzbcn zzx = this.zzdwx.zzx(zzcr(i));
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (com_google_android_gms_internal_ads_zzbbs_.zzq(((Integer) entry.getValue()).intValue()) == null) {
                if (ub == null) {
                    ub = com_google_android_gms_internal_ads_zzbee_UT__UB.zzagb();
                }
                zzbam zzbo = zzbah.zzbo(zzbcm.zza(zzx, entry.getKey(), entry.getValue()));
                try {
                    zzbcm.zza(zzbo.zzabj(), zzx, entry.getKey(), entry.getValue());
                    com_google_android_gms_internal_ads_zzbee_UT__UB.zza((Object) ub, i2, zzbo.zzabi());
                    it.remove();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    private final <UT, UB> UB zza(Object obj, int i, UB ub, zzbee<UT, UB> com_google_android_gms_internal_ads_zzbee_UT__UB) {
        int i2 = this.zzdwg[i];
        Object zzp = zzbek.zzp(obj, (long) (zzct(i) & 1048575));
        if (zzp == null) {
            return ub;
        }
        zzbbs zzcs = zzcs(i);
        if (zzcs == null) {
            return ub;
        }
        return zza(i, i2, this.zzdwx.zzs(zzp), zzcs, (Object) ub, (zzbee) com_google_android_gms_internal_ads_zzbee_UT__UB);
    }

    private static void zza(int i, Object obj, zzbey com_google_android_gms_internal_ads_zzbey) throws IOException {
        if (obj instanceof String) {
            com_google_android_gms_internal_ads_zzbey.zzf(i, (String) obj);
        } else {
            com_google_android_gms_internal_ads_zzbey.zza(i, (zzbah) obj);
        }
    }

    private static <UT, UB> void zza(zzbee<UT, UB> com_google_android_gms_internal_ads_zzbee_UT__UB, T t, zzbey com_google_android_gms_internal_ads_zzbey) throws IOException {
        com_google_android_gms_internal_ads_zzbee_UT__UB.zza(com_google_android_gms_internal_ads_zzbee_UT__UB.zzac(t), com_google_android_gms_internal_ads_zzbey);
    }

    private final <K, V> void zza(zzbey com_google_android_gms_internal_ads_zzbey, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            com_google_android_gms_internal_ads_zzbey.zza(i, this.zzdwx.zzx(zzcr(i2)), this.zzdwx.zzt(obj));
        }
    }

    private final void zza(Object obj, int i, zzbdl com_google_android_gms_internal_ads_zzbdl) throws IOException {
        if (zzcv(i)) {
            zzbek.zza(obj, (long) (i & 1048575), com_google_android_gms_internal_ads_zzbdl.zzabr());
        } else if (this.zzdwn) {
            zzbek.zza(obj, (long) (i & 1048575), com_google_android_gms_internal_ads_zzbdl.readString());
        } else {
            zzbek.zza(obj, (long) (i & 1048575), com_google_android_gms_internal_ads_zzbdl.zzabs());
        }
    }

    private final void zza(T t, T t2, int i) {
        long zzct = (long) (zzct(i) & 1048575);
        if (zza((Object) t2, i)) {
            Object zzp = zzbek.zzp(t, zzct);
            Object zzp2 = zzbek.zzp(t2, zzct);
            if (zzp != null && zzp2 != null) {
                zzbek.zza((Object) t, zzct, zzbbq.zza(zzp, zzp2));
                zzb((Object) t, i);
            } else if (zzp2 != null) {
                zzbek.zza((Object) t, zzct, zzp2);
                zzb((Object) t, i);
            }
        }
    }

    private final boolean zza(T t, int i) {
        if (this.zzdwo) {
            int zzct;
            zzct = zzct(i);
            long j = (long) (zzct & 1048575);
            switch ((zzct & 267386880) >>> 20) {
                case 0:
                    return zzbek.zzo(t, j) != 0.0d;
                case 1:
                    return zzbek.zzn(t, j) != 0.0f;
                case 2:
                    return zzbek.zzl(t, j) != 0;
                case 3:
                    return zzbek.zzl(t, j) != 0;
                case 4:
                    return zzbek.zzk(t, j) != 0;
                case 5:
                    return zzbek.zzl(t, j) != 0;
                case 6:
                    return zzbek.zzk(t, j) != 0;
                case 7:
                    return zzbek.zzm(t, j);
                case 8:
                    Object zzp = zzbek.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    } else {
                        if (zzp instanceof zzbah) {
                            return !zzbah.zzdpq.equals(zzp);
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                case 9:
                    return zzbek.zzp(t, j) != null;
                case 10:
                    return !zzbah.zzdpq.equals(zzbek.zzp(t, j));
                case 11:
                    return zzbek.zzk(t, j) != 0;
                case 12:
                    return zzbek.zzk(t, j) != 0;
                case 13:
                    return zzbek.zzk(t, j) != 0;
                case 14:
                    return zzbek.zzl(t, j) != 0;
                case 15:
                    return zzbek.zzk(t, j) != 0;
                case 16:
                    return zzbek.zzl(t, j) != 0;
                case 17:
                    return zzbek.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        zzct = zzcu(i);
        return (zzbek.zzk(t, (long) (zzct & 1048575)) & (1 << (zzct >>> 20))) != 0;
    }

    private final boolean zza(T t, int i, int i2) {
        return zzbek.zzk(t, (long) (zzcu(i2) & 1048575)) == i;
    }

    private final boolean zza(T t, int i, int i2, int i3) {
        return this.zzdwo ? zza((Object) t, i) : (i2 & i3) != 0;
    }

    private static boolean zza(Object obj, int i, zzbdm com_google_android_gms_internal_ads_zzbdm) {
        return com_google_android_gms_internal_ads_zzbdm.zzaa(zzbek.zzp(obj, (long) (1048575 & i)));
    }

    private final void zzb(T t, int i) {
        if (!this.zzdwo) {
            int zzcu = zzcu(i);
            long j = (long) (zzcu & 1048575);
            zzbek.zzb((Object) t, j, zzbek.zzk(t, j) | (1 << (zzcu >>> 20)));
        }
    }

    private final void zzb(T t, int i, int i2) {
        zzbek.zzb((Object) t, (long) (zzcu(i2) & 1048575), i);
    }

    private final void zzb(T t, zzbey com_google_android_gms_internal_ads_zzbey) throws IOException {
        Iterator it = null;
        Entry entry = null;
        if (this.zzdwm) {
            zzbbg zzm = this.zzdww.zzm(t);
            if (!zzm.isEmpty()) {
                it = zzm.iterator();
                entry = (Entry) it.next();
            }
        }
        int length = this.zzdwg.length;
        Unsafe unsafe = zzdwf;
        int i = 0;
        int i2 = -1;
        Entry entry2 = entry;
        int i3 = 0;
        while (i < length) {
            Entry entry3;
            int i4;
            int zzct = zzct(i);
            int i5 = this.zzdwg[i];
            int i6 = (267386880 & zzct) >>> 20;
            int i7 = 0;
            if (this.zzdwo || i6 > 17) {
                entry3 = entry2;
                i4 = i2;
                i2 = i3;
            } else {
                int i8 = this.zzdwg[i + 2];
                i7 = 1048575 & i8;
                if (i7 != i2) {
                    i3 = unsafe.getInt(t, (long) i7);
                } else {
                    i7 = i2;
                }
                i2 = 1 << (i8 >>> 20);
                entry3 = entry2;
                i4 = i7;
                i7 = i2;
                i2 = i3;
            }
            while (entry3 != null && this.zzdww.zza(entry3) <= i5) {
                this.zzdww.zza(com_google_android_gms_internal_ads_zzbey, entry3);
                entry3 = it.hasNext() ? (Entry) it.next() : null;
            }
            long j = (long) (1048575 & zzct);
            switch (i6) {
                case 0:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zza(i5, zzbek.zzo(t, j));
                    break;
                case 1:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zza(i5, zzbek.zzn(t, j));
                    break;
                case 2:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzi(i5, unsafe.getLong(t, j));
                    break;
                case 3:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zza(i5, unsafe.getLong(t, j));
                    break;
                case 4:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzm(i5, unsafe.getInt(t, j));
                    break;
                case 5:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzc(i5, unsafe.getLong(t, j));
                    break;
                case 6:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzp(i5, unsafe.getInt(t, j));
                    break;
                case 7:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzf(i5, zzbek.zzm(t, j));
                    break;
                case 8:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    zza(i5, unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey);
                    break;
                case 9:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zza(i5, unsafe.getObject(t, j), zzcq(i));
                    break;
                case 10:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zza(i5, (zzbah) unsafe.getObject(t, j));
                    break;
                case 11:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzn(i5, unsafe.getInt(t, j));
                    break;
                case 12:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzx(i5, unsafe.getInt(t, j));
                    break;
                case 13:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzw(i5, unsafe.getInt(t, j));
                    break;
                case 14:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzj(i5, unsafe.getLong(t, j));
                    break;
                case 15:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzo(i5, unsafe.getInt(t, j));
                    break;
                case 16:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzb(i5, unsafe.getLong(t, j));
                    break;
                case 17:
                    if ((i2 & i7) == 0) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzb(i5, unsafe.getObject(t, j), zzcq(i));
                    break;
                case 18:
                    zzbdo.zza(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 19:
                    zzbdo.zzb(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 20:
                    zzbdo.zzc(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 21:
                    zzbdo.zzd(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 22:
                    zzbdo.zzh(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 23:
                    zzbdo.zzf(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 24:
                    zzbdo.zzk(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 25:
                    zzbdo.zzn(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 26:
                    zzbdo.zza(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey);
                    break;
                case 27:
                    zzbdo.zza(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, zzcq(i));
                    break;
                case 28:
                    zzbdo.zzb(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey);
                    break;
                case 29:
                    zzbdo.zzi(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 30:
                    zzbdo.zzm(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 31:
                    zzbdo.zzl(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 32:
                    zzbdo.zzg(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 33:
                    zzbdo.zzj(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 34:
                    zzbdo.zze(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, false);
                    break;
                case 35:
                    zzbdo.zza(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 36:
                    zzbdo.zzb(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 37:
                    zzbdo.zzc(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 38:
                    zzbdo.zzd(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 39:
                    zzbdo.zzh(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 40:
                    zzbdo.zzf(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 41:
                    zzbdo.zzk(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 42:
                    zzbdo.zzn(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 43:
                    zzbdo.zzi(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 44:
                    zzbdo.zzm(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 45:
                    zzbdo.zzl(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 46:
                    zzbdo.zzg(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 47:
                    zzbdo.zzj(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 48:
                    zzbdo.zze(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, true);
                    break;
                case 49:
                    zzbdo.zzb(this.zzdwg[i], (List) unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey, zzcq(i));
                    break;
                case 50:
                    zza(com_google_android_gms_internal_ads_zzbey, i5, unsafe.getObject(t, j), i);
                    break;
                case 51:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zza(i5, zzf(t, j));
                    break;
                case 52:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zza(i5, zzg(t, j));
                    break;
                case 53:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzi(i5, zzi(t, j));
                    break;
                case 54:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zza(i5, zzi(t, j));
                    break;
                case 55:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzm(i5, zzh(t, j));
                    break;
                case 56:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzc(i5, zzi(t, j));
                    break;
                case 57:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzp(i5, zzh(t, j));
                    break;
                case 58:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzf(i5, zzj(t, j));
                    break;
                case 59:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    zza(i5, unsafe.getObject(t, j), com_google_android_gms_internal_ads_zzbey);
                    break;
                case 60:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zza(i5, unsafe.getObject(t, j), zzcq(i));
                    break;
                case 61:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zza(i5, (zzbah) unsafe.getObject(t, j));
                    break;
                case 62:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzn(i5, zzh(t, j));
                    break;
                case 63:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzx(i5, zzh(t, j));
                    break;
                case 64:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzw(i5, zzh(t, j));
                    break;
                case 65:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzj(i5, zzi(t, j));
                    break;
                case 66:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzo(i5, zzh(t, j));
                    break;
                case 67:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzb(i5, zzi(t, j));
                    break;
                case 68:
                    if (!zza((Object) t, i5, i)) {
                        break;
                    }
                    com_google_android_gms_internal_ads_zzbey.zzb(i5, unsafe.getObject(t, j), zzcq(i));
                    break;
                default:
                    break;
            }
            i += 4;
            i3 = i2;
            i2 = i4;
            entry2 = entry3;
        }
        for (entry = entry2; entry != null; entry = it.hasNext() ? (Entry) it.next() : null) {
            this.zzdww.zza(com_google_android_gms_internal_ads_zzbey, entry);
        }
        zza(this.zzdwv, (Object) t, com_google_android_gms_internal_ads_zzbey);
    }

    private final void zzb(T t, T t2, int i) {
        int zzct = zzct(i);
        int i2 = this.zzdwg[i];
        long j = (long) (zzct & 1048575);
        if (zza((Object) t2, i2, i)) {
            Object zzp = zzbek.zzp(t, j);
            Object zzp2 = zzbek.zzp(t2, j);
            if (zzp != null && zzp2 != null) {
                zzbek.zza((Object) t, j, zzbbq.zza(zzp, zzp2));
                zzb((Object) t, i2, i);
            } else if (zzp2 != null) {
                zzbek.zza((Object) t, j, zzp2);
                zzb((Object) t, i2, i);
            }
        }
    }

    private final boolean zzc(T t, T t2, int i) {
        return zza((Object) t, i) == zza((Object) t2, i);
    }

    private final zzbdm zzcq(int i) {
        int i2 = (i / 4) << 1;
        zzbdm com_google_android_gms_internal_ads_zzbdm = (zzbdm) this.zzdwh[i2];
        if (com_google_android_gms_internal_ads_zzbdm != null) {
            return com_google_android_gms_internal_ads_zzbdm;
        }
        com_google_android_gms_internal_ads_zzbdm = zzbdg.zzaeo().zze((Class) this.zzdwh[i2 + 1]);
        this.zzdwh[i2] = com_google_android_gms_internal_ads_zzbdm;
        return com_google_android_gms_internal_ads_zzbdm;
    }

    private final Object zzcr(int i) {
        return this.zzdwh[(i / 4) << 1];
    }

    private final zzbbs<?> zzcs(int i) {
        return (zzbbs) this.zzdwh[((i / 4) << 1) + 1];
    }

    private final int zzct(int i) {
        return this.zzdwg[i + 1];
    }

    private final int zzcu(int i) {
        return this.zzdwg[i + 2];
    }

    private static boolean zzcv(int i) {
        return (ErrorDialogData.DYNAMITE_CRASH & i) != 0;
    }

    private final int zzcw(int i) {
        if (i >= this.zzdwi) {
            int i2;
            if (i < this.zzdwk) {
                i2 = (i - this.zzdwi) << 2;
                return this.zzdwg[i2] == i ? i2 : -1;
            } else if (i <= this.zzdwj) {
                int i3 = this.zzdwk - this.zzdwi;
                int length = (this.zzdwg.length / 4) - 1;
                while (i3 <= length) {
                    int i4 = (length + i3) >>> 1;
                    i2 = i4 << 2;
                    int i5 = this.zzdwg[i2];
                    if (i == i5) {
                        return i2;
                    }
                    if (i < i5) {
                        length = i4 - 1;
                    } else {
                        i3 = i4 + 1;
                    }
                }
                return -1;
            }
        }
        return -1;
    }

    private static <E> List<E> zze(Object obj, long j) {
        return (List) zzbek.zzp(obj, j);
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzbek.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzbek.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzbek.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzbek.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzbek.zzp(t, j)).booleanValue();
    }

    private static zzbef zzz(Object obj) {
        zzbef com_google_android_gms_internal_ads_zzbef = ((zzbbo) obj).zzdtt;
        if (com_google_android_gms_internal_ads_zzbef != zzbef.zzagc()) {
            return com_google_android_gms_internal_ads_zzbef;
        }
        com_google_android_gms_internal_ads_zzbef = zzbef.zzagd();
        ((zzbbo) obj).zzdtt = com_google_android_gms_internal_ads_zzbef;
        return com_google_android_gms_internal_ads_zzbef;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(T r12, T r13) {
        /*
        r11 = this;
        r1 = 1;
        r10 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r0 = 0;
        r2 = r11.zzdwg;
        r4 = r2.length;
        r3 = r0;
    L_0x0009:
        if (r3 >= r4) goto L_0x01cf;
    L_0x000b:
        r2 = r11.zzct(r3);
        r5 = r2 & r10;
        r6 = (long) r5;
        r5 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r2 = r2 & r5;
        r2 = r2 >>> 20;
        switch(r2) {
            case 0: goto L_0x001e;
            case 1: goto L_0x0032;
            case 2: goto L_0x0044;
            case 3: goto L_0x0058;
            case 4: goto L_0x006c;
            case 5: goto L_0x007e;
            case 6: goto L_0x0092;
            case 7: goto L_0x00a5;
            case 8: goto L_0x00b8;
            case 9: goto L_0x00cf;
            case 10: goto L_0x00e6;
            case 11: goto L_0x00fd;
            case 12: goto L_0x0110;
            case 13: goto L_0x0123;
            case 14: goto L_0x0136;
            case 15: goto L_0x014b;
            case 16: goto L_0x015e;
            case 17: goto L_0x0173;
            case 18: goto L_0x018a;
            case 19: goto L_0x018a;
            case 20: goto L_0x018a;
            case 21: goto L_0x018a;
            case 22: goto L_0x018a;
            case 23: goto L_0x018a;
            case 24: goto L_0x018a;
            case 25: goto L_0x018a;
            case 26: goto L_0x018a;
            case 27: goto L_0x018a;
            case 28: goto L_0x018a;
            case 29: goto L_0x018a;
            case 30: goto L_0x018a;
            case 31: goto L_0x018a;
            case 32: goto L_0x018a;
            case 33: goto L_0x018a;
            case 34: goto L_0x018a;
            case 35: goto L_0x018a;
            case 36: goto L_0x018a;
            case 37: goto L_0x018a;
            case 38: goto L_0x018a;
            case 39: goto L_0x018a;
            case 40: goto L_0x018a;
            case 41: goto L_0x018a;
            case 42: goto L_0x018a;
            case 43: goto L_0x018a;
            case 44: goto L_0x018a;
            case 45: goto L_0x018a;
            case 46: goto L_0x018a;
            case 47: goto L_0x018a;
            case 48: goto L_0x018a;
            case 49: goto L_0x018a;
            case 50: goto L_0x0198;
            case 51: goto L_0x01a6;
            case 52: goto L_0x01a6;
            case 53: goto L_0x01a6;
            case 54: goto L_0x01a6;
            case 55: goto L_0x01a6;
            case 56: goto L_0x01a6;
            case 57: goto L_0x01a6;
            case 58: goto L_0x01a6;
            case 59: goto L_0x01a6;
            case 60: goto L_0x01a6;
            case 61: goto L_0x01a6;
            case 62: goto L_0x01a6;
            case 63: goto L_0x01a6;
            case 64: goto L_0x01a6;
            case 65: goto L_0x01a6;
            case 66: goto L_0x01a6;
            case 67: goto L_0x01a6;
            case 68: goto L_0x01a6;
            default: goto L_0x001a;
        };
    L_0x001a:
        r2 = r1;
    L_0x001b:
        if (r2 != 0) goto L_0x01ca;
    L_0x001d:
        return r0;
    L_0x001e:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x0030;
    L_0x0024:
        r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6);
        r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6);
        r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x001a;
    L_0x0030:
        r2 = r0;
        goto L_0x001b;
    L_0x0032:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x0042;
    L_0x0038:
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6);
        if (r2 == r5) goto L_0x001a;
    L_0x0042:
        r2 = r0;
        goto L_0x001b;
    L_0x0044:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x0056;
    L_0x004a:
        r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6);
        r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6);
        r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x001a;
    L_0x0056:
        r2 = r0;
        goto L_0x001b;
    L_0x0058:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x006a;
    L_0x005e:
        r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6);
        r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6);
        r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x001a;
    L_0x006a:
        r2 = r0;
        goto L_0x001b;
    L_0x006c:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x007c;
    L_0x0072:
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6);
        if (r2 == r5) goto L_0x001a;
    L_0x007c:
        r2 = r0;
        goto L_0x001b;
    L_0x007e:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x0090;
    L_0x0084:
        r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6);
        r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6);
        r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x001a;
    L_0x0090:
        r2 = r0;
        goto L_0x001b;
    L_0x0092:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x00a2;
    L_0x0098:
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6);
        if (r2 == r5) goto L_0x001a;
    L_0x00a2:
        r2 = r0;
        goto L_0x001b;
    L_0x00a5:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x00b5;
    L_0x00ab:
        r2 = com.google.android.gms.internal.ads.zzbek.zzm(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzm(r13, r6);
        if (r2 == r5) goto L_0x001a;
    L_0x00b5:
        r2 = r0;
        goto L_0x001b;
    L_0x00b8:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x00cc;
    L_0x00be:
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6);
        r2 = com.google.android.gms.internal.ads.zzbdo.zzd(r2, r5);
        if (r2 != 0) goto L_0x001a;
    L_0x00cc:
        r2 = r0;
        goto L_0x001b;
    L_0x00cf:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x00e3;
    L_0x00d5:
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6);
        r2 = com.google.android.gms.internal.ads.zzbdo.zzd(r2, r5);
        if (r2 != 0) goto L_0x001a;
    L_0x00e3:
        r2 = r0;
        goto L_0x001b;
    L_0x00e6:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x00fa;
    L_0x00ec:
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6);
        r2 = com.google.android.gms.internal.ads.zzbdo.zzd(r2, r5);
        if (r2 != 0) goto L_0x001a;
    L_0x00fa:
        r2 = r0;
        goto L_0x001b;
    L_0x00fd:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x010d;
    L_0x0103:
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6);
        if (r2 == r5) goto L_0x001a;
    L_0x010d:
        r2 = r0;
        goto L_0x001b;
    L_0x0110:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x0120;
    L_0x0116:
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6);
        if (r2 == r5) goto L_0x001a;
    L_0x0120:
        r2 = r0;
        goto L_0x001b;
    L_0x0123:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x0133;
    L_0x0129:
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6);
        if (r2 == r5) goto L_0x001a;
    L_0x0133:
        r2 = r0;
        goto L_0x001b;
    L_0x0136:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x0148;
    L_0x013c:
        r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6);
        r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6);
        r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x001a;
    L_0x0148:
        r2 = r0;
        goto L_0x001b;
    L_0x014b:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x015b;
    L_0x0151:
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r6);
        if (r2 == r5) goto L_0x001a;
    L_0x015b:
        r2 = r0;
        goto L_0x001b;
    L_0x015e:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x0170;
    L_0x0164:
        r8 = com.google.android.gms.internal.ads.zzbek.zzl(r12, r6);
        r6 = com.google.android.gms.internal.ads.zzbek.zzl(r13, r6);
        r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1));
        if (r2 == 0) goto L_0x001a;
    L_0x0170:
        r2 = r0;
        goto L_0x001b;
    L_0x0173:
        r2 = r11.zzc(r12, r13, r3);
        if (r2 == 0) goto L_0x0187;
    L_0x0179:
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6);
        r2 = com.google.android.gms.internal.ads.zzbdo.zzd(r2, r5);
        if (r2 != 0) goto L_0x001a;
    L_0x0187:
        r2 = r0;
        goto L_0x001b;
    L_0x018a:
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6);
        r2 = com.google.android.gms.internal.ads.zzbdo.zzd(r2, r5);
        goto L_0x001b;
    L_0x0198:
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6);
        r2 = com.google.android.gms.internal.ads.zzbdo.zzd(r2, r5);
        goto L_0x001b;
    L_0x01a6:
        r2 = r11.zzcu(r3);
        r5 = r2 & r10;
        r8 = (long) r5;
        r5 = com.google.android.gms.internal.ads.zzbek.zzk(r12, r8);
        r2 = r2 & r10;
        r8 = (long) r2;
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r13, r8);
        if (r5 != r2) goto L_0x01c7;
    L_0x01b9:
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r12, r6);
        r5 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r6);
        r2 = com.google.android.gms.internal.ads.zzbdo.zzd(r2, r5);
        if (r2 != 0) goto L_0x001a;
    L_0x01c7:
        r2 = r0;
        goto L_0x001b;
    L_0x01ca:
        r2 = r3 + 4;
        r3 = r2;
        goto L_0x0009;
    L_0x01cf:
        r2 = r11.zzdwv;
        r2 = r2.zzac(r12);
        r3 = r11.zzdwv;
        r3 = r3.zzac(r13);
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x001d;
    L_0x01e1:
        r0 = r11.zzdwm;
        if (r0 == 0) goto L_0x01f7;
    L_0x01e5:
        r0 = r11.zzdww;
        r0 = r0.zzm(r12);
        r1 = r11.zzdww;
        r1 = r1.zzm(r13);
        r0 = r0.equals(r1);
        goto L_0x001d;
    L_0x01f7:
        r0 = r1;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int hashCode(T r10) {
        /*
        r9 = this;
        r1 = 37;
        r0 = 0;
        r2 = r9.zzdwg;
        r4 = r2.length;
        r3 = r0;
        r2 = r0;
    L_0x0008:
        if (r3 >= r4) goto L_0x0255;
    L_0x000a:
        r0 = r9.zzct(r3);
        r5 = r9.zzdwg;
        r5 = r5[r3];
        r6 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r6 = r6 & r0;
        r6 = (long) r6;
        r8 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r0 = r0 & r8;
        r0 = r0 >>> 20;
        switch(r0) {
            case 0: goto L_0x0025;
            case 1: goto L_0x0035;
            case 2: goto L_0x0041;
            case 3: goto L_0x004d;
            case 4: goto L_0x0059;
            case 5: goto L_0x0061;
            case 6: goto L_0x006d;
            case 7: goto L_0x0075;
            case 8: goto L_0x0081;
            case 9: goto L_0x008f;
            case 10: goto L_0x009d;
            case 11: goto L_0x00aa;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00bc;
            case 14: goto L_0x00c5;
            case 15: goto L_0x00d2;
            case 16: goto L_0x00db;
            case 17: goto L_0x00e8;
            case 18: goto L_0x00f7;
            case 19: goto L_0x00f7;
            case 20: goto L_0x00f7;
            case 21: goto L_0x00f7;
            case 22: goto L_0x00f7;
            case 23: goto L_0x00f7;
            case 24: goto L_0x00f7;
            case 25: goto L_0x00f7;
            case 26: goto L_0x00f7;
            case 27: goto L_0x00f7;
            case 28: goto L_0x00f7;
            case 29: goto L_0x00f7;
            case 30: goto L_0x00f7;
            case 31: goto L_0x00f7;
            case 32: goto L_0x00f7;
            case 33: goto L_0x00f7;
            case 34: goto L_0x00f7;
            case 35: goto L_0x00f7;
            case 36: goto L_0x00f7;
            case 37: goto L_0x00f7;
            case 38: goto L_0x00f7;
            case 39: goto L_0x00f7;
            case 40: goto L_0x00f7;
            case 41: goto L_0x00f7;
            case 42: goto L_0x00f7;
            case 43: goto L_0x00f7;
            case 44: goto L_0x00f7;
            case 45: goto L_0x00f7;
            case 46: goto L_0x00f7;
            case 47: goto L_0x00f7;
            case 48: goto L_0x00f7;
            case 49: goto L_0x00f7;
            case 50: goto L_0x0104;
            case 51: goto L_0x0111;
            case 52: goto L_0x0128;
            case 53: goto L_0x013b;
            case 54: goto L_0x014e;
            case 55: goto L_0x0161;
            case 56: goto L_0x0170;
            case 57: goto L_0x0183;
            case 58: goto L_0x0192;
            case 59: goto L_0x01a5;
            case 60: goto L_0x01ba;
            case 61: goto L_0x01cd;
            case 62: goto L_0x01e0;
            case 63: goto L_0x01ef;
            case 64: goto L_0x01fe;
            case 65: goto L_0x020d;
            case 66: goto L_0x0220;
            case 67: goto L_0x022f;
            case 68: goto L_0x0242;
            default: goto L_0x001f;
        };
    L_0x001f:
        r0 = r2;
    L_0x0020:
        r2 = r3 + 4;
        r3 = r2;
        r2 = r0;
        goto L_0x0008;
    L_0x0025:
        r0 = r2 * 53;
        r6 = com.google.android.gms.internal.ads.zzbek.zzo(r10, r6);
        r6 = java.lang.Double.doubleToLongBits(r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0035:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzn(r10, r6);
        r2 = java.lang.Float.floatToIntBits(r2);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0041:
        r0 = r2 * 53;
        r6 = com.google.android.gms.internal.ads.zzbek.zzl(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x004d:
        r0 = r2 * 53;
        r6 = com.google.android.gms.internal.ads.zzbek.zzl(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0059:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0061:
        r0 = r2 * 53;
        r6 = com.google.android.gms.internal.ads.zzbek.zzl(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x006d:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0075:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzm(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzar(r2);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0081:
        r2 = r2 * 53;
        r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6);
        r0 = (java.lang.String) r0;
        r0 = r0.hashCode();
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x008f:
        r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6);
        if (r0 == 0) goto L_0x0277;
    L_0x0095:
        r0 = r0.hashCode();
    L_0x0099:
        r2 = r2 * 53;
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x009d:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6);
        r2 = r2.hashCode();
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x00aa:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x00b3:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x00bc:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x00c5:
        r0 = r2 * 53;
        r6 = com.google.android.gms.internal.ads.zzbek.zzl(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x00d2:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzk(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x00db:
        r0 = r2 * 53;
        r6 = com.google.android.gms.internal.ads.zzbek.zzl(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x00e8:
        r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6);
        if (r0 == 0) goto L_0x0274;
    L_0x00ee:
        r0 = r0.hashCode();
    L_0x00f2:
        r2 = r2 * 53;
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x00f7:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6);
        r2 = r2.hashCode();
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0104:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6);
        r2 = r2.hashCode();
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0111:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0117:
        r0 = r2 * 53;
        r6 = zzf(r10, r6);
        r6 = java.lang.Double.doubleToLongBits(r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0128:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x012e:
        r0 = r2 * 53;
        r2 = zzg(r10, r6);
        r2 = java.lang.Float.floatToIntBits(r2);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x013b:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0141:
        r0 = r2 * 53;
        r6 = zzi(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x014e:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0154:
        r0 = r2 * 53;
        r6 = zzi(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0161:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0167:
        r0 = r2 * 53;
        r2 = zzh(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0170:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0176:
        r0 = r2 * 53;
        r6 = zzi(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0183:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0189:
        r0 = r2 * 53;
        r2 = zzh(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0192:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0198:
        r0 = r2 * 53;
        r2 = zzj(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzar(r2);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x01a5:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x01ab:
        r2 = r2 * 53;
        r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6);
        r0 = (java.lang.String) r0;
        r0 = r0.hashCode();
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x01ba:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x01c0:
        r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6);
        r2 = r2 * 53;
        r0 = r0.hashCode();
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x01cd:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x01d3:
        r0 = r2 * 53;
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6);
        r2 = r2.hashCode();
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x01e0:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x01e6:
        r0 = r2 * 53;
        r2 = zzh(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x01ef:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x01f5:
        r0 = r2 * 53;
        r2 = zzh(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x01fe:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0204:
        r0 = r2 * 53;
        r2 = zzh(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x020d:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0213:
        r0 = r2 * 53;
        r6 = zzi(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0220:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0226:
        r0 = r2 * 53;
        r2 = zzh(r10, r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x022f:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0235:
        r0 = r2 * 53;
        r6 = zzi(r10, r6);
        r2 = com.google.android.gms.internal.ads.zzbbq.zzv(r6);
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0242:
        r0 = r9.zza(r10, r5, r3);
        if (r0 == 0) goto L_0x001f;
    L_0x0248:
        r0 = com.google.android.gms.internal.ads.zzbek.zzp(r10, r6);
        r2 = r2 * 53;
        r0 = r0.hashCode();
        r0 = r0 + r2;
        goto L_0x0020;
    L_0x0255:
        r0 = r2 * 53;
        r1 = r9.zzdwv;
        r1 = r1.zzac(r10);
        r1 = r1.hashCode();
        r0 = r0 + r1;
        r1 = r9.zzdwm;
        if (r1 == 0) goto L_0x0273;
    L_0x0266:
        r0 = r0 * 53;
        r1 = r9.zzdww;
        r1 = r1.zzm(r10);
        r1 = r1.hashCode();
        r0 = r0 + r1;
    L_0x0273:
        return r0;
    L_0x0274:
        r0 = r1;
        goto L_0x00f2;
    L_0x0277:
        r0 = r1;
        goto L_0x0099;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.hashCode(java.lang.Object):int");
    }

    public final T newInstance() {
        return this.zzdwt.newInstance(this.zzdwl);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(T r13, com.google.android.gms.internal.ads.zzbdl r14, com.google.android.gms.internal.ads.zzbbb r15) throws java.io.IOException {
        /*
        r12 = this;
        if (r15 != 0) goto L_0x0008;
    L_0x0002:
        r0 = new java.lang.NullPointerException;
        r0.<init>();
        throw r0;
    L_0x0008:
        r6 = r12.zzdwv;
        r0 = r12.zzdww;
        r5 = 0;
        r4 = 0;
    L_0x000e:
        r1 = r14.zzaci();	 Catch:{ all -> 0x00e9 }
        r2 = r12.zzcw(r1);	 Catch:{ all -> 0x00e9 }
        if (r2 >= 0) goto L_0x0079;
    L_0x0018:
        r2 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        if (r1 != r2) goto L_0x0036;
    L_0x001d:
        r0 = r12.zzdwr;
        if (r0 == 0) goto L_0x0030;
    L_0x0021:
        r1 = r12.zzdwr;
        r2 = r1.length;
        r0 = 0;
    L_0x0025:
        if (r0 >= r2) goto L_0x0030;
    L_0x0027:
        r3 = r1[r0];
        r5 = r12.zza(r13, r3, r5, r6);
        r0 = r0 + 1;
        goto L_0x0025;
    L_0x0030:
        if (r5 == 0) goto L_0x0035;
    L_0x0032:
        r6.zzf(r13, r5);
    L_0x0035:
        return;
    L_0x0036:
        r2 = r12.zzdwm;	 Catch:{ all -> 0x00e9 }
        if (r2 != 0) goto L_0x004a;
    L_0x003a:
        r2 = 0;
    L_0x003b:
        if (r2 == 0) goto L_0x0051;
    L_0x003d:
        if (r4 != 0) goto L_0x0043;
    L_0x003f:
        r4 = r0.zzn(r13);	 Catch:{ all -> 0x00e9 }
    L_0x0043:
        r1 = r14;
        r3 = r15;
        r5 = r0.zza(r1, r2, r3, r4, r5, r6);	 Catch:{ all -> 0x00e9 }
        goto L_0x000e;
    L_0x004a:
        r2 = r12.zzdwl;	 Catch:{ all -> 0x00e9 }
        r2 = r0.zza(r15, r2, r1);	 Catch:{ all -> 0x00e9 }
        goto L_0x003b;
    L_0x0051:
        r6.zza(r14);	 Catch:{ all -> 0x00e9 }
        if (r5 != 0) goto L_0x005a;
    L_0x0056:
        r5 = r6.zzad(r13);	 Catch:{ all -> 0x00e9 }
    L_0x005a:
        r1 = r6.zza(r5, r14);	 Catch:{ all -> 0x00e9 }
        if (r1 != 0) goto L_0x000e;
    L_0x0060:
        r0 = r12.zzdwr;
        if (r0 == 0) goto L_0x0073;
    L_0x0064:
        r1 = r12.zzdwr;
        r2 = r1.length;
        r0 = 0;
    L_0x0068:
        if (r0 >= r2) goto L_0x0073;
    L_0x006a:
        r3 = r1[r0];
        r5 = r12.zza(r13, r3, r5, r6);
        r0 = r0 + 1;
        goto L_0x0068;
    L_0x0073:
        if (r5 == 0) goto L_0x0035;
    L_0x0075:
        r6.zzf(r13, r5);
        goto L_0x0035;
    L_0x0079:
        r3 = r12.zzct(r2);	 Catch:{ all -> 0x00e9 }
        r7 = 267386880; // 0xff00000 float:2.3665827E-29 double:1.321066716E-315;
        r7 = r7 & r3;
        r7 = r7 >>> 20;
        switch(r7) {
            case 0: goto L_0x00a4;
            case 1: goto L_0x00d8;
            case 2: goto L_0x00fd;
            case 3: goto L_0x010e;
            case 4: goto L_0x011f;
            case 5: goto L_0x0130;
            case 6: goto L_0x0141;
            case 7: goto L_0x0152;
            case 8: goto L_0x0163;
            case 9: goto L_0x016b;
            case 10: goto L_0x01a5;
            case 11: goto L_0x01b6;
            case 12: goto L_0x01c7;
            case 13: goto L_0x01ea;
            case 14: goto L_0x01fb;
            case 15: goto L_0x020c;
            case 16: goto L_0x021d;
            case 17: goto L_0x022e;
            case 18: goto L_0x0268;
            case 19: goto L_0x0278;
            case 20: goto L_0x0288;
            case 21: goto L_0x0298;
            case 22: goto L_0x02a8;
            case 23: goto L_0x02b8;
            case 24: goto L_0x02c8;
            case 25: goto L_0x02d8;
            case 26: goto L_0x02e8;
            case 27: goto L_0x030e;
            case 28: goto L_0x0322;
            case 29: goto L_0x0332;
            case 30: goto L_0x0342;
            case 31: goto L_0x035a;
            case 32: goto L_0x036a;
            case 33: goto L_0x037a;
            case 34: goto L_0x038a;
            case 35: goto L_0x039a;
            case 36: goto L_0x03aa;
            case 37: goto L_0x03ba;
            case 38: goto L_0x03ca;
            case 39: goto L_0x03da;
            case 40: goto L_0x03ea;
            case 41: goto L_0x03fa;
            case 42: goto L_0x040a;
            case 43: goto L_0x041a;
            case 44: goto L_0x042a;
            case 45: goto L_0x0442;
            case 46: goto L_0x0452;
            case 47: goto L_0x0462;
            case 48: goto L_0x0472;
            case 49: goto L_0x0482;
            case 50: goto L_0x0496;
            case 51: goto L_0x04da;
            case 52: goto L_0x04ef;
            case 53: goto L_0x0504;
            case 54: goto L_0x0519;
            case 55: goto L_0x052e;
            case 56: goto L_0x0543;
            case 57: goto L_0x0558;
            case 58: goto L_0x056d;
            case 59: goto L_0x0582;
            case 60: goto L_0x058a;
            case 61: goto L_0x05c6;
            case 62: goto L_0x05d7;
            case 63: goto L_0x05ec;
            case 64: goto L_0x0613;
            case 65: goto L_0x0628;
            case 66: goto L_0x063d;
            case 67: goto L_0x0652;
            case 68: goto L_0x0667;
            default: goto L_0x0085;
        };
    L_0x0085:
        if (r5 != 0) goto L_0x008b;
    L_0x0087:
        r5 = r6.zzagb();	 Catch:{ zzbbv -> 0x00b5 }
    L_0x008b:
        r1 = r6.zza(r5, r14);	 Catch:{ zzbbv -> 0x00b5 }
        if (r1 != 0) goto L_0x000e;
    L_0x0091:
        r0 = r12.zzdwr;
        if (r0 == 0) goto L_0x067c;
    L_0x0095:
        r1 = r12.zzdwr;
        r2 = r1.length;
        r0 = 0;
    L_0x0099:
        if (r0 >= r2) goto L_0x067c;
    L_0x009b:
        r3 = r1[r0];
        r5 = r12.zza(r13, r3, r5, r6);
        r0 = r0 + 1;
        goto L_0x0099;
    L_0x00a4:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;
        r10 = r14.readDouble();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r10);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x00b5:
        r1 = move-exception;
        r6.zza(r14);	 Catch:{ all -> 0x00e9 }
        if (r5 != 0) goto L_0x00bf;
    L_0x00bb:
        r5 = r6.zzad(r13);	 Catch:{ all -> 0x00e9 }
    L_0x00bf:
        r1 = r6.zza(r5, r14);	 Catch:{ all -> 0x00e9 }
        if (r1 != 0) goto L_0x000e;
    L_0x00c5:
        r0 = r12.zzdwr;
        if (r0 == 0) goto L_0x0683;
    L_0x00c9:
        r1 = r12.zzdwr;
        r2 = r1.length;
        r0 = 0;
    L_0x00cd:
        if (r0 >= r2) goto L_0x0683;
    L_0x00cf:
        r3 = r1[r0];
        r5 = r12.zza(r13, r3, r5, r6);
        r0 = r0 + 1;
        goto L_0x00cd;
    L_0x00d8:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;
        r1 = r14.readFloat();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x00e9:
        r0 = move-exception;
        r1 = r12.zzdwr;
        if (r1 == 0) goto L_0x068a;
    L_0x00ee:
        r2 = r12.zzdwr;
        r3 = r2.length;
        r1 = 0;
    L_0x00f2:
        if (r1 >= r3) goto L_0x068a;
    L_0x00f4:
        r4 = r2[r1];
        r5 = r12.zza(r13, r4, r5, r6);
        r1 = r1 + 1;
        goto L_0x00f2;
    L_0x00fd:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;
        r10 = r14.zzabm();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r10);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x010e:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r10 = r14.zzabl();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r10);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x011f:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r14.zzabn();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zzb(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0130:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r10 = r14.zzabo();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r10);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0141:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r14.zzabp();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zzb(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0152:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r14.zzabq();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0163:
        r12.zza(r13, r3, r14);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x016b:
        r1 = r12.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        if (r1 == 0) goto L_0x0190;
    L_0x0171:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r8);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = r12.zzcq(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = r14.zza(r2, r15);	 Catch:{ zzbbv -> 0x00b5 }
        r1 = com.google.android.gms.internal.ads.zzbbq.zza(r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r2, r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0190:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r12.zzcq(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r14.zza(r1, r15);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x01a5:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r14.zzabs();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x01b6:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r14.zzabt();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zzb(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x01c7:
        r7 = r14.zzabu();	 Catch:{ zzbbv -> 0x00b5 }
        r8 = r12.zzcs(r2);	 Catch:{ zzbbv -> 0x00b5 }
        if (r8 == 0) goto L_0x01d7;
    L_0x01d1:
        r8 = r8.zzq(r7);	 Catch:{ zzbbv -> 0x00b5 }
        if (r8 == 0) goto L_0x01e4;
    L_0x01d7:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zzb(r13, r8, r7);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x01e4:
        r5 = com.google.android.gms.internal.ads.zzbdo.zza(r1, r7, r5, r6);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x01ea:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r14.zzabv();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zzb(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x01fb:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r10 = r14.zzabw();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r10);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x020c:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r14.zzabx();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zzb(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x021d:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r10 = r14.zzaby();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r10);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x022e:
        r1 = r12.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        if (r1 == 0) goto L_0x0253;
    L_0x0234:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r8);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = r12.zzcq(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = r14.zzb(r2, r15);	 Catch:{ zzbbv -> 0x00b5 }
        r1 = com.google.android.gms.internal.ads.zzbbq.zza(r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r2, r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0253:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r12.zzcq(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r14.zzb(r1, r15);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0268:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzp(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0278:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzq(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0288:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzs(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0298:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzr(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x02a8:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzt(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x02b8:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzu(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x02c8:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzv(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x02d8:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzw(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x02e8:
        r1 = zzcv(r3);	 Catch:{ zzbbv -> 0x00b5 }
        if (r1 == 0) goto L_0x02fe;
    L_0x02ee:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzx(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x02fe:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.readStringList(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x030e:
        r1 = r12.zzcq(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r7 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = r7.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zza(r2, r1, r15);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0322:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzy(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0332:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzz(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0342:
        r7 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r8 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r8;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r7.zza(r13, r8);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzaa(r3);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = r12.zzcs(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r5 = com.google.android.gms.internal.ads.zzbdo.zza(r1, r3, r2, r5, r6);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x035a:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzab(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x036a:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzac(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x037a:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzad(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x038a:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzae(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x039a:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzp(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x03aa:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzq(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x03ba:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzs(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x03ca:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzr(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x03da:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzt(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x03ea:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzu(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x03fa:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzv(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x040a:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzw(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x041a:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzz(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x042a:
        r7 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r8 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r8;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r7.zza(r13, r8);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzaa(r3);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = r12.zzcs(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r5 = com.google.android.gms.internal.ads.zzbdo.zza(r1, r3, r2, r5, r6);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0442:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzab(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0452:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzac(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0462:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzad(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0472:
        r1 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r2 = r2 & r3;
        r2 = (long) r2;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zza(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzae(r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0482:
        r1 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r3;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r12.zzcq(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = r12.zzdwu;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = r2.zza(r13, r8);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zzb(r2, r1, r15);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0496:
        r3 = r12.zzcr(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r12.zzct(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r1 = r1 & r2;
        r8 = (long) r1;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r8);	 Catch:{ zzbbv -> 0x00b5 }
        if (r2 != 0) goto L_0x04c3;
    L_0x04a9:
        r1 = r12.zzdwx;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zzw(r3);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
    L_0x04b2:
        r2 = r12.zzdwx;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r2.zzs(r1);	 Catch:{ zzbbv -> 0x00b5 }
        r2 = r12.zzdwx;	 Catch:{ zzbbv -> 0x00b5 }
        r2 = r2.zzx(r3);	 Catch:{ zzbbv -> 0x00b5 }
        r14.zza(r1, r2, r15);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x04c3:
        r1 = r12.zzdwx;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zzu(r2);	 Catch:{ zzbbv -> 0x00b5 }
        if (r1 == 0) goto L_0x0690;
    L_0x04cb:
        r1 = r12.zzdwx;	 Catch:{ zzbbv -> 0x00b5 }
        r1 = r1.zzw(r3);	 Catch:{ zzbbv -> 0x00b5 }
        r7 = r12.zzdwx;	 Catch:{ zzbbv -> 0x00b5 }
        r7.zzb(r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r1);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x04b2;
    L_0x04da:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r10 = r14.readDouble();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Double.valueOf(r10);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x04ef:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r14.readFloat();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Float.valueOf(r3);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0504:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r10 = r14.zzabm();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Long.valueOf(r10);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0519:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r10 = r14.zzabl();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Long.valueOf(r10);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x052e:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r14.zzabn();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0543:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r10 = r14.zzabo();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Long.valueOf(r10);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0558:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r14.zzabp();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x056d:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r14.zzabq();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Boolean.valueOf(r3);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0582:
        r12.zza(r13, r3, r14);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x058a:
        r7 = r12.zza(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        if (r7 == 0) goto L_0x05b2;
    L_0x0590:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r7 = r7 & r3;
        r8 = (long) r7;	 Catch:{ zzbbv -> 0x00b5 }
        r7 = com.google.android.gms.internal.ads.zzbek.zzp(r13, r8);	 Catch:{ zzbbv -> 0x00b5 }
        r8 = r12.zzcq(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r8 = r14.zza(r8, r15);	 Catch:{ zzbbv -> 0x00b5 }
        r7 = com.google.android.gms.internal.ads.zzbbq.zza(r7, r8);	 Catch:{ zzbbv -> 0x00b5 }
        r8 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r8;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r7);	 Catch:{ zzbbv -> 0x00b5 }
    L_0x05ad:
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x05b2:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r12.zzcq(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r14.zza(r3, r15);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x05ad;
    L_0x05c6:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r14.zzabs();	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x05d7:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r14.zzabt();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x05ec:
        r7 = r14.zzabu();	 Catch:{ zzbbv -> 0x00b5 }
        r8 = r12.zzcs(r2);	 Catch:{ zzbbv -> 0x00b5 }
        if (r8 == 0) goto L_0x05fc;
    L_0x05f6:
        r8 = r8.zzq(r7);	 Catch:{ zzbbv -> 0x00b5 }
        if (r8 == 0) goto L_0x060d;
    L_0x05fc:
        r8 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r8;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Integer.valueOf(r7);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x060d:
        r5 = com.google.android.gms.internal.ads.zzbdo.zza(r1, r7, r5, r6);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0613:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r14.zzabv();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0628:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r10 = r14.zzabw();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Long.valueOf(r10);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x063d:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r14.zzabx();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0652:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r10 = r14.zzaby();	 Catch:{ zzbbv -> 0x00b5 }
        r3 = java.lang.Long.valueOf(r10);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x0667:
        r7 = 1048575; // 0xfffff float:1.469367E-39 double:5.18065E-318;
        r3 = r3 & r7;
        r8 = (long) r3;	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r12.zzcq(r2);	 Catch:{ zzbbv -> 0x00b5 }
        r3 = r14.zzb(r3, r15);	 Catch:{ zzbbv -> 0x00b5 }
        com.google.android.gms.internal.ads.zzbek.zza(r13, r8, r3);	 Catch:{ zzbbv -> 0x00b5 }
        r12.zzb(r13, r1, r2);	 Catch:{ zzbbv -> 0x00b5 }
        goto L_0x000e;
    L_0x067c:
        if (r5 == 0) goto L_0x0035;
    L_0x067e:
        r6.zzf(r13, r5);
        goto L_0x0035;
    L_0x0683:
        if (r5 == 0) goto L_0x0035;
    L_0x0685:
        r6.zzf(r13, r5);
        goto L_0x0035;
    L_0x068a:
        if (r5 == 0) goto L_0x068f;
    L_0x068c:
        r6.zzf(r13, r5);
    L_0x068f:
        throw r0;
    L_0x0690:
        r1 = r2;
        goto L_0x04b2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzbcy.zza(java.lang.Object, com.google.android.gms.internal.ads.zzbdl, com.google.android.gms.internal.ads.zzbbb):void");
    }

    public final void zza(T t, zzbey com_google_android_gms_internal_ads_zzbey) throws IOException {
        Iterator it;
        Entry entry;
        zzbbg zzm;
        int length;
        int zzct;
        int i;
        Entry entry2;
        if (com_google_android_gms_internal_ads_zzbey.zzacn() == zze.zzdum) {
            zza(this.zzdwv, (Object) t, com_google_android_gms_internal_ads_zzbey);
            it = null;
            entry = null;
            if (this.zzdwm) {
                zzm = this.zzdww.zzm(t);
                if (!zzm.isEmpty()) {
                    it = zzm.descendingIterator();
                    entry = (Entry) it.next();
                }
            }
            length = this.zzdwg.length - 4;
            while (length >= 0) {
                zzct = zzct(length);
                i = this.zzdwg[length];
                entry2 = entry;
                while (entry2 != null && this.zzdww.zza(entry2) > i) {
                    this.zzdww.zza(com_google_android_gms_internal_ads_zzbey, entry2);
                    entry2 = it.hasNext() ? (Entry) it.next() : null;
                }
                switch ((267386880 & zzct) >>> 20) {
                    case 0:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i, zzbek.zzo(t, (long) (1048575 & zzct)));
                        break;
                    case 1:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i, zzbek.zzn(t, (long) (1048575 & zzct)));
                        break;
                    case 2:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzi(i, zzbek.zzl(t, (long) (1048575 & zzct)));
                        break;
                    case 3:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i, zzbek.zzl(t, (long) (1048575 & zzct)));
                        break;
                    case 4:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzm(i, zzbek.zzk(t, (long) (1048575 & zzct)));
                        break;
                    case 5:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzc(i, zzbek.zzl(t, (long) (1048575 & zzct)));
                        break;
                    case 6:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzp(i, zzbek.zzk(t, (long) (1048575 & zzct)));
                        break;
                    case 7:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzf(i, zzbek.zzm(t, (long) (1048575 & zzct)));
                        break;
                    case 8:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        zza(i, zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey);
                        break;
                    case 9:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i, zzbek.zzp(t, (long) (1048575 & zzct)), zzcq(length));
                        break;
                    case 10:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i, (zzbah) zzbek.zzp(t, (long) (1048575 & zzct)));
                        break;
                    case 11:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzn(i, zzbek.zzk(t, (long) (1048575 & zzct)));
                        break;
                    case 12:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzx(i, zzbek.zzk(t, (long) (1048575 & zzct)));
                        break;
                    case 13:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzw(i, zzbek.zzk(t, (long) (1048575 & zzct)));
                        break;
                    case 14:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzj(i, zzbek.zzl(t, (long) (1048575 & zzct)));
                        break;
                    case 15:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzo(i, zzbek.zzk(t, (long) (1048575 & zzct)));
                        break;
                    case 16:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzb(i, zzbek.zzl(t, (long) (1048575 & zzct)));
                        break;
                    case 17:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzb(i, zzbek.zzp(t, (long) (1048575 & zzct)), zzcq(length));
                        break;
                    case 18:
                        zzbdo.zza(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 19:
                        zzbdo.zzb(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 20:
                        zzbdo.zzc(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 21:
                        zzbdo.zzd(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 22:
                        zzbdo.zzh(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 23:
                        zzbdo.zzf(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 24:
                        zzbdo.zzk(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 25:
                        zzbdo.zzn(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 26:
                        zzbdo.zza(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey);
                        break;
                    case 27:
                        zzbdo.zza(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, zzcq(length));
                        break;
                    case 28:
                        zzbdo.zzb(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey);
                        break;
                    case 29:
                        zzbdo.zzi(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 30:
                        zzbdo.zzm(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 31:
                        zzbdo.zzl(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 32:
                        zzbdo.zzg(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 33:
                        zzbdo.zzj(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 34:
                        zzbdo.zze(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 35:
                        zzbdo.zza(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 36:
                        zzbdo.zzb(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 37:
                        zzbdo.zzc(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 38:
                        zzbdo.zzd(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 39:
                        zzbdo.zzh(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 40:
                        zzbdo.zzf(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 41:
                        zzbdo.zzk(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 42:
                        zzbdo.zzn(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 43:
                        zzbdo.zzi(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 44:
                        zzbdo.zzm(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 45:
                        zzbdo.zzl(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 46:
                        zzbdo.zzg(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 47:
                        zzbdo.zzj(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 48:
                        zzbdo.zze(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 49:
                        zzbdo.zzb(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey, zzcq(length));
                        break;
                    case 50:
                        zza(com_google_android_gms_internal_ads_zzbey, i, zzbek.zzp(t, (long) (1048575 & zzct)), length);
                        break;
                    case 51:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i, zzf(t, (long) (1048575 & zzct)));
                        break;
                    case 52:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i, zzg(t, (long) (1048575 & zzct)));
                        break;
                    case 53:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzi(i, zzi(t, (long) (1048575 & zzct)));
                        break;
                    case 54:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i, zzi(t, (long) (1048575 & zzct)));
                        break;
                    case 55:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzm(i, zzh(t, (long) (1048575 & zzct)));
                        break;
                    case 56:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzc(i, zzi(t, (long) (1048575 & zzct)));
                        break;
                    case 57:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzp(i, zzh(t, (long) (1048575 & zzct)));
                        break;
                    case 58:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzf(i, zzj(t, (long) (1048575 & zzct)));
                        break;
                    case 59:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        zza(i, zzbek.zzp(t, (long) (1048575 & zzct)), com_google_android_gms_internal_ads_zzbey);
                        break;
                    case 60:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i, zzbek.zzp(t, (long) (1048575 & zzct)), zzcq(length));
                        break;
                    case 61:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i, (zzbah) zzbek.zzp(t, (long) (1048575 & zzct)));
                        break;
                    case 62:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzn(i, zzh(t, (long) (1048575 & zzct)));
                        break;
                    case 63:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzx(i, zzh(t, (long) (1048575 & zzct)));
                        break;
                    case 64:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzw(i, zzh(t, (long) (1048575 & zzct)));
                        break;
                    case 65:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzj(i, zzi(t, (long) (1048575 & zzct)));
                        break;
                    case 66:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzo(i, zzh(t, (long) (1048575 & zzct)));
                        break;
                    case 67:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzb(i, zzi(t, (long) (1048575 & zzct)));
                        break;
                    case 68:
                        if (!zza((Object) t, i, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzb(i, zzbek.zzp(t, (long) (1048575 & zzct)), zzcq(length));
                        break;
                    default:
                        break;
                }
                length -= 4;
                entry = entry2;
            }
            while (entry != null) {
                this.zzdww.zza(com_google_android_gms_internal_ads_zzbey, entry);
                entry = it.hasNext() ? (Entry) it.next() : null;
            }
        } else if (this.zzdwo) {
            it = null;
            entry = null;
            if (this.zzdwm) {
                zzm = this.zzdww.zzm(t);
                if (!zzm.isEmpty()) {
                    it = zzm.iterator();
                    entry = (Entry) it.next();
                }
            }
            zzct = this.zzdwg.length;
            length = 0;
            while (length < zzct) {
                i = zzct(length);
                int i2 = this.zzdwg[length];
                entry2 = entry;
                while (entry2 != null && this.zzdww.zza(entry2) <= i2) {
                    this.zzdww.zza(com_google_android_gms_internal_ads_zzbey, entry2);
                    entry2 = it.hasNext() ? (Entry) it.next() : null;
                }
                switch ((267386880 & i) >>> 20) {
                    case 0:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i2, zzbek.zzo(t, (long) (1048575 & i)));
                        break;
                    case 1:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i2, zzbek.zzn(t, (long) (1048575 & i)));
                        break;
                    case 2:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzi(i2, zzbek.zzl(t, (long) (1048575 & i)));
                        break;
                    case 3:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i2, zzbek.zzl(t, (long) (1048575 & i)));
                        break;
                    case 4:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzm(i2, zzbek.zzk(t, (long) (1048575 & i)));
                        break;
                    case 5:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzc(i2, zzbek.zzl(t, (long) (1048575 & i)));
                        break;
                    case 6:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzp(i2, zzbek.zzk(t, (long) (1048575 & i)));
                        break;
                    case 7:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzf(i2, zzbek.zzm(t, (long) (1048575 & i)));
                        break;
                    case 8:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        zza(i2, zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey);
                        break;
                    case 9:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i2, zzbek.zzp(t, (long) (1048575 & i)), zzcq(length));
                        break;
                    case 10:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i2, (zzbah) zzbek.zzp(t, (long) (1048575 & i)));
                        break;
                    case 11:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzn(i2, zzbek.zzk(t, (long) (1048575 & i)));
                        break;
                    case 12:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzx(i2, zzbek.zzk(t, (long) (1048575 & i)));
                        break;
                    case 13:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzw(i2, zzbek.zzk(t, (long) (1048575 & i)));
                        break;
                    case 14:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzj(i2, zzbek.zzl(t, (long) (1048575 & i)));
                        break;
                    case 15:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzo(i2, zzbek.zzk(t, (long) (1048575 & i)));
                        break;
                    case 16:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzb(i2, zzbek.zzl(t, (long) (1048575 & i)));
                        break;
                    case 17:
                        if (!zza((Object) t, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzb(i2, zzbek.zzp(t, (long) (1048575 & i)), zzcq(length));
                        break;
                    case 18:
                        zzbdo.zza(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 19:
                        zzbdo.zzb(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 20:
                        zzbdo.zzc(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 21:
                        zzbdo.zzd(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 22:
                        zzbdo.zzh(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 23:
                        zzbdo.zzf(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 24:
                        zzbdo.zzk(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 25:
                        zzbdo.zzn(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 26:
                        zzbdo.zza(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey);
                        break;
                    case 27:
                        zzbdo.zza(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, zzcq(length));
                        break;
                    case 28:
                        zzbdo.zzb(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey);
                        break;
                    case 29:
                        zzbdo.zzi(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 30:
                        zzbdo.zzm(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 31:
                        zzbdo.zzl(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 32:
                        zzbdo.zzg(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 33:
                        zzbdo.zzj(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 34:
                        zzbdo.zze(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, false);
                        break;
                    case 35:
                        zzbdo.zza(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 36:
                        zzbdo.zzb(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 37:
                        zzbdo.zzc(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 38:
                        zzbdo.zzd(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 39:
                        zzbdo.zzh(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 40:
                        zzbdo.zzf(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 41:
                        zzbdo.zzk(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 42:
                        zzbdo.zzn(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 43:
                        zzbdo.zzi(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 44:
                        zzbdo.zzm(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 45:
                        zzbdo.zzl(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 46:
                        zzbdo.zzg(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 47:
                        zzbdo.zzj(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 48:
                        zzbdo.zze(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, true);
                        break;
                    case 49:
                        zzbdo.zzb(this.zzdwg[length], (List) zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey, zzcq(length));
                        break;
                    case 50:
                        zza(com_google_android_gms_internal_ads_zzbey, i2, zzbek.zzp(t, (long) (1048575 & i)), length);
                        break;
                    case 51:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i2, zzf(t, (long) (1048575 & i)));
                        break;
                    case 52:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i2, zzg(t, (long) (1048575 & i)));
                        break;
                    case 53:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzi(i2, zzi(t, (long) (1048575 & i)));
                        break;
                    case 54:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i2, zzi(t, (long) (1048575 & i)));
                        break;
                    case 55:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzm(i2, zzh(t, (long) (1048575 & i)));
                        break;
                    case 56:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzc(i2, zzi(t, (long) (1048575 & i)));
                        break;
                    case 57:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzp(i2, zzh(t, (long) (1048575 & i)));
                        break;
                    case 58:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzf(i2, zzj(t, (long) (1048575 & i)));
                        break;
                    case 59:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        zza(i2, zzbek.zzp(t, (long) (1048575 & i)), com_google_android_gms_internal_ads_zzbey);
                        break;
                    case 60:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i2, zzbek.zzp(t, (long) (1048575 & i)), zzcq(length));
                        break;
                    case 61:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zza(i2, (zzbah) zzbek.zzp(t, (long) (1048575 & i)));
                        break;
                    case 62:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzn(i2, zzh(t, (long) (1048575 & i)));
                        break;
                    case 63:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzx(i2, zzh(t, (long) (1048575 & i)));
                        break;
                    case 64:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzw(i2, zzh(t, (long) (1048575 & i)));
                        break;
                    case 65:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzj(i2, zzi(t, (long) (1048575 & i)));
                        break;
                    case 66:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzo(i2, zzh(t, (long) (1048575 & i)));
                        break;
                    case 67:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzb(i2, zzi(t, (long) (1048575 & i)));
                        break;
                    case 68:
                        if (!zza((Object) t, i2, length)) {
                            break;
                        }
                        com_google_android_gms_internal_ads_zzbey.zzb(i2, zzbek.zzp(t, (long) (1048575 & i)), zzcq(length));
                        break;
                    default:
                        break;
                }
                length += 4;
                entry = entry2;
            }
            while (entry != null) {
                this.zzdww.zza(com_google_android_gms_internal_ads_zzbey, entry);
                entry = it.hasNext() ? (Entry) it.next() : null;
            }
            zza(this.zzdwv, (Object) t, com_google_android_gms_internal_ads_zzbey);
        } else {
            zzb((Object) t, com_google_android_gms_internal_ads_zzbey);
        }
    }

    public final void zza(T t, byte[] bArr, int i, int i2, zzbae com_google_android_gms_internal_ads_zzbae) throws IOException {
        if (this.zzdwo) {
            Unsafe unsafe = zzdwf;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                int i5 = bArr[i3];
                if (i5 < 0) {
                    i4 = zzbad.zza(i5, bArr, i4, com_google_android_gms_internal_ads_zzbae);
                    i5 = com_google_android_gms_internal_ads_zzbae.zzdpl;
                }
                int i6 = i5 >>> 3;
                i3 = i5 & 7;
                int zzcw = zzcw(i6);
                if (zzcw >= 0) {
                    int i7 = this.zzdwg[zzcw + 1];
                    int i8 = (267386880 & i7) >>> 20;
                    long j = (long) (1048575 & i7);
                    if (i8 <= 17) {
                        switch (i8) {
                            case 0:
                                if (i3 == 1) {
                                    zzbek.zza((Object) t, j, zzbad.zzg(bArr, i4));
                                    i3 = i4 + 8;
                                    continue;
                                }
                                break;
                            case 1:
                                if (i3 == 5) {
                                    zzbek.zza((Object) t, j, zzbad.zzh(bArr, i4));
                                    i3 = i4 + 4;
                                    continue;
                                }
                                break;
                            case 2:
                            case 3:
                                if (i3 == 0) {
                                    i3 = zzbad.zzb(bArr, i4, com_google_android_gms_internal_ads_zzbae);
                                    unsafe.putLong(t, j, com_google_android_gms_internal_ads_zzbae.zzdpm);
                                    continue;
                                }
                                break;
                            case 4:
                            case 11:
                                if (i3 == 0) {
                                    i3 = zzbad.zza(bArr, i4, com_google_android_gms_internal_ads_zzbae);
                                    unsafe.putInt(t, j, com_google_android_gms_internal_ads_zzbae.zzdpl);
                                    continue;
                                }
                                break;
                            case 5:
                            case 14:
                                if (i3 == 1) {
                                    unsafe.putLong(t, j, zzbad.zzf(bArr, i4));
                                    i3 = i4 + 8;
                                    continue;
                                }
                                break;
                            case 6:
                            case 13:
                                if (i3 == 5) {
                                    unsafe.putInt(t, j, zzbad.zze(bArr, i4));
                                    i3 = i4 + 4;
                                    continue;
                                }
                                break;
                            case 7:
                                if (i3 == 0) {
                                    i3 = zzbad.zzb(bArr, i4, com_google_android_gms_internal_ads_zzbae);
                                    zzbek.zza((Object) t, j, com_google_android_gms_internal_ads_zzbae.zzdpm != 0);
                                    continue;
                                }
                                break;
                            case 8:
                                if (i3 == 2) {
                                    int zzc = (ErrorDialogData.DYNAMITE_CRASH & i7) == 0 ? zzbad.zzc(bArr, i4, com_google_android_gms_internal_ads_zzbae) : zzbad.zzd(bArr, i4, com_google_android_gms_internal_ads_zzbae);
                                    unsafe.putObject(t, j, com_google_android_gms_internal_ads_zzbae.zzdpn);
                                    i3 = zzc;
                                    continue;
                                }
                                break;
                            case 9:
                                if (i3 == 2) {
                                    i3 = zza(zzcq(zzcw), bArr, i4, i2, com_google_android_gms_internal_ads_zzbae);
                                    Object object = unsafe.getObject(t, j);
                                    if (object != null) {
                                        unsafe.putObject(t, j, zzbbq.zza(object, com_google_android_gms_internal_ads_zzbae.zzdpn));
                                        break;
                                    } else {
                                        unsafe.putObject(t, j, com_google_android_gms_internal_ads_zzbae.zzdpn);
                                        continue;
                                    }
                                }
                                break;
                            case 10:
                                if (i3 == 2) {
                                    i3 = zzbad.zze(bArr, i4, com_google_android_gms_internal_ads_zzbae);
                                    unsafe.putObject(t, j, com_google_android_gms_internal_ads_zzbae.zzdpn);
                                    continue;
                                }
                                break;
                            case 12:
                                if (i3 == 0) {
                                    i3 = zzbad.zza(bArr, i4, com_google_android_gms_internal_ads_zzbae);
                                    unsafe.putInt(t, j, com_google_android_gms_internal_ads_zzbae.zzdpl);
                                    continue;
                                }
                                break;
                            case 15:
                                if (i3 == 0) {
                                    i3 = zzbad.zza(bArr, i4, com_google_android_gms_internal_ads_zzbae);
                                    unsafe.putInt(t, j, zzbaq.zzbu(com_google_android_gms_internal_ads_zzbae.zzdpl));
                                    continue;
                                }
                                break;
                            case 16:
                                if (i3 == 0) {
                                    i3 = zzbad.zzb(bArr, i4, com_google_android_gms_internal_ads_zzbae);
                                    unsafe.putLong(t, j, zzbaq.zzl(com_google_android_gms_internal_ads_zzbae.zzdpm));
                                    continue;
                                }
                                break;
                            default:
                                i3 = i4;
                                break;
                        }
                    } else if (i8 == 27) {
                        if (i3 == 2) {
                            zzbbt com_google_android_gms_internal_ads_zzbbt;
                            zzbbt com_google_android_gms_internal_ads_zzbbt2 = (zzbbt) unsafe.getObject(t, j);
                            if (com_google_android_gms_internal_ads_zzbbt2.zzaay()) {
                                com_google_android_gms_internal_ads_zzbbt = com_google_android_gms_internal_ads_zzbbt2;
                            } else {
                                int size = com_google_android_gms_internal_ads_zzbbt2.size();
                                com_google_android_gms_internal_ads_zzbbt = com_google_android_gms_internal_ads_zzbbt2.zzbm(size == 0 ? 10 : size << 1);
                                unsafe.putObject(t, j, com_google_android_gms_internal_ads_zzbbt);
                            }
                            i3 = zza(zzcq(zzcw), i5, bArr, i4, i2, com_google_android_gms_internal_ads_zzbbt, com_google_android_gms_internal_ads_zzbae);
                        }
                    } else if (i8 <= 49) {
                        i3 = zza((Object) t, bArr, i4, i2, i5, i6, i3, zzcw, (long) i7, i8, j, com_google_android_gms_internal_ads_zzbae);
                        if (i3 == i4) {
                            i3 = zza(i5, bArr, i3, i2, (Object) t, com_google_android_gms_internal_ads_zzbae);
                        }
                    } else if (i8 != 50) {
                        i3 = zza((Object) t, bArr, i4, i2, i5, i6, i3, i7, i8, j, zzcw, com_google_android_gms_internal_ads_zzbae);
                        if (i3 == i4) {
                            i3 = zza(i5, bArr, i3, i2, (Object) t, com_google_android_gms_internal_ads_zzbae);
                        }
                    } else if (i3 == 2) {
                        i3 = zza(t, bArr, i4, i2, zzcw, i6, j, com_google_android_gms_internal_ads_zzbae);
                        if (i3 == i4) {
                            i3 = zza(i5, bArr, i3, i2, (Object) t, com_google_android_gms_internal_ads_zzbae);
                        }
                    }
                }
                i3 = i4;
                i3 = zza(i5, bArr, i3, i2, (Object) t, com_google_android_gms_internal_ads_zzbae);
            }
            if (i3 != i2) {
                throw zzbbu.zzadr();
            }
            return;
        }
        zza((Object) t, bArr, i, i2, 0, com_google_android_gms_internal_ads_zzbae);
    }

    public final boolean zzaa(T t) {
        if (this.zzdwq == null || this.zzdwq.length == 0) {
            return true;
        }
        int i = -1;
        int i2 = 0;
        int[] iArr = this.zzdwq;
        int length = iArr.length;
        int i3 = 0;
        while (i3 < length) {
            int i4;
            List list;
            zzbdm zzcq;
            Object obj;
            Map zzt;
            zzbdm com_google_android_gms_internal_ads_zzbdm;
            int i5 = iArr[i3];
            int zzcw = zzcw(i5);
            int zzct = zzct(zzcw);
            int i6 = 0;
            if (!this.zzdwo) {
                i6 = this.zzdwg[zzcw + 2];
                i4 = 1048575 & i6;
                i6 = 1 << (i6 >>> 20);
                if (i4 != i) {
                    i = i4;
                    i4 = zzdwf.getInt(t, (long) i4);
                    i2 = i6;
                    if (((ErrorDialogData.BINDER_CRASH & zzct) == 0 ? 1 : null) == null && !zza((Object) t, zzcw, i4, i2)) {
                        return false;
                    }
                    switch ((267386880 & zzct) >>> 20) {
                        case 9:
                        case 17:
                            if (zza((Object) t, zzcw, i4, i2) && !zza((Object) t, zzct, zzcq(zzcw))) {
                                return false;
                            }
                        case 27:
                        case 49:
                            list = (List) zzbek.zzp(t, (long) (1048575 & zzct));
                            if (!list.isEmpty()) {
                                zzcq = zzcq(zzcw);
                                for (i2 = 0; i2 < list.size(); i2++) {
                                    if (!zzcq.zzaa(list.get(i2))) {
                                        obj = null;
                                        if (obj != null) {
                                            break;
                                        }
                                        return false;
                                    }
                                }
                            }
                            obj = 1;
                            if (obj != null) {
                                return false;
                            }
                        case 50:
                            zzt = this.zzdwx.zzt(zzbek.zzp(t, (long) (1048575 & zzct)));
                            if (!zzt.isEmpty()) {
                                if (this.zzdwx.zzx(zzcr(zzcw)).zzdwa.zzagl() == zzbex.MESSAGE) {
                                    com_google_android_gms_internal_ads_zzbdm = null;
                                    for (Object next : zzt.values()) {
                                        if (com_google_android_gms_internal_ads_zzbdm == null) {
                                            com_google_android_gms_internal_ads_zzbdm = zzbdg.zzaeo().zze(next.getClass());
                                        }
                                        if (!com_google_android_gms_internal_ads_zzbdm.zzaa(next)) {
                                            obj = null;
                                            if (obj != null) {
                                                break;
                                            }
                                            return false;
                                        }
                                    }
                                }
                            }
                            obj = 1;
                            if (obj != null) {
                                return false;
                            }
                        case 60:
                        case 68:
                            if (zza((Object) t, i5, zzcw) && !zza((Object) t, zzct, zzcq(zzcw))) {
                                return false;
                            }
                        default:
                            break;
                    }
                    i3++;
                    i2 = i4;
                }
            }
            i4 = i2;
            i2 = i6;
            if ((ErrorDialogData.BINDER_CRASH & zzct) == 0) {
            }
            if (((ErrorDialogData.BINDER_CRASH & zzct) == 0 ? 1 : null) == null) {
            }
            switch ((267386880 & zzct) >>> 20) {
                case 9:
                case 17:
                    return false;
                case 27:
                case 49:
                    list = (List) zzbek.zzp(t, (long) (1048575 & zzct));
                    if (list.isEmpty()) {
                        zzcq = zzcq(zzcw);
                        for (i2 = 0; i2 < list.size(); i2++) {
                            if (!zzcq.zzaa(list.get(i2))) {
                                obj = null;
                                if (obj != null) {
                                    break;
                                }
                                return false;
                            }
                        }
                    }
                    obj = 1;
                    if (obj != null) {
                        return false;
                    }
                case 50:
                    zzt = this.zzdwx.zzt(zzbek.zzp(t, (long) (1048575 & zzct)));
                    if (zzt.isEmpty()) {
                        if (this.zzdwx.zzx(zzcr(zzcw)).zzdwa.zzagl() == zzbex.MESSAGE) {
                            com_google_android_gms_internal_ads_zzbdm = null;
                            for (Object next2 : zzt.values()) {
                                if (com_google_android_gms_internal_ads_zzbdm == null) {
                                    com_google_android_gms_internal_ads_zzbdm = zzbdg.zzaeo().zze(next2.getClass());
                                }
                                if (com_google_android_gms_internal_ads_zzbdm.zzaa(next2)) {
                                    obj = null;
                                    if (obj != null) {
                                        break;
                                    }
                                    return false;
                                }
                            }
                        }
                    }
                    obj = 1;
                    if (obj != null) {
                        return false;
                    }
                case 60:
                case 68:
                    return false;
                default:
                    break;
            }
            i3++;
            i2 = i4;
        }
        return !this.zzdwm || this.zzdww.zzm(t).isInitialized();
    }

    public final void zzc(T t, T t2) {
        if (t2 == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < this.zzdwg.length; i += 4) {
            int zzct = zzct(i);
            long j = (long) (1048575 & zzct);
            int i2 = this.zzdwg[i];
            switch ((zzct & 267386880) >>> 20) {
                case 0:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzo(t2, j));
                    zzb((Object) t, i);
                    break;
                case 1:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzn(t2, j));
                    zzb((Object) t, i);
                    break;
                case 2:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                    zzb((Object) t, i);
                    break;
                case 3:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                    zzb((Object) t, i);
                    break;
                case 4:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 5:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                    zzb((Object) t, i);
                    break;
                case 6:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 7:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzm(t2, j));
                    zzb((Object) t, i);
                    break;
                case 8:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzp(t2, j));
                    zzb((Object) t, i);
                    break;
                case 9:
                    zza((Object) t, (Object) t2, i);
                    break;
                case 10:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzp(t2, j));
                    zzb((Object) t, i);
                    break;
                case 11:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 12:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 13:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 14:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                    zzb((Object) t, i);
                    break;
                case 15:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zzb((Object) t, j, zzbek.zzk(t2, j));
                    zzb((Object) t, i);
                    break;
                case 16:
                    if (!zza((Object) t2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzl(t2, j));
                    zzb((Object) t, i);
                    break;
                case 17:
                    zza((Object) t, (Object) t2, i);
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    this.zzdwu.zza(t, t2, j);
                    break;
                case 50:
                    zzbdo.zza(this.zzdwx, (Object) t, (Object) t2, j);
                    break;
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                    if (!zza((Object) t2, i2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzp(t2, j));
                    zzb((Object) t, i2, i);
                    break;
                case 60:
                    zzb((Object) t, (Object) t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (!zza((Object) t2, i2, i)) {
                        break;
                    }
                    zzbek.zza((Object) t, j, zzbek.zzp(t2, j));
                    zzb((Object) t, i2, i);
                    break;
                case 68:
                    zzb((Object) t, (Object) t2, i);
                    break;
                default:
                    break;
            }
        }
        if (!this.zzdwo) {
            zzbdo.zza(this.zzdwv, (Object) t, (Object) t2);
            if (this.zzdwm) {
                zzbdo.zza(this.zzdww, (Object) t, (Object) t2);
            }
        }
    }

    public final void zzo(T t) {
        int i = 0;
        if (this.zzdwr != null) {
            for (int zzct : this.zzdwr) {
                long zzct2 = (long) (zzct(zzct) & 1048575);
                Object zzp = zzbek.zzp(t, zzct2);
                if (zzp != null) {
                    zzbek.zza((Object) t, zzct2, this.zzdwx.zzv(zzp));
                }
            }
        }
        if (this.zzdws != null) {
            int[] iArr = this.zzdws;
            int length = iArr.length;
            while (i < length) {
                this.zzdwu.zzb(t, (long) iArr[i]);
                i++;
            }
        }
        this.zzdwv.zzo(t);
        if (this.zzdwm) {
            this.zzdww.zzo(t);
        }
    }

    public final int zzy(T t) {
        int i;
        int i2;
        int zzct;
        int i3;
        int i4;
        int i5;
        Object zzp;
        if (this.zzdwo) {
            Unsafe unsafe = zzdwf;
            i = 0;
            for (i2 = 0; i2 < this.zzdwg.length; i2 += 4) {
                zzct = zzct(i2);
                i3 = (267386880 & zzct) >>> 20;
                i4 = this.zzdwg[i2];
                long j = (long) (zzct & 1048575);
                i5 = (i3 < zzbbj.DOUBLE_LIST_PACKED.id() || i3 > zzbbj.SINT64_LIST_PACKED.id()) ? 0 : this.zzdwg[i2 + 2] & 1048575;
                switch (i3) {
                    case 0:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzb(i4, 0.0d);
                        break;
                    case 1:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzb(i4, 0.0f);
                        break;
                    case 2:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzd(i4, zzbek.zzl(t, j));
                        break;
                    case 3:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zze(i4, zzbek.zzl(t, j));
                        break;
                    case 4:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzq(i4, zzbek.zzk(t, j));
                        break;
                    case 5:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzg(i4, 0);
                        break;
                    case 6:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzt(i4, 0);
                        break;
                    case 7:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzg(i4, true);
                        break;
                    case 8:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        zzp = zzbek.zzp(t, j);
                        if (!(zzp instanceof zzbah)) {
                            i += zzbav.zzg(i4, (String) zzp);
                            break;
                        }
                        i += zzbav.zzc(i4, (zzbah) zzp);
                        break;
                    case 9:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbdo.zzc(i4, zzbek.zzp(t, j), zzcq(i2));
                        break;
                    case 10:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzc(i4, (zzbah) zzbek.zzp(t, j));
                        break;
                    case 11:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzr(i4, zzbek.zzk(t, j));
                        break;
                    case 12:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzv(i4, zzbek.zzk(t, j));
                        break;
                    case 13:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzu(i4, 0);
                        break;
                    case 14:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzh(i4, 0);
                        break;
                    case 15:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzs(i4, zzbek.zzk(t, j));
                        break;
                    case 16:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzf(i4, zzbek.zzl(t, j));
                        break;
                    case 17:
                        if (!zza((Object) t, i2)) {
                            break;
                        }
                        i += zzbav.zzc(i4, (zzbcu) zzbek.zzp(t, j), zzcq(i2));
                        break;
                    case 18:
                        i += zzbdo.zzw(i4, zze(t, j), false);
                        break;
                    case 19:
                        i += zzbdo.zzv(i4, zze(t, j), false);
                        break;
                    case 20:
                        i += zzbdo.zzo(i4, zze(t, j), false);
                        break;
                    case 21:
                        i += zzbdo.zzp(i4, zze(t, j), false);
                        break;
                    case 22:
                        i += zzbdo.zzs(i4, zze(t, j), false);
                        break;
                    case 23:
                        i += zzbdo.zzw(i4, zze(t, j), false);
                        break;
                    case 24:
                        i += zzbdo.zzv(i4, zze(t, j), false);
                        break;
                    case 25:
                        i += zzbdo.zzx(i4, zze(t, j), false);
                        break;
                    case 26:
                        i += zzbdo.zzc(i4, zze(t, j));
                        break;
                    case 27:
                        i += zzbdo.zzc(i4, zze(t, j), zzcq(i2));
                        break;
                    case 28:
                        i += zzbdo.zzd(i4, zze(t, j));
                        break;
                    case 29:
                        i += zzbdo.zzt(i4, zze(t, j), false);
                        break;
                    case 30:
                        i += zzbdo.zzr(i4, zze(t, j), false);
                        break;
                    case 31:
                        i += zzbdo.zzv(i4, zze(t, j), false);
                        break;
                    case 32:
                        i += zzbdo.zzw(i4, zze(t, j), false);
                        break;
                    case 33:
                        i += zzbdo.zzu(i4, zze(t, j), false);
                        break;
                    case 34:
                        i += zzbdo.zzq(i4, zze(t, j), false);
                        break;
                    case 35:
                        zzct = zzbdo.zzan((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 36:
                        zzct = zzbdo.zzam((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 37:
                        zzct = zzbdo.zzaf((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 38:
                        zzct = zzbdo.zzag((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 39:
                        zzct = zzbdo.zzaj((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 40:
                        zzct = zzbdo.zzan((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 41:
                        zzct = zzbdo.zzam((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 42:
                        zzct = zzbdo.zzao((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 43:
                        zzct = zzbdo.zzak((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 44:
                        zzct = zzbdo.zzai((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 45:
                        zzct = zzbdo.zzam((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 46:
                        zzct = zzbdo.zzan((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 47:
                        zzct = zzbdo.zzal((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 48:
                        zzct = zzbdo.zzah((List) unsafe.getObject(t, j));
                        if (zzct > 0) {
                            if (this.zzdwp) {
                                unsafe.putInt(t, (long) i5, zzct);
                            }
                            i += zzct + (zzbav.zzcd(i4) + zzbav.zzcf(zzct));
                            break;
                        }
                        break;
                    case 49:
                        i += zzbdo.zzd(i4, zze(t, j), zzcq(i2));
                        break;
                    case 50:
                        i += this.zzdwx.zzb(i4, zzbek.zzp(t, j), zzcr(i2));
                        break;
                    case 51:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzb(i4, 0.0d);
                        break;
                    case 52:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzb(i4, 0.0f);
                        break;
                    case 53:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzd(i4, zzi(t, j));
                        break;
                    case 54:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zze(i4, zzi(t, j));
                        break;
                    case 55:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzq(i4, zzh(t, j));
                        break;
                    case 56:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzg(i4, 0);
                        break;
                    case 57:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzt(i4, 0);
                        break;
                    case 58:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzg(i4, true);
                        break;
                    case 59:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        zzp = zzbek.zzp(t, j);
                        if (!(zzp instanceof zzbah)) {
                            i += zzbav.zzg(i4, (String) zzp);
                            break;
                        }
                        i += zzbav.zzc(i4, (zzbah) zzp);
                        break;
                    case 60:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbdo.zzc(i4, zzbek.zzp(t, j), zzcq(i2));
                        break;
                    case 61:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzc(i4, (zzbah) zzbek.zzp(t, j));
                        break;
                    case 62:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzr(i4, zzh(t, j));
                        break;
                    case 63:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzv(i4, zzh(t, j));
                        break;
                    case 64:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzu(i4, 0);
                        break;
                    case 65:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzh(i4, 0);
                        break;
                    case 66:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzs(i4, zzh(t, j));
                        break;
                    case 67:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzf(i4, zzi(t, j));
                        break;
                    case 68:
                        if (!zza((Object) t, i4, i2)) {
                            break;
                        }
                        i += zzbav.zzc(i4, (zzbcu) zzbek.zzp(t, j), zzcq(i2));
                        break;
                    default:
                        break;
                }
            }
            return zza(this.zzdwv, (Object) t) + i;
        }
        Unsafe unsafe2 = zzdwf;
        i = -1;
        int i6 = 0;
        i2 = 0;
        zzct = 0;
        while (i2 < this.zzdwg.length) {
            int zzct2 = zzct(i2);
            int i7 = this.zzdwg[i2];
            int i8 = (267386880 & zzct2) >>> 20;
            i3 = 0;
            if (i8 <= 17) {
                i4 = this.zzdwg[i2 + 2];
                i5 = 1048575 & i4;
                i3 = 1 << (i4 >>> 20);
                if (i5 != i) {
                    zzct = unsafe2.getInt(t, (long) i5);
                    i = i5;
                }
                i5 = i;
                i = zzct;
                zzct = i3;
                i3 = i4;
            } else if (!this.zzdwp || i8 < zzbbj.DOUBLE_LIST_PACKED.id() || i8 > zzbbj.SINT64_LIST_PACKED.id()) {
                i5 = i;
                i = zzct;
                zzct = 0;
            } else {
                i3 = this.zzdwg[i2 + 2] & 1048575;
                i5 = i;
                i = zzct;
                zzct = 0;
            }
            long j2 = (long) (1048575 & zzct2);
            switch (i8) {
                case 0:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzb(i7, 0.0d);
                    break;
                case 1:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzb(i7, 0.0f);
                    break;
                case 2:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzd(i7, unsafe2.getLong(t, j2));
                    break;
                case 3:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zze(i7, unsafe2.getLong(t, j2));
                    break;
                case 4:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzq(i7, unsafe2.getInt(t, j2));
                    break;
                case 5:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzg(i7, 0);
                    break;
                case 6:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzt(i7, 0);
                    break;
                case 7:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzg(i7, true);
                    break;
                case 8:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    zzp = unsafe2.getObject(t, j2);
                    if (!(zzp instanceof zzbah)) {
                        i6 += zzbav.zzg(i7, (String) zzp);
                        break;
                    }
                    i6 += zzbav.zzc(i7, (zzbah) zzp);
                    break;
                case 9:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbdo.zzc(i7, unsafe2.getObject(t, j2), zzcq(i2));
                    break;
                case 10:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzc(i7, (zzbah) unsafe2.getObject(t, j2));
                    break;
                case 11:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzr(i7, unsafe2.getInt(t, j2));
                    break;
                case 12:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzv(i7, unsafe2.getInt(t, j2));
                    break;
                case 13:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzu(i7, 0);
                    break;
                case 14:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzh(i7, 0);
                    break;
                case 15:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzs(i7, unsafe2.getInt(t, j2));
                    break;
                case 16:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzf(i7, unsafe2.getLong(t, j2));
                    break;
                case 17:
                    if ((zzct & i) == 0) {
                        break;
                    }
                    i6 += zzbav.zzc(i7, (zzbcu) unsafe2.getObject(t, j2), zzcq(i2));
                    break;
                case 18:
                    i6 += zzbdo.zzw(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 19:
                    i6 += zzbdo.zzv(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 20:
                    i6 += zzbdo.zzo(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 21:
                    i6 += zzbdo.zzp(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 22:
                    i6 += zzbdo.zzs(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 23:
                    i6 += zzbdo.zzw(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 24:
                    i6 += zzbdo.zzv(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 25:
                    i6 += zzbdo.zzx(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 26:
                    i6 += zzbdo.zzc(i7, (List) unsafe2.getObject(t, j2));
                    break;
                case 27:
                    i6 += zzbdo.zzc(i7, (List) unsafe2.getObject(t, j2), zzcq(i2));
                    break;
                case 28:
                    i6 += zzbdo.zzd(i7, (List) unsafe2.getObject(t, j2));
                    break;
                case 29:
                    i6 += zzbdo.zzt(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 30:
                    i6 += zzbdo.zzr(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 31:
                    i6 += zzbdo.zzv(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 32:
                    i6 += zzbdo.zzw(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 33:
                    i6 += zzbdo.zzu(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 34:
                    i6 += zzbdo.zzq(i7, (List) unsafe2.getObject(t, j2), false);
                    break;
                case 35:
                    zzct = zzbdo.zzan((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 36:
                    zzct = zzbdo.zzam((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 37:
                    zzct = zzbdo.zzaf((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 38:
                    zzct = zzbdo.zzag((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 39:
                    zzct = zzbdo.zzaj((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 40:
                    zzct = zzbdo.zzan((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 41:
                    zzct = zzbdo.zzam((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 42:
                    zzct = zzbdo.zzao((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 43:
                    zzct = zzbdo.zzak((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 44:
                    zzct = zzbdo.zzai((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 45:
                    zzct = zzbdo.zzam((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 46:
                    zzct = zzbdo.zzan((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 47:
                    zzct = zzbdo.zzal((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 48:
                    zzct = zzbdo.zzah((List) unsafe2.getObject(t, j2));
                    if (zzct > 0) {
                        if (this.zzdwp) {
                            unsafe2.putInt(t, (long) i3, zzct);
                        }
                        i6 += zzct + (zzbav.zzcd(i7) + zzbav.zzcf(zzct));
                        break;
                    }
                    break;
                case 49:
                    i6 += zzbdo.zzd(i7, (List) unsafe2.getObject(t, j2), zzcq(i2));
                    break;
                case 50:
                    i6 += this.zzdwx.zzb(i7, unsafe2.getObject(t, j2), zzcr(i2));
                    break;
                case 51:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzb(i7, 0.0d);
                    break;
                case 52:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzb(i7, 0.0f);
                    break;
                case 53:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzd(i7, zzi(t, j2));
                    break;
                case 54:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zze(i7, zzi(t, j2));
                    break;
                case 55:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzq(i7, zzh(t, j2));
                    break;
                case 56:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzg(i7, 0);
                    break;
                case 57:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzt(i7, 0);
                    break;
                case 58:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzg(i7, true);
                    break;
                case 59:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    zzp = unsafe2.getObject(t, j2);
                    if (!(zzp instanceof zzbah)) {
                        i6 += zzbav.zzg(i7, (String) zzp);
                        break;
                    }
                    i6 += zzbav.zzc(i7, (zzbah) zzp);
                    break;
                case 60:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbdo.zzc(i7, unsafe2.getObject(t, j2), zzcq(i2));
                    break;
                case 61:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzc(i7, (zzbah) unsafe2.getObject(t, j2));
                    break;
                case 62:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzr(i7, zzh(t, j2));
                    break;
                case 63:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzv(i7, zzh(t, j2));
                    break;
                case 64:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzu(i7, 0);
                    break;
                case 65:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzh(i7, 0);
                    break;
                case 66:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzs(i7, zzh(t, j2));
                    break;
                case 67:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzf(i7, zzi(t, j2));
                    break;
                case 68:
                    if (!zza((Object) t, i7, i2)) {
                        break;
                    }
                    i6 += zzbav.zzc(i7, (zzbcu) unsafe2.getObject(t, j2), zzcq(i2));
                    break;
                default:
                    break;
            }
            i2 += 4;
            zzct = i;
            i = i5;
        }
        zzct = zza(this.zzdwv, (Object) t) + i6;
        return this.zzdwm ? zzct + this.zzdww.zzm(t).zzacw() : zzct;
    }
}
