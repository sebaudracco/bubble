package com.google.android.gms.internal.wearable;

import java.io.IOException;

public abstract class zzn<M extends zzn<M>> extends zzt {
    protected zzp zzhc;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzn com_google_android_gms_internal_wearable_zzn = (zzn) super.zzs();
        zzr.zza(this, com_google_android_gms_internal_wearable_zzn);
        return com_google_android_gms_internal_wearable_zzn;
    }

    public void zza(zzl com_google_android_gms_internal_wearable_zzl) throws IOException {
        if (this.zzhc != null) {
            for (int i = 0; i < this.zzhc.size(); i++) {
                this.zzhc.zzp(i).zza(com_google_android_gms_internal_wearable_zzl);
            }
        }
    }

    protected final boolean zza(zzk com_google_android_gms_internal_wearable_zzk, int i) throws IOException {
        int position = com_google_android_gms_internal_wearable_zzk.getPosition();
        if (!com_google_android_gms_internal_wearable_zzk.zzd(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzv com_google_android_gms_internal_wearable_zzv = new zzv(i, com_google_android_gms_internal_wearable_zzk.zzb(position, com_google_android_gms_internal_wearable_zzk.getPosition() - position));
        zzq com_google_android_gms_internal_wearable_zzq = null;
        if (this.zzhc == null) {
            this.zzhc = new zzp();
        } else {
            com_google_android_gms_internal_wearable_zzq = this.zzhc.zzo(i2);
        }
        if (com_google_android_gms_internal_wearable_zzq == null) {
            com_google_android_gms_internal_wearable_zzq = new zzq();
            this.zzhc.zza(i2, com_google_android_gms_internal_wearable_zzq);
        }
        com_google_android_gms_internal_wearable_zzq.zza(com_google_android_gms_internal_wearable_zzv);
        return true;
    }

    protected int zzg() {
        int i = 0;
        if (this.zzhc == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.zzhc.size()) {
            i2 += this.zzhc.zzp(i).zzg();
            i++;
        }
        return i2;
    }

    public final /* synthetic */ zzt zzs() throws CloneNotSupportedException {
        return (zzn) clone();
    }
}
