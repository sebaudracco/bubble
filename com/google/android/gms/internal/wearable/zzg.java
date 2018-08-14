package com.google.android.gms.internal.wearable;

import java.io.IOException;

public final class zzg extends zzn<zzg> {
    public zzh[] zzfy;

    public zzg() {
        this.zzfy = zzh.zzh();
        this.zzhc = null;
        this.zzhl = -1;
    }

    public static zzg zza(byte[] bArr) throws zzs {
        return (zzg) zzt.zza(new zzg(), bArr, 0, bArr.length);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzg)) {
            return false;
        }
        zzg com_google_android_gms_internal_wearable_zzg = (zzg) obj;
        return !zzr.equals(this.zzfy, com_google_android_gms_internal_wearable_zzg.zzfy) ? false : (this.zzhc == null || this.zzhc.isEmpty()) ? com_google_android_gms_internal_wearable_zzg.zzhc == null || com_google_android_gms_internal_wearable_zzg.zzhc.isEmpty() : this.zzhc.equals(com_google_android_gms_internal_wearable_zzg.zzhc);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + zzr.hashCode(this.zzfy)) * 31;
        int hashCode2 = (this.zzhc == null || this.zzhc.isEmpty()) ? 0 : this.zzhc.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzt zza(zzk com_google_android_gms_internal_wearable_zzk) throws IOException {
        while (true) {
            int zzj = com_google_android_gms_internal_wearable_zzk.zzj();
            switch (zzj) {
                case 0:
                    break;
                case 10:
                    int zzb = zzw.zzb(com_google_android_gms_internal_wearable_zzk, 10);
                    zzj = this.zzfy == null ? 0 : this.zzfy.length;
                    Object obj = new zzh[(zzb + zzj)];
                    if (zzj != 0) {
                        System.arraycopy(this.zzfy, 0, obj, 0, zzj);
                    }
                    while (zzj < obj.length - 1) {
                        obj[zzj] = new zzh();
                        com_google_android_gms_internal_wearable_zzk.zza(obj[zzj]);
                        com_google_android_gms_internal_wearable_zzk.zzj();
                        zzj++;
                    }
                    obj[zzj] = new zzh();
                    com_google_android_gms_internal_wearable_zzk.zza(obj[zzj]);
                    this.zzfy = obj;
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
        if (this.zzfy != null && this.zzfy.length > 0) {
            for (zzt com_google_android_gms_internal_wearable_zzt : this.zzfy) {
                if (com_google_android_gms_internal_wearable_zzt != null) {
                    com_google_android_gms_internal_wearable_zzl.zza(1, com_google_android_gms_internal_wearable_zzt);
                }
            }
        }
        super.zza(com_google_android_gms_internal_wearable_zzl);
    }

    protected final int zzg() {
        int zzg = super.zzg();
        if (this.zzfy != null && this.zzfy.length > 0) {
            for (zzt com_google_android_gms_internal_wearable_zzt : this.zzfy) {
                if (com_google_android_gms_internal_wearable_zzt != null) {
                    zzg += zzl.zzb(1, com_google_android_gms_internal_wearable_zzt);
                }
            }
        }
        return zzg;
    }
}
