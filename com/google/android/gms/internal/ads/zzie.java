package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzie extends zzbfc<zzie> {
    private String zzamj;
    private zzic[] zzamk;
    private Integer zzaml;
    private Integer zzamm;
    private Integer zzamn;

    public zzie() {
        this.zzamj = null;
        this.zzamk = zzic.zzhr();
        this.zzaml = null;
        this.zzamm = null;
        this.zzamn = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzie zzh(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        int zzb;
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzamj = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 18:
                    zzb = zzbfl.zzb(com_google_android_gms_internal_ads_zzbez, 18);
                    zzabk = this.zzamk == null ? 0 : this.zzamk.length;
                    Object obj = new zzic[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzamk, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzic();
                        com_google_android_gms_internal_ads_zzbez.zza(obj[zzabk]);
                        com_google_android_gms_internal_ads_zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzic();
                    com_google_android_gms_internal_ads_zzbez.zza(obj[zzabk]);
                    this.zzamk = obj;
                    continue;
                case 24:
                    zzb = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzaml = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(zzb);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 32:
                    zzb = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzamm = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(zzb);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 40:
                    zzb = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzamn = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e3) {
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
        return zzh(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzamj != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(1, this.zzamj);
        }
        if (this.zzamk != null && this.zzamk.length > 0) {
            for (zzbfi com_google_android_gms_internal_ads_zzbfi : this.zzamk) {
                if (com_google_android_gms_internal_ads_zzbfi != null) {
                    com_google_android_gms_internal_ads_zzbfa.zza(2, com_google_android_gms_internal_ads_zzbfi);
                }
            }
        }
        if (this.zzaml != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(3, this.zzaml.intValue());
        }
        if (this.zzamm != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(4, this.zzamm.intValue());
        }
        if (this.zzamn != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(5, this.zzamn.intValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzamj != null) {
            zzr += zzbfa.zzg(1, this.zzamj);
        }
        if (this.zzamk != null && this.zzamk.length > 0) {
            int i = zzr;
            for (zzbfi com_google_android_gms_internal_ads_zzbfi : this.zzamk) {
                if (com_google_android_gms_internal_ads_zzbfi != null) {
                    i += zzbfa.zzb(2, com_google_android_gms_internal_ads_zzbfi);
                }
            }
            zzr = i;
        }
        if (this.zzaml != null) {
            zzr += zzbfa.zzq(3, this.zzaml.intValue());
        }
        if (this.zzamm != null) {
            zzr += zzbfa.zzq(4, this.zzamm.intValue());
        }
        return this.zzamn != null ? zzr + zzbfa.zzq(5, this.zzamn.intValue()) : zzr;
    }
}
