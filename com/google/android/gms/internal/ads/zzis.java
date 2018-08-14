package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzis extends zzbfc<zzis> {
    private static volatile zzis[] zzaoq;
    private Integer zzaor;
    private Integer zzaos;

    public zzis() {
        this.zzaor = null;
        this.zzaos = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    public static zzis[] zzht() {
        if (zzaoq == null) {
            synchronized (zzbfg.zzebs) {
                if (zzaoq == null) {
                    zzaoq = new zzis[0];
                }
            }
        }
        return zzaoq;
    }

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzaor = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 16:
                    this.zzaos = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
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
        if (this.zzaor != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(1, this.zzaor.intValue());
        }
        if (this.zzaos != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(2, this.zzaos.intValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzaor != null) {
            zzr += zzbfa.zzq(1, this.zzaor.intValue());
        }
        return this.zzaos != null ? zzr + zzbfa.zzq(2, this.zzaos.intValue()) : zzr;
    }
}
