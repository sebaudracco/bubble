package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzid extends zzbfc<zzid> {
    private String zzacp;
    private zzic[] zzamh;
    private Integer zzami;

    public zzid() {
        this.zzacp = null;
        this.zzamh = zzic.zzhr();
        this.zzami = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzid zzg(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            int zzb;
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzacp = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 18:
                    zzb = zzbfl.zzb(com_google_android_gms_internal_ads_zzbez, 18);
                    zzabk = this.zzamh == null ? 0 : this.zzamh.length;
                    Object obj = new zzic[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzamh, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzic();
                        com_google_android_gms_internal_ads_zzbez.zza(obj[zzabk]);
                        com_google_android_gms_internal_ads_zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzic();
                    com_google_android_gms_internal_ads_zzbez.zza(obj[zzabk]);
                    this.zzamh = obj;
                    continue;
                case 24:
                    zzb = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzami = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(zzb);
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
        return zzg(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzacp != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(1, this.zzacp);
        }
        if (this.zzamh != null && this.zzamh.length > 0) {
            for (zzbfi com_google_android_gms_internal_ads_zzbfi : this.zzamh) {
                if (com_google_android_gms_internal_ads_zzbfi != null) {
                    com_google_android_gms_internal_ads_zzbfa.zza(2, com_google_android_gms_internal_ads_zzbfi);
                }
            }
        }
        if (this.zzami != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(3, this.zzami.intValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzacp != null) {
            zzr += zzbfa.zzg(1, this.zzacp);
        }
        if (this.zzamh != null && this.zzamh.length > 0) {
            int i = zzr;
            for (zzbfi com_google_android_gms_internal_ads_zzbfi : this.zzamh) {
                if (com_google_android_gms_internal_ads_zzbfi != null) {
                    i += zzbfa.zzb(2, com_google_android_gms_internal_ads_zzbfi);
                }
            }
            zzr = i;
        }
        return this.zzami != null ? zzr + zzbfa.zzq(3, this.zzami.intValue()) : zzr;
    }
}
