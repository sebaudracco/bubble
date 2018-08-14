package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzig extends zzbfc<zzig> {
    public String zzamu;
    private zzis zzamv;
    private Integer zzamw;
    public zzit zzamx;
    private Integer zzamy;
    private Integer zzamz;
    private Integer zzana;
    private Integer zzanb;

    public zzig() {
        this.zzamu = null;
        this.zzamv = null;
        this.zzamw = null;
        this.zzamx = null;
        this.zzamy = null;
        this.zzamz = null;
        this.zzana = null;
        this.zzanb = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzig zzi(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        int position;
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzamu = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 18:
                    if (this.zzamv == null) {
                        this.zzamv = new zzis();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzamv);
                    continue;
                case 24:
                    this.zzamw = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 34:
                    if (this.zzamx == null) {
                        this.zzamx = new zzit();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzamx);
                    continue;
                case 40:
                    this.zzamy = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 48:
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzamz = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 56:
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzana = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 64:
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzanb = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e3) {
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
        return zzi(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzamu != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(1, this.zzamu);
        }
        if (this.zzamv != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(2, this.zzamv);
        }
        if (this.zzamw != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(3, this.zzamw.intValue());
        }
        if (this.zzamx != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(4, this.zzamx);
        }
        if (this.zzamy != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(5, this.zzamy.intValue());
        }
        if (this.zzamz != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(6, this.zzamz.intValue());
        }
        if (this.zzana != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(7, this.zzana.intValue());
        }
        if (this.zzanb != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(8, this.zzanb.intValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzamu != null) {
            zzr += zzbfa.zzg(1, this.zzamu);
        }
        if (this.zzamv != null) {
            zzr += zzbfa.zzb(2, this.zzamv);
        }
        if (this.zzamw != null) {
            zzr += zzbfa.zzq(3, this.zzamw.intValue());
        }
        if (this.zzamx != null) {
            zzr += zzbfa.zzb(4, this.zzamx);
        }
        if (this.zzamy != null) {
            zzr += zzbfa.zzq(5, this.zzamy.intValue());
        }
        if (this.zzamz != null) {
            zzr += zzbfa.zzq(6, this.zzamz.intValue());
        }
        if (this.zzana != null) {
            zzr += zzbfa.zzq(7, this.zzana.intValue());
        }
        return this.zzanb != null ? zzr + zzbfa.zzq(8, this.zzanb.intValue()) : zzr;
    }
}
