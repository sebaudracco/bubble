package com.google.android.gms.internal.ads;

final class zzalx implements zzy {
    private final /* synthetic */ String zzcce;
    private final /* synthetic */ zzama zzctj;

    zzalx(zzalt com_google_android_gms_internal_ads_zzalt, String str, zzama com_google_android_gms_internal_ads_zzama) {
        this.zzcce = str;
        this.zzctj = com_google_android_gms_internal_ads_zzama;
    }

    public final void zzd(zzae com_google_android_gms_internal_ads_zzae) {
        String str = this.zzcce;
        String com_google_android_gms_internal_ads_zzae2 = com_google_android_gms_internal_ads_zzae.toString();
        zzakb.zzdk(new StringBuilder((String.valueOf(str).length() + 21) + String.valueOf(com_google_android_gms_internal_ads_zzae2).length()).append("Failed to load URL: ").append(str).append("\n").append(com_google_android_gms_internal_ads_zzae2).toString());
        this.zzctj.zzb(null);
    }
}
