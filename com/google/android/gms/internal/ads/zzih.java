package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzih extends zzbfc<zzih> {
    private Integer zzanc;
    private zzit zzand;
    private String zzane;
    private String zzanf;

    public zzih() {
        this.zzanc = null;
        this.zzand = null;
        this.zzane = null;
        this.zzanf = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzih zzj(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 40:
                    int position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        int zzacc = com_google_android_gms_internal_ads_zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 2) {
                            throw new IllegalArgumentException(zzacc + " is not a valid enum Platform");
                        }
                        this.zzanc = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 50:
                    if (this.zzand == null) {
                        this.zzand = new zzit();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzand);
                    continue;
                case 58:
                    this.zzane = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 66:
                    this.zzanf = com_google_android_gms_internal_ads_zzbez.readString();
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
        return zzj(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzanc != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(5, this.zzanc.intValue());
        }
        if (this.zzand != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(6, this.zzand);
        }
        if (this.zzane != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(7, this.zzane);
        }
        if (this.zzanf != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(8, this.zzanf);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzanc != null) {
            zzr += zzbfa.zzq(5, this.zzanc.intValue());
        }
        if (this.zzand != null) {
            zzr += zzbfa.zzb(6, this.zzand);
        }
        if (this.zzane != null) {
            zzr += zzbfa.zzg(7, this.zzane);
        }
        return this.zzanf != null ? zzr + zzbfa.zzg(8, this.zzanf) : zzr;
    }
}
