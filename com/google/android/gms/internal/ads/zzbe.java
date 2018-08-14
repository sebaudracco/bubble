package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbe extends zzbfc<zzbe> {
    public byte[] data;
    public byte[] zzgq;
    public byte[] zzgr;
    public byte[] zzgs;

    public zzbe() {
        this.data = null;
        this.zzgq = null;
        this.zzgr = null;
        this.zzgs = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.data = com_google_android_gms_internal_ads_zzbez.readBytes();
                    continue;
                case 18:
                    this.zzgq = com_google_android_gms_internal_ads_zzbez.readBytes();
                    continue;
                case 26:
                    this.zzgr = com_google_android_gms_internal_ads_zzbez.readBytes();
                    continue;
                case 34:
                    this.zzgs = com_google_android_gms_internal_ads_zzbez.readBytes();
                    continue;
                default:
                    if (!super.zza(com_google_android_gms_internal_ads_zzbez, zzabk)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.data != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(1, this.data);
        }
        if (this.zzgq != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(2, this.zzgq);
        }
        if (this.zzgr != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(3, this.zzgr);
        }
        if (this.zzgs != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(4, this.zzgs);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.data != null) {
            zzr += zzbfa.zzb(1, this.data);
        }
        if (this.zzgq != null) {
            zzr += zzbfa.zzb(2, this.zzgq);
        }
        if (this.zzgr != null) {
            zzr += zzbfa.zzb(3, this.zzgr);
        }
        return this.zzgs != null ? zzr + zzbfa.zzb(4, this.zzgs) : zzr;
    }
}
