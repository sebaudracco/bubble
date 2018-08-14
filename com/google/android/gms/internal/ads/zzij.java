package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzij extends zzbfc<zzij> {
    private String zzanq;
    private Integer zzanr;
    private int[] zzans;
    private zzis zzant;

    public zzij() {
        this.zzanq = null;
        this.zzanr = null;
        this.zzans = zzbfl.zzeby;
        this.zzant = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzij zzl(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            int position;
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    this.zzanq = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 16:
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzanr = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 24:
                    position = zzbfl.zzb(com_google_android_gms_internal_ads_zzbez, 24);
                    zzabk = this.zzans == null ? 0 : this.zzans.length;
                    Object obj = new int[(position + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzans, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = com_google_android_gms_internal_ads_zzbez.zzacc();
                        com_google_android_gms_internal_ads_zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = com_google_android_gms_internal_ads_zzbez.zzacc();
                    this.zzans = obj;
                    continue;
                case 26:
                    int zzbr = com_google_android_gms_internal_ads_zzbez.zzbr(com_google_android_gms_internal_ads_zzbez.zzacc());
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    zzabk = 0;
                    while (com_google_android_gms_internal_ads_zzbez.zzagn() > 0) {
                        com_google_android_gms_internal_ads_zzbez.zzacc();
                        zzabk++;
                    }
                    com_google_android_gms_internal_ads_zzbez.zzdc(position);
                    position = this.zzans == null ? 0 : this.zzans.length;
                    Object obj2 = new int[(zzabk + position)];
                    if (position != 0) {
                        System.arraycopy(this.zzans, 0, obj2, 0, position);
                    }
                    while (position < obj2.length) {
                        obj2[position] = com_google_android_gms_internal_ads_zzbez.zzacc();
                        position++;
                    }
                    this.zzans = obj2;
                    com_google_android_gms_internal_ads_zzbez.zzbs(zzbr);
                    continue;
                case 34:
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
        return zzl(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzanq != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(1, this.zzanq);
        }
        if (this.zzanr != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(2, this.zzanr.intValue());
        }
        if (this.zzans != null && this.zzans.length > 0) {
            for (int zzm : this.zzans) {
                com_google_android_gms_internal_ads_zzbfa.zzm(3, zzm);
            }
        }
        if (this.zzant != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(4, this.zzant);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int i = 0;
        int zzr = super.zzr();
        if (this.zzanq != null) {
            zzr += zzbfa.zzg(1, this.zzanq);
        }
        if (this.zzanr != null) {
            zzr += zzbfa.zzq(2, this.zzanr.intValue());
        }
        if (this.zzans != null && this.zzans.length > 0) {
            int i2 = 0;
            while (i < this.zzans.length) {
                i2 += zzbfa.zzce(this.zzans[i]);
                i++;
            }
            zzr = (zzr + i2) + (this.zzans.length * 1);
        }
        return this.zzant != null ? zzr + zzbfa.zzb(4, this.zzant) : zzr;
    }
}
