package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zziw extends zzbfc<zziw> {
    private Integer zzapp;

    public zziw() {
        this.zzapp = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zziw zzt(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        int zzacc = com_google_android_gms_internal_ads_zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 3) {
                            throw new IllegalArgumentException(zzacc + " is not a valid enum VideoErrorCode");
                        }
                        this.zzapp = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                default:
                    if (!super.zza(com_google_android_gms_internal_ads_zzbez, zzabk)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        return zzt(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzapp != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzapp.intValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        return this.zzapp != null ? zzr + zzbfa.zzq(1, this.zzapp.intValue()) : zzr;
    }
}
