package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzjc extends zzbfc<zzjc> {
    private Integer zzanu;
    private zziw zzapn;

    public zzjc() {
        this.zzanu = null;
        this.zzapn = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzjc zzz(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzanu = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 18:
                    if (this.zzapn == null) {
                        this.zzapn = new zziw();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzapn);
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

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        return zzz(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzanu != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzanu.intValue());
        }
        if (this.zzapn != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(2, this.zzapn);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzanu != null) {
            zzr += zzbfa.zzq(1, this.zzanu.intValue());
        }
        return this.zzapn != null ? zzr + zzbfa.zzb(2, this.zzapn) : zzr;
    }
}
