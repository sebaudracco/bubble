package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzif extends zzbfc<zzif> {
    private Integer zzamo;
    private zzis zzamp;
    private zzis zzamq;
    private zzis zzamr;
    private zzis[] zzams;
    private Integer zzamt;

    public zzif() {
        this.zzamo = null;
        this.zzamp = null;
        this.zzamq = null;
        this.zzamr = null;
        this.zzams = zzis.zzht();
        this.zzamt = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzamo = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 18:
                    if (this.zzamp == null) {
                        this.zzamp = new zzis();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzamp);
                    continue;
                case 26:
                    if (this.zzamq == null) {
                        this.zzamq = new zzis();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzamq);
                    continue;
                case 34:
                    if (this.zzamr == null) {
                        this.zzamr = new zzis();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzamr);
                    continue;
                case 42:
                    int zzb = zzbfl.zzb(com_google_android_gms_internal_ads_zzbez, 42);
                    zzabk = this.zzams == null ? 0 : this.zzams.length;
                    Object obj = new zzis[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzams, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzis();
                        com_google_android_gms_internal_ads_zzbez.zza(obj[zzabk]);
                        com_google_android_gms_internal_ads_zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzis();
                    com_google_android_gms_internal_ads_zzbez.zza(obj[zzabk]);
                    this.zzams = obj;
                    continue;
                case 48:
                    this.zzamt = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
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

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzamo != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzamo.intValue());
        }
        if (this.zzamp != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(2, this.zzamp);
        }
        if (this.zzamq != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(3, this.zzamq);
        }
        if (this.zzamr != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(4, this.zzamr);
        }
        if (this.zzams != null && this.zzams.length > 0) {
            for (zzbfi com_google_android_gms_internal_ads_zzbfi : this.zzams) {
                if (com_google_android_gms_internal_ads_zzbfi != null) {
                    com_google_android_gms_internal_ads_zzbfa.zza(5, com_google_android_gms_internal_ads_zzbfi);
                }
            }
        }
        if (this.zzamt != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(6, this.zzamt.intValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzamo != null) {
            zzr += zzbfa.zzq(1, this.zzamo.intValue());
        }
        if (this.zzamp != null) {
            zzr += zzbfa.zzb(2, this.zzamp);
        }
        if (this.zzamq != null) {
            zzr += zzbfa.zzb(3, this.zzamq);
        }
        if (this.zzamr != null) {
            zzr += zzbfa.zzb(4, this.zzamr);
        }
        if (this.zzams != null && this.zzams.length > 0) {
            int i = zzr;
            for (zzbfi com_google_android_gms_internal_ads_zzbfi : this.zzams) {
                if (com_google_android_gms_internal_ads_zzbfi != null) {
                    i += zzbfa.zzb(5, com_google_android_gms_internal_ads_zzbfi);
                }
            }
            zzr = i;
        }
        return this.zzamt != null ? zzr + zzbfa.zzq(6, this.zzamt.intValue()) : zzr;
    }
}
