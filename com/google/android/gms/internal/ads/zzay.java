package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzay extends zzbfc<zzay> {
    public String zzcx;
    private String zzcy;
    private String zzcz;
    private String zzda;
    private String zzdb;

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzcx = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 18:
                    this.zzcy = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 26:
                    this.zzcz = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 34:
                    this.zzda = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 42:
                    this.zzdb = com_google_android_gms_internal_ads_zzbez.readString();
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
        if (this.zzcx != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(1, this.zzcx);
        }
        if (this.zzcy != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(2, this.zzcy);
        }
        if (this.zzcz != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(3, this.zzcz);
        }
        if (this.zzda != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(4, this.zzda);
        }
        if (this.zzdb != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(5, this.zzdb);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzcx != null) {
            zzr += zzbfa.zzg(1, this.zzcx);
        }
        if (this.zzcy != null) {
            zzr += zzbfa.zzg(2, this.zzcy);
        }
        if (this.zzcz != null) {
            zzr += zzbfa.zzg(3, this.zzcz);
        }
        if (this.zzda != null) {
            zzr += zzbfa.zzg(4, this.zzda);
        }
        return this.zzdb != null ? zzr + zzbfa.zzg(5, this.zzdb) : zzr;
    }
}
