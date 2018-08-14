package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzip extends zzbfc<zzip> {
    private Integer zzaol;
    private Integer zzaom;

    public zzip() {
        this.zzaol = null;
        this.zzaom = null;
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
                    this.zzaol = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 16:
                    this.zzaom = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
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
        if (this.zzaol != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzaol.intValue());
        }
        if (this.zzaom != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(2, this.zzaom.intValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzaol != null) {
            zzr += zzbfa.zzq(1, this.zzaol.intValue());
        }
        return this.zzaom != null ? zzr + zzbfa.zzq(2, this.zzaom.intValue()) : zzr;
    }
}
