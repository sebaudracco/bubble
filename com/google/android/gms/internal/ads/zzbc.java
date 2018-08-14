package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbc extends zzbfc<zzbc> {
    private Long zzeq;
    private Long zzer;
    public Long zzgi;
    public Long zzgj;
    public Long zzgk;

    public zzbc() {
        this.zzeq = null;
        this.zzer = null;
        this.zzgi = null;
        this.zzgj = null;
        this.zzgk = null;
        this.zzebt = -1;
    }

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzeq = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 16:
                    this.zzer = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 24:
                    this.zzgi = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 32:
                    this.zzgj = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 40:
                    this.zzgk = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
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
        if (this.zzeq != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(1, this.zzeq.longValue());
        }
        if (this.zzer != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(2, this.zzer.longValue());
        }
        if (this.zzgi != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(3, this.zzgi.longValue());
        }
        if (this.zzgj != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(4, this.zzgj.longValue());
        }
        if (this.zzgk != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(5, this.zzgk.longValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzeq != null) {
            zzr += zzbfa.zzd(1, this.zzeq.longValue());
        }
        if (this.zzer != null) {
            zzr += zzbfa.zzd(2, this.zzer.longValue());
        }
        if (this.zzgi != null) {
            zzr += zzbfa.zzd(3, this.zzgi.longValue());
        }
        if (this.zzgj != null) {
            zzr += zzbfa.zzd(4, this.zzgj.longValue());
        }
        return this.zzgk != null ? zzr + zzbfa.zzd(5, this.zzgk.longValue()) : zzr;
    }
}
