package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziq extends zzbfc<zziq> {
    private Integer zzaon;
    private Integer zzaoo;

    public zziq() {
        this.zzaon = null;
        this.zzaoo = null;
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
                    this.zzaon = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 16:
                    this.zzaoo = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
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
        if (this.zzaon != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzaon.intValue());
        }
        if (this.zzaoo != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(2, this.zzaoo.intValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzaon != null) {
            zzr += zzbfa.zzq(1, this.zzaon.intValue());
        }
        return this.zzaoo != null ? zzr + zzbfa.zzq(2, this.zzaoo.intValue()) : zzr;
    }
}
