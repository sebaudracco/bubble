package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbg extends zzbfc<zzbg> {
    public Integer zzfe;
    private Integer zzff;
    public byte[] zzgq;
    public byte[][] zzgv;

    public zzbg() {
        this.zzgv = zzbfl.zzece;
        this.zzgq = null;
        this.zzebt = -1;
    }

    private final zzbg zzd(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            int zzb;
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    zzb = zzbfl.zzb(com_google_android_gms_internal_ads_zzbez, 10);
                    zzabk = this.zzgv == null ? 0 : this.zzgv.length;
                    Object obj = new byte[(zzb + zzabk)][];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzgv, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = com_google_android_gms_internal_ads_zzbez.readBytes();
                        com_google_android_gms_internal_ads_zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = com_google_android_gms_internal_ads_zzbez.readBytes();
                    this.zzgv = obj;
                    continue;
                case 18:
                    this.zzgq = com_google_android_gms_internal_ads_zzbez.readBytes();
                    continue;
                case 24:
                    zzb = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzff = Integer.valueOf(zzaz.zze(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(zzb);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 32:
                    zzb = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzfe = Integer.valueOf(zzaz.zzf(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
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
        return zzd(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzgv != null && this.zzgv.length > 0) {
            for (byte[] bArr : this.zzgv) {
                if (bArr != null) {
                    com_google_android_gms_internal_ads_zzbfa.zza(1, bArr);
                }
            }
        }
        if (this.zzgq != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(2, this.zzgq);
        }
        if (this.zzff != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(3, this.zzff.intValue());
        }
        if (this.zzfe != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(4, this.zzfe.intValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int i = 0;
        int zzr = super.zzr();
        if (this.zzgv == null || this.zzgv.length <= 0) {
            i = zzr;
        } else {
            int i2 = 0;
            int i3 = 0;
            while (i < this.zzgv.length) {
                byte[] bArr = this.zzgv[i];
                if (bArr != null) {
                    i3++;
                    i2 += zzbfa.zzv(bArr);
                }
                i++;
            }
            i = (zzr + i2) + (i3 * 1);
        }
        if (this.zzgq != null) {
            i += zzbfa.zzb(2, this.zzgq);
        }
        if (this.zzff != null) {
            i += zzbfa.zzq(3, this.zzff.intValue());
        }
        return this.zzfe != null ? i + zzbfa.zzq(4, this.zzfe.intValue()) : i;
    }
}
