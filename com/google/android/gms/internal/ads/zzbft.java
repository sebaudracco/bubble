package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbft extends zzbfc<zzbft> {
    public String mimeType;
    public Integer zzamf;
    public byte[] zzedl;

    public zzbft() {
        this.zzamf = null;
        this.mimeType = null;
        this.zzedl = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzbft zzab(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    int position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        int zzabn = com_google_android_gms_internal_ads_zzbez.zzabn();
                        if (zzabn < 0 || zzabn > 1) {
                            throw new IllegalArgumentException(zzabn + " is not a valid enum Type");
                        }
                        this.zzamf = Integer.valueOf(zzabn);
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 18:
                    this.mimeType = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 26:
                    this.zzedl = com_google_android_gms_internal_ads_zzbez.readBytes();
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
        return zzab(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzamf != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzamf.intValue());
        }
        if (this.mimeType != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(2, this.mimeType);
        }
        if (this.zzedl != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(3, this.zzedl);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzamf != null) {
            zzr += zzbfa.zzq(1, this.zzamf.intValue());
        }
        if (this.mimeType != null) {
            zzr += zzbfa.zzg(2, this.mimeType);
        }
        return this.zzedl != null ? zzr + zzbfa.zzb(3, this.zzedl) : zzr;
    }
}
