package com.google.android.gms.internal.clearcut;

import java.io.IOException;

public final class zzhb extends zzfu<zzhb> implements Cloneable {
    private static volatile zzhb[] zzbkd;
    private String value;
    private String zzbke;

    public zzhb() {
        this.zzbke = "";
        this.value = "";
        this.zzrj = null;
        this.zzrs = -1;
    }

    public static zzhb[] zzge() {
        if (zzbkd == null) {
            synchronized (zzfy.zzrr) {
                if (zzbkd == null) {
                    zzbkd = new zzhb[0];
                }
            }
        }
        return zzbkd;
    }

    private final zzhb zzgf() {
        try {
            return (zzhb) super.zzeo();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzgf();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzhb)) {
            return false;
        }
        zzhb com_google_android_gms_internal_clearcut_zzhb = (zzhb) obj;
        if (this.zzbke == null) {
            if (com_google_android_gms_internal_clearcut_zzhb.zzbke != null) {
                return false;
            }
        } else if (!this.zzbke.equals(com_google_android_gms_internal_clearcut_zzhb.zzbke)) {
            return false;
        }
        if (this.value == null) {
            if (com_google_android_gms_internal_clearcut_zzhb.value != null) {
                return false;
            }
        } else if (!this.value.equals(com_google_android_gms_internal_clearcut_zzhb.value)) {
            return false;
        }
        return (this.zzrj == null || this.zzrj.isEmpty()) ? com_google_android_gms_internal_clearcut_zzhb.zzrj == null || com_google_android_gms_internal_clearcut_zzhb.zzrj.isEmpty() : this.zzrj.equals(com_google_android_gms_internal_clearcut_zzhb.zzrj);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.zzbke == null ? 0 : this.zzbke.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
        if (!(this.zzrj == null || this.zzrj.isEmpty())) {
            i = this.zzrj.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzfs com_google_android_gms_internal_clearcut_zzfs) throws IOException {
        if (!(this.zzbke == null || this.zzbke.equals(""))) {
            com_google_android_gms_internal_clearcut_zzfs.zza(1, this.zzbke);
        }
        if (!(this.value == null || this.value.equals(""))) {
            com_google_android_gms_internal_clearcut_zzfs.zza(2, this.value);
        }
        super.zza(com_google_android_gms_internal_clearcut_zzfs);
    }

    protected final int zzen() {
        int zzen = super.zzen();
        if (!(this.zzbke == null || this.zzbke.equals(""))) {
            zzen += zzfs.zzb(1, this.zzbke);
        }
        return (this.value == null || this.value.equals("")) ? zzen : zzen + zzfs.zzb(2, this.value);
    }

    public final /* synthetic */ zzfu zzeo() throws CloneNotSupportedException {
        return (zzhb) clone();
    }

    public final /* synthetic */ zzfz zzep() throws CloneNotSupportedException {
        return (zzhb) clone();
    }
}
