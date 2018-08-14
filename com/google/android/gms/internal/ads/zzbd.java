package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbd extends zzbfc<zzbd> {
    private Long zzgl;
    private Integer zzgm;
    private Boolean zzgn;
    private int[] zzgo;
    private Long zzgp;

    public zzbd() {
        this.zzgl = null;
        this.zzgm = null;
        this.zzgn = null;
        this.zzgo = zzbfl.zzeby;
        this.zzgp = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            int zzb;
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzgl = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 16:
                    this.zzgm = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 24:
                    this.zzgn = Boolean.valueOf(com_google_android_gms_internal_ads_zzbez.zzabq());
                    continue;
                case 32:
                    zzb = zzbfl.zzb(com_google_android_gms_internal_ads_zzbez, 32);
                    zzabk = this.zzgo == null ? 0 : this.zzgo.length;
                    Object obj = new int[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzgo, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = com_google_android_gms_internal_ads_zzbez.zzacc();
                        com_google_android_gms_internal_ads_zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = com_google_android_gms_internal_ads_zzbez.zzacc();
                    this.zzgo = obj;
                    continue;
                case 34:
                    int zzbr = com_google_android_gms_internal_ads_zzbez.zzbr(com_google_android_gms_internal_ads_zzbez.zzacc());
                    zzb = com_google_android_gms_internal_ads_zzbez.getPosition();
                    zzabk = 0;
                    while (com_google_android_gms_internal_ads_zzbez.zzagn() > 0) {
                        com_google_android_gms_internal_ads_zzbez.zzacc();
                        zzabk++;
                    }
                    com_google_android_gms_internal_ads_zzbez.zzdc(zzb);
                    zzb = this.zzgo == null ? 0 : this.zzgo.length;
                    Object obj2 = new int[(zzabk + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzgo, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_ads_zzbez.zzacc();
                        zzb++;
                    }
                    this.zzgo = obj2;
                    com_google_android_gms_internal_ads_zzbez.zzbs(zzbr);
                    continue;
                case 40:
                    this.zzgp = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
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
        if (this.zzgl != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(1, this.zzgl.longValue());
        }
        if (this.zzgm != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(2, this.zzgm.intValue());
        }
        if (this.zzgn != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(3, this.zzgn.booleanValue());
        }
        if (this.zzgo != null && this.zzgo.length > 0) {
            for (int zzm : this.zzgo) {
                com_google_android_gms_internal_ads_zzbfa.zzm(4, zzm);
            }
        }
        if (this.zzgp != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(5, this.zzgp.longValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int i = 0;
        int zzr = super.zzr();
        if (this.zzgl != null) {
            zzr += zzbfa.zzd(1, this.zzgl.longValue());
        }
        if (this.zzgm != null) {
            zzr += zzbfa.zzq(2, this.zzgm.intValue());
        }
        if (this.zzgn != null) {
            this.zzgn.booleanValue();
            zzr += zzbfa.zzcd(3) + 1;
        }
        if (this.zzgo != null && this.zzgo.length > 0) {
            int i2 = 0;
            while (i < this.zzgo.length) {
                i2 += zzbfa.zzce(this.zzgo[i]);
                i++;
            }
            zzr = (zzr + i2) + (this.zzgo.length * 1);
        }
        return this.zzgp != null ? zzr + zzbfa.zze(5, this.zzgp.longValue()) : zzr;
    }
}
