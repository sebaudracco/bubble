package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzii extends zzbfc<zzii> {
    private Integer zzang;
    public String zzanh;
    private Integer zzani;
    private Integer zzanj;
    private zzit zzank;
    public long[] zzanl;
    public zzig zzanm;
    private zzih zzann;
    private zzim zzano;
    public zzib zzanp;

    public zzii() {
        this.zzang = null;
        this.zzanh = null;
        this.zzani = null;
        this.zzanj = null;
        this.zzank = null;
        this.zzanl = zzbfl.zzebz;
        this.zzanm = null;
        this.zzann = null;
        this.zzano = null;
        this.zzanp = null;
        this.zzebk = null;
        this.zzebt = -1;
    }

    private final zzii zzk(zzbez com_google_android_gms_internal_ads_zzbez) throws IOException {
        while (true) {
            int zzabk = com_google_android_gms_internal_ads_zzbez.zzabk();
            int position;
            switch (zzabk) {
                case 0:
                    break;
                case 72:
                    this.zzang = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 82:
                    this.zzanh = com_google_android_gms_internal_ads_zzbez.readString();
                    continue;
                case 88:
                    this.zzani = Integer.valueOf(com_google_android_gms_internal_ads_zzbez.zzacc());
                    continue;
                case 96:
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    try {
                        this.zzanj = Integer.valueOf(zzia.zzd(com_google_android_gms_internal_ads_zzbez.zzacc()));
                        continue;
                    } catch (IllegalArgumentException e) {
                        com_google_android_gms_internal_ads_zzbez.zzdc(position);
                        zza(com_google_android_gms_internal_ads_zzbez, zzabk);
                        break;
                    }
                case 106:
                    if (this.zzank == null) {
                        this.zzank = new zzit();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzank);
                    continue;
                case 112:
                    position = zzbfl.zzb(com_google_android_gms_internal_ads_zzbez, 112);
                    zzabk = this.zzanl == null ? 0 : this.zzanl.length;
                    Object obj = new long[(position + zzabk)];
                    if (zzabk != 0) {
                        System.arraycopy(this.zzanl, 0, obj, 0, zzabk);
                    }
                    while (zzabk < obj.length - 1) {
                        obj[zzabk] = com_google_android_gms_internal_ads_zzbez.zzacd();
                        com_google_android_gms_internal_ads_zzbez.zzabk();
                        zzabk++;
                    }
                    obj[zzabk] = com_google_android_gms_internal_ads_zzbez.zzacd();
                    this.zzanl = obj;
                    continue;
                case 114:
                    int zzbr = com_google_android_gms_internal_ads_zzbez.zzbr(com_google_android_gms_internal_ads_zzbez.zzacc());
                    position = com_google_android_gms_internal_ads_zzbez.getPosition();
                    zzabk = 0;
                    while (com_google_android_gms_internal_ads_zzbez.zzagn() > 0) {
                        com_google_android_gms_internal_ads_zzbez.zzacd();
                        zzabk++;
                    }
                    com_google_android_gms_internal_ads_zzbez.zzdc(position);
                    position = this.zzanl == null ? 0 : this.zzanl.length;
                    Object obj2 = new long[(zzabk + position)];
                    if (position != 0) {
                        System.arraycopy(this.zzanl, 0, obj2, 0, position);
                    }
                    while (position < obj2.length) {
                        obj2[position] = com_google_android_gms_internal_ads_zzbez.zzacd();
                        position++;
                    }
                    this.zzanl = obj2;
                    com_google_android_gms_internal_ads_zzbez.zzbs(zzbr);
                    continue;
                case 122:
                    if (this.zzanm == null) {
                        this.zzanm = new zzig();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzanm);
                    continue;
                case 130:
                    if (this.zzann == null) {
                        this.zzann = new zzih();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzann);
                    continue;
                case 138:
                    if (this.zzano == null) {
                        this.zzano = new zzim();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzano);
                    continue;
                case 146:
                    if (this.zzanp == null) {
                        this.zzanp = new zzib();
                    }
                    com_google_android_gms_internal_ads_zzbez.zza(this.zzanp);
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
        return zzk(com_google_android_gms_internal_ads_zzbez);
    }

    public final void zza(zzbfa com_google_android_gms_internal_ads_zzbfa) throws IOException {
        int i = 0;
        if (this.zzang != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(9, this.zzang.intValue());
        }
        if (this.zzanh != null) {
            com_google_android_gms_internal_ads_zzbfa.zzf(10, this.zzanh);
        }
        if (this.zzani != null) {
            int intValue = this.zzani.intValue();
            com_google_android_gms_internal_ads_zzbfa.zzl(11, 0);
            com_google_android_gms_internal_ads_zzbfa.zzde(intValue);
        }
        if (this.zzanj != null) {
            com_google_android_gms_internal_ads_zzbfa.zzm(12, this.zzanj.intValue());
        }
        if (this.zzank != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(13, this.zzank);
        }
        if (this.zzanl != null && this.zzanl.length > 0) {
            while (i < this.zzanl.length) {
                com_google_android_gms_internal_ads_zzbfa.zza(14, this.zzanl[i]);
                i++;
            }
        }
        if (this.zzanm != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(15, this.zzanm);
        }
        if (this.zzann != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(16, this.zzann);
        }
        if (this.zzano != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(17, this.zzano);
        }
        if (this.zzanp != null) {
            com_google_android_gms_internal_ads_zzbfa.zza(18, this.zzanp);
        }
        super.zza(com_google_android_gms_internal_ads_zzbfa);
    }

    protected final int zzr() {
        int i = 0;
        int zzr = super.zzr();
        if (this.zzang != null) {
            zzr += zzbfa.zzq(9, this.zzang.intValue());
        }
        if (this.zzanh != null) {
            zzr += zzbfa.zzg(10, this.zzanh);
        }
        if (this.zzani != null) {
            zzr += zzbfa.zzcl(this.zzani.intValue()) + zzbfa.zzcd(11);
        }
        if (this.zzanj != null) {
            zzr += zzbfa.zzq(12, this.zzanj.intValue());
        }
        if (this.zzank != null) {
            zzr += zzbfa.zzb(13, this.zzank);
        }
        if (this.zzanl != null && this.zzanl.length > 0) {
            int i2 = 0;
            while (i < this.zzanl.length) {
                i2 += zzbfa.zzy(this.zzanl[i]);
                i++;
            }
            zzr = (zzr + i2) + (this.zzanl.length * 1);
        }
        if (this.zzanm != null) {
            zzr += zzbfa.zzb(15, this.zzanm);
        }
        if (this.zzann != null) {
            zzr += zzbfa.zzb(16, this.zzann);
        }
        if (this.zzano != null) {
            zzr += zzbfa.zzb(17, this.zzano);
        }
        return this.zzanp != null ? zzr + zzbfa.zzb(18, this.zzanp) : zzr;
    }
}
