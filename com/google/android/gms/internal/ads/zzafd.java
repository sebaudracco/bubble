package com.google.android.gms.internal.ads;

final class zzafd implements zzaoo<zzwb> {
    private final /* synthetic */ zzafc zzcgm;

    zzafd(zzafc com_google_android_gms_internal_ads_zzafc) {
        this.zzcgm = com_google_android_gms_internal_ads_zzafc;
    }

    public final /* synthetic */ void zze(Object obj) {
        try {
            ((zzwb) obj).zzb("AFMA_getAdapterLessMediationAd", this.zzcgm.zzcgk);
        } catch (Throwable e) {
            zzakb.zzb("Error requesting an ad url", e);
            zzafa.zzod().zzat(this.zzcgm.zzcgl);
        }
    }
}
