package com.google.android.gms.internal.ads;

@zzadh
public final class zzasi {
    public final int heightPixels;
    private final int type;
    public final int widthPixels;

    private zzasi(int i, int i2, int i3) {
        this.type = i;
        this.widthPixels = i2;
        this.heightPixels = i3;
    }

    public static zzasi zzb(zzjn com_google_android_gms_internal_ads_zzjn) {
        return com_google_android_gms_internal_ads_zzjn.zzarc ? new zzasi(3, 0, 0) : com_google_android_gms_internal_ads_zzjn.zzarf ? new zzasi(2, 0, 0) : com_google_android_gms_internal_ads_zzjn.zzare ? zzvq() : zzi(com_google_android_gms_internal_ads_zzjn.widthPixels, com_google_android_gms_internal_ads_zzjn.heightPixels);
    }

    public static zzasi zzi(int i, int i2) {
        return new zzasi(1, i, i2);
    }

    public static zzasi zzvq() {
        return new zzasi(0, 0, 0);
    }

    public static zzasi zzvr() {
        return new zzasi(4, 0, 0);
    }

    public final boolean isFluid() {
        return this.type == 2;
    }

    public final boolean zzvs() {
        return this.type == 3;
    }

    public final boolean zzvt() {
        return this.type == 0;
    }

    public final boolean zzvu() {
        return this.type == 4;
    }
}
