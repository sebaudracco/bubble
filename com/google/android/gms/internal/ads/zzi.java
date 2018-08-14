package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

public final class zzi implements zzaa {
    private final Executor zzv;

    public zzi(Handler handler) {
        this.zzv = new zzj(this, handler);
    }

    public final void zza(zzr<?> com_google_android_gms_internal_ads_zzr_, zzae com_google_android_gms_internal_ads_zzae) {
        com_google_android_gms_internal_ads_zzr_.zzb("post-error");
        this.zzv.execute(new zzk(this, com_google_android_gms_internal_ads_zzr_, zzx.zzc(com_google_android_gms_internal_ads_zzae), null));
    }

    public final void zza(zzr<?> com_google_android_gms_internal_ads_zzr_, zzx<?> com_google_android_gms_internal_ads_zzx_, Runnable runnable) {
        com_google_android_gms_internal_ads_zzr_.zzk();
        com_google_android_gms_internal_ads_zzr_.zzb("post-response");
        this.zzv.execute(new zzk(this, com_google_android_gms_internal_ads_zzr_, com_google_android_gms_internal_ads_zzx_, runnable));
    }

    public final void zzb(zzr<?> com_google_android_gms_internal_ads_zzr_, zzx<?> com_google_android_gms_internal_ads_zzx_) {
        zza(com_google_android_gms_internal_ads_zzr_, com_google_android_gms_internal_ads_zzx_, null);
    }
}
