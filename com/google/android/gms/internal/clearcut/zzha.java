package com.google.android.gms.internal.clearcut;

import com.google.android.gms.internal.clearcut.zzge.zzd;
import com.google.android.gms.internal.clearcut.zzge.zzs;
import java.io.IOException;
import java.util.Arrays;

public final class zzha extends zzfu<zzha> implements Cloneable {
    private String tag;
    public long zzbjf;
    public long zzbjg;
    private long zzbjh;
    public int zzbji;
    private String zzbjj;
    private int zzbjk;
    private boolean zzbjl;
    private zzhb[] zzbjm;
    private byte[] zzbjn;
    private zzd zzbjo;
    public byte[] zzbjp;
    private String zzbjq;
    private String zzbjr;
    private zzgy zzbjs;
    private String zzbjt;
    public long zzbju;
    private zzgz zzbjv;
    public byte[] zzbjw;
    private String zzbjx;
    private int zzbjy;
    private int[] zzbjz;
    private long zzbka;
    private zzs zzbkb;
    public boolean zzbkc;

    public zzha() {
        this.zzbjf = 0;
        this.zzbjg = 0;
        this.zzbjh = 0;
        this.tag = "";
        this.zzbji = 0;
        this.zzbjj = "";
        this.zzbjk = 0;
        this.zzbjl = false;
        this.zzbjm = zzhb.zzge();
        this.zzbjn = zzgb.zzse;
        this.zzbjo = null;
        this.zzbjp = zzgb.zzse;
        this.zzbjq = "";
        this.zzbjr = "";
        this.zzbjs = null;
        this.zzbjt = "";
        this.zzbju = 180000;
        this.zzbjv = null;
        this.zzbjw = zzgb.zzse;
        this.zzbjx = "";
        this.zzbjy = 0;
        this.zzbjz = zzgb.zzrx;
        this.zzbka = 0;
        this.zzbkb = null;
        this.zzbkc = false;
        this.zzrj = null;
        this.zzrs = -1;
    }

    private final zzha zzgd() {
        try {
            zzha com_google_android_gms_internal_clearcut_zzha = (zzha) super.zzeo();
            if (this.zzbjm != null && this.zzbjm.length > 0) {
                com_google_android_gms_internal_clearcut_zzha.zzbjm = new zzhb[this.zzbjm.length];
                for (int i = 0; i < this.zzbjm.length; i++) {
                    if (this.zzbjm[i] != null) {
                        com_google_android_gms_internal_clearcut_zzha.zzbjm[i] = (zzhb) this.zzbjm[i].clone();
                    }
                }
            }
            if (this.zzbjo != null) {
                com_google_android_gms_internal_clearcut_zzha.zzbjo = this.zzbjo;
            }
            if (this.zzbjs != null) {
                com_google_android_gms_internal_clearcut_zzha.zzbjs = (zzgy) this.zzbjs.clone();
            }
            if (this.zzbjv != null) {
                com_google_android_gms_internal_clearcut_zzha.zzbjv = (zzgz) this.zzbjv.clone();
            }
            if (this.zzbjz != null && this.zzbjz.length > 0) {
                com_google_android_gms_internal_clearcut_zzha.zzbjz = (int[]) this.zzbjz.clone();
            }
            if (this.zzbkb != null) {
                com_google_android_gms_internal_clearcut_zzha.zzbkb = this.zzbkb;
            }
            return com_google_android_gms_internal_clearcut_zzha;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzgd();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzha)) {
            return false;
        }
        zzha com_google_android_gms_internal_clearcut_zzha = (zzha) obj;
        if (this.zzbjf != com_google_android_gms_internal_clearcut_zzha.zzbjf) {
            return false;
        }
        if (this.zzbjg != com_google_android_gms_internal_clearcut_zzha.zzbjg) {
            return false;
        }
        if (0 != 0) {
            return false;
        }
        if (this.tag == null) {
            if (com_google_android_gms_internal_clearcut_zzha.tag != null) {
                return false;
            }
        } else if (!this.tag.equals(com_google_android_gms_internal_clearcut_zzha.tag)) {
            return false;
        }
        if (this.zzbji != com_google_android_gms_internal_clearcut_zzha.zzbji) {
            return false;
        }
        if (this.zzbjj == null) {
            if (com_google_android_gms_internal_clearcut_zzha.zzbjj != null) {
                return false;
            }
        } else if (!this.zzbjj.equals(com_google_android_gms_internal_clearcut_zzha.zzbjj)) {
            return false;
        }
        if (!zzfy.equals(this.zzbjm, com_google_android_gms_internal_clearcut_zzha.zzbjm)) {
            return false;
        }
        if (!Arrays.equals(this.zzbjn, com_google_android_gms_internal_clearcut_zzha.zzbjn)) {
            return false;
        }
        if (this.zzbjo == null) {
            if (com_google_android_gms_internal_clearcut_zzha.zzbjo != null) {
                return false;
            }
        } else if (!this.zzbjo.equals(com_google_android_gms_internal_clearcut_zzha.zzbjo)) {
            return false;
        }
        if (!Arrays.equals(this.zzbjp, com_google_android_gms_internal_clearcut_zzha.zzbjp)) {
            return false;
        }
        if (this.zzbjq == null) {
            if (com_google_android_gms_internal_clearcut_zzha.zzbjq != null) {
                return false;
            }
        } else if (!this.zzbjq.equals(com_google_android_gms_internal_clearcut_zzha.zzbjq)) {
            return false;
        }
        if (this.zzbjr == null) {
            if (com_google_android_gms_internal_clearcut_zzha.zzbjr != null) {
                return false;
            }
        } else if (!this.zzbjr.equals(com_google_android_gms_internal_clearcut_zzha.zzbjr)) {
            return false;
        }
        if (this.zzbjs == null) {
            if (com_google_android_gms_internal_clearcut_zzha.zzbjs != null) {
                return false;
            }
        } else if (!this.zzbjs.equals(com_google_android_gms_internal_clearcut_zzha.zzbjs)) {
            return false;
        }
        if (this.zzbjt == null) {
            if (com_google_android_gms_internal_clearcut_zzha.zzbjt != null) {
                return false;
            }
        } else if (!this.zzbjt.equals(com_google_android_gms_internal_clearcut_zzha.zzbjt)) {
            return false;
        }
        if (this.zzbju != com_google_android_gms_internal_clearcut_zzha.zzbju) {
            return false;
        }
        if (this.zzbjv == null) {
            if (com_google_android_gms_internal_clearcut_zzha.zzbjv != null) {
                return false;
            }
        } else if (!this.zzbjv.equals(com_google_android_gms_internal_clearcut_zzha.zzbjv)) {
            return false;
        }
        if (!Arrays.equals(this.zzbjw, com_google_android_gms_internal_clearcut_zzha.zzbjw)) {
            return false;
        }
        if (this.zzbjx == null) {
            if (com_google_android_gms_internal_clearcut_zzha.zzbjx != null) {
                return false;
            }
        } else if (!this.zzbjx.equals(com_google_android_gms_internal_clearcut_zzha.zzbjx)) {
            return false;
        }
        if (!zzfy.equals(this.zzbjz, com_google_android_gms_internal_clearcut_zzha.zzbjz)) {
            return false;
        }
        if (0 != 0) {
            return false;
        }
        if (this.zzbkb == null) {
            if (com_google_android_gms_internal_clearcut_zzha.zzbkb != null) {
                return false;
            }
        } else if (!this.zzbkb.equals(com_google_android_gms_internal_clearcut_zzha.zzbkb)) {
            return false;
        }
        return this.zzbkc != com_google_android_gms_internal_clearcut_zzha.zzbkc ? false : (this.zzrj == null || this.zzrj.isEmpty()) ? com_google_android_gms_internal_clearcut_zzha.zzrj == null || com_google_android_gms_internal_clearcut_zzha.zzrj.isEmpty() : this.zzrj.equals(com_google_android_gms_internal_clearcut_zzha.zzrj);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((this.zzbjj == null ? 0 : this.zzbjj.hashCode()) + (((((this.tag == null ? 0 : this.tag.hashCode()) + (((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzbjf ^ (this.zzbjf >>> 32)))) * 31) + ((int) (this.zzbjg ^ (this.zzbjg >>> 32)))) * 31) * 31)) * 31) + this.zzbji) * 31)) * 31) * 31) + 1237) * 31) + zzfy.hashCode(this.zzbjm)) * 31) + Arrays.hashCode(this.zzbjn);
        zzcg com_google_android_gms_internal_clearcut_zzcg = this.zzbjo;
        hashCode = (this.zzbjr == null ? 0 : this.zzbjr.hashCode()) + (((this.zzbjq == null ? 0 : this.zzbjq.hashCode()) + (((((com_google_android_gms_internal_clearcut_zzcg == null ? 0 : com_google_android_gms_internal_clearcut_zzcg.hashCode()) + (hashCode * 31)) * 31) + Arrays.hashCode(this.zzbjp)) * 31)) * 31);
        zzgy com_google_android_gms_internal_clearcut_zzgy = this.zzbjs;
        hashCode = (((this.zzbjt == null ? 0 : this.zzbjt.hashCode()) + (((com_google_android_gms_internal_clearcut_zzgy == null ? 0 : com_google_android_gms_internal_clearcut_zzgy.hashCode()) + (hashCode * 31)) * 31)) * 31) + ((int) (this.zzbju ^ (this.zzbju >>> 32)));
        zzgz com_google_android_gms_internal_clearcut_zzgz = this.zzbjv;
        hashCode = (((((this.zzbjx == null ? 0 : this.zzbjx.hashCode()) + (((((com_google_android_gms_internal_clearcut_zzgz == null ? 0 : com_google_android_gms_internal_clearcut_zzgz.hashCode()) + (hashCode * 31)) * 31) + Arrays.hashCode(this.zzbjw)) * 31)) * 31) * 31) + zzfy.hashCode(this.zzbjz)) * 31;
        com_google_android_gms_internal_clearcut_zzcg = this.zzbkb;
        hashCode = ((this.zzbkc ? 1231 : 1237) + (((com_google_android_gms_internal_clearcut_zzcg == null ? 0 : com_google_android_gms_internal_clearcut_zzcg.hashCode()) + (hashCode * 31)) * 31)) * 31;
        if (!(this.zzrj == null || this.zzrj.isEmpty())) {
            i = this.zzrj.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzfs com_google_android_gms_internal_clearcut_zzfs) throws IOException {
        int i = 0;
        if (this.zzbjf != 0) {
            com_google_android_gms_internal_clearcut_zzfs.zzi(1, this.zzbjf);
        }
        if (!(this.tag == null || this.tag.equals(""))) {
            com_google_android_gms_internal_clearcut_zzfs.zza(2, this.tag);
        }
        if (this.zzbjm != null && this.zzbjm.length > 0) {
            for (zzfz com_google_android_gms_internal_clearcut_zzfz : this.zzbjm) {
                if (com_google_android_gms_internal_clearcut_zzfz != null) {
                    com_google_android_gms_internal_clearcut_zzfs.zza(3, com_google_android_gms_internal_clearcut_zzfz);
                }
            }
        }
        if (!Arrays.equals(this.zzbjn, zzgb.zzse)) {
            com_google_android_gms_internal_clearcut_zzfs.zza(4, this.zzbjn);
        }
        if (!Arrays.equals(this.zzbjp, zzgb.zzse)) {
            com_google_android_gms_internal_clearcut_zzfs.zza(6, this.zzbjp);
        }
        if (this.zzbjs != null) {
            com_google_android_gms_internal_clearcut_zzfs.zza(7, this.zzbjs);
        }
        if (!(this.zzbjq == null || this.zzbjq.equals(""))) {
            com_google_android_gms_internal_clearcut_zzfs.zza(8, this.zzbjq);
        }
        if (this.zzbjo != null) {
            com_google_android_gms_internal_clearcut_zzfs.zze(9, this.zzbjo);
        }
        if (this.zzbji != 0) {
            com_google_android_gms_internal_clearcut_zzfs.zzc(11, this.zzbji);
        }
        if (!(this.zzbjr == null || this.zzbjr.equals(""))) {
            com_google_android_gms_internal_clearcut_zzfs.zza(13, this.zzbjr);
        }
        if (!(this.zzbjt == null || this.zzbjt.equals(""))) {
            com_google_android_gms_internal_clearcut_zzfs.zza(14, this.zzbjt);
        }
        if (this.zzbju != 180000) {
            long j = this.zzbju;
            com_google_android_gms_internal_clearcut_zzfs.zzb(15, 0);
            com_google_android_gms_internal_clearcut_zzfs.zzn(zzfs.zzj(j));
        }
        if (this.zzbjv != null) {
            com_google_android_gms_internal_clearcut_zzfs.zza(16, this.zzbjv);
        }
        if (this.zzbjg != 0) {
            com_google_android_gms_internal_clearcut_zzfs.zzi(17, this.zzbjg);
        }
        if (!Arrays.equals(this.zzbjw, zzgb.zzse)) {
            com_google_android_gms_internal_clearcut_zzfs.zza(18, this.zzbjw);
        }
        if (this.zzbjz != null && this.zzbjz.length > 0) {
            while (i < this.zzbjz.length) {
                com_google_android_gms_internal_clearcut_zzfs.zzc(20, this.zzbjz[i]);
                i++;
            }
        }
        if (0 != 0) {
            com_google_android_gms_internal_clearcut_zzfs.zzi(21, 0);
        }
        if (0 != 0) {
            com_google_android_gms_internal_clearcut_zzfs.zzi(22, 0);
        }
        if (this.zzbkb != null) {
            com_google_android_gms_internal_clearcut_zzfs.zze(23, this.zzbkb);
        }
        if (!(this.zzbjx == null || this.zzbjx.equals(""))) {
            com_google_android_gms_internal_clearcut_zzfs.zza(24, this.zzbjx);
        }
        if (this.zzbkc) {
            com_google_android_gms_internal_clearcut_zzfs.zzb(25, this.zzbkc);
        }
        if (!(this.zzbjj == null || this.zzbjj.equals(""))) {
            com_google_android_gms_internal_clearcut_zzfs.zza(26, this.zzbjj);
        }
        super.zza(com_google_android_gms_internal_clearcut_zzfs);
    }

    protected final int zzen() {
        int i;
        int i2 = 0;
        int zzen = super.zzen();
        if (this.zzbjf != 0) {
            zzen += zzfs.zzd(1, this.zzbjf);
        }
        if (!(this.tag == null || this.tag.equals(""))) {
            zzen += zzfs.zzb(2, this.tag);
        }
        if (this.zzbjm != null && this.zzbjm.length > 0) {
            i = zzen;
            for (zzfz com_google_android_gms_internal_clearcut_zzfz : this.zzbjm) {
                if (com_google_android_gms_internal_clearcut_zzfz != null) {
                    i += zzfs.zzb(3, com_google_android_gms_internal_clearcut_zzfz);
                }
            }
            zzen = i;
        }
        if (!Arrays.equals(this.zzbjn, zzgb.zzse)) {
            zzen += zzfs.zzb(4, this.zzbjn);
        }
        if (!Arrays.equals(this.zzbjp, zzgb.zzse)) {
            zzen += zzfs.zzb(6, this.zzbjp);
        }
        if (this.zzbjs != null) {
            zzen += zzfs.zzb(7, this.zzbjs);
        }
        if (!(this.zzbjq == null || this.zzbjq.equals(""))) {
            zzen += zzfs.zzb(8, this.zzbjq);
        }
        if (this.zzbjo != null) {
            zzen += zzbn.zzc(9, this.zzbjo);
        }
        if (this.zzbji != 0) {
            zzen += zzfs.zzr(11) + zzfs.zzs(this.zzbji);
        }
        if (!(this.zzbjr == null || this.zzbjr.equals(""))) {
            zzen += zzfs.zzb(13, this.zzbjr);
        }
        if (!(this.zzbjt == null || this.zzbjt.equals(""))) {
            zzen += zzfs.zzb(14, this.zzbjt);
        }
        if (this.zzbju != 180000) {
            zzen += zzfs.zzo(zzfs.zzj(this.zzbju)) + zzfs.zzr(15);
        }
        if (this.zzbjv != null) {
            zzen += zzfs.zzb(16, this.zzbjv);
        }
        if (this.zzbjg != 0) {
            zzen += zzfs.zzd(17, this.zzbjg);
        }
        if (!Arrays.equals(this.zzbjw, zzgb.zzse)) {
            zzen += zzfs.zzb(18, this.zzbjw);
        }
        if (this.zzbjz != null && this.zzbjz.length > 0) {
            i = 0;
            while (i2 < this.zzbjz.length) {
                i += zzfs.zzs(this.zzbjz[i2]);
                i2++;
            }
            zzen = (zzen + i) + (this.zzbjz.length * 2);
        }
        if (0 != 0) {
            zzen += zzfs.zzd(21, 0);
        }
        if (0 != 0) {
            zzen += zzfs.zzd(22, 0);
        }
        if (this.zzbkb != null) {
            zzen += zzbn.zzc(23, this.zzbkb);
        }
        if (!(this.zzbjx == null || this.zzbjx.equals(""))) {
            zzen += zzfs.zzb(24, this.zzbjx);
        }
        if (this.zzbkc) {
            zzen += zzfs.zzr(25) + 1;
        }
        return (this.zzbjj == null || this.zzbjj.equals("")) ? zzen : zzen + zzfs.zzb(26, this.zzbjj);
    }

    public final /* synthetic */ zzfu zzeo() throws CloneNotSupportedException {
        return (zzha) clone();
    }

    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzha) clone();
    }
}
