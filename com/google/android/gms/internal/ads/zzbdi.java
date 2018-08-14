package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbbo.zze;

final class zzbdi implements zzbcs {
    private final String info;
    private final zzbcu zzdwl;
    private final zzbdj zzdxe;

    zzbdi(zzbcu com_google_android_gms_internal_ads_zzbcu, String str, Object[] objArr) {
        this.zzdwl = com_google_android_gms_internal_ads_zzbcu;
        this.info = str;
        this.zzdxe = new zzbdj(com_google_android_gms_internal_ads_zzbcu.getClass(), str, objArr);
    }

    public final int getFieldCount() {
        return this.zzdxe.zzdxh;
    }

    public final int zzaeh() {
        return (this.zzdxe.flags & 1) == 1 ? zze.zzdui : zze.zzduj;
    }

    public final boolean zzaei() {
        return (this.zzdxe.flags & 2) == 2;
    }

    public final zzbcu zzaej() {
        return this.zzdwl;
    }

    final zzbdj zzaeq() {
        return this.zzdxe;
    }

    public final int zzaer() {
        return this.zzdxe.zzdwi;
    }

    public final int zzaes() {
        return this.zzdxe.zzdwj;
    }

    public final int zzaet() {
        return this.zzdxe.zzdxk;
    }

    public final int zzaeu() {
        return this.zzdxe.zzdxm;
    }

    final int[] zzaev() {
        return this.zzdxe.zzdwq;
    }

    public final int zzaew() {
        return this.zzdxe.zzdxl;
    }

    public final int zzaex() {
        return this.zzdxe.zzdwk;
    }
}
