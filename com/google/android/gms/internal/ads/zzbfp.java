package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfp extends zzbfc<zzbfp> {
    private zzbfq zzecz;
    public zzbfo[] zzeda;
    private byte[] zzedb;
    private byte[] zzedc;
    private Integer zzedd;

    public zzbfp() {
        this.zzecz = null;
        this.zzeda = zzbfo.zzagt();
        this.zzedb = null;
        this.zzedc = null;
        this.zzedd = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 10:
                    if (this.zzecz == null) {
                        this.zzecz = new zzbfq();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzecz);
                    continue;
                case 18:
                    int zzb = zzbfl.zzb(com_google_android_gms_internal_ads_zzbez, 18);
                    zzabk = this.zzeda == null ? 0 : this.zzeda.length;
                    Object obj = new zzbfo[(zzb + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzeda, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = new zzbfo();
                        com_google_android_gms_internal_ads_zzbez.zza(obj[zzabk]);
                        com_google_android_gms_internal_ads_zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = new zzbfo();
                    com_google_android_gms_internal_ads_zzbez.zza(obj[zzabk]);
                    this.zzeda = obj;
                    continue;
                case 26:
                    this.zzedb = com_google_android_gms_internal_ads_zzbez.readBytes();
                    continue;
                case 34:
                    this.zzedc = com_google_android_gms_internal_ads_zzbez.readBytes();
                    continue;
                case 40:
                    this.zzedd = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzabn());
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
        if (this.zzecz != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(1, this.zzecz);
        }
        if (this.zzeda != null && this.zzeda.length > 0) {
            for (zzbfi com_google_android_gms_internal_ads_zzbfi : this.zzeda) {
                if (com_google_android_gms_internal_ads_zzbfi != null) {
                    com_google_android_gms_internal_ads_zzbfa.zza(2, com_google_android_gms_internal_ads_zzbfi);
                }
            }
        }
        if (this.zzedb != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(3, this.zzedb);
        }
        if (this.zzedc != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(4, this.zzedc);
        }
        if (this.zzedd != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(5, this.zzedd.intValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzecz != null) {
            zzr += zzbfa.zzb(1, this.zzecz);
        }
        if (this.zzeda != null && this.zzeda.length > 0) {
            int i = zzr;
            for (zzbfi com_google_android_gms_internal_ads_zzbfi : this.zzeda) {
                if (com_google_android_gms_internal_ads_zzbfi != null) {
                    i += zzbfa.zzb(2, com_google_android_gms_internal_ads_zzbfi);
                }
            }
            zzr = i;
        }
        if (this.zzedb != null) {
            zzr += zzbfa.zzb(3, this.zzedb);
        }
        if (this.zzedc != null) {
            zzr += zzbfa.zzb(4, this.zzedc);
        }
        return this.zzedd != null ? zzr + zzbfa.zzq(5, this.zzedd.intValue()) : zzr;
    }
}
