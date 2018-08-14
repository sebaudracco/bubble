package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbb extends zzbfc<zzbb> {
    private static volatile zzbb[] zzfo;
    public Long zzdo;
    public Long zzdp;
    public Long zzfp;
    public Long zzfq;
    public Long zzfr;
    public Long zzfs;
    public Integer zzft;
    public Long zzfu;
    public Long zzfv;
    public Long zzfw;
    public Integer zzfx;
    public Long zzfy;
    public Long zzfz;
    public Long zzga;
    public Long zzgb;
    public Long zzgc;
    public Long zzgd;
    public Long zzge;
    public Long zzgf;
    private Long zzgg;
    private Long zzgh;

    public zzbb() {
        this.zzdo = null;
        this.zzdp = null;
        this.zzfp = null;
        this.zzfq = null;
        this.zzfr = null;
        this.zzfs = null;
        this.zzfu = null;
        this.zzfv = null;
        this.zzfw = null;
        this.zzfy = null;
        this.zzfz = null;
        this.zzga = null;
        this.zzgb = null;
        this.zzgc = null;
        this.zzgd = null;
        this.zzge = null;
        this.zzgf = null;
        this.zzgg = null;
        this.zzgh = null;
        this.zzebt = -1;
    }

    private final zzbb zzc(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        int position;
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            switch (zzabk) {
                case 0:
                    break;
                case 8:
                    this.zzdo = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 16:
                    this.zzdp = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 24:
                    this.zzfp = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 32:
                    this.zzfq = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 40:
                    this.zzfr = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 48:
                    this.zzfs = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 56:
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzft = Integer.valueOf(zzaz.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 64:
                    this.zzfu = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 72:
                    this.zzfv = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 80:
                    this.zzfw = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 88:
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzfx = Integer.valueOf(zzaz.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e2) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 96:
                    this.zzfy = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 104:
                    this.zzfz = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 112:
                    this.zzga = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 120:
                    this.zzgb = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 128:
                    this.zzgc = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 136:
                    this.zzgd = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 144:
                    this.zzge = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 152:
                    this.zzgf = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 160:
                    this.zzgg = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
                    continue;
                case 168:
                    this.zzgh = Long.valueOf(com_google_android_gms_internal_ads_zzbez.zzacd());
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

    public static zzbb[] zzs() {
        if (zzfo == null) {
            synchronized (zzbfg.zzebs) {
                if (zzfo == null) {
                    zzfo = new zzbb[0];
                }
            }
        }
        return zzfo;
    }

    public final /* synthetic */ zzbfi zza(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        return zzc(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        if (this.zzdo != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(1, this.zzdo.longValue());
        }
        if (this.zzdp != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(2, this.zzdp.longValue());
        }
        if (this.zzfp != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(3, this.zzfp.longValue());
        }
        if (this.zzfq != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(4, this.zzfq.longValue());
        }
        if (this.zzfr != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(5, this.zzfr.longValue());
        }
        if (this.zzfs != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(6, this.zzfs.longValue());
        }
        if (this.zzft != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(7, this.zzft.intValue());
        }
        if (this.zzfu != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(8, this.zzfu.longValue());
        }
        if (this.zzfv != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(9, this.zzfv.longValue());
        }
        if (this.zzfw != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(10, this.zzfw.longValue());
        }
        if (this.zzfx != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(11, this.zzfx.intValue());
        }
        if (this.zzfy != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(12, this.zzfy.longValue());
        }
        if (this.zzfz != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(13, this.zzfz.longValue());
        }
        if (this.zzga != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(14, this.zzga.longValue());
        }
        if (this.zzgb != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(15, this.zzgb.longValue());
        }
        if (this.zzgc != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(16, this.zzgc.longValue());
        }
        if (this.zzgd != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(17, this.zzgd.longValue());
        }
        if (this.zzge != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(18, this.zzge.longValue());
        }
        if (this.zzgf != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(19, this.zzgf.longValue());
        }
        if (this.zzgg != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(20, this.zzgg.longValue());
        }
        if (this.zzgh != null) {
            com_google_android_gms_internal_ads_zzbfa.zzi(21, this.zzgh.longValue());
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int zzr = super.zzr();
        if (this.zzdo != null) {
            zzr += zzbfa.zzd(1, this.zzdo.longValue());
        }
        if (this.zzdp != null) {
            zzr += zzbfa.zzd(2, this.zzdp.longValue());
        }
        if (this.zzfp != null) {
            zzr += zzbfa.zzd(3, this.zzfp.longValue());
        }
        if (this.zzfq != null) {
            zzr += zzbfa.zzd(4, this.zzfq.longValue());
        }
        if (this.zzfr != null) {
            zzr += zzbfa.zzd(5, this.zzfr.longValue());
        }
        if (this.zzfs != null) {
            zzr += zzbfa.zzd(6, this.zzfs.longValue());
        }
        if (this.zzft != null) {
            zzr += zzbfa.zzq(7, this.zzft.intValue());
        }
        if (this.zzfu != null) {
            zzr += zzbfa.zzd(8, this.zzfu.longValue());
        }
        if (this.zzfv != null) {
            zzr += zzbfa.zzd(9, this.zzfv.longValue());
        }
        if (this.zzfw != null) {
            zzr += zzbfa.zzd(10, this.zzfw.longValue());
        }
        if (this.zzfx != null) {
            zzr += zzbfa.zzq(11, this.zzfx.intValue());
        }
        if (this.zzfy != null) {
            zzr += zzbfa.zzd(12, this.zzfy.longValue());
        }
        if (this.zzfz != null) {
            zzr += zzbfa.zzd(13, this.zzfz.longValue());
        }
        if (this.zzga != null) {
            zzr += zzbfa.zzd(14, this.zzga.longValue());
        }
        if (this.zzgb != null) {
            zzr += zzbfa.zzd(15, this.zzgb.longValue());
        }
        if (this.zzgc != null) {
            zzr += zzbfa.zzd(16, this.zzgc.longValue());
        }
        if (this.zzgd != null) {
            zzr += zzbfa.zzd(17, this.zzgd.longValue());
        }
        if (this.zzge != null) {
            zzr += zzbfa.zzd(18, this.zzge.longValue());
        }
        if (this.zzgf != null) {
            zzr += zzbfa.zzd(19, this.zzgf.longValue());
        }
        if (this.zzgg != null) {
            zzr += zzbfa.zzd(20, this.zzgg.longValue());
        }
        return this.zzgh != null ? zzr + zzbfa.zzd(21, this.zzgh.longValue()) : zzr;
    }
}
