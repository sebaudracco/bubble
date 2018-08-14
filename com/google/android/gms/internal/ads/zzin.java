package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzin extends zzbfc<zzin> {
    private Integer zzany;
    private zzis zzanz;

    public zzin() {
        this.zzany = null;
        this.zzanz = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzin zzp(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzany = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 18:
                    if (this.zzanz == null) {
                        this.zzanz = new zzis();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzanz);
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
        return zzp(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzany != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzany.intValue());
        }
        if (this.zzanz != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(2, this.zzanz);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzany != null) {
            zzr += zzbfa.zzq(1, this.zzany.intValue());
        }
        return this.zzanz != null ? zzr + zzbfa.zzb(2, this.zzanz) : zzr;
    }
}
