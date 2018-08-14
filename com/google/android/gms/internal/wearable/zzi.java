package com.google.android.gms.internal.wearable;

import java.io.IOException;

public final class zzi extends zzn<zzi> {
    private static volatile zzi[] zzgb;
    public int type;
    public zzj zzgc;

    public zzi() {
        this.type = 1;
        this.zzgc = null;
        this.zzhc = null;
        this.zzhl = -1;
    }

    private final zzi zzb(zzk com_google_android_gms_internal_wearable_zzk) throws IOException {
        while (true) {
            int zzj = com_google_android_gms_internal_wearable_zzk.zzj();
            switch (zzj) {
                case 0:
                    break;
                case 8:
                    int position = com_google_android_gms_internal_wearable_zzk.getPosition();
                    try {
                        int zzk = com_google_android_gms_internal_wearable_zzk.zzk();
                        if (zzk <= 0 || zzk > 15) {
                            throw new IllegalArgumentException(zzk + " is not a valid enum Type");
                        }
                        this.type = zzk;
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_wearable_zzk.zzg(position);
                        zza(com_google_android_gms_internal_wearable_zzk, zzj);
                        break;
                    }
                case 18:
                    if (this.zzgc == null) {
                        this.zzgc = new zzj();
                    }
                    com_google_android_gms_internal_wearable_zzk.zza(this.zzgc);
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

    public static zzi[] zzi() {
        if (zzgb == null) {
            synchronized (zzr.zzhk) {
                if (zzgb == null) {
                    zzgb = new zzi[0];
                }
            }
        }
        return zzgb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi com_google_android_gms_internal_wearable_zzi = (zzi) obj;
        if (this.type != com_google_android_gms_internal_wearable_zzi.type) {
            return false;
        }
        if (this.zzgc == null) {
            if (com_google_android_gms_internal_wearable_zzi.zzgc != null) {
                return false;
            }
        } else if (!this.zzgc.equals(com_google_android_gms_internal_wearable_zzi.zzgc)) {
            return false;
        }
        return (this.zzhc == null || this.zzhc.isEmpty()) ? com_google_android_gms_internal_wearable_zzi.zzhc == null || com_google_android_gms_internal_wearable_zzi.zzhc.isEmpty() : this.zzhc.equals(com_google_android_gms_internal_wearable_zzi.zzhc);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + this.type;
        zzj com_google_android_gms_internal_wearable_zzj = this.zzgc;
        hashCode = ((com_google_android_gms_internal_wearable_zzj == null ? 0 : com_google_android_gms_internal_wearable_zzj.hashCode()) + (hashCode * 31)) * 31;
        if (!(this.zzhc == null || this.zzhc.isEmpty())) {
            i = this.zzhc.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzt zza(zzk com_google_android_gms_internal_wearable_zzk) throws IOException {
        return zzb(com_google_android_gms_internal_wearable_zzk);
    }

    public final void zza(zzl com_google_android_gms_internal_wearable_zzl) throws IOException {
        com_google_android_gms_internal_wearable_zzl.zzd(1, this.type);
        if (this.zzgc != null) {
            com_google_android_gms_internal_wearable_zzl.zza(2, this.zzgc);
        }
        super.zza(com_google_android_gms_internal_wearable_zzl);
    }

    protected final int zzg() {
        int zzg = super.zzg() + zzl.zze(1, this.type);
        return this.zzgc != null ? zzg + zzl.zzb(2, this.zzgc) : zzg;
    }
}
