package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfn extends zzbfc<zzbfn> {
    public String zzcnd;

    public zzbfn() {
        this.zzcnd = null;
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
                    this.zzcnd = com_google_android_gms_internal_ads_zzbez.readString();
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
        if (this.zzcnd != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(1, this.zzcnd);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        return this.zzcnd != null ? zzr + zzbfa.zzg(1, this.zzcnd) : zzr;
    }
}
