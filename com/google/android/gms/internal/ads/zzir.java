package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzir extends zzbfc<zzir> {
    private static volatile zzir[] zzaop;
    private String zzanq;
    private Integer zzanr;
    private zzis zzant;

    public zzir() {
        this.zzanq = null;
        this.zzanr = null;
        this.zzant = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public static zzir[] zzhs() {
        if (zzaop == null) {
            synchronized (zzbfg.zzebs) {
                if (zzaop == null) {
                    zzaop = new zzir[0];
                }
            }
        }
        return zzaop;
    }

    private final zzir zzr(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzanq = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 16:
                    int position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzanr = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 26:
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
        return zzr(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzanq != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(1, this.zzanq);
        }
        if (this.zzanr != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(2, this.zzanr.intValue());
        }
        if (this.zzant != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(3, this.zzant);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzanq != null) {
            zzr += zzbfa.zzg(1, this.zzanq);
        }
        if (this.zzanr != null) {
            zzr += zzbfa.zzq(2, this.zzanr.intValue());
        }
        return this.zzant != null ? zzr + zzbfa.zzb(3, this.zzant) : zzr;
    }
}
