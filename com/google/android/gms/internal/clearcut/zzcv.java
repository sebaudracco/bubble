package com.google.android.gms.internal.clearcut;

public class zzcv {
    private static final zzbt zzez = zzbt.zzan();
    private zzbb zzln;
    private volatile zzdo zzlo;
    private volatile zzbb zzlp;

    private final zzdo zzh(zzdo com_google_android_gms_internal_clearcut_zzdo) {
        if (this.zzlo == null) {
            synchronized (this) {
                if (this.zzlo != null) {
                } else {
                    try {
                        this.zzlo = com_google_android_gms_internal_clearcut_zzdo;
                        this.zzlp = zzbb.zzfi;
                    } catch (zzco e) {
                        this.zzlo = com_google_android_gms_internal_clearcut_zzdo;
                        this.zzlp = zzbb.zzfi;
                    }
                }
            }
        }
        return this.zzlo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcv)) {
            return false;
        }
        zzcv com_google_android_gms_internal_clearcut_zzcv = (zzcv) obj;
        zzdo com_google_android_gms_internal_clearcut_zzdo = this.zzlo;
        zzdo com_google_android_gms_internal_clearcut_zzdo2 = com_google_android_gms_internal_clearcut_zzcv.zzlo;
        return (com_google_android_gms_internal_clearcut_zzdo == null && com_google_android_gms_internal_clearcut_zzdo2 == null) ? zzr().equals(com_google_android_gms_internal_clearcut_zzcv.zzr()) : (com_google_android_gms_internal_clearcut_zzdo == null || com_google_android_gms_internal_clearcut_zzdo2 == null) ? com_google_android_gms_internal_clearcut_zzdo != null ? com_google_android_gms_internal_clearcut_zzdo.equals(com_google_android_gms_internal_clearcut_zzcv.zzh(com_google_android_gms_internal_clearcut_zzdo.zzbe())) : zzh(com_google_android_gms_internal_clearcut_zzdo2.zzbe()).equals(com_google_android_gms_internal_clearcut_zzdo2) : com_google_android_gms_internal_clearcut_zzdo.equals(com_google_android_gms_internal_clearcut_zzdo2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zzas() {
        return this.zzlp != null ? this.zzlp.size() : this.zzlo != null ? this.zzlo.zzas() : 0;
    }

    public final zzdo zzi(zzdo com_google_android_gms_internal_clearcut_zzdo) {
        zzdo com_google_android_gms_internal_clearcut_zzdo2 = this.zzlo;
        this.zzln = null;
        this.zzlp = null;
        this.zzlo = com_google_android_gms_internal_clearcut_zzdo;
        return com_google_android_gms_internal_clearcut_zzdo2;
    }

    public final zzbb zzr() {
        if (this.zzlp != null) {
            return this.zzlp;
        }
        synchronized (this) {
            if (this.zzlp != null) {
                zzbb com_google_android_gms_internal_clearcut_zzbb = this.zzlp;
                return com_google_android_gms_internal_clearcut_zzbb;
            }
            if (this.zzlo == null) {
                this.zzlp = zzbb.zzfi;
            } else {
                this.zzlp = this.zzlo.zzr();
            }
            com_google_android_gms_internal_clearcut_zzbb = this.zzlp;
            return com_google_android_gms_internal_clearcut_zzbb;
        }
    }
}
