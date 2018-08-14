package com.google.android.gms.internal.wearable;

import java.io.IOException;

public final class zzh extends zzn<zzh> {
    private static volatile zzh[] zzfz;
    public String name;
    public zzi zzga;

    public zzh() {
        this.name = "";
        this.zzga = null;
        this.zzhc = null;
        this.zzhl = -1;
    }

    public static zzh[] zzh() {
        if (zzfz == null) {
            synchronized (zzr.zzhk) {
                if (zzfz == null) {
                    zzfz = new zzh[0];
                }
            }
        }
        return zzfz;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzh)) {
            return false;
        }
        zzh com_google_android_gms_internal_wearable_zzh = (zzh) obj;
        if (this.name == null) {
            if (com_google_android_gms_internal_wearable_zzh.name != null) {
                return false;
            }
        } else if (!this.name.equals(com_google_android_gms_internal_wearable_zzh.name)) {
            return false;
        }
        if (this.zzga == null) {
            if (com_google_android_gms_internal_wearable_zzh.zzga != null) {
                return false;
            }
        } else if (!this.zzga.equals(com_google_android_gms_internal_wearable_zzh.zzga)) {
            return false;
        }
        return (this.zzhc == null || this.zzhc.isEmpty()) ? com_google_android_gms_internal_wearable_zzh.zzhc == null || com_google_android_gms_internal_wearable_zzh.zzhc.isEmpty() : this.zzhc.equals(com_google_android_gms_internal_wearable_zzh.zzhc);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31);
        zzi com_google_android_gms_internal_wearable_zzi = this.zzga;
        hashCode = ((com_google_android_gms_internal_wearable_zzi == null ? 0 : com_google_android_gms_internal_wearable_zzi.hashCode()) + (hashCode * 31)) * 31;
        if (!(this.zzhc == null || this.zzhc.isEmpty())) {
            i = this.zzhc.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzt zza(zzk com_google_android_gms_internal_wearable_zzk) throws IOException {
        while (true) {
            int zzj = com_google_android_gms_internal_wearable_zzk.zzj();
            switch (zzj) {
                case 0:
                    break;
                case 10:
                    this.name = com_google_android_gms_internal_wearable_zzk.readString();
                    continue;
                case 18:
                    if (this.zzga == null) {
                        this.zzga = new zzi();
                    }
                    com_google_android_gms_internal_wearable_zzk.zza(this.zzga);
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
        com_google_android_gms_internal_wearable_zzl.zza(1, this.name);
        if (this.zzga != null) {
            com_google_android_gms_internal_wearable_zzl.zza(2, this.zzga);
        }
        super.zza(com_google_android_gms_internal_wearable_zzl);
    }

    protected final int zzg() {
        int zzg = super.zzg() + zzl.zzb(1, this.name);
        return this.zzga != null ? zzg + zzl.zzb(2, this.zzga) : zzg;
    }
}
