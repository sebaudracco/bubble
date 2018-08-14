package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzix extends zzbfc<zzix> {
    private Integer zzanu;
    private zziw zzapn;
    private zzis zzapo;
    private zzit zzapq;

    public zzix() {
        this.zzapq = null;
        this.zzanu = null;
        this.zzapn = null;
        this.zzapo = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzix zzu(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    if (this.zzapq == null) {
                        this.zzapq = new zzit();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzapq);
                    continue;
                case 16:
                    int position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzanu = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 26:
                    if (this.zzapn == null) {
                        this.zzapn = new zziw();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzapn);
                    continue;
                case 34:
                    if (this.zzapo == null) {
                        this.zzapo = new zzis();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzapo);
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
        return zzu(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzapq != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(1, this.zzapq);
        }
        if (this.zzanu != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(2, this.zzanu.intValue());
        }
        if (this.zzapn != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(3, this.zzapn);
        }
        if (this.zzapo != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(4, this.zzapo);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzapq != null) {
            zzr += zzbfa.zzb(1, this.zzapq);
        }
        if (this.zzanu != null) {
            zzr += zzbfa.zzq(2, this.zzanu.intValue());
        }
        if (this.zzapn != null) {
            zzr += zzbfa.zzb(3, this.zzapn);
        }
        return this.zzapo != null ? zzr + zzbfa.zzb(4, this.zzapo) : zzr;
    }
}
