package com.google.android.gms.internal.wearable;

import java.io.IOException;
import java.util.Arrays;

public final class zzj extends zzn<zzj> {
    public byte[] zzgd;
    public String zzge;
    public double zzgf;
    public float zzgg;
    public long zzgh;
    public int zzgi;
    public int zzgj;
    public boolean zzgk;
    public zzh[] zzgl;
    public zzi[] zzgm;
    public String[] zzgn;
    public long[] zzgo;
    public float[] zzgp;
    public long zzgq;

    public zzj() {
        this.zzgd = zzw.zzhy;
        this.zzge = "";
        this.zzgf = 0.0d;
        this.zzgg = 0.0f;
        this.zzgh = 0;
        this.zzgi = 0;
        this.zzgj = 0;
        this.zzgk = false;
        this.zzgl = zzh.zzh();
        this.zzgm = zzi.zzi();
        this.zzgn = zzw.zzhw;
        this.zzgo = zzw.zzhs;
        this.zzgp = zzw.zzht;
        this.zzgq = 0;
        this.zzhc = null;
        this.zzhl = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzj)) {
            return false;
        }
        zzj com_google_android_gms_internal_wearable_zzj = (zzj) obj;
        if (!Arrays.equals(this.zzgd, com_google_android_gms_internal_wearable_zzj.zzgd)) {
            return false;
        }
        if (this.zzge == null) {
            if (com_google_android_gms_internal_wearable_zzj.zzge != null) {
                return false;
            }
        } else if (!this.zzge.equals(com_google_android_gms_internal_wearable_zzj.zzge)) {
            return false;
        }
        return Double.doubleToLongBits(this.zzgf) != Double.doubleToLongBits(com_google_android_gms_internal_wearable_zzj.zzgf) ? false : Float.floatToIntBits(this.zzgg) != Float.floatToIntBits(com_google_android_gms_internal_wearable_zzj.zzgg) ? false : this.zzgh != com_google_android_gms_internal_wearable_zzj.zzgh ? false : this.zzgi != com_google_android_gms_internal_wearable_zzj.zzgi ? false : this.zzgj != com_google_android_gms_internal_wearable_zzj.zzgj ? false : this.zzgk != com_google_android_gms_internal_wearable_zzj.zzgk ? false : !zzr.equals(this.zzgl, com_google_android_gms_internal_wearable_zzj.zzgl) ? false : !zzr.equals(this.zzgm, com_google_android_gms_internal_wearable_zzj.zzgm) ? false : !zzr.equals(this.zzgn, com_google_android_gms_internal_wearable_zzj.zzgn) ? false : !zzr.equals(this.zzgo, com_google_android_gms_internal_wearable_zzj.zzgo) ? false : !zzr.equals(this.zzgp, com_google_android_gms_internal_wearable_zzj.zzgp) ? false : this.zzgq != com_google_android_gms_internal_wearable_zzj.zzgq ? false : (this.zzhc == null || this.zzhc.isEmpty()) ? com_google_android_gms_internal_wearable_zzj.zzhc == null || com_google_android_gms_internal_wearable_zzj.zzhc.isEmpty() : this.zzhc.equals(com_google_android_gms_internal_wearable_zzj.zzhc);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.zzge == null ? 0 : this.zzge.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zzgd)) * 31);
        long doubleToLongBits = Double.doubleToLongBits(this.zzgf);
        hashCode = ((((((((((((((this.zzgk ? 1231 : 1237) + (((((((((((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + Float.floatToIntBits(this.zzgg)) * 31) + ((int) (this.zzgh ^ (this.zzgh >>> 32)))) * 31) + this.zzgi) * 31) + this.zzgj) * 31)) * 31) + zzr.hashCode(this.zzgl)) * 31) + zzr.hashCode(this.zzgm)) * 31) + zzr.hashCode(this.zzgn)) * 31) + zzr.hashCode(this.zzgo)) * 31) + zzr.hashCode(this.zzgp)) * 31) + ((int) (this.zzgq ^ (this.zzgq >>> 32)))) * 31;
        if (!(this.zzhc == null || this.zzhc.isEmpty())) {
            i = this.zzhc.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzt zza(zzk com_google_android_gms_internal_wearable_zzk) throws IOException {
        while (true) {
            int zzj = com_google_android_gms_internal_wearable_zzk.zzj();
            int zzb;
            Object obj;
            int zze;
            switch (zzj) {
                case 0:
                    break;
                case 10:
                    this.zzgd = com_google_android_gms_internal_wearable_zzk.readBytes();
                    continue;
                case 18:
                    this.zzge = com_google_android_gms_internal_wearable_zzk.readString();
                    continue;
                case 25:
                    this.zzgf = Double.longBitsToDouble(com_google_android_gms_internal_wearable_zzk.zzn());
                    continue;
                case 37:
                    this.zzgg = Float.intBitsToFloat(com_google_android_gms_internal_wearable_zzk.zzm());
                    continue;
                case 40:
                    this.zzgh = com_google_android_gms_internal_wearable_zzk.zzl();
                    continue;
                case 48:
                    this.zzgi = com_google_android_gms_internal_wearable_zzk.zzk();
                    continue;
                case 56:
                    zzj = com_google_android_gms_internal_wearable_zzk.zzk();
                    this.zzgj = (-(zzj & 1)) ^ (zzj >>> 1);
                    continue;
                case 64:
                    this.zzgk = com_google_android_gms_internal_wearable_zzk.zzk() != 0;
                    continue;
                case 74:
                    zzb = zzw.zzb(com_google_android_gms_internal_wearable_zzk, 74);
                    zzj = this.zzgl == null ? 0 : this.zzgl.length;
                    obj = new zzh[(zzb + zzj)];
                    if (zzj != 0) {
                        System.arraycopy(this.zzgl, 0, obj, 0, zzj);
                    }
                    while (zzj < obj.length - 1) {
                        obj[zzj] = new zzh();
                        com_google_android_gms_internal_wearable_zzk.zza(obj[zzj]);
                        com_google_android_gms_internal_wearable_zzk.zzj();
                        zzj++;
                    }
                    obj[zzj] = new zzh();
                    com_google_android_gms_internal_wearable_zzk.zza(obj[zzj]);
                    this.zzgl = obj;
                    continue;
                case 82:
                    zzb = zzw.zzb(com_google_android_gms_internal_wearable_zzk, 82);
                    zzj = this.zzgm == null ? 0 : this.zzgm.length;
                    obj = new zzi[(zzb + zzj)];
                    if (zzj != 0) {
                        System.arraycopy(this.zzgm, 0, obj, 0, zzj);
                    }
                    while (zzj < obj.length - 1) {
                        obj[zzj] = new zzi();
                        com_google_android_gms_internal_wearable_zzk.zza(obj[zzj]);
                        com_google_android_gms_internal_wearable_zzk.zzj();
                        zzj++;
                    }
                    obj[zzj] = new zzi();
                    com_google_android_gms_internal_wearable_zzk.zza(obj[zzj]);
                    this.zzgm = obj;
                    continue;
                case 90:
                    zzb = zzw.zzb(com_google_android_gms_internal_wearable_zzk, 90);
                    zzj = this.zzgn == null ? 0 : this.zzgn.length;
                    obj = new String[(zzb + zzj)];
                    if (zzj != 0) {
                        System.arraycopy(this.zzgn, 0, obj, 0, zzj);
                    }
                    while (zzj < obj.length - 1) {
                        obj[zzj] = com_google_android_gms_internal_wearable_zzk.readString();
                        com_google_android_gms_internal_wearable_zzk.zzj();
                        zzj++;
                    }
                    obj[zzj] = com_google_android_gms_internal_wearable_zzk.readString();
                    this.zzgn = obj;
                    continue;
                case 96:
                    zzb = zzw.zzb(com_google_android_gms_internal_wearable_zzk, 96);
                    zzj = this.zzgo == null ? 0 : this.zzgo.length;
                    obj = new long[(zzb + zzj)];
                    if (zzj != 0) {
                        System.arraycopy(this.zzgo, 0, obj, 0, zzj);
                    }
                    while (zzj < obj.length - 1) {
                        obj[zzj] = com_google_android_gms_internal_wearable_zzk.zzl();
                        com_google_android_gms_internal_wearable_zzk.zzj();
                        zzj++;
                    }
                    obj[zzj] = com_google_android_gms_internal_wearable_zzk.zzl();
                    this.zzgo = obj;
                    continue;
                case 98:
                    zze = com_google_android_gms_internal_wearable_zzk.zze(com_google_android_gms_internal_wearable_zzk.zzk());
                    zzb = com_google_android_gms_internal_wearable_zzk.getPosition();
                    zzj = 0;
                    while (com_google_android_gms_internal_wearable_zzk.zzp() > 0) {
                        com_google_android_gms_internal_wearable_zzk.zzl();
                        zzj++;
                    }
                    com_google_android_gms_internal_wearable_zzk.zzg(zzb);
                    zzb = this.zzgo == null ? 0 : this.zzgo.length;
                    Object obj2 = new long[(zzj + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzgo, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_wearable_zzk.zzl();
                        zzb++;
                    }
                    this.zzgo = obj2;
                    com_google_android_gms_internal_wearable_zzk.zzf(zze);
                    continue;
                case 104:
                    this.zzgq = com_google_android_gms_internal_wearable_zzk.zzl();
                    continue;
                case 114:
                    zzj = com_google_android_gms_internal_wearable_zzk.zzk();
                    zzb = com_google_android_gms_internal_wearable_zzk.zze(zzj);
                    zze = zzj / 4;
                    zzj = this.zzgp == null ? 0 : this.zzgp.length;
                    Object obj3 = new float[(zze + zzj)];
                    if (zzj != 0) {
                        System.arraycopy(this.zzgp, 0, obj3, 0, zzj);
                    }
                    while (zzj < obj3.length) {
                        obj3[zzj] = Float.intBitsToFloat(com_google_android_gms_internal_wearable_zzk.zzm());
                        zzj++;
                    }
                    this.zzgp = obj3;
                    com_google_android_gms_internal_wearable_zzk.zzf(zzb);
                    continue;
                case 117:
                    zzb = zzw.zzb(com_google_android_gms_internal_wearable_zzk, 117);
                    zzj = this.zzgp == null ? 0 : this.zzgp.length;
                    obj = new float[(zzb + zzj)];
                    if (zzj != 0) {
                        System.arraycopy(this.zzgp, 0, obj, 0, zzj);
                    }
                    while (zzj < obj.length - 1) {
                        obj[zzj] = Float.intBitsToFloat(com_google_android_gms_internal_wearable_zzk.zzm());
                        com_google_android_gms_internal_wearable_zzk.zzj();
                        zzj++;
                    }
                    obj[zzj] = Float.intBitsToFloat(com_google_android_gms_internal_wearable_zzk.zzm());
                    this.zzgp = obj;
                    continue;
                default:
                    if (!super.zza(com_google_android_gms_internal_wearable_zzk, zzj)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzl com_google_android_gms_internal_wearable_zzl) throws IOException {
        int i = 1;
        int i2 = 0;
        if (!Arrays.equals(this.zzgd, zzw.zzhy)) {
            byte[] bArr = this.zzgd;
            com_google_android_gms_internal_wearable_zzl.zzf(1, 2);
            com_google_android_gms_internal_wearable_zzl.zzl(bArr.length);
            com_google_android_gms_internal_wearable_zzl.zzc(bArr);
        }
        if (!(this.zzge == null || this.zzge.equals(""))) {
            com_google_android_gms_internal_wearable_zzl.zza(2, this.zzge);
        }
        if (Double.doubleToLongBits(this.zzgf) != Double.doubleToLongBits(0.0d)) {
            double d = this.zzgf;
            com_google_android_gms_internal_wearable_zzl.zzf(3, 1);
            com_google_android_gms_internal_wearable_zzl.zzb(Double.doubleToLongBits(d));
        }
        if (Float.floatToIntBits(this.zzgg) != Float.floatToIntBits(0.0f)) {
            com_google_android_gms_internal_wearable_zzl.zza(4, this.zzgg);
        }
        if (this.zzgh != 0) {
            com_google_android_gms_internal_wearable_zzl.zza(5, this.zzgh);
        }
        if (this.zzgi != 0) {
            com_google_android_gms_internal_wearable_zzl.zzd(6, this.zzgi);
        }
        if (this.zzgj != 0) {
            int i3 = this.zzgj;
            com_google_android_gms_internal_wearable_zzl.zzf(7, 0);
            com_google_android_gms_internal_wearable_zzl.zzl(zzl.zzn(i3));
        }
        if (this.zzgk) {
            boolean z = this.zzgk;
            com_google_android_gms_internal_wearable_zzl.zzf(8, 0);
            if (!z) {
                i = 0;
            }
            com_google_android_gms_internal_wearable_zzl.zza((byte) i);
        }
        if (this.zzgl != null && this.zzgl.length > 0) {
            for (zzt com_google_android_gms_internal_wearable_zzt : this.zzgl) {
                if (com_google_android_gms_internal_wearable_zzt != null) {
                    com_google_android_gms_internal_wearable_zzl.zza(9, com_google_android_gms_internal_wearable_zzt);
                }
            }
        }
        if (this.zzgm != null && this.zzgm.length > 0) {
            for (zzt com_google_android_gms_internal_wearable_zzt2 : this.zzgm) {
                if (com_google_android_gms_internal_wearable_zzt2 != null) {
                    com_google_android_gms_internal_wearable_zzl.zza(10, com_google_android_gms_internal_wearable_zzt2);
                }
            }
        }
        if (this.zzgn != null && this.zzgn.length > 0) {
            for (String str : this.zzgn) {
                if (str != null) {
                    com_google_android_gms_internal_wearable_zzl.zza(11, str);
                }
            }
        }
        if (this.zzgo != null && this.zzgo.length > 0) {
            for (long zza : this.zzgo) {
                com_google_android_gms_internal_wearable_zzl.zza(12, zza);
            }
        }
        if (this.zzgq != 0) {
            com_google_android_gms_internal_wearable_zzl.zza(13, this.zzgq);
        }
        if (this.zzgp != null && this.zzgp.length > 0) {
            while (i2 < this.zzgp.length) {
                com_google_android_gms_internal_wearable_zzl.zza(14, this.zzgp[i2]);
                i2++;
            }
        }
        super.zza(com_google_android_gms_internal_wearable_zzl);
    }

    protected final int zzg() {
        int i;
        int i2;
        int i3 = 0;
        int zzg = super.zzg();
        if (!Arrays.equals(this.zzgd, zzw.zzhy)) {
            byte[] bArr = this.zzgd;
            zzg += (bArr.length + zzl.zzm(bArr.length)) + zzl.zzk(1);
        }
        if (!(this.zzge == null || this.zzge.equals(""))) {
            zzg += zzl.zzb(2, this.zzge);
        }
        if (Double.doubleToLongBits(this.zzgf) != Double.doubleToLongBits(0.0d)) {
            zzg += zzl.zzk(3) + 8;
        }
        if (Float.floatToIntBits(this.zzgg) != Float.floatToIntBits(0.0f)) {
            zzg += zzl.zzk(4) + 4;
        }
        if (this.zzgh != 0) {
            zzg += zzl.zzb(5, this.zzgh);
        }
        if (this.zzgi != 0) {
            zzg += zzl.zze(6, this.zzgi);
        }
        if (this.zzgj != 0) {
            zzg += zzl.zzm(zzl.zzn(this.zzgj)) + zzl.zzk(7);
        }
        if (this.zzgk) {
            zzg += zzl.zzk(8) + 1;
        }
        if (this.zzgl != null && this.zzgl.length > 0) {
            i = zzg;
            for (zzt com_google_android_gms_internal_wearable_zzt : this.zzgl) {
                if (com_google_android_gms_internal_wearable_zzt != null) {
                    i += zzl.zzb(9, com_google_android_gms_internal_wearable_zzt);
                }
            }
            zzg = i;
        }
        if (this.zzgm != null && this.zzgm.length > 0) {
            i = zzg;
            for (zzt com_google_android_gms_internal_wearable_zzt2 : this.zzgm) {
                if (com_google_android_gms_internal_wearable_zzt2 != null) {
                    i += zzl.zzb(10, com_google_android_gms_internal_wearable_zzt2);
                }
            }
            zzg = i;
        }
        if (this.zzgn != null && this.zzgn.length > 0) {
            i2 = 0;
            int i4 = 0;
            for (String str : this.zzgn) {
                if (str != null) {
                    i4++;
                    i2 += zzl.zzg(str);
                }
            }
            zzg = (zzg + i2) + (i4 * 1);
        }
        if (this.zzgo != null && this.zzgo.length > 0) {
            i = 0;
            while (i3 < this.zzgo.length) {
                long j = this.zzgo[i3];
                i2 = (-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (j & Long.MIN_VALUE) == 0 ? 9 : 10;
                i += i2;
                i3++;
            }
            zzg = (zzg + i) + (this.zzgo.length * 1);
        }
        if (this.zzgq != 0) {
            zzg += zzl.zzb(13, this.zzgq);
        }
        return (this.zzgp == null || this.zzgp.length <= 0) ? zzg : (zzg + (this.zzgp.length * 4)) + (this.zzgp.length * 1);
    }
}
