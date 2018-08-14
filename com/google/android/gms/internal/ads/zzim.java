package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzim extends zzbfc<zzim> {
    private Integer zzamf;
    private Integer zzanx;

    public zzim() {
        this.zzamf = null;
        this.zzanx = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzim zzo(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        int position;
        int zzacc;
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        zzacc = com_google_android_gms_internal_ads_zzbez.zzacc();
                        if (zzacc < 0 || zzacc > 2) {
                            throw new IllegalArgumentException(zzacc + " is not a valid enum NetworkType");
                        }
                        this.zzamf = Integer.valueOf(zzacc);
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 16:
                    zzacc = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        position = com_google_android_gms_internal_ads_zzbez.zzacc();
                        if ((position < 0 || position > 2) && (position < 4 || position > 4)) {
                            throw new IllegalArgumentException(position + " is not a valid enum CellularNetworkType");
                        }
                        this.zzanx = Integer.valueOf(position);
                        continue;
                    } catch (IllegalArgumentException e2) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(zzacc);
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
        return zzo(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzamf != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzamf.intValue());
        }
        if (this.zzanx != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(2, this.zzanx.intValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzamf != null) {
            zzr += zzbfa.zzq(1, this.zzamf.intValue());
        }
        return this.zzanx != null ? zzr + zzbfa.zzq(2, this.zzanx.intValue()) : zzr;
    }
}
