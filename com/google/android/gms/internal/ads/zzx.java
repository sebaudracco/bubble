package com.google.android.gms.internal.ads;

public final class zzx<T> {
    public final T result;
    public final zzc zzbg;
    public final zzae zzbh;
    public boolean zzbi;

    private zzx(zzae com_google_android_gms_internal_ads_zzae) {
        this.zzbi = false;
        this.result = null;
        this.zzbg = null;
        this.zzbh = com_google_android_gms_internal_ads_zzae;
    }

    private zzx(T t, zzc com_google_android_gms_internal_ads_zzc) {
        this.zzbi = false;
        this.result = t;
        this.zzbg = com_google_android_gms_internal_ads_zzc;
        this.zzbh = null;
    }

    public static <T> zzx<T> zza(T t, zzc com_google_android_gms_internal_ads_zzc) {
        return new zzx(t, com_google_android_gms_internal_ads_zzc);
    }

    public static <T> zzx<T> zzc(zzae com_google_android_gms_internal_ads_zzae) {
        return new zzx(com_google_android_gms_internal_ads_zzae);
    }
}
