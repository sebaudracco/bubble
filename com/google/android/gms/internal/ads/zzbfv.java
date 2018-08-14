package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfv extends zzbfc<zzbfv> {
    public String zzedv;
    public Long zzedw;
    public Boolean zzedx;

    public zzbfv() {
        this.zzedv = null;
        this.zzedw = null;
        this.zzedx = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzedv = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 16:
                    this.zzedw = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzabm());
                    continue;
                case 24:
                    this.zzedx = Boolean.valueOf(com_google_android_gms_internal_ads_zzbez.zzabq());
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
        if (this.zzedv != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(1, this.zzedv);
        }
        if (this.zzedw != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(2, this.zzedw.longValue());
        }
        if (this.zzedx != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(3, this.zzedx.booleanValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzedv != null) {
            zzr += zzbfa.zzg(1, this.zzedv);
        }
        if (this.zzedw != null) {
            zzr += zzbfa.zzd(2, this.zzedw.longValue());
        }
        if (this.zzedx == null) {
            return zzr;
        }
        this.zzedx.booleanValue();
        return zzr + (zzbfa.zzcd(3) + 1);
    }
}
