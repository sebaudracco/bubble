package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public final class zzgy extends zzfu<zzgy> implements Cloneable {
    private String[] zzbiw;
    private String[] zzbix;
    private int[] zzbiy;
    private long[] zzbiz;
    private long[] zzbja;

    public zzgy() {
        this.zzbiw = zzgb.zzsc;
        this.zzbix = zzgb.zzsc;
        this.zzbiy = zzgb.zzrx;
        this.zzbiz = zzgb.zzry;
        this.zzbja = zzgb.zzry;
        this.zzrj = null;
        this.zzrs = -1;
    }

    private final zzgy zzgb() {
        try {
            zzgy com_google_android_gms_internal_clearcut_zzgy = (zzgy) super.zzeo();
            if (this.zzbiw != null && this.zzbiw.length > 0) {
                com_google_android_gms_internal_clearcut_zzgy.zzbiw = (String[]) this.zzbiw.clone();
            }
            if (this.zzbix != null && this.zzbix.length > 0) {
                com_google_android_gms_internal_clearcut_zzgy.zzbix = (String[]) this.zzbix.clone();
            }
            if (this.zzbiy != null && this.zzbiy.length > 0) {
                com_google_android_gms_internal_clearcut_zzgy.zzbiy = (int[]) this.zzbiy.clone();
            }
            if (this.zzbiz != null && this.zzbiz.length > 0) {
                com_google_android_gms_internal_clearcut_zzgy.zzbiz = (long[]) this.zzbiz.clone();
            }
            if (this.zzbja != null && this.zzbja.length > 0) {
                com_google_android_gms_internal_clearcut_zzgy.zzbja = (long[]) this.zzbja.clone();
            }
            return com_google_android_gms_internal_clearcut_zzgy;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzgb();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgy)) {
            return false;
        }
        zzgy com_google_android_gms_internal_clearcut_zzgy = (zzgy) obj;
        return !zzfy.equals(this.zzbiw, com_google_android_gms_internal_clearcut_zzgy.zzbiw) ? false : !zzfy.equals(this.zzbix, com_google_android_gms_internal_clearcut_zzgy.zzbix) ? false : !zzfy.equals(this.zzbiy, com_google_android_gms_internal_clearcut_zzgy.zzbiy) ? false : !zzfy.equals(this.zzbiz, com_google_android_gms_internal_clearcut_zzgy.zzbiz) ? false : !zzfy.equals(this.zzbja, com_google_android_gms_internal_clearcut_zzgy.zzbja) ? false : (this.zzrj == null || this.zzrj.isEmpty()) ? com_google_android_gms_internal_clearcut_zzgy.zzrj == null || com_google_android_gms_internal_clearcut_zzgy.zzrj.isEmpty() : this.zzrj.equals(com_google_android_gms_internal_clearcut_zzgy.zzrj);
    }

    public final int hashCode() {
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + zzfy.hashCode(this.zzbiw)) * 31) + zzfy.hashCode(this.zzbix)) * 31) + zzfy.hashCode(this.zzbiy)) * 31) + zzfy.hashCode(this.zzbiz)) * 31) + zzfy.hashCode(this.zzbja)) * 31;
        int hashCode2 = (this.zzrj == null || this.zzrj.isEmpty()) ? 0 : this.zzrj.hashCode();
        return hashCode2 + hashCode;
    }

    public final void zza(zzfs com_google_android_gms_internal_clearcut_zzfs) throws IOException {
        int i = 0;
        if (this.zzbiw != null && this.zzbiw.length > 0) {
            for (String str : this.zzbiw) {
                if (str != null) {
                    com_google_android_gms_internal_clearcut_zzfs.zza(1, str);
                }
            }
        }
        if (this.zzbix != null && this.zzbix.length > 0) {
            for (String str2 : this.zzbix) {
                if (str2 != null) {
                    com_google_android_gms_internal_clearcut_zzfs.zza(2, str2);
                }
            }
        }
        if (this.zzbiy != null && this.zzbiy.length > 0) {
            for (int zzc : this.zzbiy) {
                com_google_android_gms_internal_clearcut_zzfs.zzc(3, zzc);
            }
        }
        if (this.zzbiz != null && this.zzbiz.length > 0) {
            for (long zzi : this.zzbiz) {
                com_google_android_gms_internal_clearcut_zzfs.zzi(4, zzi);
            }
        }
        if (this.zzbja != null && this.zzbja.length > 0) {
            while (i < this.zzbja.length) {
                com_google_android_gms_internal_clearcut_zzfs.zzi(5, this.zzbja[i]);
                i++;
            }
        }
        super.zza(com_google_android_gms_internal_clearcut_zzfs);
    }

    protected final int zzen() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int zzen = super.zzen();
        if (this.zzbiw == null || this.zzbiw.length <= 0) {
            i = zzen;
        } else {
            i2 = 0;
            i3 = 0;
            for (String str : this.zzbiw) {
                if (str != null) {
                    i3++;
                    i2 += zzfs.zzh(str);
                }
            }
            i = (zzen + i2) + (i3 * 1);
        }
        if (this.zzbix != null && this.zzbix.length > 0) {
            i3 = 0;
            zzen = 0;
            for (String str2 : this.zzbix) {
                if (str2 != null) {
                    zzen++;
                    i3 += zzfs.zzh(str2);
                }
            }
            i = (i + i3) + (zzen * 1);
        }
        if (this.zzbiy != null && this.zzbiy.length > 0) {
            i3 = 0;
            for (int zzen2 : this.zzbiy) {
                i3 += zzfs.zzs(zzen2);
            }
            i = (i + i3) + (this.zzbiy.length * 1);
        }
        if (this.zzbiz != null && this.zzbiz.length > 0) {
            i3 = 0;
            for (long zzo : this.zzbiz) {
                i3 += zzfs.zzo(zzo);
            }
            i = (i + i3) + (this.zzbiz.length * 1);
        }
        if (this.zzbja == null || this.zzbja.length <= 0) {
            return i;
        }
        i2 = 0;
        while (i4 < this.zzbja.length) {
            i2 += zzfs.zzo(this.zzbja[i4]);
            i4++;
        }
        return (i + i2) + (this.zzbja.length * 1);
    }

    public final /* synthetic */ zzfu zzeo() throws CloneNotSupportedException {
        return (zzgy) clone();
    }

    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzgy) clone();
    }
}
