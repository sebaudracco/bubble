package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzil extends zzbfc<zzil> {
    private zzis zzant;
    private Integer zzanu;
    private zzij zzanv;
    private zzir[] zzanw;

    public zzil() {
        this.zzanv = null;
        this.zzanw = zzir.zzhs();
        this.zzanu = null;
        this.zzant = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzil zzn(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            int zzb;
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    if (this.zzanv == null) {
                        this.zzanv = new zzij();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzanv);
                    continue;
                case 18:
                    zzb = zzbfl.zzb(com_google_android_gms_internal_ads_zzbez, 18);
                    zzabk = this.zzanw == null ? 0 : this.zzanw.length;
                    Object obj = new zzir[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzanw, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzir();
                        com_google_android_gms_internal_ads_zzbez.zza(obj[zzabk]);
                        com_google_android_gms_internal_ads_zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzir();
                    com_google_android_gms_internal_ads_zzbez.zza(obj[zzabk]);
                    this.zzanw = obj;
                    continue;
                case 24:
                    zzb = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzanu = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(zzb);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 34:
                    if (this.zzant == null) {
                        this.zzant = new zzis();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzant);
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
        return zzn(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzanv != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(1, this.zzanv);
        }
        if (this.zzanw != null && this.zzanw.length > 0) {
            for (zzbfi com_google_android_gms_internal_ads_zzbfi : this.zzanw) {
                if (com_google_android_gms_internal_ads_zzbfi != null) {
                    com_google_android_gms_internal_ads_zzbfa.zza(2, com_google_android_gms_internal_ads_zzbfi);
                }
            }
        }
        if (this.zzanu != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(3, this.zzanu.intValue());
        }
        if (this.zzant != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(4, this.zzant);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzanv != null) {
            zzr += zzbfa.zzb(1, this.zzanv);
        }
        if (this.zzanw != null && this.zzanw.length > 0) {
            int i = zzr;
            for (zzbfi com_google_android_gms_internal_ads_zzbfi : this.zzanw) {
                if (com_google_android_gms_internal_ads_zzbfi != null) {
                    i += zzbfa.zzb(2, com_google_android_gms_internal_ads_zzbfi);
                }
            }
            zzr = i;
        }
        if (this.zzanu != null) {
            zzr += zzbfa.zzq(3, this.zzanu.intValue());
        }
        return this.zzant != null ? zzr + zzbfa.zzb(4, this.zzant) : zzr;
    }
}
