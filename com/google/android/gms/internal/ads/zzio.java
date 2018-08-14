package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzio extends zzbfc<zzio> {
    private Integer zzaoa;
    private Integer zzaob;
    private Integer zzaoc;
    private Integer zzaod;
    private Integer zzaoe;
    private Integer zzaof;
    private Integer zzaog;
    private Integer zzaoh;
    private Integer zzaoi;
    private Integer zzaoj;
    private zzip zzaok;

    public zzio() {
        this.zzaoa = null;
        this.zzaob = null;
        this.zzaoc = null;
        this.zzaod = null;
        this.zzaoe = null;
        this.zzaof = null;
        this.zzaog = null;
        this.zzaoh = null;
        this.zzaoi = null;
        this.zzaoj = null;
        this.zzaok = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzio zzq(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        int position;
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzaoa = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 16:
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzaob = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 24:
                    this.zzaoc = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 32:
                    this.zzaod = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 40:
                    this.zzaoe = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 48:
                    this.zzaof = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 56:
                    this.zzaog = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 64:
                    this.zzaoh = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 72:
                    this.zzaoi = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 80:
                    this.zzaoj = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 90:
                    if (this.zzaok == null) {
                        this.zzaok = new zzip();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzaok);
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
        return zzq(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzaoa != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzaoa.intValue());
        }
        if (this.zzaob != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(2, this.zzaob.intValue());
        }
        if (this.zzaoc != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(3, this.zzaoc.intValue());
        }
        if (this.zzaod != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(4, this.zzaod.intValue());
        }
        if (this.zzaoe != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(5, this.zzaoe.intValue());
        }
        if (this.zzaof != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(6, this.zzaof.intValue());
        }
        if (this.zzaog != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(7, this.zzaog.intValue());
        }
        if (this.zzaoh != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(8, this.zzaoh.intValue());
        }
        if (this.zzaoi != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(9, this.zzaoi.intValue());
        }
        if (this.zzaoj != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(10, this.zzaoj.intValue());
        }
        if (this.zzaok != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(11, this.zzaok);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzaoa != null) {
            zzr += zzbfa.zzq(1, this.zzaoa.intValue());
        }
        if (this.zzaob != null) {
            zzr += zzbfa.zzq(2, this.zzaob.intValue());
        }
        if (this.zzaoc != null) {
            zzr += zzbfa.zzq(3, this.zzaoc.intValue());
        }
        if (this.zzaod != null) {
            zzr += zzbfa.zzq(4, this.zzaod.intValue());
        }
        if (this.zzaoe != null) {
            zzr += zzbfa.zzq(5, this.zzaoe.intValue());
        }
        if (this.zzaof != null) {
            zzr += zzbfa.zzq(6, this.zzaof.intValue());
        }
        if (this.zzaog != null) {
            zzr += zzbfa.zzq(7, this.zzaog.intValue());
        }
        if (this.zzaoh != null) {
            zzr += zzbfa.zzq(8, this.zzaoh.intValue());
        }
        if (this.zzaoi != null) {
            zzr += zzbfa.zzq(9, this.zzaoi.intValue());
        }
        if (this.zzaoj != null) {
            zzr += zzbfa.zzq(10, this.zzaoj.intValue());
        }
        return this.zzaok != null ? zzr + zzbfa.zzb(11, this.zzaok) : zzr;
    }
}
