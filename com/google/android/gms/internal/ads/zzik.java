package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzik extends zzbfc<zzik> {
    private int[] zzans;
    private Integer zzanu;

    public zzik() {
        this.zzanu = null;
        this.zzans = zzbfl.zzeby;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzik zzm(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        int position;
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzanu = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 16:
                    position = zzbfl.zzb(com_google_android_gms_internal_ads_zzbez, 16);
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
                case 18:
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
        return zzm(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzanu != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzanu.intValue());
        }
        if (this.zzans != null && this.zzans.length > 0) {
            for (int zzm : this.zzans) {
                com_google_android_gms_internal_ads_zzbfa.zzm(2, zzm);
            }
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int i = 0;
        int zzr = super.zzr();
        if (this.zzanu != null) {
            zzr += zzbfa.zzq(1, this.zzanu.intValue());
        }
        if (this.zzans == null || this.zzans.length <= 0) {
            return zzr;
        }
        int i2 = 0;
        while (i < this.zzans.length) {
            i2 += zzbfa.zzce(this.zzans[i]);
            i++;
        }
        return (zzr + i2) + (this.zzans.length * 1);
    }
}
