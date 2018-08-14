package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfs extends zzbfc<zzbfs> {
    private byte[] zzedg;
    private Integer zzedj;
    private byte[] zzedk;

    public zzbfs() {
        this.zzedj = null;
        this.zzedk = null;
        this.zzedg = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzedj = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzabn());
                    continue;
                case 18:
                    this.zzedk = com_google_android_gms_internal_ads_zzbez.readBytes();
                    continue;
                case 26:
                    this.zzedg = com_google_android_gms_internal_ads_zzbez.readBytes();
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
        if (this.zzedj != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzedj.intValue());
        }
        if (this.zzedk != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(2, this.zzedk);
        }
        if (this.zzedg != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(3, this.zzedg);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzedj != null) {
            zzr += zzbfa.zzq(1, this.zzedj.intValue());
        }
        if (this.zzedk != null) {
            zzr += zzbfa.zzb(2, this.zzedk);
        }
        return this.zzedg != null ? zzr + zzbfa.zzb(3, this.zzedg) : zzr;
    }
}
