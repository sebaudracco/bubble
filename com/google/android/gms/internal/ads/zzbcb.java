package com.google.android.gms.internal.ads;

public class zzbcb {
    private static final zzbbb zzdph = zzbbb.zzacr();
    private zzbah zzdvk;
    private volatile zzbcu zzdvl;
    private volatile zzbah zzdvm;

    private final zzbcu zzk(zzbcu com_google_android_gms_internal_ads_zzbcu) {
        if (this.zzdvl == null) {
            synchronized (this) {
                if (this.zzdvl != null) {
                } else {
                    try {
                        this.zzdvl = com_google_android_gms_internal_ads_zzbcu;
                        this.zzdvm = zzbah.zzdpq;
                    } catch (zzbbu e) {
                        this.zzdvl = com_google_android_gms_internal_ads_zzbcu;
                        this.zzdvm = zzbah.zzdpq;
                    }
                }
            }
        }
        return this.zzdvl;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbcb)) {
            return false;
        }
        zzbcb com_google_android_gms_internal_ads_zzbcb = (zzbcb) obj;
        zzbcu com_google_android_gms_internal_ads_zzbcu = this.zzdvl;
        zzbcu com_google_android_gms_internal_ads_zzbcu2 = com_google_android_gms_internal_ads_zzbcb.zzdvl;
        return (com_google_android_gms_internal_ads_zzbcu == null && com_google_android_gms_internal_ads_zzbcu2 == null) ? zzaav().equals(com_google_android_gms_internal_ads_zzbcb.zzaav()) : (com_google_android_gms_internal_ads_zzbcu == null || com_google_android_gms_internal_ads_zzbcu2 == null) ? com_google_android_gms_internal_ads_zzbcu != null ? com_google_android_gms_internal_ads_zzbcu.equals(com_google_android_gms_internal_ads_zzbcb.zzk(com_google_android_gms_internal_ads_zzbcu.zzadg())) : zzk(com_google_android_gms_internal_ads_zzbcu2.zzadg()).equals(com_google_android_gms_internal_ads_zzbcu2) : com_google_android_gms_internal_ads_zzbcu.equals(com_google_android_gms_internal_ads_zzbcu2);
    }

    public int hashCode() {
        return 1;
    }

    public final zzbah zzaav() {
        if (this.zzdvm != null) {
            return this.zzdvm;
        }
        synchronized (this) {
            if (this.zzdvm != null) {
                zzbah com_google_android_gms_internal_ads_zzbah = this.zzdvm;
                return com_google_android_gms_internal_ads_zzbah;
            }
            if (this.zzdvl == null) {
                this.zzdvm = zzbah.zzdpq;
            } else {
                this.zzdvm = this.zzdvl.zzaav();
            }
            com_google_android_gms_internal_ads_zzbah = this.zzdvm;
            return com_google_android_gms_internal_ads_zzbah;
        }
    }

    public final int zzacw() {
        return this.zzdvm != null ? this.zzdvm.size() : this.zzdvl != null ? this.zzdvl.zzacw() : 0;
    }

    public final zzbcu zzl(zzbcu com_google_android_gms_internal_ads_zzbcu) {
        zzbcu com_google_android_gms_internal_ads_zzbcu2 = this.zzdvl;
        this.zzdvk = null;
        this.zzdvm = null;
        this.zzdvl = com_google_android_gms_internal_ads_zzbcu;
        return com_google_android_gms_internal_ads_zzbcu2;
    }
}
